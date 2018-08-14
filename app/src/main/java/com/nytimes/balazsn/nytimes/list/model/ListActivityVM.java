package com.nytimes.balazsn.nytimes.list.model;

import android.databinding.ObservableBoolean;

import com.nytimes.balazsn.nytimes.list.NewDataSourceAvailableEvent;
import com.nytimes.balazsn.nytimes.remote.IDataProgressListener;
import com.nytimes.balazsn.nytimes.remote.DataManager;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class ListActivityVM implements IDataProgressListener {

    @Inject
    protected DataManager mDataManager;

    private Disposable mDisposable;

    public final ObservableBoolean isRefreshing = new ObservableBoolean();

    @Inject
    public ListActivityVM(DataManager dataManager) {
        mDataManager = dataManager;
    }

    public void onCreate() {
        refreshData();
    }

    public void onDestroy() {
        if (mDisposable!= null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    public void refreshData() {
        if (mDisposable!= null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        mDisposable = mDataManager.getDataAsync(this);
    }

    @Override
    public void onSubscribe() {
        isRefreshing.set(true);
    }

    @Override
    public void onError(Throwable throwable) {
        isRefreshing.set(false);
    }

    @Override
    public void onComplete() {
        isRefreshing.set(false);
        EventBus.getDefault().post(new NewDataSourceAvailableEvent());
    }
}
