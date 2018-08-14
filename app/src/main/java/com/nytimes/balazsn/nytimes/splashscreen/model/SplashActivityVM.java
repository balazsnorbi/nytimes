package com.nytimes.balazsn.nytimes.splashscreen.model;

import com.nytimes.balazsn.nytimes.splashscreen.GoToActivityEvent;
import com.nytimes.balazsn.nytimes.list.view.ListActivity;
import com.nytimes.balazsn.nytimes.delay.Delay;
import com.nytimes.balazsn.nytimes.delay.IDelayCallback;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class SplashActivityVM implements IDelayCallback {

    @Inject
    protected Delay mDelay;

    private Disposable mDisposable;

    @Inject
    public SplashActivityVM(Delay delay) {
        mDelay = delay;
    }

    public final void onCreate() {

        mDisposable = mDelay.delay(1500, TimeUnit.MILLISECONDS, this);
    }

    public final void onDestroy() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @Override
    public void onDelayExpired() {
        EventBus.getDefault().post(new GoToActivityEvent(ListActivity.class));
    }
}
