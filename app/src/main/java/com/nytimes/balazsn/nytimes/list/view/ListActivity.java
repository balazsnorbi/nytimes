package com.nytimes.balazsn.nytimes.list.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.nytimes.balazsn.nytimes.common.BaseActivity;
import com.nytimes.balazsn.nytimes.NYTimesApplication;
import com.nytimes.balazsn.nytimes.R;
import com.nytimes.balazsn.nytimes.databinding.ActivityListBinding;
import com.nytimes.balazsn.nytimes.list.model.ListActivityVM;
import com.nytimes.balazsn.nytimes.list.NewDataSourceAvailableEvent;
import com.nytimes.balazsn.nytimes.list.ResultClickedEvent;
import com.nytimes.balazsn.nytimes.list.SpaceItemDecoration;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

public class ListActivity extends BaseActivity{

    @Inject
    protected Gson gson;

    @Inject
    protected ListActivityVM mViewModel;

    @Inject
    protected ListAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((NYTimesApplication) getApplication()).getAppComponent().inject(this);

        ActivityListBinding mBinding = DataBindingUtil.setContentView(this, R.layout.activity_list);
        mBinding.setViewModel(mViewModel);

        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.setAdapter(mAdapter);
        mBinding.recyclerView.addItemDecoration(new SpaceItemDecoration(12));

        mViewModel.onCreate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(mAdapter);

        return true;
    }

    @Override
    protected boolean getUseEventBus() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mViewModel.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(NewDataSourceAvailableEvent event) {
        mAdapter.updateResultList();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ResultClickedEvent event) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(event.getResult().getUrl()));
        startActivity(intent);
    }
}
