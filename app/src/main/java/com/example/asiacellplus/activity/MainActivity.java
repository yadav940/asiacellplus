package com.example.asiacellplus.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.se.omapi.Session;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.asiacellplus.Home2Activity;
import com.example.asiacellplus.R;
import com.example.asiacellplus.helpful.AsiacellplusSharedPreferences;
import com.example.asiacellplus.network.APIClient;
import com.example.asiacellplus.network.APIInterface;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ll_next,ll_lang1,ll_lang2,ll_lang3,ll_edit,ll_lang,ll_lets_go;
    private View v_1,v_2;
    private VideoView videoView;
    private ImageView imv_lang1,imv_lang2,imv_lang3,imv_tick;
    private EditText edt_phone;
    private ProgressDialog dialog;
    private final int DIALOG_LOADING=1321;

    AsiacellplusSharedPreferences sharedPreferences;

    String baseUrl="https://iraqcomprojects.com/v2/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        videoView = (VideoView)findViewById(R.id.videoView);
        ll_next=findViewById(R.id.ll_next);

        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setTitle("Loading...");


        ll_lang1=findViewById(R.id.ll_lang1);
        ll_lang2=findViewById(R.id.ll_lang2);
        ll_lang3=findViewById(R.id.ll_lang3);

        ll_edit=findViewById(R.id.ll_edit);
        ll_lets_go=findViewById(R.id.ll_lets_go);
        ll_lang=findViewById(R.id.ll_lang);
        imv_lang1=findViewById(R.id.imv_lang1);
        imv_lang2=findViewById(R.id.imv_lang2);
        imv_lang3=findViewById(R.id.imv_lang3);

        edt_phone=findViewById(R.id.edt_phone);

        imv_tick=findViewById(R.id.imv_tick);

        v_1=findViewById(R.id.v_1);
        v_2=findViewById(R.id.v_2);

        sharedPreferences=new AsiacellplusSharedPreferences(this);

        String path = "android.resource://" + getPackageName() + "/" + R.raw.teamwork_9;
        videoView.setVideoURI(Uri.parse(path));

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //setLocale(this);
    }

    @Override
    protected void onResume() {
        super.onResume();



        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        videoView.start();


        ll_next.setOnClickListener(this);
        ll_lets_go.setOnClickListener(this);
        ll_lang1.setOnClickListener(this);
        ll_lang2.setOnClickListener(this);
        ll_lang3.setOnClickListener(this);

        edt_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length()==10){
                    imv_tick.setImageDrawable(getResources().getDrawable(R.drawable.icon_green_tick));
                }

            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        videoView.pause();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_lets_go:
                if(edt_phone.getText().length()==10){
                    APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                    dialog.show();
                    //showDialog(DIALOG_LOADING);
                    Call<Object> call = apiInterface.addPhone(edt_phone.getText().toString());
                    call.enqueue(new Callback<Object>() {
                        @Override
                        public void onResponse(Call<Object> call, Response<Object> response) {
                            dialog.dismiss();
                            //dismissDialog(DIALOG_LOADING);
                            Log.d("tag----------","getting responce");
                            Home2Activity.startActivity(MainActivity.this);

                        }

                        @Override
                        public void onFailure(Call<Object> call, Throwable t) {
                            Log.d("tag----------","getting Error"+t.getMessage());
                            dialog.dismiss();
                            //dismissDialog(DIALOG_LOADING);
                            Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });


                }else {
                    Toast.makeText(this,"Please enter phone number",Toast.LENGTH_SHORT).show();
                    Home2Activity.startActivity(MainActivity.this);
                }
                //HomeActivity.startActivity(MainActivity.this);
                break;
            case R.id.ll_next:
                //Main2Activity.startActivity(MainActivity.this);
                ll_lang.setVisibility(View.GONE);
                ll_next.setVisibility(View.GONE);
                ll_edit.setVisibility(View.VISIBLE);
                ll_lets_go.setVisibility(View.VISIBLE);
                v_2.setBackground(new ColorDrawable(getResources().getColor(R.color.red)));
                v_1.setBackground(new ColorDrawable(getResources().getColor(R.color.gray)));

                break;
            case R.id.ll_lang1:
                imv_lang1.setVisibility(View.VISIBLE);
                imv_lang2.setVisibility(View.INVISIBLE);
                imv_lang3.setVisibility(View.INVISIBLE);
                sharedPreferences.setLanguage(3);
                MainActivity.setLocale(this,"ku");
                break;
            case R.id.ll_lang2:
                imv_lang1.setVisibility(View.INVISIBLE);
                imv_lang2.setVisibility(View.VISIBLE);
                imv_lang3.setVisibility(View.INVISIBLE);
                sharedPreferences.setLanguage(2);
                MainActivity.setLocale(this,"ar");

                break;
            case R.id.ll_lang3:
                imv_lang1.setVisibility(View.INVISIBLE);
                imv_lang2.setVisibility(View.INVISIBLE);
                imv_lang3.setVisibility(View.VISIBLE);
                sharedPreferences.setLanguage(1);
                MainActivity.setLocale(this,"en");
                break;
        }

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_LOADING:
                final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.loading);
                dialog.setCancelable(true);
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        //onBackPressed();
                    }
                });
                return dialog;

            default:
                return null;
        }
    };


    private void restartActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public static void setLocale(Activity context,String langCode) {
        Locale locale;
        //Sessions session = new Sessions(context);

        //Log.e("Lan",session.getLanguage());
        Log.e("Lan","-----------------------------");

        locale = new Locale(langCode);
        Configuration config = new Configuration(context.getResources().getConfiguration());
        Locale.setDefault(locale);
        config.setLocale(locale);

        context.getBaseContext().getResources().updateConfiguration(config,
                context.getBaseContext().getResources().getDisplayMetrics());
        //restartActivity();
    }

}