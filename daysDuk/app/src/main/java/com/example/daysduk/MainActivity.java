package com.example.daysduk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.daysduk.model.Calendar;
import com.example.daysduk.model.PostItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    //프래그먼트 선언
    HomeFragment fragment_home = new HomeFragment();
    WriteFragment fragment_write = new WriteFragment();
    CalendarFragment fragment_calendar = new CalendarFragment();
    SettingFragment fragment_setting = new SettingFragment();

    //바텀 네비게이션 변수 선언
    BottomNavigationView bottomNavigationView;

    //response 받아온 객체 저장할 필드 선언 및 초기화 단계
    String diary_date = "";
    String freqDate;
    ArrayList <String>freq = new ArrayList<String>();

    //레트로핏 관련
    ArrayList<PostItem> postList;   //객체 담을 그릇

    //레트로핏 Call객체 생성을 위한 서버버기본주소
    private static final String BASE_URL = "http://192.168.219.104:8000/api/";
    private MyAPI mMyAPI;   //API SERVICE객체 생성

    //레트로핏 초기화 및 생성을 위한 메소드
    private void initMyAPI(String baseUrl){
        Log.d(TAG,"initMyAPI : " + baseUrl);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mMyAPI = retrofit.create(MyAPI.class);
    }

    private final  String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //바텀 네비게이션
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        //바텀 네비게이션 리스너 등록
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.i(TAG, "바텀 네비게이션 클릭");

                switch (item.getItemId()){
                    case R.id.menu_home:
                        Log.i(TAG, "홈 화면으로 전환");
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment_home).commitAllowingStateLoss();
                        return true;
                    case R.id.menu_write:
                        Log.i(TAG, "일기쓰기 화면으로 전환");
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment_write).commitAllowingStateLoss();
                        return true;
                    case R.id.menu_calendar:
                        Log.i(TAG, "캘린더 화면으로 전환");
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment_calendar).commitAllowingStateLoss();
                        return true;
                    case R.id.menu_setting:
                        Log.i(TAG, "설정 화면으로 전환");
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment_setting).commitAllowingStateLoss();
                        return true;

                }
                return true;
            }
        });

        //send date Data from MainActivity to CalendarFragment
        initMyAPI(BASE_URL); //객체 생성
        Log.d(TAG,"GET");
        postList = new ArrayList<>();
        Call<List<PostItem>> getCall = mMyAPI.get_diary();  //GET요청 보낼 CALL 객체 생성 , response는 LIST타입

        getCall.enqueue(new Callback<List<PostItem>>() {
            @Override
            public void onResponse(Call<List<PostItem>> call, Response<List<PostItem>> response) {
                if( response.isSuccessful()){
                    freq.clear();
                    List<PostItem> result = response.body();
                    for(int i=0; i<result.size(); i++){
                        diary_date = result.get(i).getDate();
                        freqDate = result.get(i).getDate();
                        freq.add(freqDate);
                    }
                }else {
                    Log.d(TAG,"Status Code : " + response.code());
                    Log.d(TAG,new Gson().toJson(response.errorBody()));
                    Log.d(TAG,new Gson().toJson(call.request().body()));
                }

                if(freq!=null){
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("fromMain", freq);
                    fragment_calendar.setArguments(bundle);
                }

            }

            @Override
            public void onFailure(Call<List<PostItem>> call, Throwable t) {
                Log.d(TAG,"Fail msg : " + t.getMessage());
            }
        });


    }

}