package com.example.app_gk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
Button btn_update ;
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
        Long id = intent.getLongExtra("id",0);
        String imgUrl =intent.getStringExtra("image");
        new DisplayImage(imageView).execute(imgUrl);
        btn_update = findViewById(R.id.btn_dt_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(DetailActivity.this,UpdateActivity.class);
                myIntent.putExtra("title",tvTitle.getText().toString());
                myIntent.putExtra("des",tvDes.getText().toString());
                myIntent.putExtra("image",imgUrl);
                myIntent.putExtra("id",id);
                startActivity(myIntent);
            }
        });
    }

}