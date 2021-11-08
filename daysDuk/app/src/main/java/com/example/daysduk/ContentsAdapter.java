package com.example.daysduk;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.ContentInfoCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ContentsAdapter extends RecyclerView.Adapter<ContentsAdapter.Holder> {

    ArrayList<ContentsInfo> items = new ArrayList<>();
    public ContentsAdapter(ArrayList<ContentsInfo> items){
        this.items = items;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ContentsAdapter.Holder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ContentsAdapter.Holder holder, int position) {
        ContentsInfo item = items.get(position);
        holder.list_year.setText(item.getYear());
        holder.list_month.setText(item.getMonth());
        holder.list_day.setText(item.getDay());
        holder.list_weekday.setText(item.getWeekday());
        holder.list_weather.setImageDrawable(item.getWeather());
        holder.list_imgView.setImageDrawable(item.getImage());
        holder.list_content.setText(item.getContent());
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
}
