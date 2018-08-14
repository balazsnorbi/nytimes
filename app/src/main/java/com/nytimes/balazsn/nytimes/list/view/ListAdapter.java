package com.nytimes.balazsn.nytimes.list.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nytimes.balazsn.nytimes.R;
import com.nytimes.balazsn.nytimes.list.ResultClickedEvent;
import com.nytimes.balazsn.nytimes.list.SortingCriteria;
import com.nytimes.balazsn.nytimes.remote.DataManager;
import com.nytimes.balazsn.nytimes.databinding.ListRowBinding;
import com.nytimes.balazsn.pojo.Result;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemViewHolder> implements SearchView.OnQueryTextListener{

    private final SortedList<Result> mSortedList = new SortedList<>(Result.class, new SortingCriteria(this));

    @Inject
    protected DataManager mDataManager;

    @Inject
    protected LayoutInflater mInflater;

    private List<Result> mResultList;

    @Inject
    public ListAdapter(Context context, DataManager dataManager) {
        mInflater = LayoutInflater.from(context);
        mDataManager = dataManager;
        mResultList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ListRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.list_row, viewGroup, false);
        return new ItemViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.update(mSortedList.get(i));
    }

    @Override
    public int getItemCount() {
        return mSortedList.size();
    }

    public void updateResultList() {
        mResultList = mDataManager.getResultList();
        mSortedList.beginBatchedUpdates();
        for (int i = mSortedList.size() - 1; i >= 0; i--) {
            final Result model = mSortedList.get(i);
            if (!mResultList.contains(model)) {
                mSortedList.remove(model);
            }
        }
        mSortedList.addAll(mResultList);
        mSortedList.endBatchedUpdates();
    }

    private void filterResultList(String query) {
        final String lowerCaseQuery = query.toLowerCase();
        final List<Result> filteredModelList = new ArrayList<>();
        for (Result model : mResultList) {
            final String text = model.getTitle().toLowerCase();
            if (text.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }
        mSortedList.replaceAll(filteredModelList);
    }

    @Override
    public boolean onQueryTextChange(String query) {
        filterResultList(query);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ListRowBinding mBinding;

        private Result mResult;

        ItemViewHolder(@NonNull ListRowBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void update(Result result) {
            mResult = result;

            mBinding.date.setText(result.getPublishedDate());
            mBinding.title.setText(result.getTitle());
            mBinding.author.setText(result.getByline());

            mBinding.date.setOnClickListener(this);
            mBinding.title.setOnClickListener(this);
            mBinding.author.setOnClickListener(this);
            mBinding.picture.setOnClickListener(this);
            mBinding.rightArrow.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            EventBus.getDefault().post(new ResultClickedEvent(mResult));
        }
    }
}
