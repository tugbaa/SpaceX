package com.tugba.cevizci.spacex.ui.launches;

import com.tugba.cevizci.spacex.data.Launch;

import java.util.List;

import retrofit2.Response;

public interface LaunchesInteractor {

    void getLaunches(GetLaunchesListener listener);

    interface GetLaunchesListener {

        void onGetLaunchesSuccess(Response<List<Launch>> response);

        void onGetLaunchesFailed(Throwable e, String errorMessage);
    }
}
