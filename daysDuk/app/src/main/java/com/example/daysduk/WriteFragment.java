package com.example.daysduk;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daysduk.model.PostItem;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.example.daysduk.R.mipmap.ic_launcher;
import static com.example.daysduk.R.mipmap.ic_launcher_round;

public class WriteFragment extends Fragment {

    //변수 선언 -xml
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
    EditText write_diaryTitle;
    EditText write_for_today;
    EditText write_for_tommorow;
    Button write_register;
    ImageView write_upload_img;
    ImageView write_picked_imgview;
    String day = "";
    int picked_weather;

    private MyAPI mMyAPI;
    public static final String BASE_URL = "http://192.168.219.104:8000/api/";
    public final  String TAG = getClass().getSimpleName();
    //post요청을 위한 필드변수 선언
    String diary_weather = "1"; //default값
    String diary_img;
    String diary_date = "";
    String diary_todayme = "";
    String diary_tomorrowme = "";
    String diary_content ="default content";
    String diary_title="default title";
    int diary_id;
    byte[] byterray;


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
        write_diaryTitle = (EditText) view.findViewById(R.id.write_diaryTitle);
        write_for_today = (EditText) view.findViewById(R.id.write_for_today);
        write_for_tommorow = (EditText) view.findViewById(R.id.write_for_tommorow);
        write_register = (Button)view.findViewById(R.id.write_register);
        write_upload_img = (ImageView)view.findViewById(R.id.write_upload_img);
        write_picked_imgview = (ImageView)view.findViewById(R.id.write_picked_imgview);

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

        //레트로핏 API 생성 메소드 호출
        initMyAPI(BASE_URL);

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

                write_weather2.setImageResource(R.mipmap.wind);
                write_weather3.setImageResource(R.mipmap.rain);
                write_weather4.setImageResource(R.mipmap.lightning);
            }
        });

        write_weather2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                write_weather2.setImageResource(R.mipmap.checked);
                picked_weather=2;

                write_weather1.setImageResource(R.mipmap.sun);
                write_weather3.setImageResource(R.mipmap.rain);
                write_weather4.setImageResource(R.mipmap.lightning);
            }
        });

        write_weather3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                write_weather3.setImageResource(R.mipmap.checked);
                picked_weather=3;

                write_weather1.setImageResource(R.mipmap.sun);
                write_weather2.setImageResource(R.mipmap.wind);
                write_weather4.setImageResource(R.mipmap.lightning);
            }
        });

        write_weather4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                write_weather4.setImageResource(R.mipmap.checked);
                picked_weather=4;

                write_weather1.setImageResource(R.mipmap.sun);
                write_weather2.setImageResource(R.mipmap.wind);
                write_weather3.setImageResource(R.mipmap.rain);
            }
        });

        //사진 추가 버튼 클릭 시
        write_upload_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageUpload(view);
            }
        });

        //등록하기 버튼 클릭 시
        write_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"POST");
                diary_weather = String.valueOf(picked_weather);
                diary_title = write_diaryTitle.getText().toString();
                diary_content = write_diary.getText().toString();
                diary_todayme = write_for_today.getText().toString();
                diary_tomorrowme = write_for_tommorow.getText().toString();
                //Diary내용 Post하기위한 객체 생성
                PostItem item = new PostItem(diary_id,diary_title, diary_date, diary_weather,
                        diary_content, diary_todayme, diary_tomorrowme,diary_img);
                //Service이용해서 CALL 보낸다
                Call<PostItem> postCall = mMyAPI.post_diary(item);
                //Call 객체 네트워킹 시킴
                postCall.enqueue(new Callback<PostItem>() {
                    @Override
                    //반응 오면 onResponse
                    public void onResponse(Call<PostItem> call, Response<PostItem> response) {
                        //반응이 성공이면
                        if(response.isSuccessful()){
                            Log.d(TAG,"등록 완료");
                        }else {
                            //안될 경우 item에 저장값 보기위한 로그출력
                            Log.d(TAG,new Gson().toJson(item));
                            //상태코드 로그 출력
                            Log.d(TAG,"Status Code : " + response.code());
                            Log.d(TAG,new Gson().toJson(response.errorBody()));
                            Log.d(TAG,new Gson().toJson(call.request().body()));

                        }
                    }

                    @Override
                    public void onFailure(Call<PostItem> call, Throwable t) {
                        Log.d(TAG,"Fail msg : " + t.getMessage());
                    }
                });

            }
        });

        // Inflate the layout for this fragment
        return view;
    }

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
                    //데이터 타입 data.getData() : uri
                    InputStream in = getContext().getContentResolver().openInputStream(data.getData());
                    //데이터 byteArray 이진파일로 바꾸기
                    //byte[] inputData = getBytes(in);    //inputData를 diary_img로 해서 POST요청 보내기
                    //diary_img = inputData;
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    write_picked_imgview.setVisibility(View.VISIBLE);
                    write_picked_imgview.setImageBitmap(img);
                    //Bitmap에서 byteArray
                    byterray = bitmapToByteArray(img);
                    //byteArray를 base64로 인코딩해서 diary_img에 넣기
                    diary_img = Base64.encodeToString(byterray,Base64.NO_WRAP);
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
    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    // Bitmap을 Byte로 변환
    public byte[] bitmapToByteArray( Bitmap bitmap ) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream() ;
        bitmap.compress( Bitmap.CompressFormat.JPEG, 100, stream) ;
        byte[] byteArray = stream.toByteArray() ;
        return byteArray ;
    }
}