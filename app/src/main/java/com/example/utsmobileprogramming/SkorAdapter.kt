package com.example.utsmobileprogramming

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.utsmobileprogramming.model.LeaderBoard
import java.text.SimpleDateFormat
import java.util.*

class SkorAdapter(private val skor: List<LeaderBoard>) : RecyclerView.Adapter<SkorHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): SkorHolder {
        return SkorHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.leaderboard_item, viewGroup, false))
    }

//    Hitung jumlah data
    override fun getItemCount(): Int = skor.size

    override fun onBindViewHolder(holder: SkorHolder, position: Int) {
        holder.bindHero(skor[position],position)
    }
}

class SkorHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvHeroName = view.findViewById<TextView>(R.id.name_ld)
    private val skorLd = view.findViewById<TextView>(R.id.skor_ld)
    private val image = view.findViewById<ImageView>(R.id.image_ld)
    private val tgl = view.findViewById<TextView>(R.id.tgl_ld)
    @SuppressLint("SimpleDateFormat")
    fun bindHero(skor: LeaderBoard, position: Int) {
        val date = Date(skor.time!!.seconds * 1000)
        val format = SimpleDateFormat("dd/MM/yyyy")
        val dateString = format.format(date)
        when (position) {
            0 -> image.setImageResource(R.drawable.juara1)
            1 -> image.setImageResource(R.drawable.juara2)
            2 -> image.setImageResource(R.drawable.juara3)
            else -> image.setImageResource(R.drawable.other)
        }
        tgl.text = dateString
        tvHeroName.text = skor.uid
        skorLd.text = skor.skor.toString()
    }
}