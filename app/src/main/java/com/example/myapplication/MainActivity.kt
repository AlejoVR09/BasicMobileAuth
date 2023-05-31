package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var autoStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etUserName=findViewById<EditText>(R.id.etUserName)
        val etPassword=findViewById<EditText>(R.id.etPassword)
        val btnLogin=findViewById<Button>(R.id.btnLogin)
        val btnRegister=findViewById<TextView>(R.id.btnRegister)
        val btnRecover=findViewById<TextView>(R.id.btnRecover)
        firebaseAuth= Firebase.auth
        btnLogin.setOnClickListener {
            singIn(etUserName.text.toString(),etPassword.text.toString())
        }
        btnRegister.setOnClickListener {
            val i = Intent(this,CreateActivity::class.java)
            startActivity(i)
        }
        btnRecover.setOnClickListener{
            val i = Intent(this,RecoverActivity::class.java)
            startActivity(i)
        }

    }

    private fun singIn(email:String, password:String){
        firebaseAuth.signInWithEmailAndPassword(email,password)
        .addOnCompleteListener(this) { task->
            if (task.isSuccessful){
                val user=firebaseAuth.currentUser
                val verified=user?.isEmailVerified
                if (verified!!){
                    val i = Intent(this, MainActivity2::class.java)
                    startActivity(i)
                }
                else{
                    Toast.makeText(baseContext,"No esta",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}