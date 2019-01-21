package com.example.icode.randevusistemi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_randevu.*

class Randevu : AppCompatActivity() {
    val helper=DatabaseHelper(this)
    val randevuList=ArrayList<MainActivity.RandevuModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_randevu)

        getData()
    }

    fun getData(){
        val cur=helper.getData
        while (cur.moveToNext()){
            randevuList.add(MainActivity.RandevuModel(cur.getString(1),cur.getString(2),cur.getString(3)))
        }

        val adapter=RandevuAdapter(randevuList)
        rc_randevu.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rc_randevu.adapter=adapter
    }

}
