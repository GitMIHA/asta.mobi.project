package com.example.astamobiproject.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.astamobiproject.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class PhoneVerify : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var userNumber: TextView

    var numberuser = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.phone_verify)

        val buttverify = findViewById<Button>(R.id.buttonVerifyNumb)
        val otpGiven = findViewById<EditText>(R.id.editTextVerifyNum)

        var numberUSEdio = intent.getStringExtra("numberuser")//достаю номер телефона
        numberuser = numberUSEdio.toString()//вставляю в змінну  номер телефона -->(передаю в UserBIO)

        auth = FirebaseAuth.getInstance()

        val storedVerificationId = intent.getStringExtra("storedVerificationId")

        buttverify.setOnClickListener {
            var otp = otpGiven.text.toString().trim()
            if (otp.isNotEmpty()) {
                val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(
                    storedVerificationId.toString(), otp
                )
                signInWithPhoneAuthCredential(credential)
            } else {
                Toast.makeText(this, "Код верифікації введено невірно", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    var intent = Intent(applicationContext, UserBIO::class.java)
                    intent.putExtra("numberuser", numberuser) //  Дані яку будемо передавати в інше актівіті
                    startActivity(intent)
                    finish()
                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(this, "Не вірний код!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

}