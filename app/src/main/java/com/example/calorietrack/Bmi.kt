package com.example.calorietrack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.*
import kotlinx.android.synthetic.main.activity_bmi.*


import kotlin.math.pow
var b=0.0
var bmr=0.0
var clickedGirl = false
class Bmi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)
        supportActionBar?.hide()
        val imageBoy = findViewById<ImageView>(R.id.image_boy)
        val imageGirl = findViewById<ImageView>(R.id.image_girl)
        val weight = findViewById<EditText>(R.id.weight_value)
        val height = findViewById<EditText>(R.id.height_value)
        val age = findViewById<EditText>(R.id.age_value)
        val calculateButton = findViewById<Button>(R.id.calculate_button)
        val bmi = findViewById<TextView>(R.id.bmi)
        val bmiStatus = findViewById<TextView>(R.id.bmi_status)
        val calValue = findViewById<TextView>(R.id.calorie_value)
        val bmiView = findViewById<LinearLayout>(R.id.bmiView)
        val calculateAgainButton = findViewById<TextView>(R.id.calculate_again)

        imageBoy.setOnClickListener {
            imageBoy.setImageResource(R.drawable.ic_boy)
            imageGirl.setImageResource(R.drawable.ic_girl_blur)

        }

        imageGirl.setOnClickListener { //change boolean value
            clickedGirl = true
            imageBoy.setImageResource(R.drawable.ic_boy_blur)
            imageGirl.setImageResource(R.drawable.ic_girl)
        }

        calculateButton.setOnClickListener {
            var weightValue = 0.0
            var heightValue = 0.0
            var ageValue = 0.0
            if (weight.text.toString().isNotEmpty()) {
                weightValue = weight.text.toString().toDouble()
            }
            if (age.text.toString().isNotEmpty()) {
                ageValue = age.text.toString().toDouble()
            }
            if (height.text.toString().isNotEmpty()) {
                heightValue = (height.text.toString().toDouble() / 100)
            }
            if (weightValue > 0.0 && heightValue > 0.0 && ageValue > 0.0) {
                if(clickedGirl)
                {
                    bmr = bmrGirl(
                            weight.text.toString().toDouble(),
                            height.text.toString().toDouble(),
                            age.text.toString().toDouble())

                }
                else
                {
                    bmr = bmrBoy(
                            weight.text.toString().toDouble(),
                            height.text.toString().toDouble(),
                            age.text.toString().toDouble()
                    )
                }
                val bmiValue = String.format("%.2f", weightValue / heightValue.pow(2))
                bmi.text = bmiValue
                bmiStatus.text = bmiStatusValue(weightValue / heightValue.pow(2))
                bmiView.visibility = VISIBLE
                calculateButton.visibility = GONE
            } else
                Toast.makeText(
                        this,
                        "Please Input Weight,Height and Age Values greater than 0",
                        Toast.LENGTH_LONG
                ).show()
            val cValue= String.format("%.0f",b)
            calValue.text = cValue

        }

        calculateAgainButton.setOnClickListener {
            bmiView.visibility = GONE
            calculateButton.visibility = VISIBLE
            weight.text.clear()
            height.text.clear()
            age.text.clear()
            weight.requestFocus()

        }
    }
}


private fun bmrGirl(weight: Double, height: Double, age: Double): Double {
    var bmrStat = 0.0
    bmrStat = 655.1 + (9.563 * weight) + (1.850 * height) - (4.676 * age)
    return bmrStat
}

private fun bmrBoy(weight: Double, height: Double, age: Double): Double {
    var bmrStat = 0.0
    bmrStat = 66.47 + (13.75 * weight) + (5.003 * height) - (6.755 * age)
    return bmrStat
}


private fun bmiStatusValue(bmi: Double): String {
    lateinit var bmiStatus: String
    if (bmi < 18.5) {
        bmiStatus = "Underweight"
        b = bmr * 1.9
    } else if (bmi >= 18.5 && bmi < 25) {
        bmiStatus = "Normal"
        b = bmr * 1.625
    } else if (bmi >= 25 && bmi < 30) {
        bmiStatus = "Overweight"
        b = bmr * 1.375
    } else if (bmi > 30) {
        bmiStatus = "Obese"
        b = bmr * 1.2
    }
    return bmiStatus

}