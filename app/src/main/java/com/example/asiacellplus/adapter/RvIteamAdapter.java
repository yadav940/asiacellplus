package com.example.asiacellplus.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.asiacellplus.R;
import com.example.asiacellplus.model.DatumIteam;

import java.util.List;

public class RvIteamAdapter extends RecyclerView.Adapter<RvIteamAdapter.IteamViewHolder> {
    private List<DatumIteam> datumIteamList;

    public RvIteamAdapter(List<DatumIteam> datumIteamList) {
        this.datumIteamList = datumIteamList;
    }

    @NonNull
    @Override
    public IteamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.melody_list_item, parent, false);
        IteamViewHolder viewHolder = new IteamViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IteamViewHolder holder, int position) {
        Glide.with(holder.itemView)  //2
                .load(datumIteamList.get(position).getIcon()) //3
                .centerCrop() //4
                //.circleCrop()
                //.placeholder(R.drawable.img_asiacell_logo) //5//7
                .into(holder.imv_melody_iteam);
        holder.tv_item.setText(datumIteamList.get(position).getItem());
        holder.tv_description.setText(datumIteamList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return datumIteamList.size();
    }

    public class IteamViewHolder extends RecyclerView.ViewHolder {
        ImageView imv_melody_iteam;
        TextView tv_item,tv_description;
        public IteamViewHolder(@NonNull View itemView) {
            super(itemView);
            imv_melody_iteam=itemView.findViewById(R.id.imv_melody_iteam);
            tv_item=itemView.findViewById(R.id.tv_item);
            tv_description=itemView.findViewById(R.id.tv_description);
        }
    }
}
