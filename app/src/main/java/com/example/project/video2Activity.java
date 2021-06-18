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

public class video2Activity extends AppCompatActivity {
    String videoUrl = "https://r1---sn-cx1x9-ua8s.googlevideo.com/videoplayback?expire=1624069015&ei=N__MYP7_EOrYxN8P6uyOkAg&ip=81.218.117.40&id=o-ALndNek5_RJ_XxXR1VADTzvsiI0bOGNKgzhwvTi1Dh-E&itag=244&aitags=133%2C134%2C135%2C136%2C160%2C242%2C243%2C244%2C247%2C278&source=youtube&requiressl=yes&mh=2f&mm=31%2C29&mn=sn-cx1x9-ua8s%2Csn-hgn7yn7s&ms=au%2Crdu&mv=m&mvi=1&pl=26&initcwndbps=882500&vprv=1&mime=video%2Fwebm&ns=i6wrmRtGQbdV54TJ4vI-SrUF&gir=yes&clen=3024756&dur=106.440&lmt=1500698378830033&mt=1624047164&fvip=1&keepalive=yes&fexp=24001373%2C24007246&c=WEB&n=LtlVIR-AHMCFjg&sparams=expire%2Cei%2Cip%2Cid%2Caitags%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cdur%2Clmt&sig=AOq0QJ8wRgIhAL8M17dcsd4lv0y7Cjp7Z2f_CuT34vwmjj7vAeoY2Ra8AiEA676RR2HjEnjSjuOKnjoPWdoyTQdEAoht2Hc2XDOYtaY%3D&lsparams=mh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AG3C_xAwRQIgfIbCnHSl1v1Nkjxb82sWGfJ3qW8M4BwejbwzVtHH_JUCIQDE9pa3MwGuk1yJuIvH1WVGxkTiuJu9K8SVx_AkRAVp4g%3D%3D&alr=yes&cpn=zZ_3GECAprURs9ig&cver=2.20210617.01.00&rn=1&altitags=243%2C242";
    PlayerView playerView;
    SimpleExoPlayer player;
    private boolean playWhenReady = true ;
    private int currentWindow =0;
    private long playPackPosition =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video2);
        playerView = findViewById(R.id.video2);
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