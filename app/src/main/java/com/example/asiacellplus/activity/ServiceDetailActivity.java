package com.example.asiacellplus.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asiacellplus.R;
import com.example.asiacellplus.model.DatumMelody;
import com.example.asiacellplus.model.DatumService;

public class ServiceDetailActivity extends AppCompatActivity implements View.OnClickListener {

    public static void startActivity(Activity activity, DatumService datumService) {
        Intent i=new Intent(activity, ServiceDetailActivity.class);
        i.putExtra("item", datumService.getItem());
        i.putExtra("description", datumService.getDescription());
        i.putExtra("icon", datumService.getIcon());
        i.putExtra("banner", datumService.getBanner());
        i.putExtra("sub_data", datumService.getSubData());
        i.putExtra("sub_no", datumService.getSubNo());
        i.putExtra("unsub_data", datumService.getUnsubData());
        i.putExtra("unsub_no", datumService.getUnsubNo());
        i.putExtra("custom", datumService.getCustom());
        i.putExtra("youtube_url", datumService.getYoutubeUrl());

        activity.startActivity(i);
    }

    private DatumService datumService;
    private TextView tv_title,tv_description;
    private WebView videoWebView;
    private String youtube_url;
    private ImageView imv;

    private Button btn_subscribe,btn_unsubscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_detail);
        //datumService=(DatumService)getIntent().getSerializableExtra("MyClass");
        tv_title=findViewById(R.id.tv_title);
        tv_description=findViewById(R.id.tv);
        btn_subscribe=findViewById(R.id.Sub);
        btn_unsubscribe=findViewById(R.id.button2);
        imv=findViewById(R.id.imv);
        videoWebView=findViewById(R.id.videoWebView);

        btn_subscribe.setOnClickListener(this);
        btn_unsubscribe.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();

        youtube_url= bundle.getString("youtube_url");

        if (youtube_url==null||youtube_url.length()==0){
            videoWebView.setVisibility(View.GONE);
            Glide.with(this)  //2
                    .load(bundle.getString("banner")) //3
                    .centerCrop() //4
                    //.circleCrop()
                    //.placeholder(R.drawable.img_asiacell_logo) //5//7
                    .into(imv);
        }

        tv_description.setText(bundle.getString("description"));
        tv_title.setText(bundle.getString("item"));

        Log.d("ServiceDetailActivity--",""+youtube_url);

        youtube_url="<iframe width=\"100%\" height=\"100%\" src=\""+youtube_url+"\" frameborder=\"0\" allowfullscreen></iframe>";



        /*String iframString = "<iframe class=\"youtube-player\" style=\"border: 0; width: 100%; height: 95%; padding:0px; margin:0px\" id=\"ytplayer\" type=\"text/html\" src=\""+youtube_url
                + "?fs=0\" frameborder=\"0\">\n"
                + "</iframe>";*/

        Log.d("ServiceDetailActivity--",""+youtube_url);



        videoWebView.getSettings().setJavaScriptEnabled(true);
        videoWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        videoWebView.setWebChromeClient(new WebChromeClient() {

        } );

        //videoWebView.loadDataWithBaseURL("",iframString,"text/html",  "UTF-8", "");
        videoWebView.loadData( youtube_url, "text/html" , "utf-8" );


    }

    @Override
    public void onClick(View v) {


        Intent intent = new Intent("android.intent.action.VIEW");

        Uri data = Uri.parse("sms:");

        switch (v.getId()){
            case R.id.Sub:
                intent.setData(data);
                startActivity(intent);
                break;
            case R.id.button2:

                intent.setData(data);
                startActivity(intent);
                break;
        }
    }
}