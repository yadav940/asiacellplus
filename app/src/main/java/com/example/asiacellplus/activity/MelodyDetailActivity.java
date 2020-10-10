package com.example.asiacellplus.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.asiacellplus.Home2Activity;
import com.example.asiacellplus.R;
import com.example.asiacellplus.model.DatumMelody;
import com.gauravk.audiovisualizer.visualizer.BarVisualizer;

import java.io.IOException;

public class MelodyDetailActivity extends AppCompatActivity implements View.OnClickListener {

    public static void startActivity(Activity activity, DatumMelody datumMelody) {
        Intent i=new Intent(activity, MelodyDetailActivity.class);
        i.putExtra("item", datumMelody.getItem());
        i.putExtra("description", datumMelody.getDescription());
        i.putExtra("icon", datumMelody.getIcon());
        i.putExtra("audio", datumMelody.getAudio());
        i.putExtra("sub_data", datumMelody.getSubData());
        i.putExtra("sub_no", datumMelody.getSubNo());
        i.putExtra("unsub_data", datumMelody.getSubData());
        i.putExtra("unsub_no", datumMelody.getUnsubNo());
        i.putExtra("custom", datumMelody.getCustom());
        i.putExtra("flag", datumMelody.getFlag());
        i.putExtra("tone_number", datumMelody.getToneNumber());
        i.putExtra("tone_code", datumMelody.getToneCode());
        i.putExtra("tone_mobile", datumMelody.getToneMobile());
        activity.startActivity(i);
    }

    private ImageView imv;
    private TextView tv,tv_title;
    private DatumMelody datumMelody;

    private Button btn_Sub,btn_unsub;

    private BarVisualizer bar_visualizer;
    private String audio;

    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_melody_detail);
        //datumMelody=(DatumMelody)getIntent().getSerializableExtra("MyClass");



        Bundle bundle = getIntent().getExtras();
        //datumMelody = (DatumMelody)bundle.getSerializable("MyClass");

        imv=findViewById(R.id.imv);
        tv=findViewById(R.id.tv);
        tv_title=findViewById(R.id.tv_title);
        btn_Sub=findViewById(R.id.btn_Sub);
        btn_unsub=findViewById(R.id.btn_unsub);

        bar_visualizer=findViewById(R.id.bar_visualizer);


        btn_Sub.setOnClickListener(this);
        btn_unsub.setOnClickListener(this);

        tv_title.setText(bundle.getString("item"));

        audio=bundle.getString("audio");

        Log.d("MelodyDetailActivity------",""+audio);

        mPlayer = new MediaPlayer();


        /*int audioSessionId = mPlayer.getAudioSessionId();
        if (audioSessionId != -1)
            bar_visualizer.setAudioSessionId(audioSessionId);*/


        Glide.with(this)  //2
                .load(bundle.getString("icon")) //3
                .centerCrop() //4
                //.circleCrop()
                //.placeholder(R.drawable.img_asiacell_logo) //5//7
                .into(imv);
        tv.setText(bundle.getString("description"));


        try{
                    /*
                        void setDataSource (String path)
                            Sets the data source (file-path or http/rtsp URL) to use.

                        Parameters
                            path String : the path of the file, or the http/rtsp URL of the stream you want to play

                        Throws
                            IllegalStateException : if it is called in an invalid state

                                When path refers to a local file, the file may actually be opened by a
                                process other than the calling application. This implies that the
                                pathname should be an absolute path (as any other process runs with
                                unspecified current working directory), and that the pathname should
                                reference a world-readable file. As an alternative, the application
                                could first open the file for reading, and then use the file
                                descriptor form setDataSource(FileDescriptor).

                            IOException
                            IllegalArgumentException
                            SecurityException
                    */
            // Set the audio data source
            mPlayer.setDataSource(audio);

                    /*
                        void prepare ()
                            Prepares the player for playback, synchronously. After setting the
                            datasource and the display surface, you need to either call prepare()
                            or prepareAsync(). For files, it is OK to call prepare(), which blocks
                            until MediaPlayer is ready for playback.

                        Throws
                            IllegalStateException : if it is called in an invalid state
                            IOException
                    */
            // Prepare the media player
            mPlayer.prepare();

            // Start playing audio from http url
            mPlayer.start();

            // Inform user for audio streaming
            Toast.makeText(this,"Playing", Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            // Catch the exception
            e.printStackTrace();
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }catch (SecurityException e){
            e.printStackTrace();
        }catch (IllegalStateException e){
            e.printStackTrace();
        }

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Toast.makeText(getApplicationContext(),"End",Toast.LENGTH_SHORT).show();
                //mButtonPlay.setEnabled(true);
            }
        });


    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent("android.intent.action.VIEW");

        Uri data = Uri.parse("sms:");

        switch (v.getId()){
            case R.id.btn_Sub:
                intent.setData(data);
                startActivity(intent);
                break;
            case R.id.btn_unsub:
                intent.setData(data);
                startActivity(intent);
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bar_visualizer != null)
            bar_visualizer.release();
    }
}