package com.example.daysduk;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity {
    private MyAPI mMyAPI;

    //diary, user, calener객체 들어갈 것들 미리 선언및 default값 선언
    String diary_weather = "1"; //default값
    String diary_img;       //default없이 null로 설정할 것
    String diary_date = "";
    String diary_todayme = "";
    String diary_tomorrowme = "";
    String diary_content ="default content";
    String diary_title="default title";
    int diary_id;

    //User
    int id_user;
    String kakaotoken="20170815";

    //Calendar
    String cal_todaysdate;
    String cal_thismonth;
    int cal_diaryfrequency;

    //구동하는 서버의 무선랜 ipv4 주소로 바꾸어 줄 것. ip는 ipconfig로 찾을 수 있음.
    private static final String BASE_URL = "http://192.168.219.103:8000/api/";

    private final  String TAG = getClass().getSimpleName();

    //레트로핏 객체 생성을 위한 메소드
    private void initMyAPI(String baseUrl){
        Log.d(TAG,"initMyAPI : " + baseUrl);

        //Json 컨버터 등록
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mMyAPI = retrofit.create(MyAPI.class);
    }

    //레트로핏 객체 생성
//    initMyAPI(BASE_URL);


}