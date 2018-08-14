package com.nytimes.balazsn.nytimes.common;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nytimes.balazsn.nytimes.R;

import org.greenrobot.eventbus.EventBus;

@SuppressLint("Registered")
public abstract class BaseActivity extends AppCompatActivity{

    private boolean mUseEventBus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
        mUseEventBus = getUseEventBus();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mUseEventBus) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mUseEventBus) {
            EventBus.getDefault().unregister(this);
        }
    }

    protected abstract boolean getUseEventBus();
}
