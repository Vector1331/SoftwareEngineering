package com.example.daysduk;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daysduk.model.PostItem;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CalendarFragment extends Fragment {

    ArrayList<PostItem> postList;   //객체 담을 그릇
    //레트로핏 Call객체 생성을 위한 서버버기본주소
    private static final String BASE_URL = "http://192.168.219.103:8000/api/";
    private MyAPI mMyAPI;   //API SERVICE객체 생성
    //response 받아온 객체 저장할 필드 선언 및 초기화 단계
    String diary_weather = "1"; //default값
    String diary_img;
    String diary_date = "";
    String diary_todayme = "";
    String diary_tomorrowme = "";
    String diary_content ="default content";
    String diary_title="default title";
    int diary_id;

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

    /*// TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CalendarFragment() {
        // Required empty public constructor
    }


    public static CalendarFragment newInstance(String param1, String param2) {
        CalendarFragment fragment = new CalendarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_calendar, container, false);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initMyAPI(BASE_URL); //객체 생성
        Log.d(TAG,"GET");
        postList = new ArrayList<>();
        Call<List<PostItem>> getCall = mMyAPI.get_diary();  //GET요청 보낼 CALL 객체 생성 , response는 LIST타입

        getCall.enqueue(new Callback<List<PostItem>>() {
            @Override
            public void onResponse(Call<List<PostItem>> call, Response<List<PostItem>> response) {
                if( response.isSuccessful()){
                    List<PostItem> result = response.body();
                    for(int i=0; i<result.size(); i++){
                        diary_weather = result.get(i).getWeather();
                        diary_img = result.get(i).getImage();
                        diary_title = result.get(i).getTitle();
                        diary_content = result.get(i).getContent();
                        diary_id = result.get(i).getDiary_id();
                        diary_date = result.get(i).getDate();
                        diary_todayme = result.get(i).getTodayme();
                        diary_tomorrowme = result.get(i).getTomorrowme();

                        System.out.println("시작 **weather:"+diary_weather+
                                "  **image:"+diary_img+
                                "  **title:"+diary_title+
                                "  **content:"+diary_content+
                                "  **id:"+diary_id+
                                "  **date:"+diary_date+
                                "  **todayme:"+diary_todayme+
                                "  **tomorrowme:"+diary_tomorrowme +"** 끝");
                        PostItem postItem = new PostItem(diary_id,diary_title, diary_date, diary_weather,
                                diary_content, diary_todayme, diary_tomorrowme, diary_img);
                    }

                }else {
                    Log.d(TAG,"Status Code : " + response.code());
                    Log.d(TAG,new Gson().toJson(response.errorBody()));
                    Log.d(TAG,new Gson().toJson(call.request().body()));
                }
            }

            @Override
            public void onFailure(Call<List<PostItem>> call, Throwable t) {
                Log.d(TAG,"Fail msg : " + t.getMessage());
            }
        });



        return view;
    }
}