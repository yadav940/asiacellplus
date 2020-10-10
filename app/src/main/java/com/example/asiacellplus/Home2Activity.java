package com.example.asiacellplus;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.asiacellplus.activity.MainActivity;
import com.example.asiacellplus.helpful.AsiacellplusSharedPreferences;
import com.example.asiacellplus.model.search.SearchListResponce;
import com.example.asiacellplus.network.APIClient;
import com.example.asiacellplus.network.APIInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.core.view.MenuItemCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

public class Home2Activity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    AsiacellplusSharedPreferences sharedPreferences;

    public static void startActivity(Activity mainActivity) {
        mainActivity.startActivity(new Intent(mainActivity,Home2Activity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBar actionBar =getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));

        //actionBar.setCustomView(R.layout.actionbar_custom_view_home);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        //actionBar.getCustomView().setForegroundGravity(View.FOCUS_RIGHT);


        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        View customNav = LayoutInflater.from(this).inflate(R.layout.actionbar_custom_view_home, null); // layout which contains your button.
        actionBar.setCustomView(customNav, lp);
        actionBar.setDisplayShowCustomEnabled(true);


        //actionBar.setCustomView();
        //actionBar.setIcon(R.drawable.img_asiacell_logo);
        actionBar.setElevation(0);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow/*,R.id.nav_language*/)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        sharedPreferences=new AsiacellplusSharedPreferences(this);

        Switch sw = (Switch) navigationView.getMenu().findItem(R.id.nav_notification).getActionView().findViewById(R.id.drawer_layout_switch);

        Menu menu = navigationView.getMenu();
        MenuItem tools= menu.findItem(R.id.nav_privacy_policye);
        MenuItem tools2= menu.findItem(R.id.nav_terms_and_conditions);

        SpannableString s = new SpannableString(tools.getTitle());
        s.setSpan(new TextAppearanceSpan(this, R.style.DrawerInformationTheme), 0, s.length(), 0);
        tools.setTitle(s);
        SpannableString s2 = new SpannableString(tools2.getTitle());
        s2.setSpan(new TextAppearanceSpan(this, R.style.DrawerInformationTheme), 0, s.length(), 0);
        tools2.setTitle(s2);


        tools.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.d("Home2Activity------","nav_privacy_policye");
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://iraqcom.com/asiacell-plus-privacy-policy/"));
                startActivity(browserIntent);

                return false;
            }
        });
        tools2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.d("Home2Activity------","nav_terms_and_conditions");
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://iraqcom.com/asiacell-plus-terms-conditions/"));
                startActivity(browserIntent);

                return false;
            }
        });

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Log.d("Home activity","--------------isChecked "+isChecked);
                if (isChecked){
                    addToken();
                }else {
                    deleteToken();
                }

            }
        });


        /*navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                CompoundButton switchView = (CompoundButton) MenuItemCompat.getActionView(item);
                switchView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        if (isChecked){
                            addToken();
                        }else {
                            deleteToken();
                        }
                        Log.d("HomeActivity","-------nav_notification"+isChecked);
                    }
                });

                switch (item.getItemId()){
                    case R.id.nav_language:
                        Log.d("HomeActivity","-------nav_language");
                        break;
                }

                return false;
            }
        });*/
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home2, menu);
        return true;
    }*/

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    void addToken(){

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        //dialog.show();
        Call<Object> addTokenCall = apiInterface.addToken(sharedPreferences.getLanguage()+"","android","");
        addTokenCall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
    void deleteToken(){


        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        //dialog.show();
        Call<Object> deleteTokenCall = apiInterface.deleteToken("android","");
        deleteTokenCall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

}