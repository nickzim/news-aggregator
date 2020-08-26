package com.nickzim.newsaggregator.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nickzim.newsaggregator.model.News;
import com.nickzim.newsaggregator.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    public interface onClickListener{
        void onClick(News news);
    }

    private onClickListener listener;

    private LayoutInflater inflater;
    private List<News> news;

    public NewsAdapter(List<News> news, Context context) {
        this.news = news;
        this.inflater = LayoutInflater.from(context);
    }

    public void setListener(onClickListener listener){
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleView;
        private final TextView descriptionView;
        private final TextView dateView;
        private final TextView categoryView;

        ViewHolder(View view){
            super(view);
            titleView =  view.findViewById(R.id.title);
            descriptionView = view.findViewById(R.id.description);
            dateView = view.findViewById(R.id.date);
            categoryView = view.findViewById(R.id.category);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final News selectNews = news.get(i);
        viewHolder.titleView.setText(selectNews.getTitle());
        viewHolder.descriptionView.setText(selectNews.getDescription());
        viewHolder.dateView.setText(selectNews.getPubDate());
        viewHolder.categoryView.setText(selectNews.getCategory());

        if(listener != null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(selectNews);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return news.size();
    }
}
