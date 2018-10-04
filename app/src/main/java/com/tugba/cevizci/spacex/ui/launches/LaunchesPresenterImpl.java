package com.tugba.cevizci.spacex.ui.launches;

import com.tugba.cevizci.spacex.data.Launch;

import java.util.List;

import retrofit2.Response;

class LaunchesPresenterImpl implements LaunchesPresenter, LaunchesInteractor.GetLaunchesListener {

    private LaunchesView view;
    private LaunchesInteractor interactor;

    public LaunchesPresenterImpl(LaunchesView view, LaunchesInteractor interactorr) {
        this.view = view;
        this.interactor = interactorr;
    }

    @Override
    public void getLaunches() {
        interactor.getLaunches(this);
    }

    @Override
    public void onGetLaunchesSuccess(Response<List<Launch>> response) {
        view.bindLaunches(response.body());
    }

    @Override
    public void onGetLaunchesFailed(Throwable e, String errorMessage) {
        view.showErrorMessage(errorMessage);
    }
}
