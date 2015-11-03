package com.example.asus.quraan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.parse.Parse;
import com.parse.ParseInstallation;


public class MainScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Parse.initialize(this, "4Zq7ugmsgzwIWgyczr6oEQpd3mdTerqwI0j9OrbX", "ZPf6nqSWHXVdxZnPyAkyaUapse3C2iLtdXtQWpwS");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void Browse(View view){
        Intent browse = new Intent(this,Browse.class);
        startActivity(browse);
    }
    public void Login(View view){
        Intent login = new Intent(this,Login.class);
        startActivity(login);
    }

    public void app_settings(View view){
        Intent App_settings_intent = new Intent(this,appSettings.class);
        startActivity(App_settings_intent);
    }


}
