package com.example.icode.randevusistemi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_giris.*

class Giris : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_giris)

        btn_gor.setOnClickListener {
            val intent=Intent(this,Randevu::class.java)
            startActivity(intent)
        }

        btn_randevual.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}
