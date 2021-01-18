package com.example.astamobiproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class PhoneLogin : AppCompatActivity(){

    lateinit var auth: FirebaseAuth
    lateinit var storedVerificationId:String
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.phone_login)

        val ButtPhoneNumb = findViewById<Button>(R.id.buttonVerifyNumb)

        auth=FirebaseAuth.getInstance()

        //Перевірте поточного користувача, чи він вже ввійшов для автоматичного входу
        var currentUser = auth.currentUser
        if(currentUser != null) {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }

        ButtPhoneNumb.setOnClickListener{
            login()
        }

        // Callback function for Phone Auth -- Функція зворотного дзвінка для авторизації телефону
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            //метод onVerificationCompleted викличе, коли користувач вже підтверджений.
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                //чомусь попадає в цю функцію, хоть код і приходить
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            }

            //метод onVerificationFailed викличе, коли перевірка не вдалася
            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
            }

            //onCodeSent зателефонує, коли код OTP буде успішно відправлений
            override fun onCodeSent( verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                Log.d("TAG","onCodeSent:$verificationId")
                storedVerificationId = verificationId
                resendToken = token
                var intent = Intent(applicationContext, PhoneVerify::class.java)
                intent.putExtra("storedVerificationId", storedVerificationId)
                startActivity(intent)
            }
        }

    }
    //У функції login перевірте основні умови введення, ввів користувач правильний номер мобільного телефону чи ні
    private fun login() {
        val mobileNumber=findViewById<EditText>(R.id.editTextVerifyNum)
        var number=mobileNumber.text.toString().trim()

        if(!number.isEmpty()){
            number="+38"+number
            sendVerificationcode (number)
        }else{
            Toast.makeText(this,"Enter mobile number",Toast.LENGTH_SHORT).show()
        }

    }

    private fun sendVerificationcode(number: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(number) // Номер телефону для підтвердження
            .setTimeout(10L, TimeUnit.SECONDS) //Час очікування та одиниця виміру
            .setActivity(this) //Діяльність (для прив’язки зворотного дзвінка)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }


}

