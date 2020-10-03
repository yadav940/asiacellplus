package com.example.asiacellplus.ui.home;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.asiacellplus.Home2Activity;
import com.example.asiacellplus.R;
import com.example.asiacellplus.activity.MainActivity;
import com.example.asiacellplus.activity.SearchActivity;
import com.example.asiacellplus.adapter.BannerViewPagerAdapter;
import com.example.asiacellplus.adapter.RvCategoryAdapter;
import com.example.asiacellplus.model.BannerResponce;
import com.example.asiacellplus.model.CategoryListResponce;
import com.example.asiacellplus.network.APIClient;
import com.example.asiacellplus.network.APIInterface;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ViewPager viewPager;
    private BannerViewPagerAdapter viewPagerAdapter;
    private DotsIndicator dotsIndicator;

    private ProgressDialog dialog;

    private RecyclerView rv_category;

    private LinearLayout ll_search;

    int images[] = {R.drawable.icon_melody_red, R.drawable.img_asiacell_logo, R.drawable.img_asiacell_logo, R.drawable.img_asiacell_logo};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        /*homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        //textView.setText("this is vinod yadav fragment");


        //viewPager = (ViewPager)root.findViewById(R.id.viewPager);

        //viewPagerAdapter = new BannerViewPagerAdapter(getContext(), images);
        //viewPager.setAdapter(viewPagerAdapter);


        dialog = new ProgressDialog(getContext());
        dialog.setCancelable(false);
        dialog.setTitle("Loading...");

        rv_category=root.findViewById(R.id.rv_category);


        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3)
                ;

        rv_category.setLayoutManager(layoutManager);
        dotsIndicator = (DotsIndicator) root.findViewById(R.id.dots_indicator);
        viewPager = (ViewPager)root.findViewById(R.id.viewPager);

        ll_search = root.findViewById(R.id.ll_search);

        ll_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity.startActivity((Activity) v.getContext());
            }
        });
        //viewPagerAdapter = new BannerViewPagerAdapter(getContext(), images);
        //viewPager.setAdapter(viewPagerAdapter);
        //dotsIndicator.setViewPager(viewPager);

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        dialog.show();
        Call<BannerResponce> callBanner = apiInterface.getBanner("1");
        callBanner.enqueue(new Callback<BannerResponce>() {
            @Override
            public void onResponse(Call<BannerResponce> call, Response<BannerResponce> response) {
                dialog.dismiss();
                viewPagerAdapter = new BannerViewPagerAdapter(getContext(), response.body().getData());
                viewPager.setAdapter(viewPagerAdapter);
                dotsIndicator.setViewPager(viewPager);

            }

            @Override
            public void onFailure(Call<BannerResponce> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

        Call<CategoryListResponce> categoryListResponceCall=apiInterface.getCategoryList("1");
        categoryListResponceCall.enqueue(new Callback<CategoryListResponce>() {
            @Override
            public void onResponse(Call<CategoryListResponce> call, Response<CategoryListResponce> response) {

                rv_category.setAdapter(new RvCategoryAdapter(response.body().getData()));

            }

            @Override
            public void onFailure(Call<CategoryListResponce> call, Throwable t) {

                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });


        return root;
    }
}