package com.imransk.newsviews.ApiClass;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetofitClient_SingleTone {

    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static RetofitClient_SingleTone mInstance;
    private Retrofit retrofit;

    private RetofitClient_SingleTone() {
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized  RetofitClient_SingleTone getInstance(){
        if (mInstance==null){
            mInstance = new RetofitClient_SingleTone();
        }
        return mInstance;
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }
}

























