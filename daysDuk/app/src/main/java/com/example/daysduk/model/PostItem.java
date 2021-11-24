package com.example.daysduk.model;
//Diary의 모델 정의 클래스

public class PostItem {
    //필드 선언
    private int diary_id;
    private String diary_title;
    private String diary_date;
    private String diary_weather;
    private String diary_content;
    private String diary_todayme;
    private String diary_tomorrowme;
    private String diary_img;

    //생성자
    public PostItem(){}
    public PostItem(
            int diary_id,String diary_title, String diary_date, String diary_weather,
            String diary_content, String diary_todayme, String diary_tomorrowme,String diary_img) {
        this.diary_id = diary_id;
        this.diary_title = diary_title;
        this.diary_date = diary_date;
        this.diary_weather = diary_weather;
        this.diary_content = diary_content;
        this.diary_todayme = diary_todayme;
        this.diary_tomorrowme = diary_tomorrowme;
        this.diary_img = diary_img;
    }
    public PostItem(
            int diary_id,String diary_title, String diary_date, String diary_weather,
            String diary_content, String diary_todayme, String diary_tomorrowme ) {
        this.diary_id = diary_id;
        this.diary_title = diary_title;
        this.diary_date = diary_date;
        this.diary_weather = diary_weather;
        this.diary_content = diary_content;
        this.diary_todayme = diary_todayme;
        this.diary_tomorrowme = diary_tomorrowme;
    }

    //getter메소드
    public int getDiary_id() {
        return diary_id;
    }
    public String getTitle() {
        return diary_title;
    }
    public String getDate() {
        return diary_date;
    }
    public String getWeather() {
        return diary_weather;
    }
    public String getContent() {
        return diary_content;
    }
    public String getTodayme() {
        return diary_todayme;
    }
    public String getTomorrowme() {
        return diary_tomorrowme;
    }
    public String getImage() {
        return diary_img;
    }


    //setter 메소드
    public int setDiary_id(int diary_id) {
        this.diary_id = diary_id;
        return diary_id;
    }
    public String setTitle(String diary_title) {
        this.diary_title = diary_title;
        //String Diary_title = diary_title;
        return diary_title;
    }
    public String setDate(String diary_date) {
        this.diary_date = diary_date;
        //String Diary_date= diary_date;
        return diary_date;
    }
    public String setWeather(String diary_weather) {
        this.diary_weather = diary_weather;
        //String Diary_weather = diary_weather;
        return diary_weather;
    }
    public String setContent(String diary_content) {
        this.diary_content = diary_content;
        return diary_content;
    }
    public String setTodayme(String diary_todayme) {
        this.diary_todayme = diary_todayme;
        return diary_todayme;
    }
    public String setTomorrowme(String diary_tomorrowme) {
        this.diary_tomorrowme = diary_tomorrowme;
        return diary_tomorrowme;
    }
    public String setImage(String diary_img) {
        this.diary_img = diary_img;
        return diary_img;
    }

}
