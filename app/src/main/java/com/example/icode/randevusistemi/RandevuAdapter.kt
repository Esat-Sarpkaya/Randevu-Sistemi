package com.example.icode.randevusistemi

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.randevu.view.*

class RandevuAdapter(var randevuList: ArrayList<MainActivity.RandevuModel>):RecyclerView.Adapter<RandevuAdapter.RandevuHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RandevuHolder {
        val view=LayoutInflater.from(p0.context).inflate(R.layout.randevu,p0,false)
        return RandevuHolder(view)
    }

    override fun getItemCount(): Int {
        return randevuList.size
    }

    override fun onBindViewHolder(p0: RandevuHolder, p1: Int) {
        val item=randevuList[p1]

        p0.ad.text=item.ad
        p0.hocaAdi.text=item.hocaAdi
        p0.tarih.text=item.tarih
    }

    inner class RandevuHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val ad=itemView.r_ad
        val hocaAdi=itemView.r_hocaAdi
        val tarih=itemView.r_tarih


    }
}