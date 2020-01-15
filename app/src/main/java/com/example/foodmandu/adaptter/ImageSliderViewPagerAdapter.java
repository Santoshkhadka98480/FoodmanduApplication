package com.example.foodmandu.adaptter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.foodmandu.R;
import com.example.foodmandu.api.UserAPI;
import com.example.foodmandu.serverresponse.ImageResponse;
import com.example.foodmandu.strictmode.StrictModeClass;
import com.example.foodmandu.url.Url;

import retrofit2.Call;

public class ImageSliderViewPagerAdapter extends PagerAdapter {
    private Context context;
    private Integer [] images = {R.drawable.slider1,R.drawable.slider2,R.drawable.slider3,R.drawable.slider4};
    private Object LayoutInflater;
    ImageView imageView;

    public ImageSliderViewPagerAdapter(Context context) {
        this.context = context;
    }





    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = ((LayoutInflater) LayoutInflater).inflate(R.layout.image_slider,null);
        imageView = view.findViewById(R.id.SliderImage);
        imageView.setImageResource(images[position]);

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view,0);
        return view;

    }

//    public void viewImage(){
//        UserAPI userAPI = Url.getInstance().create(UserAPI.class);
//        Call<ImageResponse> imageResponseCall = userAPI.uploadImage();
//
//        StrictModeClass.StrictMode();
//    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;

        viewPager.removeView(view);
    }
}
