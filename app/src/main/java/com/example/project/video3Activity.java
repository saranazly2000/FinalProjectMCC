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

public class video3Activity extends AppCompatActivity {
    String videoUrl = "https://r2---sn-cx1x9-ua8d.googlevideo.com/videoplayback?expire=1624069178&ei=2v_MYMmVCImdxN8P4NeisAM&ip=81.218.117.40&id=o-AMb_p8wvpPLH7PHTTsLQxl8Zqewt5HkbjC8PjLkvxlAE&itag=244&aitags=133%2C134%2C135%2C136%2C137%2C160%2C242%2C243%2C244%2C247%2C248%2C278&source=youtube&requiressl=yes&mh=rX&mm=31%2C29&mn=sn-cx1x9-ua8d%2Csn-hgn7yn76&ms=au%2Crdu&mv=m&mvi=2&pl=26&initcwndbps=1021250&vprv=1&mime=video%2Fwebm&ns=OVK9PLZ6eVHYHkkb29uutnsF&gir=yes&clen=12278306&dur=184.984&lmt=1481396662333275&mt=1624047407&fvip=2&keepalive=yes&fexp=24001373%2C24007246&c=WEB&n=rF96fix5ziRJ1g&sparams=expire%2Cei%2Cip%2Cid%2Caitags%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cdur%2Clmt&sig=AOq0QJ8wRgIhAPuPXzo9bBRgYGrSGzwJf33dw9XnJPKY7SzIE9Gdkl2pAiEAjmmlbc6KzaHCCbfqZQFOqhVbCCCFfPUSCyWqENSLPZg%3D&lsparams=mh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AG3C_xAwRgIhANfDVYPO378sXqujmZnxgp6Kr2JoEKbo7h5_KXigrA2CAiEAuQSpllfHOIZQZczOHyZeKqzOBqf_ZRaPZYdUOaHB8HQ%3D&alr=yes&cpn=Lq_fa_sVNUEffWpy&cver=2.20210617.01.00&rn=11&altitags=243%2C242";
    PlayerView playerView;
    SimpleExoPlayer player;
    private boolean playWhenReady = true ;
    private int currentWindow =0;
    private long playPackPosition =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video3);
        playerView = findViewById(R.id.video3);
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