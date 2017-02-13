package com.rxjava;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/2/10.
 */

public interface httpGetMyBuyBooks {
    @GET("/ellabook-server/mybook/")
    Call<MyResponse> getString(@Query("memberId") String memberId,
                           @Query("page") String page,
                           @Query("pagesize") String pagesize,
                           @Query("resource") String resource);
}