package com.example.astamobiproject.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.astamobiproject.fragments.HomePage
import com.example.astamobiproject.db.NewUserDB
import com.example.astamobiproject.R
import com.facebook.*
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject


//Тут є ТРИ кнопки
class Login : AppCompatActivity() {

    companion object {
        private const val RC_SIGN_IN = 120
    }

    var callbackManager: CallbackManager? = null
    var loginButton: LoginButton? = null
    var auth: FirebaseAuth? = null

    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    var User_Kay = "USER_DATA"
    var database: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        database = FirebaseDatabase.getInstance().getReference(User_Kay)//група в які будуть сохранятися дані (телефон, ім'я і т.д)

        auth = Firebase.auth

        callbackManager = CallbackManager.Factory.create()

        login_button.setPermissions("email, public_profile")
        login_button.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult?) {
                Log.d("Success", "facebook:onSuccess:$loginResult")
                handleFacebookAccessToken(loginResult?.accessToken)
                Toast.makeText(baseContext, "Все чудово", Toast.LENGTH_SHORT).show()
                //показується після Toast.makeText(this, "Ви зарегані: $nameUser", Toast.LENGTH_LONG).show()
            }
            override fun onCancel() {
                Log.d("Cancel", "facebook:onCancel")
                if (AccessToken.getCurrentAccessToken() == null) {
                    return; //вже вийшов з системи
                }
            }
            override fun onError(error: FacebookException) {
                Log.d("Error", "facebook:onError", error)
                Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
            }
        })


        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestId()
            .requestProfile()
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        //Firebase Auth instance
        mAuth = FirebaseAuth.getInstance()

        button_google.setOnClickListener{
            signIn()
        }

    }

    //////Далі все відноситься до facebook
    private fun handleFacebookAccessToken(accessToken: AccessToken?) {
        val credential = FacebookAuthProvider.getCredential(accessToken!!.token)
        auth!!.signInWithCredential(credential)
            .addOnSuccessListener { result ->
                // Увійти в систему успішно, оновіть користувальницький інтерфейс з інформацією про введеного користувача
                val numberUser = result.user?.phoneNumber
                val name = result.user!!.displayName
                val fullName = name!!.split(" ")
                val nameUser = fullName[0]
                val lastNameUser = fullName[1]
                val cityLocation = ""
                val email = result.user?.email

                if(numberUser == null || numberUser == ""){
                var intent = Intent(applicationContext, PhoneLogin::class.java)
                startActivity(intent)
                }
//                val newUser = NewUserDB(
//                    numberUser.toString(),
//                    nameUser,
//                    lastNameUser,
//                    cityLocation,
//                    email.toString()
//                )
//              database?.push()?.setValue(newUser)
//                var intent = Intent(applicationContext, HomePage::class.java)
//                startActivity(intent)
//                Toast.makeText(this, "Ви спішно зареєстровані: $nameUser", Toast.LENGTH_LONG).show()

            }.addOnFailureListener { e ->
                Toast.makeText(baseContext, e.message, Toast.LENGTH_LONG).show()
                Log.e("ERROR_EDMT", e.message!!)
            }
    }
    /////////////////////Кінець що відноситься до facebook

    //////Далі все відноситься до google
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent,
            RC_SIGN_IN
        )
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithCredential:success")
//                  val user = auth!!.currentUser
                    val acct = GoogleSignIn.getLastSignedInAccount(this)
                    if (acct != null) {
                        val personGivenName = acct.givenName
                        val personFamilyName = acct.familyName
                        val personEmail = acct.email

//                      val personId = acct.id
//                      val personPhoto: Uri? = acct.photoUrl

//                        val newUser = NewUserDB(numberUser.toString(),
//                            personGivenName.toString(),
//                            personFamilyName.toString(),
//                            cityLocation,
//                            personEmail.toString())
//                        database?.push()?.setValue(newUser)

                    }
                    val intent = Intent(applicationContext, HomePage::class.java)
                    startActivity(intent)
                } else {
                    Log.w("TAG", "signInWithCredential:failure", task.exception)
                }

                // ...
            }
    }
    /////////////////////Кінець що відноситься до google

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager!!.onActivityResult(requestCode, resultCode, data)

        var graphRequest: GraphRequest
        graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
            object : GraphRequest.GraphJSONObjectCallback {
                override fun onCompleted(`object`: JSONObject?, response: GraphResponse?) {
                    Log.d("Demo", `object`.toString())
                }
            })
        val parameters = Bundle()
        parameters.putString("fields", "first_name, last_name, email")
        graphRequest.parameters = parameters
        graphRequest.executeAsync()

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d("TAG", "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e)
                // ...
            }
        }
    }

    fun toPhonelogin(view: View) {
        val phone_login = Intent(this, PhoneLogin::class.java)
        startActivity(phone_login)
    }


}
