package com.imransk.newsviews.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.imransk.newsviews.Adapter_Class.Adapter_Recycler_Class;
import com.imransk.newsviews.ApiClass.Api;
import com.imransk.newsviews.ApiClass.RetofitClient_SingleTone;
import com.imransk.newsviews.JavaCLass.CheckNetwork;
import com.imransk.newsviews.POJO_Class.Response_Pojo;
import com.imransk.newsviews.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    SwipeRefreshLayout swipeRefreshLayout;
    ProgressBar progressBar;
    Context context;
    String base_URL = "https://newsapi.org/v2/";
    String[] news_site_name = {"cnbc", "abc-news", "aftenposten", "ars-technica", "argaam", "bild",
            "bbc-sport", "cbs-news", "die-zeit", "engadget"};

    List<Response_Pojo.Article> articleList;

    List<List<Response_Pojo.Article>> allList = new ArrayList<>();

    RecyclerView recyclerView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Home Page");
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        return inflater.inflate(R.layout.home_fragment, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefreshLayout = view.findViewById(R.id.swipe_To_refresh_ID);
        recyclerView = view.findViewById(R.id.recycler_View_ID);
        progressBar = view.findViewById(R.id.progress_bar_ID);

        if (!CheckNetwork.isInternetAvailable(context)) {
            Toast.makeText(context, "No Connection", Toast.LENGTH_SHORT).show();
        }else {

            loadJSON_Data();
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        loadJSON_Data();
                        Toast.makeText(context, "Loaded..", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private void loadJSON_Data() {

        RetofitClient_SingleTone retofitClient_singleTone = RetofitClient_SingleTone.getInstance();


        Api api = retofitClient_singleTone.getApi();


        String end_url = "top-headlines?sources=abc-news&apiKey=4969854e2d424ed9972370f709ace9cc";
        Call<Response_Pojo> call = api.getAllResponse(end_url);

        call.enqueue(new Callback<Response_Pojo>() {
            @Override
            public void onResponse(Call<Response_Pojo> call, Response<Response_Pojo> response) {
                if (response.isSuccessful()) {

                    Response_Pojo response_pojo = response.body();
                    articleList = response_pojo.getArticles();
                    Adapter_Recycler_Class adapter_recycler_class = new Adapter_Recycler_Class(articleList, context);
                    recyclerView.setAdapter(adapter_recycler_class);

                    swipeRefreshLayout.setRefreshing(false);
                    progressBar.setVisibility(View.GONE);
                   swipeRefreshLayout.setVisibility(View.VISIBLE);

                }
            }
            @Override
            public void onFailure(Call<Response_Pojo> call, Throwable t) {
                Log.e("Home Failed - -", "Failed: " + t.getMessage());
                Toast.makeText(context, "Failed - - " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
