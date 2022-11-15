package com.example.passwordmanagerapp.repository

import com.example.passwordmanagerapp.model.Account

object AccountRepository {
    public var ID = 0;
    var accounts = ArrayList<Account>()

    init {
        add(Account(ID,"Facebook","drake123","pass1"))
        add(Account(ID,"Instagram","drake1234","pass2"))
        add(Account(ID,"Metin2","drake125","pass3"))
        add(Account(ID,"LinkedIn","drake127","pass4"))
    }

    fun add(account: Account){
        ID++
        accounts.add(account)
    }

    fun getAccount(accountId: Int): Account {
        return accounts.single { account -> account.id == accountId }
    }

    private fun getPosition(accountId: Int) : Int{
        for (account in accounts){
            if (account.id == accountId)
                return accounts.indexOf(account)
        }
        return 0
    }

    fun update(updatedAccount: Account){
        accounts.set(getPosition(updatedAccount.id), updatedAccount)
    }

    fun delete(account: Account){
        accounts.remove(account)
    }
}