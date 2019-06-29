package com.example.movieguide.ViewHolders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.movieguide.R;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.squareup.picasso.Picasso;

public class MovieCreditsViewHolder extends RecyclerView.ViewHolder {

    private KenBurnsView movieCreditsImageView;

    public AppCompatTextView movieCreditsName;
    public AppCompatTextView movieCreditsCharacter;

    public MovieCreditsViewHolder(@NonNull View itemView) {
        super(itemView);

        movieCreditsImageView = itemView.findViewById(R.id.movie_credits_image_view);
        movieCreditsName = itemView.findViewById(R.id.movie_credits_name);
        movieCreditsCharacter = itemView.findViewById(R.id.movie_credits_character);
    }

    public void setMovieCreditsImageView(Context context, String imageUrl) {
        Picasso.with(context).load(imageUrl).into(movieCreditsImageView);
    }
}
