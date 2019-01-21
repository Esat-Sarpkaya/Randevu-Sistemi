package com.example.icode.randevusistemi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.github.sundeepk.compactcalendarview.CompactCalendarView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog.*
import kotlinx.android.synthetic.main.dialog.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var ad:String?=null
    var tarih:String?=null
    var saat:String?=null
    var hocaAdi:String?=null
    val helper=DatabaseHelper(this)
    var inter:getHour?= null
    var dateFormatForMonth = SimpleDateFormat("MMM - yyyy", Locale.getDefault())
    val dateFormatDefault=SimpleDateFormat("dd/MM/yyyy",Locale.getDefault())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list=ArrayList<String>()

        list.add("Yakup Kutlu")
        list.add("Ahmet Gökçen")
        list.add("Gökhan Altan")
        val adapter=ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,list)
        spinner.adapter=adapter
        inter= object : getHour {
            override fun hour(a: String) {
                saat=a

            }
        }
        initCalendar()
        setupRecycler()
        spinner.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                hocaAdi=parent?.selectedItem.toString()

            }

        }
    btn_randevu.setOnClickListener {
        ad=et_name.text.toString()


        if (selectDate.text!="Tarih seçiniz"){
            tarih=selectDate.text.toString()
        }



        if (!ad.isNullOrEmpty() && !tarih.isNullOrEmpty() && !hocaAdi.isNullOrEmpty() && !saat.isNullOrEmpty()){
            val tar= "$tarih $saat"
            helper.randevuEkle(hocaAdi!!,tar,ad!!)
            Toast.makeText(this,"Randevu alındı",Toast.LENGTH_LONG).show()
            finish()
            val intent= Intent(this,Randevu::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(this,"Boşlukları doldurun.",Toast.LENGTH_LONG).show()
        }
    }
    }


     class RandevuModel{
         var ad:String=""
        var hocaAdi:String=""
        var tarih=""
        constructor()
        constructor(ad:String,hocaAdi:String,tarih:String){
            this.ad=ad
            this.hocaAdi=hocaAdi
            this.tarih=tarih

        }
    }


    private fun setupRecycler() {
        val hourList=ArrayList<String>()
        hourList.add("10.00")
        hourList.add("11.00")
        hourList.add("12.00")
        hourList.add("13.00")
        hourList.add("14.00")
        hourList.add("15.00")
        hourList.add("16.00")
        hourList.add("17.00")
        hourList.add("18.00")
        hourList.add("19.00")
        val adapter=HourAdapter(hourList,inter!!)
        rc_date.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rc_date.adapter=adapter
    }

    interface getHour{
        fun hour(a:String)
    }

    private fun initCalendar() {
        selectDate.setOnClickListener {
            val builder=AlertDialog.Builder(this)
            val view=LayoutInflater.from(this).inflate(R.layout.dialog,null)
            val compactCalendarView=view.compactCalendarView
            val tv=view.dialog_time

            val time=Calendar.getInstance().time
            builder.setView(view)
            val dialog=builder.create()
            dialog.show()
            compactCalendarView.setUseThreeLetterAbbreviation(true)
            compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY)
            compactCalendarView.setIsRtl(false)
            compactCalendarView.displayOtherMonthDays(false)
            compactCalendarView.setCurrentDate(time)
            compactCalendarView.setCurrentDayTextColor(R.color.colorAccent)
            compactCalendarView.setListener(object : CompactCalendarView.CompactCalendarViewListener {
                override fun onDayClick(dateClicked: Date?) {
                    selectDate.text=dateFormatDefault.format(dateClicked)
                    dialog.dismiss()
                }

                override fun onMonthScroll(firstDayOfNewMonth: Date?) {
                    tv.text=dateFormatForMonth.format(firstDayOfNewMonth)
                }

            })

        }
    }
}
