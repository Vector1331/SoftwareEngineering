package com.example.daysduk;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daysduk.model.PostItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    RecyclerView contentRecyclerView;
    LinearLayoutManager linearLayoutManager;
    ContentsAdapter adapter;
    ArrayList<PostItem> postList;

    private static final String BASE_URL = "http://192.168.219.104:8000/api/";
    private MyAPI mMyAPI;

    String diary_weather = "1"; //default값
    String diary_img;
    String diary_date = "";
    String diary_todayme = "";
    String diary_tomorrowme = "";
    String diary_content ="default content";
    String diary_title="default title";
    int diary_id;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initMyAPI(BASE_URL);
        Log.d(TAG,"GET");

        postList = new ArrayList<>();


        contentRecyclerView = view.findViewById(R.id.list);
        linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        adapter = new ContentsAdapter(postList);
        contentRecyclerView.setLayoutManager(linearLayoutManager);
        contentRecyclerView.setAdapter(adapter);
        Call<List<PostItem>> getCall = mMyAPI.get_diary();

        //test
        PostItem postItem1 = new PostItem(1,"hi", "2021-01-01T", "1",
                "안녕안녕", "오늘", "내일",null);
        PostItem postItem2 = new PostItem(2,"hi2", "2021-01-02T", "3",
                "안녕안녕2", "오늘2", "내일2",null);

        adapter.addItem(postItem1);
        adapter.addItem(postItem2);

        getCall.enqueue(new Callback<List<PostItem>>() {
            @Override
            public void onResponse(Call<List<PostItem>> call, Response<List<PostItem>> response) {
                if( response.isSuccessful()){
                    List<PostItem> result = response.body();
                    //for(PostItem item : result){
                    for(int i=0; i<result.size(); i++){

                        diary_weather = result.get(i).getWeather();
                        diary_img = result.get(i).getImage();
                        diary_title = result.get(i).getTitle();
                        diary_content = result.get(i).getContent();
                        diary_id = result.get(i).getDiary_id();
                        diary_date = result.get(i).getDate();
                        diary_todayme = result.get(i).getTodayme();
                        diary_tomorrowme = result.get(i).getTomorrowme();

                        PostItem postItem = new PostItem(diary_id,diary_title, diary_date, diary_weather,
                                diary_content, diary_todayme, diary_tomorrowme, diary_img);
                        adapter.addItem(postItem);
                    }

                    adapter.notifyDataSetChanged();


                }else {
                    Log.d(TAG,"Status Code : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<PostItem>> call, Throwable t) {
                Log.d(TAG,"Fail msg : " + t.getMessage());
            }
        });
        
        
        /*
        postList.add(new PostItem(
                "2021", "11", "8", "월",
                getResources().getDrawable(R.drawable.ic_launcher_background),
                getResources().getDrawable(R.mipmap.gallery2),
                "일기 내용 아이템 추가 테스트3"
        ));
        postList.add(new PostItem(
                "2021", "11", "7", "일",
                getResources().getDrawable(R.drawable.ic_launcher_background),
                getResources().getDrawable(R.mipmap.gallery2),
                "일기 내용 아이템 추가 테스트2"
        ));
        postList.add(new PostItem(
                "2021", "11", "6", "토",
                getResources().getDrawable(R.drawable.ic_launcher_background),
                getResources().getDrawable(R.mipmap.gallery2),
                "일기 내용 아이템 추가 테스트1"

                  
        ));*/
        return view;
    }
}