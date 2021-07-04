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
    String videoUrl = "https://r5---sn-4g5ednly.googlevideo.com/videoplayback?expire=1624740385&ei=wT3XYIbxF8OfgAfukrLoAg&ip=213.6.173.26&id=o-AHXQPzRooX7tXzdLjzEh3Q16qzSvRUnQEMqSwYxT3H-x&itag=135&aitags=133%2C134%2C135%2C160%2C242%2C243%2C244%2C278&source=youtube&requiressl=yes&vprv=1&mime=video%2Fmp4&ns=t06niln67cWqtEnP_g-nejsG&gir=yes&clen=3756485&otfp=1&dur=45.066&lmt=1608107956197604&keepalive=yes&fexp=24001373,24007246&c=WEB&txp=1211222&n=F9oEztOm4BSW9g&sparams=expire%2Cei%2Cip%2Cid%2Caitags%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cotfp%2Cdur%2Clmt&sig=AOq0QJ8wRgIhAN6AxbQqkaUrCK0utLcKZ9XtQ2-ADPcRWJIUIp_6fMhiAiEAkcllXq5EXIjwQlPQ4a8qwBtq_wmZhj6sFNrqLuanzd0%3D&alr=yes&cpn=2_cIHtM4A0BcBvGX&cver=2.20210623.00.00&cm2rm=sn-25auxa-1qhl76,sn-4g5ez776&redirect_counter=2&cms_redirect=yes&mh=9m&mm=34&mn=sn-4g5ednly&ms=ltu&mt=1624718684&mv=m&mvi=5&pl=17&lsparams=mh,mm,mn,ms,mv,mvi,pl&lsig=AG3C_xAwRQIgGgyNCn93onB2j8G_Z315_DhyfVl-QBcJN5O9lveMahACIQCF_CxcptoP5vmKDV5wIrsP_bY0k9nIAfqoKN3I5xLffA%3D%3D&rn=5";
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