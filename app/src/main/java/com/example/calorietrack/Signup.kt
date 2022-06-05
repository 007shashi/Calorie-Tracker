package com.example.calorietrack

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_signup.*

class Signup : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var firestore:FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        firestore= FirebaseFirestore.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        tosignin.setOnClickListener {
            var intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        registerbutton.setOnClickListener {
            signupUser()
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            updateUI(currentUser)
        }
    }

    private fun updateUI(currentUser: FirebaseUser) {

    }


    private fun signupUser()
    {
        var uname=registername.text.toString()
        var uemail=registeremail.text.toString()
        var ph=registerph.text.toString()




        if(registername.text.toString().isEmpty())
        {
            registername.error="Please Enter name"
            registername.requestFocus()
            return

        }
        if(registerph.text.toString().isEmpty())
        {
            registerph.error="Please Enter number"
            registerph.requestFocus()
            return

        }
        if(registeremail.text.toString().isEmpty())
        {
            registeremail.error="Please Enter Email"
            registeremail.requestFocus()
            return

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(registeremail.text.toString()).matches())
        {
            registeremail.error="Please Enter Valid Email"
            registeremail.requestFocus()
            return

        }
        if(registerpassword.text.toString().isEmpty())
        {
            registerpassword.error="Please Enter Password"
            registerpassword.requestFocus()
            return

        }
        auth.createUserWithEmailAndPassword(registeremail.text.toString(), registerpassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val currentUser = auth.currentUser
                    val fuser = auth.currentUser
                    val uid = fuser!!.uid
                    var documentReference:DocumentReference=firestore.collection("users").document(uid)
                    val mapuser: HashMap<String, Any> = HashMap()
                    mapuser["name"] = uname
                    mapuser["phone"]=ph

                    documentReference.set(mapuser).addOnSuccessListener {  }



                    // Sign in success, update UI with whatever you want
                    Log.d("Tag1", "createUserWithEmail:success")
                    Toast.makeText(this, "Authentication Done.",
                            Toast.LENGTH_SHORT).show()

                    val user : FirebaseUser? = auth.currentUser
                    user!!.sendEmailVerification()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.d("Email Tag", "Email sent.")
                                startActivity(Intent(this, Login::class.java))
                                finish()
                            }
                        }
                } else {
                    // If sign in fails, then display a message to the user.
                    Log.w("Tag2", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()

                }

                // ...
            }
    }
}