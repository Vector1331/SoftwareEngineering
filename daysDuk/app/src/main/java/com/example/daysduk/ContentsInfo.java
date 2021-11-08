package com.example.daysduk;

import android.graphics.drawable.Drawable;

public class ContentsInfo {
    private String year;
    private String month;
    private String day;
    private String weekday;
    private Drawable weather;
    private Drawable image;
    private String content;


    public ContentsInfo(){}
    public ContentsInfo(String year, String month, String day, String weekday, Drawable weather, Drawable image, String content) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.weekday = weekday;
        this.weather = weather;
        this.image = image;
        this.content = content;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public Drawable getWeather() {
        return weather;
    }

    public void setWeather(Drawable weather) {
        this.weather = weather;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}