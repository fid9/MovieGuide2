package com.example.movieguide.ViewHolders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.example.movieguide.R;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import com.squareup.picasso.Picasso;

public class SearchViewHolder extends RecyclerView.ViewHolder
{
    private KenBurnsView posterImageView;
    public AppCompatTextView posterTitle;


    public SearchViewHolder(@NonNull View itemView)
    {
        super(itemView);
        posterImageView = itemView.findViewById(R.id.poster_image_view);
        posterTitle = itemView.findViewById(R.id.poster_title);

        RandomTransitionGenerator generator = new RandomTransitionGenerator(1000, new DecelerateInterpolator());
        posterImageView.setTransitionGenerator(generator);
    }

    public void setPosterImageView(Context context, String posterUrl)
    {
        Picasso.with(context).load(posterUrl).into(posterImageView);
    }
}
