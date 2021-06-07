package com.example.HealthStory.API;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.HealthStory.R;
import com.example.HealthStory.SelectActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ExeAdapter extends BaseAdapter {

    ArrayList<ExeList> arrayList = new ArrayList<>();
    private Context mContext;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    String path;
    public ExeAdapter(Context context) {
    this.mContext = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
       return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {


        Context context = parent.getContext();

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.exe_list,parent,false);

        }
        ImageView imageView = (ImageView)view.findViewById(R.id.exe_image);
        TextView textView = (TextView)view.findViewById(R.id.exe_text);
        Button button = (Button)view.findViewById(R.id.exe_butt);


        ExeList exeList = arrayList.get(position);
        imageView.setImageDrawable(exeList.getDrawable());
        textView.setText(exeList.getText());
        database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        DatabaseReference mDatabase = database.getReference("selectday");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                   String str = snapshot.getValue(String.class);
                   setPath(str);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //리스트 클릭 이벤트
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,getPath(),Toast.LENGTH_SHORT).show();
               BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(

                       (SelectActivity) mContext, R.style.BottomSheetDialogTheme
               );
               View bottom = LayoutInflater.from(mContext).inflate(
                       R.layout.layout_exe_bottom,
                       (LinearLayout)v.findViewById(R.id.bottomSheet)
               );

               bottom.findViewById(R.id.bottom_exe_butt).setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       switch (position){
                           case 0:
                               Toast.makeText(mContext,arrayList.get(position).getText(),Toast.LENGTH_SHORT).show();
                               databaseReference.child(getPath()).child("squt").setValue("0");
                               Intent squatintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://healthstory.netlify.app/domains/squat/squat"));
                               mContext.startActivity(squatintent);
                               break;
                           case 1:
                               Toast.makeText(mContext,arrayList.get(position).getText(),Toast.LENGTH_SHORT).show();
                               databaseReference.child(getPath()).child("dumb").setValue("0");
                               Intent dumbintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://healthstory.netlify.app/domains/curl/armcurl"));
                               mContext.startActivity(dumbintent);
                               break;
                           case 2:
                               Toast.makeText(mContext,arrayList.get(position).getText(),Toast.LENGTH_SHORT).show();
                               databaseReference.child(getPath()).child("leg").setValue("0");
                               Intent legintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://healthstory.netlify.app/domains/legraise/legraise"));
                               mContext.startActivity(legintent);
                               break;
                       }
                   }
               });
               bottomSheetDialog.setContentView(bottom);
               bottomSheetDialog.show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder((SelectActivity) mContext);

                String alert = arrayList.get(position).getText();
                builder.setTitle("Hi").setMessage(alert);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        return view;
    }


    public void addItem(Drawable drawable,String text){
        ExeList exeList = new ExeList();
        exeList.setDrawable(drawable);
        exeList.setText(text);
        arrayList.add(exeList);
    }
}
