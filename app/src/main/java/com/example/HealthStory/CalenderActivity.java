package com.example.HealthStory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class CalenderActivity extends AppCompatActivity {

    private CalendarView calendarView; // 캘런더뷰
    private TextView txt_sample; //날짜 확인차 출력
    private TextView bott; // 하단 텍스트
    private Button btn_eee; // 버튼
    private Button bottom_butt_show;
    private FirebaseDatabase database;
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
        bottom_butt_show = (Button)findViewById(R.id.bottom_butt_show);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = Integer.toString(year) + Integer.toString(month + 1) + dayOfMonth;
                text_date = year + "년" + (month+1) +"월" + dayOfMonth + "일";
                txt_sample.setText(text_date);
                setText_date(text_date);
                setDate(date);
                btn_eee.setEnabled(true);
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
                        intent.putExtra("datepath",getDate());
                        startActivity(intent);
                    }
                });

                // 운동기록 보기

                bottomSheetView.findViewById(R.id.bottom_butt_show).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        database = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = database.getReference();
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String dumb = snapshot.child(getDate()).child("dumb").getValue(String.class);
                                if(dumb == null) dumb="0";
                                String leg = snapshot.child(getDate()).child("leg").getValue(String.class);
                                if(leg == null) leg="0";
                                String squt = snapshot.child(getDate()).child("squt").getValue(String.class);
                                if(squt == null) squt="0";

                                AlertDialog.Builder builder = new AlertDialog.Builder(CalenderActivity.this);

                                String alert = "덤벨 : \t\t" + dumb + "회\n" + "레그레이즈 : \t\t" + leg + "회\n" + "스쿼트 : \t\t" + squt + "회\n";
                                String title = getText_date() + "  운동기록";
                                builder.setTitle(title).setMessage(alert);

                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {}
                        });
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });


    }

}