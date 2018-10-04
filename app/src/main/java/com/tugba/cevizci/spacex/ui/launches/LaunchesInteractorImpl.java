package com.tugba.cevizci.spacex.ui.launches;

import com.tugba.cevizci.spacex.data.Launch;
import com.tugba.cevizci.spacex.network.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class LaunchesInteractorImpl implements LaunchesInteractor {

    List<Launch> list;

    @Override
    public void getLaunches(final GetLaunchesListener listener) {

        NetworkService.Service().getLaunches().enqueue(new Callback<List<Launch>>() {
            @Override
            public void onResponse(Call<List<Launch>> call, Response<List<Launch>> response) {
                list = response.body();
                listener.onGetLaunchesSuccess(response);
            }

            @Override
            public void onFailure(Call<List<Launch>> call, Throwable t) {
                listener.onGetLaunchesFailed(t,t.getMessage());
            }
        });
    }
}