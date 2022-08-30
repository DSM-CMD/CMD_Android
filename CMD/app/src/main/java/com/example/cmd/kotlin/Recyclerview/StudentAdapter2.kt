package com.example.cmd.kotlin.Recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cmd.kotlin.Response.StudentInfoResponse2
import com.example.cmd.R

class StudentAdapter2(val list: ArrayList<StudentInfoResponse2>) : RecyclerView.Adapter<StudentAdapter2.StudentViewHolder2>() {

    class StudentViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.tvsubject)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentAdapter2.StudentViewHolder2 {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_student, parent, false)

        return StudentViewHolder2(view)
    }

    override fun onBindViewHolder(holder: StudentAdapter2.StudentViewHolder2, position: Int) {
        holder.name.setText(list.get(position).username)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}