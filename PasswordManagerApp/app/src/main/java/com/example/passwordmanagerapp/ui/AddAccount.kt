package com.example.passwordmanagerapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.passwordmanagerapp.R
import com.example.passwordmanagerapp.model.Account
import com.example.passwordmanagerapp.repository.AccountRepository

class AddAccount : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_account)

        val confirmBtn = findViewById<Button>(R.id.btnAddConfirm)
        confirmBtn.setOnClickListener {
            val accountPlatform = findViewById<TextView>(R.id.etAddPlatform)?.text.toString()
            val accountUsername = findViewById<TextView>(R.id.etAddUsername)?.text.toString()
            val accountPassword = findViewById<TextView>(R.id.etAddPassword)?.text.toString()
            if (accountPlatform != "" && accountUsername != "" && accountPassword != "") {
                val account =
                    Account(AccountRepository.ID, accountPlatform, accountUsername, accountPassword)
                AccountRepository.add(account)

                val intent = Intent(this@AddAccount, MainActivity::class.java)
                startActivity(intent)

                Toast.makeText(
                    this,
                    "Account added successfully.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(this, "Please complete every field.", Toast.LENGTH_SHORT).show()
            }
        }

        val cancelAddButton = findViewById<Button>(R.id.btnCancelAdd)
        cancelAddButton.setOnClickListener{
            val intent = Intent(this@AddAccount, MainActivity::class.java)
            startActivity(intent)
        }
    }
}