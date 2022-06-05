package com.example.calorietrack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_signup.*

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        barhome.setOnClickListener {

            var intent = Intent(this,welcome::class.java)
            startActivity(intent)

        }
        cardbmi.setOnClickListener {
            var intent = Intent(this,Bmi::class.java)
            startActivity(intent)
        }

        account.setOnClickListener {
            var intent = Intent(this,Profile::class.java)
            startActivity(intent)
        }
        image1.setOnClickListener {
            val intent = Intent(this,FoodSelectActivity::class.java)
            startActivity(intent)
        }
        cardpedo.setOnClickListener {
            val intent = Intent(this,Pedometer::class.java)
            startActivity(intent)
        }
        contact.setOnClickListener {
            val intent = Intent(this,Portfolio::class.java)
            startActivity(intent)
        }
        cardbreathe.setOnClickListener {
            val intent = Intent(this,breathe::class.java)
            startActivity(intent)
        }

    }
}