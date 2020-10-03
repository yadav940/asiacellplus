package com.example.asiacellplus.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.asiacellplus.R;
import com.example.asiacellplus.adapter.RvIteamAdapter;
import com.example.asiacellplus.model.IteamListResponce;
import com.example.asiacellplus.network.APIClient;
import com.example.asiacellplus.network.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IteamListActivity extends AppCompatActivity {
    public static void startActivity(Activity activity,String category_id) {
        Intent i=new Intent(activity, IteamListActivity.class);
        i.putExtra("category_id", category_id);
        activity.startActivity(i);
    }

    private RecyclerView rv_item_list;
    private ProgressDialog dialog;
    private String category_id;
    private ImageView imv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iteam_list);
        rv_item_list=findViewById(R.id.rv_item_list);
        imv_back=findViewById(R.id.imv_back);
        rv_item_list.setLayoutManager(new LinearLayoutManager(this));
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setTitle("Loading...");

        category_id = (String) getIntent().getExtras().getString("category_id");
        Log.d("tag _____IteamListActivity","----category_id__"+category_id);

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        //dialog.show();
        //showDialog(DIALOG_LOADING);
        Call<IteamListResponce> iteamListResponceCall = apiInterface.getiItem("1",category_id);
        //enteredValue.setText(passedArg);

        iteamListResponceCall.enqueue(new Callback<IteamListResponce>() {
            @Override
            public void onResponse(Call<IteamListResponce> call, Response<IteamListResponce> response) {

                rv_item_list.setAdapter(new RvIteamAdapter(response.body().getData()));
            }

            @Override
            public void onFailure(Call<IteamListResponce> call, Throwable t) {

            }
        });

        imv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}