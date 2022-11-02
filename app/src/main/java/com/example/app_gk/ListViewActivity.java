package com.example.app_gk;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.app_gk.databinding.ActivityMainBinding;
import com.example.app_gk.modules.Fruit;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import reotrfit.FruitService;
import reotrfit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListViewActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    ListView lv_fruit;
    ArrayList<Fruit> fruits;
    Adapter adapter;
    RetrofitService retrofitService = new RetrofitService();
    FruitService fruitService = retrofitService.getRetrofit().create(FruitService.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        lv_fruit = findViewById(R.id.list_view);
        fruits = new ArrayList<>();
        getAll();
        lv_fruit.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(ListViewActivity.this);
                dlgAlert.setMessage("Are you sure you want to delete?");
                dlgAlert.setTitle("Delete fruit");
                dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Fruit fruit = fruits.get(i);
                        Call<Void> call = fruitService.deleteFruit(fruit.getId());
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (!response.isSuccessful()) {
                                    return;
                                }
                                Toast.makeText(ListViewActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                getAll();
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(ListViewActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                            }
                        });
                        adapter.notifyDataSetChanged();
                    }
                });
                adapter.notifyDataSetChanged();
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
                return true;
            }
        });
        lv_fruit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fruit fr = fruits.get(i);
                Intent detailActiviti = new Intent(ListViewActivity.this, DetailActivity.class);
                doOpenDetailActivity(fr.getId(),fr.getTitle(), fr.getDescription(), fr.getThumbnail(), detailActiviti);
            }
        });
        Button btn_add = findViewById(R.id.btn_them);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addActiviti = new Intent(ListViewActivity.this, AddActivity.class);
                startActivity(addActiviti);
            }
        });
    }

    public void doOpenDetailActivity(Long id,String title, String des, String image,Intent myIntent)
    {
        myIntent = new Intent( this, DetailActivity.class);
        myIntent.putExtra("title",title);
        myIntent.putExtra("des",des);
        myIntent.putExtra("image",image);
        myIntent.putExtra("id",id);
        startActivity(myIntent);
    }
    public void getAll(){
        fruitService.getAllFruit()
                .enqueue(new Callback<List<Fruit>>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(Call<List<Fruit>> call, Response<List<Fruit>> response) {
                        fruits = (ArrayList<Fruit>) response.body();
                        Toast.makeText(ListViewActivity.this, "load fruits OK", Toast.LENGTH_SHORT).show();
                        adapter = new Adapter(ListViewActivity.this,R.layout.activity_item,fruits);
                        lv_fruit.setAdapter(adapter);
                    }
                    @Override
                    public void onFailure(Call<List<Fruit>> call, Throwable t) {
                        Toast.makeText(ListViewActivity.this, "Failed to load fruit", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    }