package com.example.passwordmanagersqlite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SQLiteHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null , DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "passwords.db"
        private const val TABLE_ACCOUNTS = "table_accounts"
        private const val ID = "id"
        private const val PLATFORM = "platform"
        private const val USERNAME = "username"
        private const val PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableAccounts = ("CREATE TABLE " + TABLE_ACCOUNTS + "(" + ID + " INTEGER PRIMARY KEY,"
                + PLATFORM + " TEXT," + USERNAME + " TEXT," + PASSWORD + " TEXT" + ")")

        db?.execSQL(createTableAccounts)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_ACCOUNTS")
        onCreate(db)
    }

    fun insertAccount(acc: AccountModel) : Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, acc.id)
        contentValues.put(PLATFORM, acc.platform)
        contentValues.put(USERNAME, acc.username)
        contentValues.put(PASSWORD, acc.password)

        val success = db.insert(TABLE_ACCOUNTS, null, contentValues)
        db.close()

        return success
    }

    @SuppressLint("Range")
    fun getAllAccounts() : ArrayList<AccountModel>{
        val accountsList: ArrayList<AccountModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TABLE_ACCOUNTS"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var platform: String
        var username: String
        var password: String

        if (cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex("id"))
                platform = cursor.getString(cursor.getColumnIndex("platform"))
                username = cursor.getString(cursor.getColumnIndex("username"))
                password = cursor.getString(cursor.getColumnIndex("password"))
                val account = AccountModel(id = id, platform = platform, username = username, password = password)
                accountsList.add(account)
            } while (cursor.moveToNext())
        }
        return accountsList
    }

    fun updateAccount(account: AccountModel): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, account.id)
        contentValues.put(USERNAME, account.username)
        contentValues.put(PLATFORM, account.platform)
        contentValues.put(PASSWORD, account.password)
        Log.e("upd","update")

        val success = db.update(TABLE_ACCOUNTS, contentValues, "id="+account.id, null)
        db.close()
        return success
    }

    fun deleteAccount(id: Int) : Int {
            val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, id)

        val success = db.delete(TABLE_ACCOUNTS, "id=$id", null)
        db.close()
        return success
    }
}