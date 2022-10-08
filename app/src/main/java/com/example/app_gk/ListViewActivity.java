package com.example.app_gk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.app_gk.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {
    ListView lv_fruit;
    ArrayList<Fruit> fruits;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);


      lv_fruit =  findViewById(R.id.list_view);
        fruits= new ArrayList<>();

        fruits.add(new Fruit("Cherry", "Cherry là loại quả giàu dinh dưỡng giúp làm" +
                " đẹp da và chế biến được đa dạng các món sinh tố, thức uống,...",R.drawable.cherry));
        fruits.add(new Fruit("Nho", "Nho là một loại quả mọng lấy từ các loài cây thân leo thuộc" +
                " chi Nho (Vitis). Quả nho mọc thành chùm từ 6 đến 300 quả, chúng có màu đen, " +
                "lam, vàng, lục, đỏ-tía hay trắng...",R.drawable.nho));
        fruits.add(new Fruit("Chuoi", "Chuối là tên gọi loại quả của các loài cây thuộc chi Musa;đây được coi là một trong các loại trái cây được ăn rộng rãi nhất. Những cây chuối " +
                "có gốc từ vùng nhiệt đới ở Đông Nam Á..." +
                " vùng nhiệt đới...",R.drawable.chuoi));
        fruits.add(new Fruit("Dâu tây"," Dâu tây là một chi thực vật hạt kín và loài thực vật có hoa thuộc họ " +
                "Hoa hồng (Rosaceae). Dâu tây xuất xứ từ châu Mỹ và được các nhà làm vườn châu Âu... ",R.drawable.dautay));
        fruits.add(new Fruit("Thơm", "Dứa có các tên gọi khác như là: khóm, thơm, khớm, gai hoặc huyền nương, tên khoa học Ananas comosus," +
                " là một loại quả nhiệt đới...",R.drawable.dua));

        adapter = new Adapter(ListViewActivity.this,R.layout.activity_item,fruits);
        lv_fruit.setAdapter(adapter);
        lv_fruit.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(ListViewActivity.this);
                dlgAlert.setMessage("Are you sure you want to delete?");
                dlgAlert.setTitle("Delete fruit");
                dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        fruits.remove(i);
                        adapter.notifyDataSetChanged();
                    }
                });
                adapter.notifyDataSetChanged();
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
                return  true;
            }
        });
        lv_fruit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fruit fr = fruits.get(i);
                    doOpenMainActivity(fr.getTitle(),fr.getDescription(),fr.getImage());
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void doOpenMainActivity(String title, String des, int image)
    {
        Intent myIntent;
        myIntent = new Intent( this, DetailActivity.class);
        myIntent.putExtra("title",title);
        myIntent.putExtra("des",des);
        myIntent.putExtra("image",image);
        startActivity(myIntent);
    }
    }