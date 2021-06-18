package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.List;

public class newsAdapter extends RecyclerView.Adapter<newsAdapter.ViewHolder>  {
private List<News> newsList;
    private  Response.Listener<JSONObject> context;


public newsAdapter(List<News> newsList,  Response.Listener<JSONObject> context) {
        this.newsList = newsList;
        this.context = context;
        }

@NonNull
@Override
public newsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.news_item,parent,false);
        return new ViewHolder(v);
        }

@Override
public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News news =newsList.get(position);
        holder.title.setText(news.getTitle());
        holder.description.setText(news.getDescription());
        holder.publishedAt.setText(news.getPublishedAt());


        }

@Override
public int getItemCount() {
        return newsList.size();
        }
public  class ViewHolder extends RecyclerView.ViewHolder{
    public TextView title;
    public TextView description;
    public TextView publishedAt;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.titleTV);
        description = (TextView) itemView.findViewById(R.id.descriptionTV);
        publishedAt = (TextView) itemView.findViewById(R.id.publishedAtTV);


    }


}


}