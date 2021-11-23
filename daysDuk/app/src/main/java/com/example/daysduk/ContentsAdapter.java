package com.example.daysduk;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.daysduk.model.PostItem;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class ContentsAdapter extends RecyclerView.Adapter<ContentsAdapter.Holder>  {
    private ArrayList<PostItem> postdata;
    String day = "";
    Drawable d;
    Bitmap bm=null;
    View v;
    ContentsAdapter.Holder holder;

    //ArrayList<PostItem> items = new ArrayList<>();
    //List<PostItem> items;
    //ArrayList<ContentsInfo> items = new ArrayList<>();
    public ContentsAdapter(ArrayList<PostItem> postdata){
        this.postdata = postdata;
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
        PostItem item = postdata.get(position);
        //날짜 분할
        String year = item.getDate();
        //String year = "2021-11-21T10:24:41.249220Z";
        String dateArray0= year.split("T")[0];
        String []dateArray1 = dateArray0.split("-");
        System.out.println(dateArray1[2]);


        holder.list_year.setText(dateArray1[0]);
        holder.list_month.setText(dateArray1[1]);
        holder.list_day.setText(dateArray1[2]);
        holder.list_weekday.setText(Toweekday(dateArray1[0]+dateArray1[1]+dateArray1[2]));
        holder.list_content.setText(item.getContent());
        holder.list_title.setText(item.getTitle());

        //이미지 부분
        if(item.getImage() == null){
            //은지님 이부분 데이터베이스에 일기 사진이 없을 경우 디폴트로 띄울 사진 넣어주시면 돼요!!
            holder.list_imgView.setImageResource(R.mipmap.gallery2);
        } else if(item.getImage() !=null){
            Glide.with(v).load(item.getImage()).into(holder.list_imgView);
        }

        //날씨 부분
        String inputWeather = item.getWeather();
        if(inputWeather=="1"){
            //해
            holder.list_weather.setImageResource(R.drawable.sun);
        } else if(inputWeather=="2"){
            //바람
            holder.list_weather.setImageResource(R.drawable.wind);
        } else if(inputWeather=="3"){
            //비
            holder.list_weather.setImageResource(R.drawable.rain);
        } else if(inputWeather=="4"){
            //번개
            holder.list_weather.setImageResource(R.drawable.lightning);
        } else {
            //null값일 경우 해(임시)로 설정
            holder.list_weather.setImageResource(R.drawable.sun);
        }
    }

    @Override
    public int getItemCount() {
        return postdata.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView list_year;
        private TextView list_month;
        private TextView list_day;
        private TextView list_weekday;
        private ImageView list_weather;
        private ImageView list_imgView;
        private TextView list_content;
        private TextView list_title;

        public Holder(@NonNull @NotNull View itemView) {
            super(itemView);
            list_year = itemView.findViewById(R.id.list_year);
            list_month = itemView.findViewById(R.id.list_month);
            list_day = itemView.findViewById(R.id.list_day);
            list_weekday = itemView.findViewById(R.id.list_weekday);
            list_weather = itemView.findViewById(R.id.list_weather);
            list_imgView = itemView.findViewById(R.id.list_imgView);
            list_content = itemView.findViewById(R.id.list_content);
            list_title = itemView.findViewById(R.id.list_title);

        }
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
    public void addItem(PostItem item){
        postdata.add(item);
    }
}
