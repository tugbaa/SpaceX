package com.tugba.cevizci.spacex.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.tugba.cevizci.spacex.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    @Nullable
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @Nullable
    @BindView(R.id.progress_layout)
    protected View progressLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
    }

    public abstract int getLayoutId();

    @Override
    public void showProgress() {
        if (progressLayout != null) {
            progressLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        if (progressLayout != null) {
            progressLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}

