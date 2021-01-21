package com.example.astamobiproject

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider

public class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun TooastMSG(view: View){
//        val msg = Toast.makeText(this,"MSG",Toast.LENGTH_SHORT)
//        msg.show()
        val ToLoginPg = Intent(this, Login::class.java)
        startActivity(ToLoginPg)
    }

}