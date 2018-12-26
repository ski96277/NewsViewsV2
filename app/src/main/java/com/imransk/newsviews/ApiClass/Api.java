package com.imransk.newsviews.ApiClass;


import com.imransk.newsviews.POJO_Class.Response_Pojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Api {



    @GET()
    Call<Response_Pojo> getAllResponse(@Url String url);

   /*
    @GET("top-headlines?sources=abc-news&apiKey=4969854e2d424ed9972370f709ace9cc")
    Call<Response_Pojo> getAllResponse();
   */

}
