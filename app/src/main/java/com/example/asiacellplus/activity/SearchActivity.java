package com.example.asiacellplus.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.asiacellplus.R;
import com.example.asiacellplus.adapter.RvSearchAdapter;
import com.example.asiacellplus.model.BannerResponce;
import com.example.asiacellplus.model.DatumMelody;
import com.example.asiacellplus.model.helper.SearchHelperModel;
import com.example.asiacellplus.model.helper.ServiceType;
import com.example.asiacellplus.model.search.Melody;
import com.example.asiacellplus.model.search.SearchListResponce;
import com.example.asiacellplus.model.search.Service;
import com.example.asiacellplus.network.APIClient;
import com.example.asiacellplus.network.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    public static void startActivity(Activity activity) {
        Intent i=new Intent(activity, SearchActivity.class);
        //i.putExtra("MyClass", datumMelody);
        activity.startActivity(i);
    }

    private RecyclerView rv_search;
    private EditText edt_search;
    private LinearLayout ll_btn_search;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        rv_search=findViewById(R.id.rv_search);
        edt_search=findViewById(R.id.edt_search);
        ll_btn_search=findViewById(R.id.ll_btn_search);
        ll_btn_search.setOnClickListener(this);

        rv_search.setLayoutManager(new LinearLayoutManager(this));

        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setTitle("Loading...");


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_btn_search:


                APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                dialog.show();
                Call<SearchListResponce> searchListCall = apiInterface.getSearchList("1",edt_search.getText().toString());
                searchListCall.enqueue(new Callback<SearchListResponce>() {
                    @Override
                    public void onResponse(Call<SearchListResponce> call, Response<SearchListResponce> response) {
                        //response.body().getData().
                        List<SearchHelperModel> searchHelperModels= new ArrayList<SearchHelperModel>();

                        for (Melody melody: response.body().getData().getMelody()){
                            searchHelperModels.add(new SearchHelperModel(melody.getIcon(),melody.getItem(),melody.getDescription(), ServiceType.MELODY));
                        }
                        for (Service service: response.body().getData().getServices()){
                            searchHelperModels.add(new SearchHelperModel(service.getIcon(),service.getItem(),service.getDescription(), ServiceType.SERVICE));
                        }

                        dialog.dismiss();
                        RvSearchAdapter searchAdapter=new RvSearchAdapter(searchHelperModels);
                        rv_search.setAdapter(searchAdapter);




                    }

                    @Override
                    public void onFailure(Call<SearchListResponce> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
                break;

        }
    }
}