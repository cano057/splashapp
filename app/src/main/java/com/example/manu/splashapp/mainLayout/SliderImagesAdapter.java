package com.example.manu.splashapp.mainLayout;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.manu.ecommerce.R;

import java.util.ArrayList;

public class SliderImagesAdapter extends PagerAdapter {


    private ArrayList<Integer> images;
    private LayoutInflater inflater;
    private Context context;
    private LayoutInflater layoutInflater;


    public SliderImagesAdapter(Context context, ArrayList<Integer> images) {
        this.context = context;
        this.images = images;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View myImageLayout = layoutInflater.inflate(R.layout.main_layout_slider, null);
        ImageView myImage = (ImageView) myImageLayout.findViewById(R.id.imageSlider);
        myImage.setImageResource(images.get(position));
        myImage.setMaxWidth(MainActivity.getWeithAndHeight(context)[0]);
        myImage.setMaxHeight(MainActivity.getWeithAndHeight(context)[0] / 2);

        //Change layout size params
        LinearLayout layout = view.findViewById(R.id.pager_layout);
        // Gets the layout params that will allow you to resize the layout
        ViewGroup.LayoutParams params = layout.getLayoutParams();
        params.height = myImage.getMaxHeight();
        params.width = myImage.getMaxWidth();
        layout.setLayoutParams(params);

        Log.i("width image", Integer.toString(myImage.getMaxWidth()));
        ViewPager vp = (ViewPager) view;
        vp.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
