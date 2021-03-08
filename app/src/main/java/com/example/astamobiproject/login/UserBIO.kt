package com.example.astamobiproject.login

import  android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.astamobiproject.R
import com.example.astamobiproject.db.BaseUserDB
import com.example.astamobiproject.fragments.HomePage
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.user_bio.*

class UserBIO : AppCompatActivity() {

    var User_Kay = "USER_DATA"
    var database: DatabaseReference? = null

    lateinit var userNumber: TextView
    lateinit var userName: EditText
    lateinit var userSurname: EditText
    lateinit var userCity: EditText
    lateinit var userEmail: EditText
    lateinit var buttonToHome: Button

    private lateinit var number: String
    private lateinit var name: String
    private lateinit var surname: String
    private lateinit var city: String
    private lateinit var email: String

    lateinit var sPref: SharedPreferences

    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    private val loadDialog = LoadingDialog(this)
    private fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_bio)

        buttonToHome = findViewById(R.id.buttonToHomePage)
        buttonToHome.setOnClickListener { onClickSaveInfo() }

        userNumber = findViewById(R.id.textEditNumberPhone)

        userName = findViewById(R.id.editTextName)
        userSurname = findViewById(R.id.editTextSurname)
        userCity = findViewById(R.id.editTextCity)
        userEmail = findViewById(R.id.editTextEmailAdr)


        editTextName.text = intent.getStringExtra("nameUser")?.toEditable()
        editTextSurname.text =intent.getStringExtra("surnameUser")?.toEditable()
        editTextEmailAdr.text =intent.getStringExtra("emailUser")?.toEditable()
        textEditNumberPhone.text =intent.getStringExtra("numberUser")?.toEditable()
        if (editTextName.text.isEmpty() || editTextSurname.text.isEmpty() || editTextEmailAdr.text.isEmpty()) {
            editTextName.text = "".toEditable()
            editTextSurname.text = "".toEditable()
            editTextEmailAdr.text = "".toEditable()
        }
        if (editTextName.text.toString() == "null") {
            editTextName.text = "".toEditable()
            editTextSurname.text = "".toEditable()
            editTextEmailAdr.text = "".toEditable()
        }

        database = FirebaseDatabase.getInstance().getReference(User_Kay)

    }
    private fun onClickSaveInfo() {
        number = userNumber.text.toString()
//      val idUser = database?.key?:""
        name = userName.text.toString()
        surname = userSurname.text.toString()
        city = userCity.text.toString()
        email = userEmail.text.toString()

        val newUser = BaseUserDB(number, name, surname, city, email)

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(surname) && !TextUtils.isEmpty(city) && !TextUtils.isEmpty(
                email
            )
        ) {
            if (email.matches(emailPattern.toRegex())) {

//                database?.push()?.setValue(newUser)

                var intent = Intent(applicationContext, HomePage::class.java)
                intent.putExtra("numberUser", number)
                intent.putExtra("nameUser", name)
                intent.putExtra("surnameUser", surname)
                intent.putExtra("cityUser", city)
                intent.putExtra("emailUser", email)

                sPref = getPreferences(Context.MODE_PRIVATE)
                val editor = sPref.edit()
                editor.putString("numberUserF", number)
                editor.putString("nameUserF", name)
                editor.putString("surnameUserF", surname)
                editor.putString("cityUserF", city)
                editor.putString("emailUserF", email)
                editor.apply()

                loadDialog.startLoadingDialog()
                startActivity(intent)
                Toast.makeText(this, "Ви заєрестровані успішно: $name", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "E-mail не вірний!", Toast.LENGTH_SHORT).show()

            }
        } else {
            Toast.makeText(this, "Деяке поле пусте", Toast.LENGTH_SHORT).show()
        }
    }


}
