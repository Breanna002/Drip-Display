package com.example.dripdisplay;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {

        ParseObject.registerSubclass(Post.class);
        super.onCreate();
        // Register your parse models

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("YyXWDcOT35QDLfj9BNZ2wA64EfKtmMto0oMFmCXT")
                .clientKey("Ly2ArkFzha3ylLtteko2lGJxjaTxkaHwXSdzYZmu")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
