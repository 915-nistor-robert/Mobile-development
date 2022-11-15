package com.example.passwordmanagerapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.passwordmanagerapp.R
import com.example.passwordmanagerapp.model.Account
import com.example.passwordmanagerapp.repository.AccountRepository

class UpdateAccount: AppCompatActivity() {
    lateinit var platformView: TextView
    lateinit var usernameView: TextView
    lateinit var passwordView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_account)

        platformView = findViewById(R.id.etUpdatePlatform)
        usernameView = findViewById(R.id.etUpdateUsername)
        passwordView = findViewById(R.id.etUpdatePassword)

        val bundle : Bundle ?= intent.extras
        val message = bundle!!.getString("id")
        val selectedAccount = message?.let { AccountRepository.getAccount(it.toInt()) }

        if (selectedAccount != null){
            platformView.text = selectedAccount.platform
            usernameView.text = selectedAccount.username
            passwordView.text = selectedAccount.password
        }

        val updateBtn = findViewById<Button>(R.id.btnUpdateConfirm)
        updateBtn.setOnClickListener{
            val newPlatform = platformView.text.toString()
            val newUsername = usernameView.text.toString()
            val newPassword = passwordView.text.toString()
            if (selectedAccount != null){
                val updatedAccount = Account(selectedAccount.id, newPlatform, newUsername, newPassword)
                AccountRepository.update(updatedAccount)
                Toast.makeText(
                    this,
                    "Account updated successfully.",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this@UpdateAccount, MainActivity::class.java).apply {
                    action = Intent.ACTION_SEND
                    type = "text/plain"
                    putExtra("msg", "done")
                }
                startActivity(intent)
            }
        }

        val cancelUpdateBtn= findViewById<Button>(R.id.btnCancelUpdate)
        cancelUpdateBtn.setOnClickListener{
            val intent = Intent(this@UpdateAccount, MainActivity::class.java)
            startActivity(intent)
        }
    }
}