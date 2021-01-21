package com.example.astamobiproject

import com.google.firebase.database.FirebaseDatabase

class NewUserDB() {

    var userNumber: String? = null
    var userName: String? = null
    var userSurname: String? = null
    var userCity: String? = null
    var userEmail: String? = null

    constructor(
        usernumber: String,
        username: String,
        usersurname: String,
        usercity: String,
        useremail: String
    ) : this() {
        this.userNumber = usernumber
        this.userName = username
        this.userSurname = usersurname
        this.userCity = usercity
        this.userEmail = useremail
    }

}