package com.example.daysduk;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.example.daysduk.R.mipmap.ic_launcher;
import static com.example.daysduk.R.mipmap.ic_launcher_round;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WriteFragment extends Fragment {

    //변수 선언
    Button write_date_button;
    LinearLayout write_date_tv;
    TextView write_year;
    TextView write_month;
    TextView write_day;
    TextView write_weekday;
    DatePicker write_date_picker;
    ImageView write_weather1;
    ImageView write_weather2;
    ImageView write_weather3;
    ImageView write_weather4;
    EditText write_diary;
    EditText write_for_today;
    EditText write_for_tommorow;
    String day = "";
    int picked_weather;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_write, container, false);

        //변수 선언
        write_date_button = (Button) view.findViewById(R.id.write_date_Button);
        write_date_tv = (LinearLayout) view.findViewById(R.id.write_date_tv);
        write_year = (TextView) view.findViewById(R.id.write_year);
        write_month = (TextView) view.findViewById(R.id.write_month);
        write_day = (TextView) view.findViewById(R.id.write_day);
        write_weekday = (TextView) view.findViewById(R.id.write_weekday);
        write_date_picker = (DatePicker) view.findViewById(R.id.write_date_picker);
        write_weather1 = (ImageView) view.findViewById(R.id.write_weather1);
        write_weather2 = (ImageView) view.findViewById(R.id.write_weather2);
        write_weather3 = (ImageView) view.findViewById(R.id.write_weather3);
        write_weather4 = (ImageView) view.findViewById(R.id.write_weather4);
        write_diary = (EditText) view.findViewById(R.id.write_diary);
        write_for_today = (EditText) view.findViewById(R.id.write_for_today);
        write_for_tommorow = (EditText) view.findViewById(R.id.write_for_tommorow);

        write_date_picker.setVisibility(View.GONE);

        //날짜 기본 설정(오늘 날짜)
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
        SimpleDateFormat weekdayFormat = new SimpleDateFormat("EE", Locale.getDefault());

        String year = yearFormat.format(currentTime);
        String month = monthFormat.format(currentTime);
        String day = dayFormat.format(currentTime);
        String weekDay = weekdayFormat.format(currentTime);


        write_year.setText(year);
        write_month.setText(month);
        write_day.setText(day);
        write_weekday.setText(weekDay);


        //날짜 선택하기 버튼 클릭 시
        write_date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(write_date_button.getText().toString().equals("날짜 선택하기")){
                    write_date_picker.setVisibility(View.VISIBLE);
                    write_date_button.setText("날짜 선택완료");
                }
                else if(write_date_button.getText().toString().equals("날짜 선택완료")){
                    write_date_picker.setVisibility(View.GONE);
                    write_date_button.setText("날짜 선택하기");
                    String setYear = Integer.toString(write_date_picker.getYear());
                    String setMonth = Integer.toString(write_date_picker.getMonth()+1);
                    String setDay = Integer.toString(write_date_picker.getDayOfMonth());
                    String inputDate=setYear+setMonth+setDay;
                    System.out.println(inputDate);
                    String setWeekDay = Toweekday(inputDate);
                    System.out.println(setWeekDay);

                    write_year.setText(setYear);
                    write_month.setText(setMonth);
                    write_day.setText(setDay);
                    write_weekday.setText(setWeekDay);
                }

            }
        });

        //날씨 선택 시 강조
        write_weather1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                System.out.println("날씨클릭됨");
                write_weather1.setImageResource(R.mipmap.checked);
                picked_weather=1;

                write_weather2.setImageResource(R.mipmap.ic_launcher);
                write_weather3.setImageResource(R.mipmap.ic_launcher);
                write_weather4.setImageResource(R.mipmap.ic_launcher);
            }
        });

        write_weather2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                write_weather2.setImageResource(R.mipmap.checked);
                picked_weather=2;

                write_weather1.setImageResource(R.mipmap.ic_launcher);
                write_weather3.setImageResource(R.mipmap.ic_launcher);
                write_weather4.setImageResource(R.mipmap.ic_launcher);
            }
        });

        write_weather3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                write_weather3.setImageResource(R.mipmap.checked);
                picked_weather=3;

                write_weather1.setImageResource(R.mipmap.ic_launcher);
                write_weather2.setImageResource(R.mipmap.ic_launcher);
                write_weather4.setImageResource(R.mipmap.ic_launcher);
            }
        });

        write_weather4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                write_weather4.setImageResource(R.mipmap.checked);
                picked_weather=4;

                write_weather1.setImageResource(R.mipmap.ic_launcher);
                write_weather2.setImageResource(R.mipmap.ic_launcher);
                write_weather3.setImageResource(R.mipmap.ic_launcher);
            }
        });

        // Inflate the layout for this fragment
        return view;
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
}