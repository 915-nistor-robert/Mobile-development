package com.example.passwordmanagersqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var etPlatform: EditText
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnView: Button
    private lateinit var btnUpdate: Button

    private lateinit var sqLiteHelper: SQLiteHelper
    private lateinit var recyclerView: RecyclerView
    private var adapter: AccountAdapter? = null
    private var account: AccountModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initRecyclerView()
        sqLiteHelper = SQLiteHelper(this)

        btnAdd.setOnClickListener { addAccount() }
        btnView.setOnClickListener { getAccounts() }
        btnUpdate.setOnClickListener { updateAccount() }

        adapter?.setOnClickItem {
            Toast.makeText(this, it.username, Toast.LENGTH_SHORT).show()
            etPlatform.setText(it.platform)
            etUsername.setText(it.username)
            etPassword.setText(it.password)
            account = it
        }

        adapter?.setOnClickDeteleItem {
            deleteAccount(it.id)
        }
    }

    private fun updateAccount() {
        val platform = etPlatform.text.toString()
        val username = etUsername.text.toString()
        val password = etPassword.text.toString()

        if (platform.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter required fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (platform == account?.platform && username == account?.username && password == account?.password) {
            Toast.makeText(this, "Account not changed", Toast.LENGTH_SHORT).show()
            return
        }

        if (account == null) {
            return
        }

        val account = AccountModel(
            id = account!!.id,
            username = username,
            platform = platform,
            password = password
        )

        val status = sqLiteHelper.updateAccount(account)
        if (status > -1) {
            clearEditText()
            getAccounts()
        } else {
            Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show()
        }

    }

    private fun getAccounts() {
        val accountList = sqLiteHelper.getAllAccounts()
        Log.e("pppp", "${accountList.size}")
        adapter?.setItems(accountList)

    }

    private fun addAccount() {
        val platform = etPlatform.text.toString()
        val username = etUsername.text.toString()
        val password = etPassword.text.toString()

        if (platform.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter required fields", Toast.LENGTH_SHORT).show()
        } else {
            val account =
                AccountModel(platform = platform, username = username, password = password)
            val status = sqLiteHelper.insertAccount(account)

            if (status > -1) {
                Toast.makeText(this, "Account added successfully", Toast.LENGTH_SHORT).show()
                clearEditText()
                getAccounts()
            } else {
                Toast.makeText(this, "Account not saved", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun deleteAccount(id: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to delete this item?")
        builder.setCancelable(true)
        builder.setPositiveButton("Yes") { dialog, _->
            sqLiteHelper.deleteAccount(id)
            getAccounts()
            dialog.dismiss()
        }
        builder.setNegativeButton("No") { dialog, _->
            dialog.dismiss()
        }

        var alert = builder.create()
        alert.show()
    }

    private fun clearEditText() {
        etPlatform.setText("")
        etUsername.setText("")
        etPassword.setText("")
        etPlatform.requestFocus()
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AccountAdapter()
        recyclerView.adapter = adapter
    }

    private fun initView() {
        etPlatform = findViewById(R.id.etPlatform)
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnAdd = findViewById(R.id.btnAdd)
        btnView = findViewById(R.id.btnView)
        btnUpdate = findViewById(R.id.btnUpdate)
        recyclerView = findViewById(R.id.rvAccounts)
    }
}