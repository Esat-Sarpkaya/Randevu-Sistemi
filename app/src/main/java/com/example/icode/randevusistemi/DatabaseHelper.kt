package com.example.icode.randevusistemi

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) :SQLiteOpenHelper(context,DATABASE_NAME,null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table if not exists $TABLE_DATE(ID INTEGER, hoca VARCHAR,tarih VARCHAR,ogrenci VARCHAR)")
    }

    fun randevuEkle(hoca:String,tarih:String,ogrenci:String){
        val db=writableDatabase
        val contentValue=ContentValues()
        contentValue.put(COL2,hoca)
        contentValue.put(COL3,tarih)
        contentValue.put(COL4,ogrenci)
        db.insert(TABLE_DATE,null,contentValue)
        Log.e("eklendi",hoca)

    }

    val getData:Cursor
    get() {
        val db=writableDatabase
        val cur=db.rawQuery("select*from $TABLE_DATE",null)
        return cur
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    companion object {
        val DATABASE_NAME="randevu.db"
        val TABLE_DATE="dates"
        val COL2="hoca"
        val COL3="tarih"
        val COL4="ogrenci"
    }
}