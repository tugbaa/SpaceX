package com.tugba.cevizci.spacex.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tugba.cevizci.spacex.R;
import com.tugba.cevizci.spacex.data.Launch;
import com.tugba.cevizci.spacex.ui.base.BaseActivity;
import com.tugba.cevizci.spacex.utils.DateUtils;

import butterknife.BindView;

public class LaunchDetailActivity extends BaseActivity {

    @BindView(R.id.iv_launch)
    ImageView ivLaunch;

    @BindView(R.id.txt_mission_name)
    TextView tvMissionName;

    @BindView(R.id.txt_flight_number)
    TextView tvFlightNumber;

    @BindView(R.id.txt_launch_date)
    TextView tvLaunchDate;

    @BindView(R.id.txt_detail)
    TextView tvDetail;

    private static Launch launch;

    public static Intent newIntent(Context context, Launch launch) {
        LaunchDetailActivity.launch = launch;

        return new Intent(context, LaunchDetailActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {
        Picasso.with(this).load(launch.links.missionPatch).noFade().into(ivLaunch);

        tvMissionName.setText(String.format("Mission Name: %s", launch.missionName));

        tvFlightNumber.setText(String.format("Flight Number: %s", launch.flightNumber.toString()));

        tvLaunchDate.setText(String.format("Launch Date: %s", DateUtils.convertToDate(launch.launchDateLocal)));

        tvDetail.setText(String.format("Details: %s", launch.details));
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_launch_detail;
    }
}
