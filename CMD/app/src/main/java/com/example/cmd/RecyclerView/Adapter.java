package com.example.cmd.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmd.R;
import com.example.cmd.Response.TimetableResponse;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.TimeTableViewHolder> {

    private List<TimetableResponse> list;

    public class TimeTableViewHolder extends RecyclerView.ViewHolder {

        public TextView subject;

        public TimeTableViewHolder(@NonNull View itemView) {
            super(itemView);

            subject = itemView.findViewById(R.id.tvsubject);
        }
    }

    public Adapter(List<TimetableResponse> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public TimeTableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_timetable, parent, false);

        return new TimeTableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeTableViewHolder holder, int position) {
        holder.subject.setText(list.get(position).getPeriod1st());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
