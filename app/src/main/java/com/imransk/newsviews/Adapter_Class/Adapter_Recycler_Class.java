package com.imransk.newsviews.Adapter_Class;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.imransk.newsviews.Fragment.WebView_Details;
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
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_card,null);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setTitle("Action ...");
                builder1.setMessage("Do you Want to open Browser ?");
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(articleList.get(i).getUrl()));
                                context.startActivity(browserIntent);
                            }
                        });
                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Fragment fragment=new WebView_Details();
                                FragmentTransaction fragmentManager = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();

                                Bundle bundle=new Bundle();
                                bundle.putString("url", articleList.get(i).getUrl());
                                fragment.setArguments(bundle);
                                fragmentManager.addToBackStack("");
                                fragmentManager.replace(R.id.screen_area, fragment);

                                fragmentManager.commit();

                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
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

   /* private void alertDailog_Exits() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setTitle("Alert...");
        builder1.setMessage("Do you Want to Exit ?");
        builder1.setCancelable(false);
        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }*/

}
