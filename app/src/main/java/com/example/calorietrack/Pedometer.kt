package com.example.calorietrack

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_pedometer.*

class Pedometer : AppCompatActivity(),SensorEventListener {

    private var sensorManager:SensorManager?=null
    private var totalsteps = 0f
    private var running = false
    private var previoustotalsteps =0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedometer)
        loadData()
        resetSteps()

        sensorManager= getSystemService(Context.SENSOR_SERVICE) as SensorManager


    }

    override fun onPause() {
        super.onPause()
        sensorManager?.unregisterListener(this)
    }


    override fun onResume() {
        super.onResume()
        running=true
        val stepSensor : Sensor? = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        if(stepSensor==null){
          Toast.makeText(this, "No sensor detected", Toast.LENGTH_LONG).show()

        }
        else{
            sensorManager?.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_FASTEST)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(running){
            if (event != null) {
                totalsteps=event.values[0]
            }
            val currentSteps:Int=totalsteps.toInt()-previoustotalsteps.toInt()
            stepstaken.text= currentSteps.toString()

            circularProgressBar.apply{
                setProgressWithAnimation(currentSteps.toFloat())}

        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    private fun resetSteps(){
        stepstaken.setOnClickListener {
            Toast.makeText(this, "long tap to reset", Toast.LENGTH_LONG).show()
        }
       stepstaken.setOnLongClickListener {
          previoustotalsteps=totalsteps
           stepstaken.text=0.toString()
           saveData()
           true
       }
    }

    private fun saveData() {
        val sharedPreferences=getSharedPreferences("myprefs", Context.MODE_PRIVATE)
        val editor=sharedPreferences.edit()
        editor.putFloat("key1", previoustotalsteps)
        editor.apply()
    }

    private fun loadData(){
        val sharedPreferences=getSharedPreferences("myprefs", Context.MODE_PRIVATE)
        val savedNumber=sharedPreferences.getFloat("key1", 0f)
        Log.d("Pedometer", "$savedNumber")
        previoustotalsteps=savedNumber
    }


}