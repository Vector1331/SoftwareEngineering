package com.example.daysduk;

import android.graphics.drawable.Drawable;

import java.util.Date;

public class ContentsInfo {
    private String diary_title;
    private String diary_date;
    private String diary_weather;
    private String diary_content;
    private String diary_todayme;
    private String diary_tommorowme;
    private String diary_img;


    public ContentsInfo(){}
    public ContentsInfo(String diary_title, String diary_date, String diary_weather, String diary_content, String diary_todayme, String diary_tommorowme, String diary_img) {
        this.diary_title = diary_title;
        this.diary_date = diary_date;
        this.diary_weather = diary_weather;
        this.diary_content = diary_content;
        this.diary_todayme = diary_todayme;
        this.diary_tommorowme = diary_tommorowme;
        this.diary_img = diary_img;
    }

    public String getDiary_title() {
        return diary_title;
    }

    public void setDiary_title(String diary_title) {
        this.diary_title = diary_title;
    }

    public String getDiary_date() {
        return diary_date;
    }

    public void setDiary_date(String diary_date) {
        this.diary_date = diary_date;
    }

    public String getDiary_weather() {
        return diary_weather;
    }

    public void setDiary_weather(String diary_weather) {
        this.diary_weather = diary_weather;
    }

    public String getDiary_content() {
        return diary_content;
    }

    public void setDiary_content(String diary_content) {
        this.diary_content = diary_content;
    }

    public String getDiary_todayme() {
        return diary_todayme;
    }

    public void setDiary_todayme(String diary_todayme) {
        this.diary_todayme = diary_todayme;
    }

    public String getDiary_tommorowme() {
        return diary_tommorowme;
    }

    public void setDiary_tommorowme(String diary_tommorowme) {
        this.diary_tommorowme = diary_tommorowme;
    }

    public String getDiary_img() {
        return diary_img;
    }

    public void setDiary_img(String diary_img) {
        this.diary_img = diary_img;
    }
}