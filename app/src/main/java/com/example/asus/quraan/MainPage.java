package com.example.asus.quraan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
    }

    public void Browse(View view){
        Intent browse = new Intent(this,Browse.class);
        startActivity(browse);
    }

    public void app_settings(View view){
        Intent App_settings_intent = new Intent(this,appSettings.class);
        startActivity(App_settings_intent);
    }
}
