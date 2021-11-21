package com.example.daysduk.model;
//User 모델 정의 클래스

public class User {
    //필드 선언
    private int id_user;
    private String kakaotoken;

    //생성자
    public User() {
    }

    public User(int id_user, String kakaotoken) {
        this.id_user = id_user;
        this.kakaotoken = kakaotoken;
    }
    //getter setter
    public int getId_user() {
        return id_user;
    }

    public String getKakaotoken() {
        return kakaotoken;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setKakaotoken(String kakaotoken) {
        this.kakaotoken = kakaotoken;
    }


}