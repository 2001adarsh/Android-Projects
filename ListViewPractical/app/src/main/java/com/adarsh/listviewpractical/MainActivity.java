package com.adarsh.listviewpractical;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv_main;
    ArrayList<Course> courses = Course.randomList(100);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_main= findViewById(R.id.lv_main);
        CourseAdapter courseAdapter = new CourseAdapter();
        lv_main.setAdapter(courseAdapter);

    }

    class CourseAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return courses.size();
        }

        @Override
        public Course getItem(int i) {
            return courses.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolderCourse holder;
            if(view == null) {
                view = getLayoutInflater().inflate(R.layout.list_resource, viewGroup, false);
                 holder = new ViewHolderCourse(view);
                view.setTag(holder);
            }else{
                holder = (ViewHolderCourse) view.getTag();
            }

            Course course = getItem(i);

            holder.tvname.setText(course.getName());
            holder.tvuid.setText(course.getUid());

            return view;
        }

        class ViewHolderCourse{
            TextView tvname, tvuid;
            ViewHolderCourse(View view){
                tvname = view.findViewById(R.id.tv_name);
                tvuid = view.findViewById(R.id.tv_uid);
            }
        }


    }

}
