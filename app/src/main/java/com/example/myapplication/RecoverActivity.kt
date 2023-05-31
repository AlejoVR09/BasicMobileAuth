package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RecoverActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover)

        firebaseAuth= Firebase.auth

        val etEmailRecover=findViewById<EditText>(R.id.etEmailRecover)
        val btnCommitRecover=findViewById<Button>(R.id.btnCommitRecover)
        btnCommitRecover.setOnClickListener{
            recover(etEmailRecover.text.toString())
        }
    }

    private fun recover(email:String){
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener(){task->
                if(task.isSuccessful){
                    Toast.makeText(baseContext,"Si el correo es correcta recibira un email",Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(baseContext,task.exception.toString(),Toast.LENGTH_SHORT).show()
                }

            }
    }
}