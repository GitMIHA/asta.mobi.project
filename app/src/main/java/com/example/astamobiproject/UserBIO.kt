package com.example.astamobiproject

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class UserBIO : AppCompatActivity() {

    var User_Kay = "USER_DATA"
    var database: DatabaseReference? = null

    lateinit var userNumber: TextView

    lateinit var userName: EditText
    lateinit var userSurname: EditText
    lateinit var userCity: EditText
    lateinit var buttonToHome: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_bio)

        buttonToHome = findViewById(R.id.buttonToHomePage)
        buttonToHome.setOnClickListener{onClickSaveInfo()}

        userName = findViewById(R.id.editTextName)
        userSurname = findViewById(R.id.editTextSurname)
        userCity = findViewById(R.id.editTextCity)

        userNumber = findViewById(R.id.textViewNumberPhone)
        var numberUSEdio = intent.getStringExtra("numberuser")//достаю номер телефона
        userNumber.text = numberUSEdio

        database = FirebaseDatabase.getInstance().getReference(User_Kay)//група в які будуть сохранятися дані (телефон, ім'я і т.д)

    }

    private fun onClickSaveInfo() {

        val number = userNumber.text.toString()
//      val idUser = database?.key?:""
        val name = userName.text.toString()
        val surname = userSurname.text.toString()
        val city = userCity.text.toString()
        val email = ""

        val newUser = NewUserDB(number, name, surname, city, email)
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(surname) && !TextUtils.isEmpty(city)) {
            database?.push()?.setValue(newUser)
            var intent = Intent(applicationContext, UserProfile::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(this, "Деяке поле пусте",Toast.LENGTH_SHORT).show()
        }
//      database?.push()?.setValue(newUser)
//      var intent = Intent(applicationContext, UserProfile::class.java)
//      startActivity(intent)


    }


}