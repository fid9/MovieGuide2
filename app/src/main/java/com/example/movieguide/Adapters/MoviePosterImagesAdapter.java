package com.example.movieguide.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieguide.ImageViewerActivity;
import com.example.movieguide.Model.MovieImagesBackdropsAndPosters;
import com.example.movieguide.R;
import com.example.movieguide.ViewHolders.PersonProfileImagesViewHolder;

import java.util.List;

@SuppressWarnings("unused")
public class MoviePosterImagesAdapter extends RecyclerView.Adapter<PersonProfileImagesViewHolder>
{
    private Activity activity;
    private List<MovieImagesBackdropsAndPosters> movieImagesBackdropsAndPostersList;

    public MoviePosterImagesAdapter(Activity activity, List<MovieImagesBackdropsAndPosters> movieImagesBackdropsAndPostersList) {
        this.activity = activity;
        this.movieImagesBackdropsAndPostersList = movieImagesBackdropsAndPostersList;
    }

    @NonNull
    @Override
    public PersonProfileImagesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(activity).inflate(R.layout.profile_images_layout, viewGroup, false);
        return new PersonProfileImagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PersonProfileImagesViewHolder personProfileImagesViewHolder, int i) {
        final MovieImagesBackdropsAndPosters movieImagesBackdropsAndPosters = movieImagesBackdropsAndPostersList.get(i);

        personProfileImagesViewHolder.setProfileImage(activity, movieImagesBackdropsAndPosters.getFile_path());

        personProfileImagesViewHolder.profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent imageViewerIntent = new Intent(activity, ImageViewerActivity.class);
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, personProfileImagesViewHolder.profileImage, ViewCompat.getTransitionName(personProfileImagesViewHolder.profileImage));
                imageViewerIntent.putExtra("image_url", movieImagesBackdropsAndPosters.getFile_path());
                activity.startActivity(imageViewerIntent, compat.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieImagesBackdropsAndPostersList.size();
    }
}
