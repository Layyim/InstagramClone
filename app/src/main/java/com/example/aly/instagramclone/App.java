package com.example.aly.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("TFcgHayfN7Db1qWR954ja9qKDAGjDDsW01xvDNTg")
                // if defined
                .clientKey("z0UuZo1DSnSyqWzYPgdNpDzy2MRnC6CFT3FR74Us")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
