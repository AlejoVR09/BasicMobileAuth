package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CreateActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var autoStateListener: FirebaseAuth.AuthStateListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        firebaseAuth=Firebase.auth
        val etEmail=findViewById<EditText>(R.id.etEmail)
        val etPassword=findViewById<EditText>(R.id.etPassword)
        val btnCommit=findViewById<Button>(R.id.btnCommit)

        btnCommit.setOnClickListener{
            signUp(etEmail.text.toString(),etPassword.text.toString())
        }
    }

    private fun signUp(email:String,password:String){

        firebaseAuth.createUserWithEmailAndPassword(email,password)
        .addOnCompleteListener{ task ->
            if (task.isSuccessful){
                emailVerified()
                Toast.makeText(baseContext,"feliz",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(baseContext,task.exception.toString(),Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun emailVerified(){
        val user=firebaseAuth.currentUser!!
        user.sendEmailVerification()
            .addOnCompleteListener(this){task->

            }
    }
}