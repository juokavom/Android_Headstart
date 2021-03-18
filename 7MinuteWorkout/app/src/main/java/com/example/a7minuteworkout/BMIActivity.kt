package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.a7minuteworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBmiBinding

    val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW"
    val US_UNITS_VIEW = "US_UNIT_VIEW"

    var currentVisibleView: String = METRIC_UNITS_VIEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarBmiActivity)

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.title = "CALCULATE BMI"

        binding.toolbarBmiActivity.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.btnCalculateUnits.setOnClickListener {
            if(currentVisibleView.equals(METRIC_UNITS_VIEW)){
                if (validateMetricUnits()) {
                    val height = binding.etMetricUnitHeight.text.toString().toFloat() / 100
                    val weight = binding.etMetricUnitWeight.text.toString().toFloat()

                    val bmi = weight / (height * height)

                    displayBMIResult(bmi)
                } else {
                    Toast.makeText(this@BMIActivity, "Please enter valid values.", Toast.LENGTH_SHORT)
                        .show()
                }
            }else{
                if(validateUSUnits()){
                    if (validateMetricUnits()) {
                        val heightFeet = binding.etUsUnitHeightFeet.text.toString()
                        val heightInch = binding.etUsUnitHeightInch.text.toString()
                        val weight = binding.etUsUnitWeight.text.toString().toFloat()

                        val height = heightInch.toFloat() + heightFeet.toFloat() * 12

                        val bmi = 703 * weight / (height * height)

                        displayBMIResult(bmi)
                    } else {
                        Toast.makeText(this@BMIActivity, "Please enter valid values.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }else{
                    Toast.makeText(this@BMIActivity, "Please enter valid values.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        makeVisibleMetricUnitsView()
        binding.rgUnits.setOnCheckedChangeListener{group, checkedId ->
            if(checkedId == R.id.rbMetricUnits){
                makeVisibleMetricUnitsView()
            } else {
                makeVisiblUSUnitsView()
            }
        }
    }

    private fun makeVisiblUSUnitsView(){
        currentVisibleView = US_UNITS_VIEW
        binding.tilMetricUnitWeight.visibility = View.GONE
        binding.tilMetricUnitHeight.visibility = View.GONE

        binding.etUsUnitWeight.text!!.clear()
        binding.etUsUnitHeightFeet.text!!.clear()
        binding.etUsUnitHeightInch.text!!.clear()

        binding.tilUsUnitWeight.visibility = View.VISIBLE
        binding.tilUsUnitHeightFeet.visibility = View.VISIBLE
        binding.tilUsUnitHeightInch.visibility = View.VISIBLE

        binding.llDiplayBMIResult.visibility = View.INVISIBLE
    }

    private fun makeVisibleMetricUnitsView(){
        currentVisibleView = METRIC_UNITS_VIEW
        binding.tilMetricUnitWeight.visibility = View.VISIBLE
        binding.tilMetricUnitHeight.visibility = View.VISIBLE

        binding.etMetricUnitHeight.text!!.clear()
        binding.etMetricUnitWeight.text!!.clear()

        binding.tilUsUnitWeight.visibility = View.GONE
        binding.tilUsUnitHeightFeet.visibility = View.GONE
        binding.tilUsUnitHeightInch.visibility = View.GONE

        binding.llDiplayBMIResult.visibility = View.INVISIBLE
    }

    private fun displayBMIResult(bmi: Float) {
        val bmiLabel: String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take care of your better! Eat more!"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0) {
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops! You really need to take care of your better! Eat more!"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0) {
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take care of your better! Eat more!"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0) {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0) {
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        } else {
            bmiLabel = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }

        binding.llDiplayBMIResult.visibility = View.VISIBLE

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        binding.tvBMIValue.text = bmiValue
        binding.tvBMIType.text = bmiLabel
        binding.tvBMIDescription.text = bmiDescription
    }

    private fun validateMetricUnits(): Boolean {
        var isValid = true

        if (binding.etMetricUnitWeight.text.toString().isEmpty()) isValid = false
        else if (binding.etMetricUnitHeight.text.toString().isEmpty()) isValid = false

        return isValid
    }

    private fun validateUSUnits(): Boolean {
        var isValid = true

        when {
            binding.etUsUnitHeightFeet.text.toString().isEmpty() -> isValid = false
            binding.etUsUnitHeightInch.text.toString().isEmpty() -> isValid = false
            binding.etUsUnitHeightFeet.text.toString().isEmpty() -> isValid = false
        }

        return isValid
    }
}