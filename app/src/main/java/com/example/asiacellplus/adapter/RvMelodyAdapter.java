package com.example.asiacellplus.adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.media.Image;
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
import com.example.asiacellplus.model.DatumMelody;

import java.util.List;

public class RvMelodyAdapter extends RecyclerView.Adapter<RvMelodyAdapter.MelodyViewHolder> {
    List<DatumMelody> melodyList;

    public RvMelodyAdapter(List<DatumMelody> datumMelodyList) {
        this.melodyList = datumMelodyList;
    }

    @NonNull
    @Override
    public MelodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.melody_list_item, parent, false);
        MelodyViewHolder viewHolder = new MelodyViewHolder(listItem);
        return viewHolder;
        //return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MelodyViewHolder holder, final int position) {
        Glide.with(holder.itemView)  //2
                .load(melodyList.get(position).getIcon()) //3
                //.centerCrop() //4
                .circleCrop()
                //.placeholder(R.drawable.img_asiacell_logo) //5//7
                .into(holder.imv_melody_iteam);
        holder.tv_item.setText(melodyList.get(position).getItem());
        holder.tv_description.setText(melodyList.get(position).getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MelodyDetailActivity.startActivity((Activity) v.getContext(),melodyList.get(position));

            }
        });

    }

    @Override
    public int getItemCount() {
        return melodyList.size();
    }

    public class MelodyViewHolder extends RecyclerView.ViewHolder{
        ImageView imv_melody_iteam;
        TextView tv_item,tv_description;
        public MelodyViewHolder(@NonNull View itemView) {
            super(itemView);
            imv_melody_iteam=itemView.findViewById(R.id.imv_melody_iteam);
            tv_item=itemView.findViewById(R.id.tv_item);
            tv_description=itemView.findViewById(R.id.tv_description);

        }
    }
}
