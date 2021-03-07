package com.example.astamobiproject.login

import android.app.Activity
import android.app.AlertDialog
import com.example.astamobiproject.R

class LoadingDialog {
     private var activity: Activity
     private lateinit var diaolog: AlertDialog

    constructor(myActivity: Activity){
        activity = myActivity
    }
    fun startLoadingDialog(){
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.progress_bar, null))
        builder.setCancelable(true)

        diaolog = builder.create()
        diaolog.show()
    }
    fun dismissLoadingDialog(){
        diaolog.dismiss()
    }

}