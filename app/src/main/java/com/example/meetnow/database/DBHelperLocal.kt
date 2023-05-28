package com.example.meetnow.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelperLocal(context: Context?): SQLiteOpenHelper(context, DB_NAME , null , DB_VERSION) {

    companion object{
        private const val DB_NAME = "meetnowlocaldb"
        private const val DB_VERSION = 1
        private const val TABLE_NAME = "userdetails"
        private const val ID_COL = "id"
        private const val NAME_COL = "name"
        private const val PHONE_COL = "phonenumber"
        private const val EMAIL_COL = "email"
        private const val PASSWORD_COL = "password"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + PHONE_COL + " TEXT,"
                + EMAIL_COL + " TEXT,"
                + PASSWORD_COL + " TEXT)")

        if (db != null) {
            db.execSQL(query)
        }
    }

    fun addUserDetails(
        userName: String?,
        userPhoneNumber: String?,
        userEmail: String?,
        userPassword: String?
    ) {

        val db = this.writableDatabase
        val values = ContentValues()


        values.put(NAME_COL, userName)
        values.put(PHONE_COL, userPhoneNumber)
        values.put(EMAIL_COL, userEmail)
        values.put(PASSWORD_COL, userPassword)


        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        }
        onCreate(db)
    }
}