package com.example.calorietrack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_welcome.*

class Login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        tosignup.setOnClickListener {
            var intent = Intent(this,Signup::class.java)
            startActivity(intent)
        }

        LoginButton.setOnClickListener {
            doLogin()
        }

    }

    private fun doLogin()
    {
        if(loginemail.text.toString().isEmpty())
        {
            loginemail.error="Please Enter Email"
            loginemail.requestFocus()
            return

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(loginemail.text.toString()).matches())
        {
            loginemail.error="Please Enter Valid Email"
            loginemail.requestFocus()
            return

        }
        if(loginpassword.text.toString().isEmpty())
        {
            loginpassword.error="Please Enter Password"
            loginpassword.requestFocus()
            return

        }
        auth.signInWithEmailAndPassword(loginemail.text.toString(), loginpassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // if Sign in is success, update the UI to any other page
                    Log.d("tag3", "signInWithEmail:success")
                    Toast.makeText(baseContext, "Login Success.",
                        Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, then display one message
                    Log.w("TAG4", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Login failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)

                }


            }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser?= auth.currentUser
        updateUI(currentUser)
    }
    private fun updateUI(currentUser:FirebaseUser?)
    {
        if(currentUser!=null)
        {  if(currentUser.isEmailVerified) {
            startActivity(Intent(this, Dashboard::class.java))
            finish()
        }
        else 
        {
            Toast.makeText(this,"Verify Your Email",Toast.LENGTH_LONG).show();
        }
        }
        else
        {
            Toast.makeText(baseContext,"Login Failed",Toast.LENGTH_SHORT).show()
        }
    }

}