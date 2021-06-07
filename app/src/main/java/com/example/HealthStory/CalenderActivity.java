package com.example.HealthStory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CalenderActivity extends AppCompatActivity {

    private CalendarView calendarView; // 캘런더뷰
    private TextView txt_sample; //날짜 확인차 출력
    private TextView bott; // 하단 텍스트
    private Button btn_eee; // 버튼

    public String getText_date() {
        return text_date;
    }

    public void setText_date(String text_date) {
        this.text_date = text_date;
    }

    private String text_date;
    private String date; // date 출력

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
                date = Integer.toString(year) + Integer.toString(month + 1) + dayOfMonth;
                text_date = year + "년" + (month+1) +"월" + dayOfMonth + "일";
                txt_sample.setText(text_date);
                setText_date(text_date);
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
                bott = bottomSheetView.findViewById(R.id.bottom_text);
                bott.setText(getText_date());

                // 운동등록 클릭하기
                bottomSheetView.findViewById(R.id.bottom_butt_select).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                        mDatabase.child("selectday").setValue(getDate());
                        Intent intent = new Intent(CalenderActivity.this, SelectActivity.class);
                        intent.putExtra("date",getText_date());
                        startActivity(intent);
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });

    }

}