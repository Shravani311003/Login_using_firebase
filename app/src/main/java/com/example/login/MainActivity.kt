package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signButton = findViewById<Button>(R.id.btnSignUp)
        val etName = findViewById<EditText>(R.id.editTextName)
        val etMail = findViewById<EditText>(R.id.editTextEmail)
        val userId = findViewById<EditText>(R.id.uniqueId)
        val userPassword = findViewById<EditText>(R.id.editTextPassword)

        signButton.setOnClickListener {

            val name = etName.text.toString()
            val mail = etMail.text.toString()
            val uniqueId = userId.text.toString()
            val password = userPassword.text.toString()

            val user = User(name, mail,password,uniqueId)
            database = FirebaseDatabase.getInstance().getReference("Users")

            database.child(uniqueId).setValue(user)
                .addOnSuccessListener {
                    Toast.makeText(this, "User Registered ", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener { exception ->
                    Toast.makeText(this, "Failed: ${exception.message}", Toast.LENGTH_SHORT).show()
                    Log.e("Firebase", "Failed to write user", exception)
                }

        }
    }
}