package com.nytimes.balazsn.nytimes.di;

import com.nytimes.balazsn.nytimes.delay.Delay;
import com.nytimes.balazsn.nytimes.list.model.ListActivityVM;
import com.nytimes.balazsn.nytimes.splashscreen.model.SplashActivityVM;
import com.nytimes.balazsn.nytimes.remote.DataManager;

import dagger.Module;
import dagger.Provides;

@Module
public class VMFactoryModule {

    @Provides
    SplashActivityVM provideSplashActivityVM(Delay delay) {
        return new SplashActivityVM(delay);
    }

    @Provides
    ListActivityVM provideListActivityVM(DataManager dataManager) {
        return new ListActivityVM(dataManager);
    }
}
