package com.example.daysduk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    //바텀 네비게이션 변수 선언
    BottomNavigationView bottomNavigationView;
    //프래그먼트 변수 선언

    Fragment fragment_home;
    Fragment fragment_write;
    Fragment fragment_calendar;
    Fragment fragment_image;
    Fragment fragment_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //바텀 네비게이션
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        //프래그먼트
        fragment_home = new HomeFragment();
        fragment_write = new WriteFragment();
        fragment_calendar = new CalendarFragment();
        fragment_image = new ImageFragment();
        fragment_setting = new SettingFragment();

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
//                    case R.id.menu_image:
//                        Log.i(TAG, "이미지 화면으로 전환");
//                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment_image).commitAllowingStateLoss();
//                        return true;
                    case R.id.menu_setting:
                        Log.i(TAG, "설정 화면으로 전환");
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment_setting).commitAllowingStateLoss();
                        return true;

                }
                return true;
            }
        });

    }
}