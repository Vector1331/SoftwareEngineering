package com.example.daysduk;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ContentInfoCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ContentsAdapter extends RecyclerView.Adapter<ContentsAdapter.Holder> {

    String day = "";
    Drawable d;
    View v;

    ArrayList<ContentsInfo> items = new ArrayList<>();
    public ContentsAdapter(ArrayList<ContentsInfo> items){
        this.items = items;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ContentsAdapter.Holder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ContentsAdapter.Holder holder, int position) {
        ContentsInfo item = items.get(position);
        //날짜 분할
        String year = item.getDiary_date();
        //String year = "2021-11-21T10:24:41.249220Z";
        String dateArray0= year.split("T")[0];
        String []dateArray1 = dateArray0.split("-");
        System.out.println(dateArray1[2]);

        holder.list_year.setText(dateArray1[0]);
        holder.list_month.setText(dateArray1[1]);
        holder.list_day.setText(dateArray1[2]);
        holder.list_weekday.setText(Toweekday(dateArray1[0]+dateArray1[1]+dateArray1[2]));
        holder.list_weather.setImageDrawable(setWeather(item.getDiary_weather()));
        //holder.list_imgView.setImageBitmap(item.getImage());
        holder.list_content.setText(item.getDiary_content());


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView list_year;
        private TextView list_month;
        private TextView list_day;
        private TextView list_weekday;
        private ImageView list_weather;
        private ImageView list_imgView;
        private TextView list_content;

        public Holder(@NonNull @NotNull View itemView) {
            super(itemView);
            list_year = itemView.findViewById(R.id.list_year);
            list_month = itemView.findViewById(R.id.list_month);
            list_day = itemView.findViewById(R.id.list_day);
            list_weekday = itemView.findViewById(R.id.list_weekday);
            list_weather = itemView.findViewById(R.id.list_weather);
            list_imgView = itemView.findViewById(R.id.list_imgView);
            list_content = itemView.findViewById(R.id.list_content);

        }
    }

    //날씨 Bitmap 설정 메소드
    private Drawable setWeather(String inputWeather) {

        if(inputWeather=="1"){
            //해
            Drawable d = v.getResources().getDrawable(R.drawable.sun);
        } else if(inputWeather=="2"){
            //바람
            Drawable d = v.getResources().getDrawable(R.drawable.wind);
        } else if(inputWeather=="3"){
            //비
            Drawable d = v.getResources().getDrawable(R.drawable.rain);
        } else if(inputWeather=="4"){
            //번개
            Drawable d = v.getResources().getDrawable(R.drawable.lightning);
        } else {
            //null값일 경우 해로 설정
            Drawable d = v.getResources().getDrawable(R.drawable.sun);
        }
        return d;
    }

    //요일 구하기 메소드
    private String Toweekday(String inputDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = dateFormat.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayNum = calendar.get(calendar.DAY_OF_WEEK);
        switch (dayNum) {
            case 1:
                day = "Sun";
                break;
            case 2:
                day = "Mon";
                break;
            case 3:
                day = "Tue";
                break;
            case 4:
                day = "Wed";
                break;
            case 5:
                day = "Thu";
                break;
            case 6:
                day = "Fri";
                break;
            case 7:
                day = "Sat";
                break;

        }
        return day;
    }
}
