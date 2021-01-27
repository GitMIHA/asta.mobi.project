package com.example.astamobiproject

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.astamobiproject.login.Login
import com.google.firebase.auth.FirebaseAuth


public class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        /** Якщо користувача не автентифіковано, надішліть його до SignInActivity, щоб спершу здійснити автентифікацію.
        * В іншому випадку надішліть його на DashboardActivity */
        Handler().postDelayed({
            if(user != null){
                val dashboardIntent = Intent(this, HomePage::class.java)
                startActivity(dashboardIntent)
                finish()
            }else{
                //Login має буть
                val signInIntent = Intent(this, Login::class.java)
                startActivity(signInIntent)
                finish()
            }
        }, 100)

    }

    fun TooastMSG(view: View) {
//        val msg = Toast.makeText(this,"MSG",Toast.LENGTH_SHORT)
//        msg.show()
        val ToLoginPg = Intent(this, Login::class.java)
        startActivity(ToLoginPg)
    }

}