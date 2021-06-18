package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void info(View v){
        Intent intent = new Intent(MainActivity.this, generalInfoActivity.class);
        startActivity(intent);
    }

    public void news(View v){
        Intent intent = new Intent(MainActivity.this, newsActivity.class);
        startActivity(intent);
    }

    public void dates(View v){
        Intent intent = new Intent(MainActivity.this, DatesActivity.class);
        startActivity(intent);
    }
    public void video(View v){
        Intent intent = new Intent(MainActivity.this, videoActivity.class);
        startActivity(intent);
    }

    public void logout(View v){
        Intent intent = new Intent(MainActivity.this,LogOut.class);
        startActivity(intent);
    }
}