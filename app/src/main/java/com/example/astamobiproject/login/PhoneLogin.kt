package com.example.astamobiproject.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.astamobiproject.MainActivity
import com.example.astamobiproject.R
import com.example.astamobiproject.fragments.HomePage
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class PhoneLogin : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var storedVerificationId: String
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    var numberuser = ""
    lateinit var nameUser: String
    lateinit var surnameUser: String
    lateinit var emailUser: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.phone_login)

        nameUser = intent.getStringExtra("nameUser").toString()
        surnameUser = intent.getStringExtra("surnameUser").toString()
        emailUser = intent.getStringExtra("emailUser").toString()

        auth = FirebaseAuth.getInstance()
        val buttPhoneNumb = findViewById<Button>(R.id.buttonVerifyNumb)

        buttPhoneNumb.setOnClickListener {
            login()
        }
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                //Незабути поміняти на MainActivity
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            }
            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(applicationContext, "Номер введено невірно", Toast.LENGTH_LONG).show()
            }
            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                Log.d("TAG", "onCodeSent:$verificationId")
                storedVerificationId = verificationId
                resendToken = token
                var intent = Intent(applicationContext, PhoneVerify::class.java)
                intent.putExtra("storedVerificationId", storedVerificationId)
                intent.putExtra("nameUser", nameUser)
                intent.putExtra("surnameUser", surnameUser)
                intent.putExtra("emailUser", emailUser)
                intent.putExtra("numberUser", numberuser)//передаю номер телефона
                startActivity(intent)
            }
        }

    }

    private fun login() {
        val mobileNumber = findViewById<EditText>(R.id.editTextVerifyNum)
        var number = mobileNumber.text.toString().trim()
        if (number.isNotEmpty()) {
            number = "+38$number"
            numberuser = number
            sendVerificationcode(number)
        } else {
            Toast.makeText(this, "Enter mobile number", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendVerificationcode(number: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(number) // Номер телефону для підтвердження
            .setTimeout(20L, TimeUnit.SECONDS) //Час очікування та одиниця виміру
            .setActivity(this) //Діяльність (для прив’язки зворотного дзвінка)
            .setCallbacks(callbacks) // визиває OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

}

