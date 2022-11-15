package com.example.passwordmanagerapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.passwordmanagerapp.R
import com.example.passwordmanagerapp.model.AccountsAdapter
import com.example.passwordmanagerapp.repository.AccountRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), AccountsAdapter.ItemClickListener {
    var adapter: AccountsAdapter? = null
    lateinit var recyclerView: RecyclerView

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set recycle view
        recyclerView = findViewById<RecyclerView>(R.id.rvAccounts)
        var layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        adapter = AccountsAdapter(this, AccountRepository.accounts)
        recyclerView.adapter = adapter
        adapter!!.setClickListener(this@MainActivity)
        val addBtn = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        addBtn.setOnClickListener{
            val intent = Intent(this@MainActivity, AddAccount::class.java)
            startActivity(intent)
        }

    }

    override fun onItemClick(view: View?, position: Int) {
        val intent = Intent(this@MainActivity, UpdateOrDeleteAccount::class.java).apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra("id", adapter!!.getAccount(position).id.toString())
        }
        startActivity(intent)
    }
}