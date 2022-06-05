package com.example.calorietrack

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_profile.*

class Profile : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        firestore=FirebaseFirestore.getInstance()
        val user = auth.currentUser
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val fuser = auth.currentUser
        val uid = fuser!!.uid


        var documentReference: DocumentReference =firestore.collection("users").document(uid)
        documentReference.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    profilenumber.text= document.data?.get("phone") as CharSequence?
                    profilename.text= document.data?.get("name") as CharSequence?
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }





        profileemail.text = user?.email


        logoutbutton.setOnClickListener {

            auth.signOut()
            startActivity(Intent(this, Login::class.java))
            finish()
        }
    }


}
