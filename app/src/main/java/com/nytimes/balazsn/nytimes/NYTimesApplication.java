package com.nytimes.balazsn.nytimes;

import android.app.Application;
import android.content.Context;

import com.nytimes.balazsn.nytimes.di.UtilsModule;
import com.nytimes.balazsn.nytimes.remote.DataManager;
import com.nytimes.balazsn.nytimes.di.AdapterFactoryModule;
import com.nytimes.balazsn.nytimes.di.AppComponent;
import com.nytimes.balazsn.nytimes.di.AppModule;
import com.nytimes.balazsn.nytimes.di.DaggerAppComponent;
import com.nytimes.balazsn.nytimes.di.NetModule;
import com.nytimes.balazsn.nytimes.di.VMFactoryModule;

import javax.inject.Inject;

public class NYTimesApplication extends Application{

    private static final String BASE_URL = "https://api.nytimes.com/svc/";

    private static final String SECTIONS = "all-sections";

    private static final Integer TIME_PERIOD = 7;

    private static final String API_KEY = "ad10c7fbdc244ff880b4cb8bf45ff152";

    private AppComponent mAppComponent;

    @Inject
    protected DataManager mDataManager;

    @Inject
    protected Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        // Dagger%COMPONENT_NAME%
        mAppComponent = DaggerAppComponent.builder()
                .adapterFactoryModule(new AdapterFactoryModule())
                .appModule(new AppModule(this))
                .netModule(new NetModule(BASE_URL, SECTIONS, TIME_PERIOD, API_KEY))
                .utilsModule(new UtilsModule())
                .vMFactoryModule(new VMFactoryModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}
