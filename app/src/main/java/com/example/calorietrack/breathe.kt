package com.example.calorietrack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler


import com.example.android_breath.Util.SharedPref
import com.github.florent37.viewanimator.ViewAnimator
import kotlinx.android.synthetic.main.activity_breathe.*
import java.text.MessageFormat

class breathe : AppCompatActivity() {
    lateinit var pref: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breathe)
        pref = SharedPref(this)
        breathtoday.text = MessageFormat.format("{0} minutes today", pref.sessions)
        lasttimeused.text = pref.date
        startbtn.setOnClickListener {
            animationstart() //method for animation

        }
    }

    private fun animationstart() {
        ViewAnimator
                .animate(flower)            //we are animating the image

                .onStart {//onstart animation
                    breathtxt.text = "Inhale........ Exhale"//text change
                }

                .thenAnimate(flower)//animating image
                .scale(0.02f, 1.5f, 0.02f)
                .rotation(360f)
                .repeatCount(10)//repeating animation
                .accelerate()
                .duration(5000)//5 seconds duration
                .onStop { //on stop animation
                    breathtxt.text = "Great"
                    flower.scaleX = 1.0f
                    flower.scaleY = 1.0f

                    pref.sessions = pref.sessions + 1 //changing values

                    pref.setDate(System.currentTimeMillis())

//refreshing the activity
                    val handler = Handler()
                    val countDownTimer = Runnable {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                    handler.postDelayed(countDownTimer, 2000)


                }.start()//starting animation
    }
}
