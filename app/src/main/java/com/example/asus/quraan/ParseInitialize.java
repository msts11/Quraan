package com.example.asus.quraan;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.parse.Parse;
import com.parse.ParseInstallation;

public class ParseInitialize extends Application {


    public void onCreate() {
        Parse.initialize(this, "4Zq7ugmsgzwIWgyczr6oEQpd3mdTerqwI0j9OrbX", "ZPf6nqSWHXVdxZnPyAkyaUapse3C2iLtdXtQWpwS");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
