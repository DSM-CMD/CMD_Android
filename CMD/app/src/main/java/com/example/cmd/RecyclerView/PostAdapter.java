package com.example.cmd.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmd.R;
import com.example.cmd.Response.UserPostResponse;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<UserPostResponse> userpostlist;

    public PostAdapter(List<UserPostResponse> userpostlist) {
        this.userpostlist = userpostlist;
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        public TextView tvtitle;
        public TextView tvcontents;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            tvtitle = itemView.findViewById(R.id.tvtitle);
            tvcontents = itemView.findViewById(R.id.tvcontents);
        }
    }
    @NonNull
    @Override
    public PostAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_post, parent, false);

        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.PostViewHolder holder, int position) {
        holder.tvtitle.setText(userpostlist.get(position).getTitle());
        holder.tvcontents.setText(userpostlist.get(position).getContents());
    }

    @Override
    public int getItemCount() {
        return userpostlist.size();
    }
}
