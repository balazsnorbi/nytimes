package com.nytimes.balazsn.nytimes.remote;

public interface IDataProgressListener {

    void onSubscribe();

    void onError(Throwable throwable);

    void onComplete();
}
