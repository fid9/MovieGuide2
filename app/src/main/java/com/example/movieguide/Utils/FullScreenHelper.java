package com.example.movieguide.Utils;

import android.app.Activity;
import android.view.View;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class FullScreenHelper
{
    private Activity activity;
    private View[] views;

    public FullScreenHelper(Activity activity, View... views) {
        this.activity = activity;
        this.views = views;
    }

    private void showSystemUI(View decorView)
    {
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }

    private void hideSystemUI(View decorView)
    {
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

    }

    public void enterFullScreen()
    {
        View view = activity.getWindow().getDecorView();
        hideSystemUI(view);
        for (View view1: views)
        {
            view1.setVisibility(GONE);
            view1.invalidate();
        }
    }

    public void exitFullScreen()
    {
        View view = activity.getWindow().getDecorView();
        showSystemUI(view);
        for (View view1: views)
        {
            view1.setVisibility(VISIBLE);
            view1.invalidate();
        }
    }
}

