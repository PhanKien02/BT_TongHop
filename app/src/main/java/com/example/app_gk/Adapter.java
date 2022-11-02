package com.example.app_gk;


import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_gk.modules.Fruit;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Adapter extends BaseAdapter {
    private Context context;
    private int layout;
    private  List<Fruit> Fruits;

    public Adapter(Context context, int layout, List<Fruit> fruits) {
        this.context = context;
        this.layout = layout;
        this.Fruits = fruits;
    }

    @Override
    public int getCount() {
        return Fruits.size();
    }

    @Override
    public Object getItem(int i) {  return null; }

    @Override
    public long getItemId(int i) { return 0;}

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout,null);
        TextView titledt = (TextView) view.findViewById(R.id.item_title);
        TextView desdt = (TextView) view.findViewById(R.id.item_description);
        ImageView imageViewdt = (ImageView) view.findViewById(R.id.item_image);
        Fruit fruit = Fruits.get(i);
        titledt.setText(fruit.getTitle());
        desdt.setText(fruit.getDescription());
        new DisplayImage(imageViewdt).execute(fruit.getThumbnail());
        return  view;

    }
}