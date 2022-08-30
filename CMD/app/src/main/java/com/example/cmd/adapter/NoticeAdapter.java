package com.example.cmd.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmd.R;
import com.example.cmd.Response.NoticeResponse;

import org.w3c.dom.Text;

import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> {

    private List<NoticeResponse> list;

    public NoticeAdapter(List<NoticeResponse> list) {
        this.list = list;
    }

    public class NoticeViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;
        public TextView tvContent;

        public NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvtitle);
            tvContent = itemView.findViewById(R.id.tvcontents);
        }
    }

    @NonNull
    @Override
    public NoticeAdapter.NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_notice, parent, false);

        return new NoticeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeAdapter.NoticeViewHolder holder, int position) {
        holder.tvTitle.setText(list.get(position).getTitle());
        holder.tvContent.setText(list.get(position).getContents());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
