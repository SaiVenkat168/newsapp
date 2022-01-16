package com.example.android.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class NewsDetailActivity extends AppCompatActivity {
    String title,desc,context,imageurl,url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        title=getIntent().getStringExtra("title");
        context=getIntent().getStringExtra("context");
        desc=getIntent().getStringExtra("desc");
        imageurl=getIntent().getStringExtra("image");
        url=getIntent().getStringExtra("url");
    }
}