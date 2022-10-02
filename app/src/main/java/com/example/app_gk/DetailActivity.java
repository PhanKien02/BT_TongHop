package com.example.app_gk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        TextView tvTitle = (TextView) findViewById(R.id.tv_dt_title);
        TextView tvDes =(TextView) findViewById(R.id.tv_dt_des);
        ImageView imageView = (ImageView) findViewById(R.id.iv_dt_ha);
        tvTitle.setText(intent.getStringExtra("title"));
        tvDes.setText(intent.getStringExtra("des"));
        imageView.setImageResource(intent.getIntExtra("image",R.id.item_image));
    }
}