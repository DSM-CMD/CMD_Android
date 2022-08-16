package com.example.cmd.Kotlin.Recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cmd.Kotlin.Response.NoticeResponse2
import com.example.cmd.R

class NoticeAdapter2(val list: ArrayList<NoticeResponse2>) :
    RecyclerView.Adapter<NoticeAdapter2.NoticeViewHolder2>() {

    class NoticeViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.tvtitle)
        val contents = itemView.findViewById<TextView>(R.id.tvcontents)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeAdapter2.NoticeViewHolder2 {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_student, parent, false)

        return NoticeViewHolder2(view)
    }

    override fun onBindViewHolder(holder: NoticeAdapter2.NoticeViewHolder2, position: Int) {
        holder.title.setText(list.get(position).title)
        holder.contents.setText(list.get(position).contents)
    }

    override fun getItemCount(): Int {
        return list.size
    }


}