package com.metalwihen.sample.gitem.core;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.metalwihen.sample.gitem.BuildConfig;

/**
 * Created on 16/04/18.
 */

public class GitEmApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            com.facebook.stetho.Stetho.initializeWithDefaults(this);
        }

        if (TextUtils.isEmpty(GithubCredentials.PERSONAL_ACCESS_TOKEN) || TextUtils.isEmpty(GithubCredentials.USERNAME)) {
            Toast.makeText(this, "Please specify your Username and Personal Access Token to use app without rate limits", Toast.LENGTH_LONG).show();
            Log.e("GIT 'EM", "Please specify your Username and Personal Access Token to use app without rate limits");
        }
    }
}
