package com.example.daysduk;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.daysduk.model.PostItem;
import com.google.gson.Gson;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CalendarFragment extends Fragment {

    //변수 선언
    MaterialCalendarView calendarView;
    TextView cal_year;
    TextView cal_month;
    TextView cal_day;
    TextView cal_freq;

    final SelectedDayDecorator selectedDayDecorator = new SelectedDayDecorator();
    ArrayList <String> freqcal = new ArrayList<String>();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        calendarView = view.findViewById(R.id.calendar_view);
        cal_year = view.findViewById(R.id.cal_year);
        cal_month = view.findViewById(R.id.cal_month);
        cal_day = view.findViewById(R.id.cal_day);
        cal_freq = view.findViewById(R.id.cal_freq);


        //get date Data from MainActivity
        Bundle bundle = this.getArguments();
        if(bundle!=null){
            freqcal = bundle.getStringArrayList("fromMain");
        } else if(bundle==null){
            System.out.println("Null");
        }


        //첫 화면에 보여질 현재 date&freq 초기 설정
        long now = System.currentTimeMillis();
        Date currentdate = new Date(now);

        String current_year = Integer.toString(currentdate.getYear()+1900);
        String current_month = Integer.toString(currentdate.getMonth()+1);
        String current_day = Integer.toString(currentdate.getDate());
        String current_dateString = current_year+"-"+current_month+"-"+current_day;
        int current_count=0;

        for(int i = 0; i<=freqcal.size()-1; i++){
            String getdate = freqcal.get(i);
            String splitedDate= getdate.split("T")[0];

            if(splitedDate.equals(current_dateString)){
                current_count = current_count+1;
            }
        }

        cal_freq.setText(Integer.toString(current_count));
        cal_year.setText(current_year);
        cal_month.setText(current_month);
        cal_day.setText(current_day);


        //날짜 선택시 쓰여진 일기 수 반영
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull @NotNull MaterialCalendarView materialCalendarView, @NonNull @NotNull CalendarDay calendarDay, boolean b) {
                int year = materialCalendarView.getSelectedDate().getYear();
                int month = materialCalendarView.getSelectedDate().getMonth()+1;
                int day = materialCalendarView.getSelectedDate().getDay();
                int count = 0;

                try {
                    String dateString = year+"-"+month+"-"+day;

                    Date date = simpleDateFormat.parse(dateString);
                    calendarView.setCurrentDate(date);

                    //TextView 변경
                    cal_year.setText(Integer.toString(year));
                    cal_month.setText(Integer.toString(month));
                    cal_day.setText(Integer.toString(day));

                    for(int i = 0; i<=freqcal.size()-1; i++){
                        String getdate = freqcal.get(i);
                        //날짜 분할
                        String splitedDate= getdate.split("T")[0];
                        if(splitedDate.equals(dateString)){
                            count = count+1;
                        }
                    }

                    cal_freq.setText(Integer.toString(count));

                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });


        calendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();



        calendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                selectedDayDecorator
        );


        return view;
    }

}