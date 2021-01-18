package com.example.astamobiproject

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

//Тут є ТРИ кнопки
class Login : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }
    fun toPhonelogin(view: View){
        val phone_login = Intent(this, PhoneLogin::class.java)
        startActivity(phone_login)
    }

    override fun onStart() {
        super.onStart()

    }
}