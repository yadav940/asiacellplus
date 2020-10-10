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
import com.example.asiacellplus.activity.MelodyDetailActivity;
import com.example.asiacellplus.activity.ServiceDetailActivity;
import com.example.asiacellplus.model.DatumService;

import java.util.List;

public class RvServiceAdapter extends RecyclerView.Adapter<RvServiceAdapter.ServiceAdapter> {
    List<DatumService> datumServiceList;

    public RvServiceAdapter(List<DatumService> datumServiceList) {
        this.datumServiceList = datumServiceList;
    }

    @NonNull
    @Override
    public ServiceAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.melody_list_item, parent, false);
        ServiceAdapter viewHolder = new ServiceAdapter(listItem);
        return viewHolder;
        //return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceAdapter holder, final int position) {
        Glide.with(holder.itemView)  //2
                .load(datumServiceList.get(position).getIcon()) //3
                .centerCrop() //4
                //.circleCrop()
                //.placeholder(R.drawable.img_asiacell_logo) //5//7
                .into(holder.imv_melody_iteam);
        holder.tv_item.setText(datumServiceList.get(position).getItem());
        holder.tv_description.setText(datumServiceList.get(position).getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceDetailActivity.startActivity((Activity) v.getContext(),datumServiceList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return datumServiceList.size();
    }

    public class ServiceAdapter extends RecyclerView.ViewHolder {
        ImageView imv_melody_iteam;
        TextView tv_item,tv_description;
        public ServiceAdapter(@NonNull View itemView) {
            super(itemView);
            imv_melody_iteam=itemView.findViewById(R.id.imv_melody_iteam);
            tv_item=itemView.findViewById(R.id.tv_item);
            tv_description=itemView.findViewById(R.id.tv_description);
        }
    }
}
