package com.nytimes.balazsn.nytimes.di;

import com.nytimes.balazsn.nytimes.list.view.ListActivity;
import com.nytimes.balazsn.nytimes.splashscreen.view.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules={AppModule.class, NetModule.class, VMFactoryModule.class, AdapterFactoryModule.class, UtilsModule.class})
public interface AppComponent {

    void inject(SplashActivity activity);

    void inject(ListActivity activity);
}
