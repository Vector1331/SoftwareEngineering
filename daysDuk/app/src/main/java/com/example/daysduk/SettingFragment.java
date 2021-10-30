package com.example.daysduk;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class SettingFragment extends Fragment {

    //변수 선언
    Button settingInquiry;
    Button settingLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        //변수 선언
        settingInquiry = (Button)view.findViewById(R.id.setting_inquiry);
        settingLogout = (Button)view.findViewById(R.id.setting_logout);

        //이메일로 문의하기 버튼 클릭시 메일 보내기 이동
        settingInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setType("plain/text");
                String[] address = {"daysduck@duksung.ac.kr"};
                email.putExtra(Intent.EXTRA_EMAIL, address);
                email.putExtra(Intent.EXTRA_SUBJECT, "DaysDuck) 문의사항");
                email.putExtra(Intent.EXTRA_TEXT, "DaysDuck 어플 관련 문의사항");
                startActivity(email);

            }
        });

        //로그아웃 버튼 클릭시
        settingLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


        // Inflate the layout for this fragment
        return view;
    }
}