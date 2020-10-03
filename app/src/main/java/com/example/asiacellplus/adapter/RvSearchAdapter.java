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
import com.example.asiacellplus.model.helper.SearchHelperModel;
import com.example.asiacellplus.model.helper.ServiceType;

import java.util.List;

public class RvSearchAdapter extends RecyclerView.Adapter<RvSearchAdapter.SearchViewHolder> {
    List<SearchHelperModel> searchHelperModelList;

    public RvSearchAdapter(List<SearchHelperModel> searchHelperModelList) {
        this.searchHelperModelList = searchHelperModelList;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.melody_list_item, parent, false);
        SearchViewHolder viewHolder = new SearchViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        if (searchHelperModelList.get(position).getType()== ServiceType.SERVICE){

            Glide.with(holder.itemView)  //2
                    .load(searchHelperModelList.get(position).getImageUrl()) //3
                    .centerCrop() //4
                    //.circleCrop()
                    //.placeholder(R.drawable.img_asiacell_logo) //5//7
                    .into(holder.imv_melody_iteam);
        }else {

            Glide.with(holder.itemView)  //2
                    .load(searchHelperModelList.get(position).getImageUrl()) //3
                    //.centerCrop() //4
                    .circleCrop()
                    //.placeholder(R.drawable.img_asiacell_logo) //5//7
                    .into(holder.imv_melody_iteam);
        }
        holder.tv_item.setText(searchHelperModelList.get(position).getTitle());
        holder.tv_description.setText(searchHelperModelList.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return searchHelperModelList.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        ImageView imv_melody_iteam;
        TextView tv_item,tv_description;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            imv_melody_iteam=itemView.findViewById(R.id.imv_melody_iteam);
            tv_item=itemView.findViewById(R.id.tv_item);
            tv_description=itemView.findViewById(R.id.tv_description);
        }
    }
}
