package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

public class video1Activity extends AppCompatActivity {
    String videoUrl = "https://cdn-cf-east.streamable.com/video/mp4/8jjpdb.mp4?Expires=1624305540&Signature=VwtovUEN9bZv3buI-aksBuD~DruSPyqSntVHY7HdIobbqoKa7DSE-9tpFLpJ6GzNv14OuTdHgnF~WDb9l8X~gAnYtYom6WWVYIUWE2jTXA2KQA-Uxba48c7aXKb5KTpnE3utFb7csBHIqoFW0-kGVHo86d7lueirQ9OHNQLGoQGJeK0cVClxqWetDWV6R1MSBs9O5IV3N61dQ83Wf3no9IEH4jJ0dzu3exDfKBVNX3GY7FNZNM9bo6F8C5AFMvkByrvIXS6nVDZrwvHEMD94u0pgcQTX~ac6tEp3id3H5u5OERYK0FureWX-nm7zsXm8ckhhdI7P05P~h1-BX7RLFA__&Key-Pair-Id=APKAIEYUVEN4EVB2OKEQ";
    PlayerView playerView;
    SimpleExoPlayer player;
    private boolean playWhenReady = true ;
    private int currentWindow =0;
    private long playPackPosition =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video1);
        playerView = findViewById(R.id.video1);
    }
    public void initVideo(){
        player = ExoPlayerFactory.newSimpleInstance(this);
        playerView.setPlayer(player);
        Uri uri =Uri.parse(videoUrl);
        DataSource.Factory dataSoursFactory = new DefaultDataSourceFactory(this,"exoplayer_codeLab");
        MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSoursFactory).createMediaSource(uri);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow,playPackPosition);
        player.prepare(mediaSource,false,false);
    }

    public void releaseVideo(){
        if(player != null){
            playWhenReady = player.getPlayWhenReady();
            playPackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        initVideo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(player != null) {
            initVideo();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseVideo();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseVideo();
    }
}