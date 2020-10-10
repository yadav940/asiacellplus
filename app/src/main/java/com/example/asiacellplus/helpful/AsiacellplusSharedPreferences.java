package com.example.asiacellplus.helpful;

import android.content.Context;
import android.content.SharedPreferences;

public class AsiacellplusSharedPreferences  {

    SharedPreferences sharedpreferences ;
    Context context;
    final private String ASIACELL_PREFERENCES="ASIACELL_PREFERENCES";
    final private String LANGUAGE_KEY="LANGUAGE_KEY";
    final private String NOTIFICATION_KEY="NOTIFICATION_KEY";

    public AsiacellplusSharedPreferences(Context context) {
        this.context = context;
        sharedpreferences=context.getSharedPreferences(ASIACELL_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void setLanguage(int i){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt(LANGUAGE_KEY, i);
        editor.commit();
    }
    public int getLanguage(){
        return sharedpreferences.getInt(LANGUAGE_KEY,1);
    }
    public void setNotificationEnable(boolean s){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(NOTIFICATION_KEY, s);
        editor.commit();
    }
    public boolean getNotificationEnable(){
        return sharedpreferences.getBoolean(NOTIFICATION_KEY,false);
    }



}
