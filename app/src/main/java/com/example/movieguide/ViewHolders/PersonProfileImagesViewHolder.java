package com.example.movieguide.ViewHolders;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.movieguide.R;
import com.squareup.picasso.Picasso;

public class PersonProfileImagesViewHolder extends RecyclerView.ViewHolder
{
    public AppCompatImageView profileImage;


    public PersonProfileImagesViewHolder(@NonNull View itemView) {
        super(itemView);
        profileImage = itemView.findViewById(R.id.profile_images);

    }

    public void setProfileImage(Activity activity, String profilePath)
    {
        Picasso.with(activity).load(profilePath).into(profileImage);
    }
}
