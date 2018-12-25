package com.imransk.newsviews.Adapter_Class;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.imransk.newsviews.POJO_Class.Response_Pojo;
import com.imransk.newsviews.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_Recycler_Class extends RecyclerView.Adapter<Adapter_Recycler_Class.MyViewHolder> {

    private List<Response_Pojo.Article> articleList;
    Context context;

    public Adapter_Recycler_Class(List<Response_Pojo.Article> articleList,Context context) {
        this.articleList = articleList;
        this.context= context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_card,null);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

       myViewHolder.title.setText(articleList.get(i).getTitle());
        myViewHolder.date.setText(articleList.get(i).getPublishedAt());
        myViewHolder.wesite_name.setText(articleList.get(i).getSource().getName());

        Picasso.get()
                .load(articleList.get(i).getUrlToImage())
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading)
                .into(myViewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,date,wesite_name;
        ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            title=view.findViewById(R.id.title_item_ID);
            date = view.findViewById(R.id.date_item_TV_id);
            wesite_name = view.findViewById(R.id.website_name_TV_ID);
            imageView= view.findViewById(R.id.image_item_ID);

        }
    }

}
