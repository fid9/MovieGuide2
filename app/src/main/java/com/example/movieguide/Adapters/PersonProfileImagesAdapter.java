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
import com.example.movieguide.Model.PersonImageProfiles;
import com.example.movieguide.PersonDetailActivity;
import com.example.movieguide.R;
import com.example.movieguide.ViewHolders.PersonProfileImagesViewHolder;

import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")

public class PersonProfileImagesAdapter extends RecyclerView.Adapter<PersonProfileImagesViewHolder>
{
    private Activity activity;
    private List<PersonImageProfiles> profileImagesList;



    public PersonProfileImagesAdapter(Activity activity, List<PersonImageProfiles> profileImagesList) {
        this.activity = activity;
        this.profileImagesList = profileImagesList;
    }

    @NonNull
    @Override
    public PersonProfileImagesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(activity).inflate(R.layout.profile_images_layout, viewGroup, false);
        return new PersonProfileImagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PersonProfileImagesViewHolder personProfileImagesViewHolder, int i) {
        final PersonImageProfiles imageProfiles = profileImagesList.get(i);

        personProfileImagesViewHolder.setProfileImage(activity, imageProfiles.getFile_path());

        personProfileImagesViewHolder.profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent imageViewerIntent = new Intent(activity, ImageViewerActivity.class);
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, personProfileImagesViewHolder.profileImage, Objects.requireNonNull(ViewCompat.getTransitionName(personProfileImagesViewHolder.profileImage)));
                imageViewerIntent.putExtra("image_url", imageProfiles.getFile_path());
                activity.startActivity(imageViewerIntent, compat.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return profileImagesList.size();
    }
}
