package com.nytimes.balazsn.nytimes.remote;

import com.nytimes.balazsn.pojo.Result;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class DataManager {

    private String mSection;

    private Integer mTimePeriod;

    private String mApiKey;

    private ApiInterface mApiInterface;

    private List<Result> mResultList = new ArrayList<>();

    public DataManager(Retrofit client, String section, Integer timePeriod, String apiKey) {
        mApiInterface = client.create(ApiInterface.class);
        mSection = section;
        mTimePeriod = timePeriod;
        mApiKey = apiKey;
    }

    public List<Result> getResultList() {
        return mResultList;
    }

    public Disposable getDataAsync(IDataProgressListener listener) {

        return mApiInterface.getMostPopularArticles(mSection, mTimePeriod, mApiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((subscription) -> listener.onSubscribe())
                .subscribe(nyTimes -> mResultList = nyTimes.getResults(), listener::onError, listener::onComplete);
    }
}