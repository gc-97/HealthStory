package com.example.HealthStory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private View drawerView;

    private String pathtext; // 파베 경로

    public String getPathtext() {
        return pathtext;
    }

    public void setPathtext(String pathtext) {
        this.pathtext = pathtext;
    }
    private Button btnyoutub;
    private Button drawer_bag;
    private Button drawer_shop;
    private Button drawer_cal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerView = (View)findViewById(R.id.drawer);

        btnyoutub = (Button)findViewById(R.id.btnyoutub);
        btnyoutub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,YouTubeActivity.class);
                startActivity(intent);
            }
        });
        drawer_bag = (Button)findViewById(R.id.drawer_bag);
        drawer_shop = (Button)findViewById(R.id.drawer_shop);
        drawer_cal = (Button)findViewById(R.id.drawer_cal);

        Button btn_open = (Button)findViewById(R.id.todaybtn);
        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(drawerView);
            }
        });

        drawer_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CalenderActivity.class);
                startActivity(intent);
            }
        });

        drawer_bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SelectProduct_Activity.class);
                setPathtext("Select_Product");
                intent.putExtra("path",getPathtext());
                startActivity(intent);
            }
        });
        drawer_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Sub_Activity.class);
                setPathtext("Product");
                intent.putExtra("path",getPathtext());
                ////////////////////////////////////////
                startActivity(intent);
            }
        });
     }
    }
