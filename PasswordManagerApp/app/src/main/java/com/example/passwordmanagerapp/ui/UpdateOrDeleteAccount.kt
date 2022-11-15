package com.example.passwordmanagerapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.passwordmanagerapp.R
import com.example.passwordmanagerapp.repository.AccountRepository

class UpdateOrDeleteAccount : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_or_delete)
        val btnDelete = findViewById<Button>(R.id.btnDelete)
        val bundle : Bundle ?= intent.extras
        val message = bundle!!.getString("id")
        val selectedAccount = message?.let { AccountRepository.getAccount(it.toInt()) }

        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        btnUpdate.setOnClickListener{
            val intent = Intent(this@UpdateOrDeleteAccount, UpdateAccount::class.java).apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra("id", message)
            }
            startActivity(intent)
        }

        btnDelete.setOnClickListener{
            if (selectedAccount != null){
                AccountRepository.delete(selectedAccount)
                Toast.makeText(this, "Account deleted successfully", Toast.LENGTH_SHORT).show()
            }
            val intent = Intent(this@UpdateOrDeleteAccount, MainActivity::class.java).apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra("msg", "done")
            }
            startActivity(intent)
        }
    }
}