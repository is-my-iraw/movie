package com.example.movie.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movie.R;
import com.example.movie.model.response.Movie;

public class VideoActivity extends AppCompatActivity {
    VideoView videoPlay;

    ProgressBar spiiner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        videoPlay = findViewById(R.id.PlayVideo);
        spiiner = findViewById(R.id.progressBar1);
        Intent intent = getIntent();
        Movie movie = (Movie) intent.getSerializableExtra("videoData");

        Uri video = Uri.parse(movie.getTrailerUrl());
        videoPlay.setVideoURI(video);
        Log.d("TAG", "onCreate: " + video);
        MediaController mc = new MediaController(this);
        videoPlay.setMediaController(mc);
        videoPlay.setOnPreparedListener(mp -> {
            ViewGroup.LayoutParams lp = videoPlay.getLayoutParams();
            float videoWidth = mp.getVideoWidth();
            float videoHeight = mp.getVideoHeight();
            float viewWidth = videoPlay.getWidth();
            lp.height = (int) (viewWidth * (videoHeight / videoWidth));
            videoPlay.setLayoutParams(lp);
            videoPlay.start();
            spiiner.setVisibility(View.GONE);
        });
    }
    @Override
    public void onStop()
    {
        super.onStop();
    }
}