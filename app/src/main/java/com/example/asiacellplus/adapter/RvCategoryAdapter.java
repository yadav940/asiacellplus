package com.example.asiacellplus.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.asiacellplus.R;
import com.example.asiacellplus.activity.IteamListActivity;
import com.example.asiacellplus.model.DatumCategory;

import org.w3c.dom.Text;

import java.util.List;

public class RvCategoryAdapter extends RecyclerView.Adapter<RvCategoryAdapter.MyViewHolder>  {
    private List<DatumCategory> datumCategoryList;

    public RvCategoryAdapter(List<DatumCategory> datumCategoryList) {
        this.datumCategoryList = datumCategoryList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.category_list_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(listItem);
        return viewHolder;
        //return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        //holder.iv_cat_iteam.setImageDrawable();

        Glide.with(holder.itemView)  //2
                .load(datumCategoryList.get(position).getIcon()) //3
                .centerCrop() //4
                //.placeholder(R.drawable.img_asiacell_logo) //5//7
                .into(holder.iv_cat_iteam);
        holder.tv_cat_iteam.setText(datumCategoryList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IteamListActivity.startActivity((Activity) v.getContext(),datumCategoryList.get(position).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return datumCategoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  {
        public ImageView iv_cat_iteam;
        public TextView tv_cat_iteam;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_cat_iteam=itemView.findViewById(R.id.iv_cat_iteam);
            tv_cat_iteam=itemView.findViewById(R.id.tv_cat_iteam);
        }
    }
}
