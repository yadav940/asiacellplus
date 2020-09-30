package com.example.asiacellplus.ui.slideshow;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asiacellplus.R;
import com.example.asiacellplus.adapter.RvMelodyAdapter;
import com.example.asiacellplus.adapter.RvServiceAdapter;
import com.example.asiacellplus.model.CategoryListResponce;
import com.example.asiacellplus.model.MelodyListResopnce;
import com.example.asiacellplus.model.ServiceListResopnce;
import com.example.asiacellplus.network.APIClient;
import com.example.asiacellplus.network.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private RecyclerView rv_service;
    private ProgressDialog dialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        /*final TextView textView = root.findViewById(R.id.text_slideshow);
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        rv_service=root.findViewById(R.id.rv_service);

        rv_service.setLayoutManager(new LinearLayoutManager(getContext()));

        dialog = new ProgressDialog(getContext());
        dialog.setCancelable(false);
        dialog.setTitle("Loading...");

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        dialog.show();
        Call<ServiceListResopnce> serviceList = apiInterface.getServiceList("1");

        serviceList.enqueue(new Callback<ServiceListResopnce>() {
            @Override
            public void onResponse(Call<ServiceListResopnce> call, Response<ServiceListResopnce> response) {

                dialog.dismiss();
                rv_service.setAdapter(new RvServiceAdapter(response.body().getData()));
            }

            @Override
            public void onFailure(Call<ServiceListResopnce> call, Throwable t) {

                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


        return root;
    }
}