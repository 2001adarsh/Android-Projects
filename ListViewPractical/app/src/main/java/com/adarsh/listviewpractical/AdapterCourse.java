package com.adarsh.listviewpractical;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterCourse extends RecyclerView.Adapter<AdapterCourse.VHCourse> {

    Context context;
    ArrayList<Course> courses;

    public AdapterCourse(Context context, ArrayList<Course> courses) {
        this.context = context;
        this.courses = courses;
    }

    @NonNull
    @Override
    public VHCourse onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = li.inflate(R.layout.list_resource, parent, false);

        return new VHCourse(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VHCourse holder, int position) {
        Course course = courses.get(position);
        holder.tvName.setText(course.getName());
        holder.tvUid.setText(course.getUid());

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class VHCourse extends RecyclerView.ViewHolder{

        TextView tvName, tvUid;

        public VHCourse(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvUid = itemView.findViewById(R.id.tv_uid);
        }
    }

}
