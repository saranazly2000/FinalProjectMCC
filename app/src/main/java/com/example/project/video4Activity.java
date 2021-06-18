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
    String videoUrl = "https://r5---sn-cx1x9-ua86.googlevideo.com/videoplayback?expire=1624069421&ei=zQDNYMDeD9yvxN8P_vGHiAU&ip=81.218.117.40&id=o-APO7wcye8KqSEL3znts5HNnran3I8r5GtZMkxkTBc205&itag=244&aitags=133%2C134%2C135%2C160%2C242%2C243%2C244%2C278&source=youtube&requiressl=yes&mh=fv&mm=31%2C29&mn=sn-cx1x9-ua86%2Csn-hpa7zne6&ms=au%2Crdu&mv=m&mvi=5&pl=26&initcwndbps=1137500&vprv=1&mime=video%2Fwebm&ns=0e_ffT8KkcuZH1JqDp9RXpoF&gir=yes&clen=8178173&dur=117.600&lmt=1507008355433929&mt=1624047407&fvip=5&keepalive=yes&fexp=24001373%2C24007246&c=WEB&n=yffWcDT8Zx81MQ&sparams=expire%2Cei%2Cip%2Cid%2Caitags%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cdur%2Clmt&sig=AOq0QJ8wRAIgQI0u0GLh_nvJsgEoFlFLV-0XjgmuRkA3aHifK-Vqn3MCICzamn3ijTWmlrf_ILP0hRnm6S_z_-XkRyQnUjDJVO0O&lsparams=mh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AG3C_xAwRQIgcSdSV7DselfP1TgfBKY1AshUv-Jtgg7PUm18v_xMJMkCIQC5s8g5n_4v8C4YvyAyvDaEv4_CoJ-ankw1NYHFbuNjqw%3D%3D&alr=yes&cpn=9EtT-LMUnJkd3Pkn&cver=2.20210617.01.00&rn=1&altitags=243%2C242";
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