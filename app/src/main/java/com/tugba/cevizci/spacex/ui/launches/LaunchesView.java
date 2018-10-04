package com.tugba.cevizci.spacex.ui.launches;

import com.tugba.cevizci.spacex.data.Launch;
import com.tugba.cevizci.spacex.ui.base.BaseView;

import java.util.List;

public interface LaunchesView extends BaseView {

    void bindLaunches(List<Launch> launches);
}
