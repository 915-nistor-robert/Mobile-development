package com.example.passwordmanagerapp.model

data class Account (
    var id: Int,
    var platform : String,
    var username: String,
    var password : String
)