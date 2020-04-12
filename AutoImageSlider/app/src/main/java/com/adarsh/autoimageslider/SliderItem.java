package com.adarsh.autoimageslider;

import android.widget.ImageView;

import java.util.ArrayList;

public class SliderItem {
    int img;

    public SliderItem(int img) {
        this.img = img;
    }

    public int getImg() {
        return img;
    }

    private static int[] images= {
            R.drawable.img,
            R.drawable.imgt,
            R.drawable.imgf,
            R.drawable.imgfi,
            R.drawable.imgth,
            R.drawable.imgs
    };

    public static ArrayList<SliderItem> getSliderWhole(int n){
        ArrayList<SliderItem> sl = new ArrayList<SliderItem>();

        for(int i=0; i<n; i++){
            SliderItem sliderItem = new SliderItem(images[i]);
            sl.add(sliderItem);
        }
        return sl;
    }
}
