package com.example.passwordmanagerapp.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.passwordmanagerapp.R
import com.example.passwordmanagerapp.repository.AccountRepository

class AccountsAdapter internal constructor(context: Context?, data: ArrayList<Account>) :
    RecyclerView.Adapter<AccountsAdapter.ViewHolder>(){
    private val accounts: ArrayList<Account>
    private val inflater: LayoutInflater
    private var accountClickListener: ItemClickListener? = null

    init {
        inflater = LayoutInflater.from(context)
        accounts = data
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener{
        var platformView : TextView
        var usernameView: TextView
        var passwordView : TextView
        init {
            platformView = itemView.findViewById(R.id.tvPlatform)
            usernameView = itemView.findViewById(R.id.tvUsername)
            passwordView = itemView.findViewById(R.id.tvPassword)
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            if(accountClickListener != null) accountClickListener!!.onItemClick(view, adapterPosition)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = inflater.inflate(R.layout.account_item, parent, false)
        return ViewHolder(view)
    }

    fun getAccount(position: Int): Account{
        return accounts[position]
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val account = accounts[position]
        holder.platformView.text = account.platform
        holder.usernameView.text = account.username
        holder.passwordView.text = account.password
    }

    override fun getItemCount(): Int {
        return accounts.size
    }

    fun setClickListener(itemClickListener: ItemClickListener){
        accountClickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }
}


