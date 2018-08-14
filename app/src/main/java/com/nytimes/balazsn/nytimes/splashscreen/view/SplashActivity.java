package com.nytimes.balazsn.nytimes.splashscreen.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.nytimes.balazsn.nytimes.common.BaseActivity;
import com.nytimes.balazsn.nytimes.splashscreen.GoToActivityEvent;
import com.nytimes.balazsn.nytimes.NYTimesApplication;
import com.nytimes.balazsn.nytimes.R;
import com.nytimes.balazsn.nytimes.databinding.ActivitySplashBinding;
import com.nytimes.balazsn.nytimes.splashscreen.model.SplashActivityVM;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import io.reactivex.annotations.Nullable;

public class SplashActivity extends BaseActivity {

    @Inject
    protected SplashActivityVM mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((NYTimesApplication)getApplication()).getAppComponent().inject(this);

        ActivitySplashBinding mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        mBinding.setViewModel(mViewModel);

        mViewModel.onCreate();
    }

    @Override
    protected boolean getUseEventBus() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModel.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(GoToActivityEvent event) {
        startActivity(new Intent(this, event.getmNextClass()));
        finish();
    }
}