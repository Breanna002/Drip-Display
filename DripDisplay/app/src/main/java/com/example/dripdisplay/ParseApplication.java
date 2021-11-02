package com.example.dripdisplay;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Register your parse models

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("uHk9w4Hmmq3wH78qaQEAqZFLENIQ5qEsGncVnJXP")
                .clientKey("6gQSvkSr8NO7aF767ypecrWvZtwb8W6pieHw4xal")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
