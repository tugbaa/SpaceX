package com.tugba.cevizci.spacex.ui.launches;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.tugba.cevizci.spacex.R;
import com.tugba.cevizci.spacex.data.Launch;
import com.tugba.cevizci.spacex.ui.base.BaseActivity;
import com.tugba.cevizci.spacex.ui.detail.LaunchDetailActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LaunchesActivity extends BaseActivity implements LaunchesView, LaunchListAdapter.LaunchesListener {

    private static final String KEY_ALL = "ALL";

    @BindView(R.id.launch_list)
    RecyclerView rvLaunchList;

    @BindView(R.id.spinner_filter)
    Spinner spinnerFilter;

    private LaunchesPresenter presenter;
    private LaunchListAdapter adapter;
    private List<Launch> launches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new LaunchesPresenterImpl(this, new LaunchesInteractorImpl());

        rvLaunchList.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        presenter.getLaunches();

        launches = new ArrayList<>();

        bindLaunches(launches);
    }

    private void addItemsOnSpinner() {
        List<String> list = new ArrayList<>();
        list.add(KEY_ALL);

        if (!launches.isEmpty()) {
            for (int i = 0; i < launches.size(); i++) {
                if (!list.contains(launches.get(i).launchYear)) {
                    list.add(launches.get(i).launchYear);
                }
            }
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        addListenerOnSpinnerItemSelection();

        spinnerFilter.setAdapter(dataAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_launches;
    }

    private void addListenerOnSpinnerItemSelection() {

        spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).toString().equals(KEY_ALL)) {
                    adapter.updateLaunches(launches);
                } else {
                    filterByYear(launches, adapterView.getItemAtPosition(i).toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    @Override
    public void bindLaunches(List<Launch> launches) {
        showProgress();

        this.launches = launches;

        rvLaunchList.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false));

        adapter = new LaunchListAdapter(launches, this);

        rvLaunchList.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        addItemsOnSpinner();
    }

    @Override
    public void onLaunchClick(Launch clickedLaunch) {
        startActivity(LaunchDetailActivity.newIntent(this, clickedLaunch));
    }

    @OnClick(R.id.button_sort)
    public void onSortButtonClicked() {
        Collections.sort(launches, new Comparator<Launch>() {
            @Override
            public int compare(Launch launch1, Launch launch2) {
                return launch1.missionName.compareTo(launch2.missionName);
            }
        });
        rvLaunchList.setAdapter(adapter);
    }

    private void filterByYear(List<Launch> launches, String year) {
        if (launches != null) {

            List<Launch> launchesByYear = new ArrayList<>();

            for (Launch launch : launches) {
                if (launch.launchYear.equals(year)) {
                    launchesByYear.add(launch);
                }
            }

            adapter.updateLaunches(launchesByYear);
            rvLaunchList.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

    }
}
