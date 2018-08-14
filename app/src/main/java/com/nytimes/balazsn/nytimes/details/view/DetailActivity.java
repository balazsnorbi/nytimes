package com.nytimes.balazsn.nytimes.details.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nytimes.balazsn.nytimes.common.BaseActivity;
import com.nytimes.balazsn.nytimes.common.Constants;
import com.nytimes.balazsn.nytimes.NYTimesApplication;
import com.nytimes.balazsn.nytimes.R;
import com.nytimes.balazsn.nytimes.databinding.ActivityDetailBinding;
import com.nytimes.balazsn.pojo.Result;

import javax.inject.Inject;

public class DetailActivity extends BaseActivity {

    @Inject
    protected Gson gson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((NYTimesApplication) getApplication()).getAppComponent().inject(this);

        ActivityDetailBinding mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        Result result = getBundleExtra();
        if (result != null) {
            mBinding.webView.loadUrl(result.getUrl());
        } else {
            Toast.makeText(this, "No content provided", Toast.LENGTH_SHORT).show();
        }
    }

    private Result getBundleExtra() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return null;
        }
        String content = bundle.getString(Constants.RESULT_BUNDLE_KEY);
        return content != null ? gson.fromJson(bundle.getString(Constants.RESULT_BUNDLE_KEY), Result.class) : null;
    }

    @Override
    protected boolean getUseEventBus() {
        return false;
    }
}
