package com.tugba.cevizci.spacex.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static final String BASE_URL = "https://api.spacexdata.com/v2/";

    private static SpaceXApi service;

    private NetworkService() {
    }

    public static SpaceXApi Service() {

        if (service == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(SpaceXApi.class);
        }

        return service;
    }
}
