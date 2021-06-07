package com.example.HealthStory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.HealthStory.API.ExeAdapter;
import com.example.HealthStory.API.ExeList;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class SelectActivity extends AppCompatActivity {

    ListView listView;
    ExeAdapter adapter;
    TextView textView;
    String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        Intent intent = getIntent();
        date = intent.getStringExtra("date");
        textView = findViewById(R.id.select_date);
        textView.setText("선택한 날짜 : " + date);
        listView = (ListView)findViewById(R.id.select_list);
        adapter = new ExeAdapter(this);


        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.squt),"스쿼트");
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.dumb),"덤벨컬");
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.leg),"레그레이즈");

        listView.setAdapter(adapter);




    }
}