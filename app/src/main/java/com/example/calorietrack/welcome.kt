package com.example.calorietrack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_welcome.*

class welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        loginbutton.setOnClickListener {
            var intent = Intent(this,Login::class.java)
            startActivity(intent)
        }
        signupview.setOnClickListener {
            var intent = Intent(this,Signup::class.java)
            startActivity(intent)
        }

    }
}