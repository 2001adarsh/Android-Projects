package com.adarsh.googlenewsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.FileModel> {
    Context context;
    ArrayList<Model> data;

    public RecyclerAdapter(Context context, ArrayList<Model> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public FileModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.row, parent, false);

        return new FileModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FileModel holder, int position) {
        Model item = data.get(position);
        holder.title.setText(item.title);

        Glide.with(context).load(item.ImageUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class FileModel extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageView;

        public FileModel(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView2);
        }
    }
}
