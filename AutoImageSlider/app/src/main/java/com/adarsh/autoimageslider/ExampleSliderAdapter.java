package com.adarsh.autoimageslider;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ExampleSliderAdapter extends SliderViewAdapter<ExampleSliderAdapter.SliderVH> {

    private Context context;
    private ArrayList<SliderItem> sliderItems;

    ExampleSliderAdapter(Context context, ArrayList<SliderItem> sliderItems){
        this.context = context;
        this.sliderItems = sliderItems;
    }

    public void renewItems(ArrayList<SliderItem> sliderItems)
    {
        this.sliderItems = sliderItems;
        notifyDataSetChanged();
    }

    public void deleteItems(int position){
        this.sliderItems.remove(position);
        notifyDataSetChanged();
    }

    public void addItems(SliderItem sliderItem){
        this.sliderItems.add(sliderItem);
        notifyDataSetChanged();
    }

    @Override
    public SliderVH onCreateViewHolder(ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = li.inflate(R.layout.images_container, parent, false);

        return new SliderVH(view);
    }

    @Override
    public void onBindViewHolder(SliderVH viewHolder, int position) {
        SliderItem sliderItem = sliderItems.get(position);
        viewHolder.img.setImageResource(sliderItem.getImg());
    }

    @Override
    public int getCount() {
        return sliderItems.size();
    }


    class SliderVH extends SliderViewAdapter.ViewHolder{
        View itemView;
        ImageView img;

        public SliderVH(View itemView) {
            super(itemView);

            this.itemView = itemView;
            this.img = itemView.findViewById(R.id.imageView);
        }
    }
}
