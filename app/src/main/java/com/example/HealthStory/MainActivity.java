package com.example.HealthStory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.HealthStory.API.ParserData;
import com.example.HealthStory.API.Product;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import com.example.HealthStory.API.*;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private TextView txt_sample;
    private String pathtext; // 파베 경로
    private Button btn_cam;
    private Button btn_ccc; // 파베
    private Button btn_bag;  // 장바구니
    private CalendarView calendarView;
    public String getPathtext() {
        return pathtext;
    }
    public void setPathtext(String pathtext) {
        this.pathtext = pathtext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Product").removeValue();
        databaseReference.child("Select_Product").removeValue();

        btn_cam = findViewById(R.id.btn_cam);
        txt_sample = findViewById(R.id.txt_sample);
        btn_bag = findViewById(R.id.btn_bag);
        btn_ccc = findViewById(R.id.btn_ccc);
        calendarView = findViewById(R.id.calender);


        btn_cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://healthstory.netlify.app/"));
                startActivity(intent);
            }
        });

        btn_ccc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                Intent intent = new Intent(getApplicationContext(), Sub_Activity.class);
                mDatabase.child("Product").removeValue();

                ArrayList<String> titlearr = new ArrayList<>();
                ArrayList<String> pricearr = new ArrayList<>();
                ArrayList<String> brandarr = new ArrayList<>();
                ArrayList<String> imagearr = new ArrayList<>();
                ArrayList<String> pdtIdarr = new ArrayList<>();
                ArrayList<Integer> indexArr = new ArrayList<>();

                ParserData psd = new ParserData();
                Product product = new Product();


                String Out = "Product";
                String json = null;

                try {
                    json = new Search_API().execute().get();
                    titlearr = psd.TitleParserData(json);
                    pricearr = psd.PriceParser(json);
                    brandarr = psd.BrandParserData(json);
                    imagearr = psd.ImageParserData(json);
                    pdtIdarr = psd.ProductIdParserData(json);


                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < titlearr.size(); i++) {  //index 배열 크기만큼 for
                    String prO = Out + Integer.toString(i);
                    product.setTitle(titlearr.get(i));
                    product.setPrice(pricearr.get(i));
                    product.setBrand(brandarr.get(i));
                    product.setImage(imagearr.get(i));
                    product.setProductId(pdtIdarr.get(i));
                    mDatabase.child("Product").child(prO).setValue(product);
                }
                setPathtext("Product");
                intent.putExtra("path",getPathtext());
                ////////////////////////////////////////
                startActivity(intent);
            }
        });

        btn_bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SelectProduct_Activity.class);
                setPathtext("Select_Product");
                intent.putExtra("path",getPathtext());
                startActivity(intent);
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = year + "년 " + (month + 1) + "월 " + dayOfMonth + "일";
                txt_sample.setText(date);
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