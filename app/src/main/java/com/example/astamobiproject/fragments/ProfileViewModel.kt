package com.example.astamobiproject.fragments

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.lifecycle.ViewModel


class ProfileViewModel: ViewModel() {

    var userPhoto: Bitmap? = null
    var imageUri: Uri? = null

    var userNumber: String? = null
    var userName: String? = null
    var userSurname: String? = null
    var userCity: String? = null
    var userEmail: String? = null

    fun saveInfoUser(){

    }
    fun getuserNumber(): String? {
        return userNumber
    }
    fun setuserNumber(userNumber: String) {
        this.userNumber = userNumber
    }






}