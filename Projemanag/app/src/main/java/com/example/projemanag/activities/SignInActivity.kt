package com.example.projemanag.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.projemanag.R
import com.example.projemanag.databinding.ActivitySignInBinding
import com.example.projemanag.firebase.FirestoreClass
import com.example.projemanag.models.User
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : BaseActivity() {

    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpActionBar()


        binding.btnSignIn.setOnClickListener {
            signInRegisteredUser()
        }

        setUpActionBar()
    }

    private fun signInRegisteredUser() {
        val email: String = binding.etEmail.text.toString().trim { it <= ' ' }
        val password: String = binding.etPassword.text.toString().trim { it <= ' ' }

        if (validateForm(email, password)) {
            showProgressDialog(getString(R.string.please_wait))
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d("Sign in", "signInWithEmail:success")
                        FirestoreClass().loadUserData(this)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("Sign in", "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun validateForm(email: String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(email) -> {
                showErrorSnackBar("Please enter email")
                false
            }
            TextUtils.isEmpty(password) -> {
                showErrorSnackBar("Please enter password")
                false
            }
            else -> true
        }
    }

    fun signInSuccess(user: User) {
        hideProgressDialog()
        startActivity(Intent(this@SignInActivity, MainActivity::class.java))
        finish()
    }

    private fun setUpActionBar() {
        setSupportActionBar(binding.toolbarSignInActivity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }

        binding.toolbarSignInActivity.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}