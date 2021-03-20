package com.example.quiz

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quiz.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener
import com.google.android.gms.ads.rewarded.RewardedAd


class MainActivity : AppCompatActivity(), RewardedVideoAdListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mInterstitialAd: InterstitialAd
    private lateinit var mRewardedVideoAd: RewardedVideoAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.i("API", getString(R.string.simple_ad))
        Log.i("API", getString(R.string.intersitial_ad))
        Log.i("API", getString(R.string.rewarded_ad))


        MobileAds.initialize(this@MainActivity)
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)

//        mInterstitialAd = InterstitialAd(this)
//        mInterstitialAd.adUnitId = getString(R.string.intersitial_ad)
//        mInterstitialAd.loadAd(AdRequest.Builder().build())
//
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this)
        mRewardedVideoAd.rewardedVideoAdListener = this
        loadRewardedVideoAd()


        binding.btnStart.setOnClickListener {
            if(mRewardedVideoAd.isLoaded){
                mRewardedVideoAd.show()
            }

//            if(mInterstitialAd.isLoaded){
//                mInterstitialAd.show()
//            } else {
//                Log.d("TAG", "The interstital ad wasn't loaded yet.")
//            }

            if(binding.etName.text.toString().isEmpty()){
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this, QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME, binding.etName.text.toString())
                startActivity(intent)
                finish()
            }

        }
    }

    private fun loadRewardedVideoAd(){
//        mRewardedVideoAd.loadAd("ca-app-pub-6006306992718753/4553543205", AdRequest.Builder().build())
        mRewardedVideoAd.loadAd(getString(R.string.rewarded_ad), AdRequest.Builder().build())
    }

    override fun onRewardedVideoAdClosed() {
        TODO("Not yet implemented")
    }

    override fun onRewardedVideoAdLeftApplication() {
        TODO("Not yet implemented")
    }

    override fun onRewardedVideoAdLoaded() {
        Log.e("TAGE", "Loaded")
    }

    override fun onRewardedVideoAdOpened() {
        TODO("Not yet implemented")
    }

    override fun onRewardedVideoCompleted() {
        TODO("Not yet implemented")
    }

    override fun onRewarded(p0: RewardItem?) {
        Toast.makeText(this, "Add completed!", Toast.LENGTH_SHORT).show()
    }

    override fun onRewardedVideoStarted() {
        TODO("Not yet implemented")
    }

    override fun onRewardedVideoAdFailedToLoad(p0: Int) {
        TODO("Not yet implemented")
    }
}