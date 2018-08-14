package com.nytimes.balazsn.nytimes.di;

import android.content.Context;

import com.nytimes.balazsn.nytimes.remote.DataManager;
import com.nytimes.balazsn.nytimes.list.view.ListAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class AdapterFactoryModule {

    @Provides
    ListAdapter provideListAdapter(Context context, DataManager dataManager) {
        return new ListAdapter(context, dataManager);
    }
}
