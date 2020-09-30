package com.example.asiacellplus.ui.gallery;

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
import com.example.asiacellplus.adapter.BannerViewPagerAdapter;
import com.example.asiacellplus.adapter.RvCategoryAdapter;
import com.example.asiacellplus.adapter.RvMelodyAdapter;
import com.example.asiacellplus.model.BannerResponce;
import com.example.asiacellplus.model.MelodyListResopnce;
import com.example.asiacellplus.network.APIClient;
import com.example.asiacellplus.network.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    private RecyclerView rv_melody;
    private ProgressDialog dialog;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        rv_melody=root.findViewById(R.id.rv_melody);
        rv_melody.setLayoutManager(new LinearLayoutManager(getContext()));

        dialog = new ProgressDialog(getContext());
        dialog.setCancelable(false);
        dialog.setTitle("Loading...");


        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        dialog.show();
        Call<MelodyListResopnce> melodyList = apiInterface.getMelodyList("1");

        melodyList.enqueue(new Callback<MelodyListResopnce>() {
            @Override
            public void onResponse(Call<MelodyListResopnce> call, Response<MelodyListResopnce> response) {
                dialog.dismiss();
                rv_melody.setAdapter(new RvMelodyAdapter(response.body().getData()));

            }

            @Override
            public void onFailure(Call<MelodyListResopnce> call, Throwable t) {

                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        /*final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}