package com.nytimes.balazsn.nytimes.list;

import android.support.v7.util.SortedList;

import com.nytimes.balazsn.nytimes.list.view.ListAdapter;
import com.nytimes.balazsn.pojo.Result;

public class SortingCriteria extends SortedList.Callback<Result> {

    private ListAdapter mAdapter;

    public SortingCriteria(ListAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public void onInserted(int position, int count) {
        mAdapter.notifyItemRangeInserted(position, count);
    }

    @Override
    public void onRemoved(int position, int count) {
        mAdapter.notifyItemRangeRemoved(position, count);
    }

    @Override
    public void onMoved(int fromPosition, int toPosition) {
        mAdapter.notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public int compare(Result result, Result t21) {
        return result.getTitle().compareTo(t21.getTitle());
    }

    @Override
    public void onChanged(int position, int count) {
        mAdapter.notifyItemRangeChanged(position, count);
    }

    @Override
    public boolean areContentsTheSame(Result result, Result t21) {
        return result.getTitle().compareTo(t21.getTitle()) == 0
                && result.getByline().compareTo(t21.getByline()) == 0
                && result.getPublishedDate().compareTo(t21.getPublishedDate()) == 0;
    }

    @Override
    public boolean areItemsTheSame(Result result, Result t21) {
        return result.getTitle().compareTo(t21.getTitle()) == 0
                && result.getByline().compareTo(t21.getByline()) == 0
                && result.getPublishedDate().compareTo(t21.getPublishedDate()) == 0;
    }
}
