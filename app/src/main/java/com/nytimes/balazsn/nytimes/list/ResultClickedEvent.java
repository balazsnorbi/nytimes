package com.nytimes.balazsn.nytimes.list;

import com.nytimes.balazsn.pojo.Result;

public class ResultClickedEvent {
    private Result mResult;

    public ResultClickedEvent(Result result) {
        this.mResult = result;
    }

    public Result getResult() {
        return mResult;
    }
}
