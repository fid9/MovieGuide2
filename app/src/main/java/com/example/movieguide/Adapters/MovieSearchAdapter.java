package com.example.movieguide.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieguide.Model.MovieResponseResults;
import com.example.movieguide.MovieDetailActivity;
import com.example.movieguide.R;
import com.example.movieguide.ViewHolders.SearchViewHolder;

import java.util.List;

@SuppressWarnings("unused")

public class MovieSearchAdapter extends RecyclerView.Adapter<SearchViewHolder>
    {
        private Activity activity;
        private List<MovieResponseResults> results;

        public MovieSearchAdapter(Activity activity, List<MovieResponseResults> results) {
            this.activity = activity;
            this.results = results;
        }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(activity).inflate(R.layout.search_layout_items, viewGroup, false);


            return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder searchViewHolder, int i) {

            final MovieResponseResults responseResults = results.get(i);

        searchViewHolder.setPosterImageView(activity, responseResults.getPoster_path());

        String title = responseResults.getTitle();

        if (title != null){
            searchViewHolder.posterTitle.setVisibility(View.VISIBLE);
            searchViewHolder.posterTitle.setText(title);

        }
        else {
            searchViewHolder.posterTitle.setVisibility(View.GONE);

        }

        searchViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(activity, MovieDetailActivity.class);
                intent.putExtra("id", String.valueOf(responseResults.getId()));
                activity.startActivity(intent);

                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

    }

    @Override
    public int getItemCount() {
        return results.size();
    }
}
