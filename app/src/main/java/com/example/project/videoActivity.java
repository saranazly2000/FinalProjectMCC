package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class videoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);



    }
    public void firstV(View v){
        Intent intent = new Intent(videoActivity.this, video1Activity.class);
        startActivity(intent);
    }
    public void secondV(View v){
        Intent intent = new Intent(videoActivity.this, video2Activity.class);
        startActivity(intent);
    }
    public void thirdV(View v){
        Intent intent = new Intent(videoActivity.this, video3Activity.class);
        startActivity(intent);
    }
    public void fourV(View v){
        Intent intent = new Intent(videoActivity.this, video4Activity.class);
        startActivity(intent);
    }


}