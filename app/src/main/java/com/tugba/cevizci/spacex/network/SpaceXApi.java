package com.tugba.cevizci.spacex.network;

import com.tugba.cevizci.spacex.data.Launch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SpaceXApi {

    @GET("launches/")
    Call<List<Launch>> getLaunches();
}

