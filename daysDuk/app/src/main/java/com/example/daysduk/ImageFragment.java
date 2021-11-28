package com.example.daysduk;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


public class ImageFragment extends Fragment {

    //변수 선언
    Button image_date_button;
    LinearLayout image_date_tv;
    TextView image_year;
    TextView image_month;
    TextView image_day;
    TextView image_weekday;
    DatePicker image_date_picker;
    ImageView image_view;
    ImageButton image_add_Button;
    TextView image_hint_tv;
    EditText image_note;
    Button image_register;

    String day="";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_image, container, false);
        //변수 선언
        image_date_button = (Button) view.findViewById(R.id.image_date_Button);
        image_date_tv = (LinearLayout) view.findViewById(R.id.image_date_tv);
        image_year = (TextView) view.findViewById(R.id.image_year);
        image_month = (TextView) view.findViewById(R.id.image_month);
        image_day = (TextView) view.findViewById(R.id.image_day);
        image_weekday = (TextView) view.findViewById(R.id.image_weekday);
        image_date_picker = (DatePicker) view.findViewById(R.id.image_date_picker);
        image_view = (ImageView) view.findViewById(R.id.image_add_Button);
        image_add_Button = (ImageButton) view.findViewById(R.id.image_add_Button);
        image_hint_tv = (TextView) view.findViewById(R.id.image_hint_tv);
        image_note = (EditText) view.findViewById(R.id.image_note);
        image_register = (Button) view.findViewById(R.id.image_register);

        image_date_picker.setVisibility(View.GONE);

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


        image_year.setText(year);
        image_month.setText(month);
        image_day.setText(day);
        image_weekday.setText(weekDay);


        //날짜 선택하기 버튼 클릭 시
        image_date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(image_date_button.getText().toString().equals("날짜 선택하기")){
                    image_date_picker.setVisibility(View.VISIBLE);
                    image_date_button.setText("날짜 선택완료");
                }
                else if(image_date_button.getText().toString().equals("날짜 선택완료")){
                    image_date_picker.setVisibility(View.GONE);
                    image_date_button.setText("날짜 선택하기");
                    String setYear = Integer.toString(image_date_picker.getYear());
                    String setMonth = Integer.toString(image_date_picker.getMonth()+1);
                    String setDay = Integer.toString(image_date_picker.getDayOfMonth());
                    String inputDate=setYear+setMonth+setDay;
                    String setWeekDay = Toweekday(inputDate);

                    image_year.setText(setYear);
                    image_month.setText(setMonth);
                    image_day.setText(setDay);
                    image_weekday.setText(setWeekDay);
                }

            }
        });

        //사진 등록하기
        image_add_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageUpload(view);
            }
        });


        //등록하기 버튼 클릭 시
        image_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        // Inflate the layout for this fragment
        return view;
    }

    //사진 등록 메소드
    //이미지 업로드
    public void imageUpload(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, 101);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == 101)
        {
            if(resultCode == RESULT_OK)
            {
                try{
                    InputStream in = getContext().getContentResolver().openInputStream(data.getData());

                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    image_add_Button.setVisibility(View.GONE);
                    image_hint_tv.setVisibility(View.GONE);
                    image_view.setVisibility(View.VISIBLE);
                    image_view.setImageBitmap(img);
                }catch(Exception e)
                {

                }
            }
            else if(resultCode == RESULT_CANCELED)
            {
                Toast.makeText(getContext(), "사진 선택 취소 ", Toast.LENGTH_LONG).show();
            }
        }
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