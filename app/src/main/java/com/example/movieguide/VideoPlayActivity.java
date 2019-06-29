package com.example.movieguide;

import android.arch.lifecycle.Lifecycle;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;

import com.codewaves.youtubethumbnailview.ThumbnailLoader;
import com.codewaves.youtubethumbnailview.ThumbnailView;
import com.example.movieguide.Adapters.ExtraVideosRecyclerAdapter;
import com.example.movieguide.Model.MovieResponseResults;
import com.example.movieguide.Model.MovieVideosResults;
import com.example.movieguide.Utils.FullScreenHelper;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerFullScreenListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerInitListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerListener;

import java.util.ArrayList;
import java.util.Objects;

public class VideoPlayActivity extends AppCompatActivity {

    private ThumbnailView thumbnailView;
    private YouTubePlayerView playerView;
    private ProgressBar progressBar;
    private RecyclerView otherVideosRecyclerView;
    private FullScreenHelper fullScreenHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        Intent intent = getIntent();
        ThumbnailLoader.initialize(BuildConfig.GOOGLE_CLOUD_API_KEY);

        fullScreenHelper = new FullScreenHelper(this);
        thumbnailView = findViewById(R.id.video_thumbnail_view);
        playerView = findViewById(R.id.video_player_view);
        AppCompatTextView videoTitle = findViewById(R.id.play_video_title);
        AppCompatTextView noResultsFound = findViewById(R.id.no_results_found);
        otherVideosRecyclerView = findViewById(R.id.other_videos_recycler_view);
        otherVideosRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        progressBar = findViewById(R.id.progress_bar);
        progressBar.getIndeterminateDrawable().setColorFilter(0XFFFFFFFF, PorterDuff.Mode.MULTIPLY);


        if (intent != null && intent.getExtras() != null)
        {
            ArrayList<MovieVideosResults> movieVideosResultsArrayList = intent.getExtras().getParcelableArrayList("video");

            int position = Integer.parseInt(Objects.requireNonNull(intent.getExtras().getString("position")));
            if (movieVideosResultsArrayList != null && movieVideosResultsArrayList.size() > 0 )
            {
                final String videoId = movieVideosResultsArrayList.get(position).getKey();
                String title = movieVideosResultsArrayList.get(position).getName();

                if(title != null)
                {
                    videoTitle.setText(title);
                }

                if (videoId != null)
                {
                    String baseUrl = "https://www.youtube.com/watch?v=";

                    thumbnailView.loadThumbnail(baseUrl + videoId);
                    playerView.initialize(new YouTubePlayerInitListener()
                    {
                        @Override
                        public void onInitSuccess(@NonNull final YouTubePlayer youTubePlayer)
                        {
                            youTubePlayer.addListener(new AbstractYouTubePlayerListener()
                            {
                                @Override
                                public void onReady()
                                {
                                    thumbnailView.setVisibility(View.GONE);
                                    progressBar.setVisibility(View.GONE);

                                    playerView.setVisibility(View.VISIBLE);

                                    if (getLifecycle().getCurrentState() == Lifecycle.State.RESUMED)
                                    {
                                        youTubePlayer.loadVideo(videoId, 0);
                                    }
                                    else
                                    {
                                        youTubePlayer.cueVideo(videoId,0);
                                    }
                                }


                            });

                            playerView.addFullScreenListener(new YouTubePlayerFullScreenListener() {
                                @Override
                                public void onYouTubePlayerEnterFullScreen()
                                {
                                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                                    fullScreenHelper.enterFullScreen();

                                }

                                @Override
                                public void onYouTubePlayerExitFullScreen() {
                                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                                    fullScreenHelper.exitFullScreen();

                                }
                            });
                        }

                    }, true);

                    ArrayList<MovieVideosResults> movieVideosResultsList = new ArrayList<>(movieVideosResultsArrayList);

                    movieVideosResultsList.remove(position);

                    if (movieVideosResultsList.size() > 0 )
                    {
                        noResultsFound.setVisibility(View.GONE);
                        otherVideosRecyclerView.setVisibility(View.VISIBLE);
                        ExtraVideosRecyclerAdapter adapter = new ExtraVideosRecyclerAdapter(VideoPlayActivity.this, movieVideosResultsList);

                        otherVideosRecyclerView.setAdapter(adapter);
                        otherVideosRecyclerView.setVisibility(View.VISIBLE);

                        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_slide_bottom);
                        otherVideosRecyclerView.setLayoutAnimation(controller);
                        otherVideosRecyclerView.scheduleLayoutAnimation();
                    }
                    else
                    {
                        otherVideosRecyclerView.setVisibility(View.GONE);
                        noResultsFound.setVisibility(View.VISIBLE)  ;
                    }
                }

            }
        }

    }

    @Override
    public void onBackPressed() {
        if (playerView.isFullScreen())
        {
            playerView.exitFullScreen();
        }
        else
        {
            otherVideosRecyclerView.setVisibility(View.GONE);

            playerView.setVisibility(View.GONE);
            thumbnailView.setVisibility(View.VISIBLE);
            super.onBackPressed();
        }
    }
}
