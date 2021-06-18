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

public class video4Activity extends AppCompatActivity {
    String videoUrl = "https://r1---sn-cx1x9-ua8z.googlevideo.com/videoplayback?expire=1623609441&ei=AfzFYOqkD4eY0wXq8bSoAw&ip=81.218.117.42&id=o-AE8t2d4IxJo7siBTnkLsVeJ18tjl8zE6vI65XcWq2xkv&itag=247&aitags=133%2C134%2C135%2C136%2C137%2C160%2C242%2C243%2C244%2C247%2C248%2C278&source=youtube&requiressl=yes&mh=ei&mm=31%2C29&mn=sn-cx1x9-ua8z%2Csn-hgn7yn7z&ms=au%2Crdu&mv=m&mvi=1&pcm2cms=yes&pl=26&initcwndbps=1008750&vprv=1&mime=video%2Fwebm&ns=wm_aNVcjxO2h5fsMzJaLLjQF&gir=yes&clen=16679453&dur=123.520&lmt=1519317148732409&mt=1623587578&fvip=1&keepalive=yes&fexp=24001373%2C24007246&c=WEB&n=gdOhy0AXuNTanA&sparams=expire%2Cei%2Cip%2Cid%2Caitags%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cdur%2Clmt&sig=AOq0QJ8wRQIgdrOfsndVSrs2fXplqiPgzt1BIHV1a5KcZXT1W4k5eWYCIQD4kaxeD93-IQKIH1rHcLf5vOjOVDt8BiMbp4TVruMkXw%3D%3D&lsparams=mh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpcm2cms%2Cpl%2Cinitcwndbps&lsig=AG3C_xAwRAIgVXR4H65j-kvK0FnbhMEZ5E3mplcXZ3I4bGwoSZxQRwoCID2sz0glDZPZX_kjePrtrTWJx679uw7plgj-GAhjQZij&alr=yes&cpn=zlkmhhI96oVQfTd_&cver=2.20210610.07.00&rn=13";
    PlayerView playerView;
    SimpleExoPlayer player;
    private boolean playWhenReady = true ;
    private int currentWindow =0;
    private long playPackPosition =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video4);
        playerView = findViewById(R.id.video4);
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