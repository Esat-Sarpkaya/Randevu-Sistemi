package com.example.icode.randevusistemi

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.hour.view.*

class HourAdapter(var hourList:ArrayList<String>,var hour:MainActivity.getHour):RecyclerView.Adapter<HourAdapter.HourHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HourHolder {
        val view=LayoutInflater.from(p0.context).inflate(R.layout.hour,p0,false)
        return HourHolder(view)
    }

    override fun getItemCount(): Int {
        return hourList.size
    }

    override fun onBindViewHolder(p0: HourHolder, p1: Int) {
        p0.tv.text=hourList[p1]
        p0.view.setOnClickListener {
            hour.hour(hourList[p1])
        }
    }

    inner class HourHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val view=itemView as ConstraintLayout
        val tv=view.tv_hour

    }
}