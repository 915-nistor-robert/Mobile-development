package com.example.passwordmanagersqlite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AccountAdapter : RecyclerView.Adapter<AccountAdapter.AccountViewHolder>() {
    private var accountsList: ArrayList<AccountModel> = ArrayList()
    private var onClickItem: ((AccountModel) -> Unit)? = null
    private var onClickDeleteItem: ((AccountModel) -> Unit)? = null


    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: ArrayList<AccountModel>){
        this.accountsList = items
        notifyDataSetChanged()
    }

    fun setOnClickItem(callback: (AccountModel) -> Unit){
        this.onClickItem = callback
    }

    fun setOnClickDeteleItem(callback: (AccountModel)-> Unit){
        this.onClickDeleteItem = callback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AccountViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_items_account, parent, false)
    )
    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val account = accountsList[position]
        holder.bindView(account)
        holder.itemView.setOnClickListener{onClickItem?.invoke(account)}
        holder.btnDelete.setOnClickListener{onClickDeleteItem?.invoke(account)}
    }

    override fun getItemCount(): Int {
        return accountsList.size
    }

    class AccountViewHolder(var view: View): RecyclerView.ViewHolder(view){
        private var id = view.findViewById<TextView>(R.id.tvId)
        private var platform = view.findViewById<TextView>(R.id.tvPlatform)
        private var username = view.findViewById<TextView>(R.id.tvUsername)
        private var password = view.findViewById<TextView>(R.id.tvPassword)
        public var btnDelete = view.findViewById<TextView>(R.id.btnDelete)

        fun bindView(account: AccountModel){
            id.text = account.id.toString()
            platform.text = account.platform.toString()
            username.text = account.username.toString()
            password.text = account.password.toString()
        }

    }
}