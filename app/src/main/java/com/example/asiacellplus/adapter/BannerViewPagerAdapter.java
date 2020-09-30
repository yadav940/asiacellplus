package com.example.asiacellplus.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.asiacellplus.R;
import com.example.asiacellplus.model.DatumBanner;

import java.util.List;

public class BannerViewPagerAdapter extends PagerAdapter {


    Context context;
    List<DatumBanner> datumBannerList;
    LayoutInflater layoutInflater;


    public BannerViewPagerAdapter(Context context, List<DatumBanner> datumBannerList) {
        this.context = context;
        this.datumBannerList = datumBannerList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return datumBannerList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.banner_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        String url =datumBannerList.get(position).getImage();
        //imageView.setImageResource();
        Glide.with(itemView)  //2
                .load(url) //3
                .centerCrop() //4
                //.placeholder(R.drawable.img_asiacell_logo) //5//7
                .into(imageView);

        container.addView(itemView);

        //listening to image click
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "you clicked image " + (position + 1), Toast.LENGTH_LONG).show();
            }
        });

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

}
