package com.example.data.model

import java.util.*

@kotlinx.serialization.Serializable
data class AccountModel(
    var id: Int = getRandId(),
    var platform: String = "",
    var username: String = "",
    var password: String = ""
) {

    companion object {
        fun getRandId(): Int {
            val random = Random()
            return Math.abs(random.nextInt() % 1000)
        }
    }

}
