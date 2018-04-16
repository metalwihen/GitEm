package com.metalwihen.sample.gitem.net;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ApiManager {

    private static final String URL = "https://api.github.com";

    private static ApiManager sApiManager = new ApiManager();

    private GithubService mGithubService;

    private ApiManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .client(
                        OkHttpClientFactory.getInstance())
                .addConverterFactory(
                        MoshiConverterFactory.create(
                                MoshiFactory.getInstance()))
                .addCallAdapterFactory(
                        RxJava2CallAdapterFactory.create())
                .build();

        mGithubService = retrofit.create(GithubService.class);
    }

    public static ApiManager getInstance() {
        return sApiManager;
    }

    public GithubService getGithubService() {
        return mGithubService;
    }
}
