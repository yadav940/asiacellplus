package com.example.asiacellplus.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asiacellplus.Home2Activity;
import com.example.asiacellplus.R;
import com.example.asiacellplus.model.DatumMelody;

public class MelodyDetailActivity extends AppCompatActivity {

    public static void startActivity(Activity activity, DatumMelody datumMelody) {
        Intent i=new Intent(activity, MelodyDetailActivity.class);
        i.putExtra("MyClass", datumMelody);
        activity.startActivity(i);
    }

    private ImageView imv;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_melody_detail);
        DatumMelody datumMelody=(DatumMelody)getIntent().getSerializableExtra("MyClass");

        imv=findViewById(R.id.imv);
        tv=findViewById(R.id.tv);


        /*Glide.with(this)  //2
                .load(datumMelody.getIcon()) //3
                .centerCrop() //4
                //.circleCrop()
                //.placeholder(R.drawable.img_asiacell_logo) //5//7
                .into(imv);*/
        tv.setText(datumMelody.getDescription());

    }
}