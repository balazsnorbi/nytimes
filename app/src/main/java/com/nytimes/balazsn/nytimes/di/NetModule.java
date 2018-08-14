package com.nytimes.balazsn.nytimes.di;

import com.nytimes.balazsn.nytimes.remote.DataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {

    private String mBaseURL;

    private String mSection;

    private Integer mTimePeriod;

    private String mApiKey;

    public NetModule(String baseURL, String section, Integer timePeriod, String apiKey) {
        mBaseURL = baseURL;
        mSection = section;
        mTimePeriod = timePeriod;
        mApiKey = apiKey;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(mBaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }

    @Provides
    @Singleton
    DataManager provideDataManager(Retrofit client) {
        return new DataManager(client, mSection, mTimePeriod, mApiKey);
    }
}
