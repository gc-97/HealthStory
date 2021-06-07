package com.example.HealthStory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class ShoppingActivity extends AppCompatActivity {

    private String pathtext; // 파베 경로
    private Button btn_cam;
    private Button btn_ccc; // 파베
    private Button btn_bag;  // 장바구니
    private Button btn_cal;

    public String getPathtext() {
        return pathtext;
    }
    public void setPathtext(String pathtext) {
        this.pathtext = pathtext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Product").removeValue();
        databaseReference.child("Select_Product").removeValue();
        btn_ccc = (Button) findViewById(R.id.btn_ccc);
        btn_cam = (Button) findViewById(R.id.btn_cam);
        btn_bag = (Button) findViewById(R.id.btn_bag);
        btn_cal = (Button) findViewById(R.id.btn_cal);

        btn_cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://debugings.netlify.app"));
                startActivity(intent);
            }
        });

        btn_ccc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sub_Activity.class);
                setPathtext("Product");
                intent.putExtra("path",getPathtext());
                ////////////////////////////////////////
                startActivity(intent);
            }
        });

        btn_bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingActivity.this, SelectProduct_Activity.class);
                setPathtext("Select_Product");
                intent.putExtra("path",getPathtext());
                startActivity(intent);
            }
        });

        btn_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingActivity.this, CalenderActivity.class);
                startActivity(intent);
            }
        });
    }
}














// api 연결 테스트
/*
        String json = null;
        String par = null;
        try {
            json = new Search_API().execute().get();
            Parser psr = new Parser();
            par =psr.Parser(json);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        txt_sample.setText(par);
        */