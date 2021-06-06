package com.example.HealthStory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class CalenderActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private TextView txt_sample;
    private TextView bott;
    private Button btn_eee;
    private DrawerLayout drawerLayout;
    private View drawerview;
    private String date;

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate(){
        return date;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        calendarView = (CalendarView)findViewById(R.id.calender);
        txt_sample = (TextView)findViewById(R.id.text_sample);
        btn_eee = (Button) findViewById(R.id.btn_eee);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = year + "년 " + (month + 1) + "월 " + dayOfMonth + "일";
                txt_sample.setText(date);
                setDate(date);
            }
        });

        btn_eee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        CalenderActivity.this, R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(
                                R.layout.layout_bottom_sheet,
                                (LinearLayout)findViewById(R.id.bottomSheet)
                        );
                bott =bottomSheetView.findViewById(R.id.bottom_text);
                bott.setText(getDate());

                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });

    }

}