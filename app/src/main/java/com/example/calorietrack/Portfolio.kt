package com.example.calorietrack

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import controller.PortfolioAdapter
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_portfolio.*


class Portfolio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayShowTitleEnabled(false);
        setContentView(R.layout.activity_portfolio)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        btn_back.setOnClickListener {
            var intent = Intent(this,Dashboard::class.java)
            startActivity(intent)
        }

        viewpager.adapter=PortfolioAdapter(supportFragmentManager)
        tabid.setupWithViewPager(viewpager)
        tabid.setTabTextColors(Color.CYAN,Color.BLUE)
    }
}