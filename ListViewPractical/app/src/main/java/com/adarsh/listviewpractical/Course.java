package com.adarsh.listviewpractical;

import java.util.ArrayList;
import java.util.Random;

public class Course  {
    String name;
    String uid;

    public Course(String name, String uid) {
        this.name = name;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public String getUid() {
        return uid;
    }

    public static final String[] listName = {
            "Adarsh", "Amit", "Abhishek", "Ayush", "Rohit", "Tushar"
    };
    public static final String[] listUid = {
            "18BCS1168", "18BCS1166", "18BCS1157", "19BCSelle", "18BCS1198", "18BCS1188"
    };

    public static ArrayList<Course>  randomList(int n){ //create random n number of lisst

            ArrayList<Course> arrayList = new ArrayList<>();

            Random r = new Random();

            for(int i=0; i<n; i++)
            {
                Course course = new Course(listName[r.nextInt(5)], listUid[r.nextInt(5)]);
                arrayList.add(course);
            }

            return arrayList;
    }

}
