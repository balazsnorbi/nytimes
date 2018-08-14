package com.nytimes.balazsn.nytimes.delay;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class Delay {

    public Disposable delay(long delay, TimeUnit timeUnit, IDelayCallback listener) {
        return Completable.timer(delay, timeUnit)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(listener::onDelayExpired);
    }
}
