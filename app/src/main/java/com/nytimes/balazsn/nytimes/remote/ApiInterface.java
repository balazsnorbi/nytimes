package com.nytimes.balazsn.nytimes.remote;

import com.nytimes.balazsn.pojo.NYTimes;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by nbalazs on 15/01/2018.
 */

public interface ApiInterface {

    @GET("mostpopular/v2/mostviewed/{section}/{time-period}.json")
    Flowable<NYTimes> getMostPopularArticles(@Path("section") String name, @Path("time-period") Integer timePeriod, @Query("api-key") String key);
}
