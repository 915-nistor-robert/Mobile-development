package com.example.passwordmanagersqlite

import java.lang.Math.abs
import java.util.*


data class AccountModel(
    var id: Int = getRandId(),
    var platform: String = "",
    var username: String = "",
    var password: String = ""
) {

    companion object {
        fun getRandId(): Int {
            val random = Random()
            return abs(random.nextInt() % 1000)
        }
    }

}