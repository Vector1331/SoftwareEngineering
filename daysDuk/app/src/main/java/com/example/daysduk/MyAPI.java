package com.example.daysduk;

import com.example.daysduk.model.PostItem;
import com.example.daysduk.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyAPI {
    //웹서버로 부터 diary 데이터 가져오기 -GET
    @GET("diary/")
    Call<List<PostItem>> get_diary();
    @GET("calendar/")
    Call<List<PostItem>> get_calendar();
    @GET("user/")
    Call<List<PostItem>> get_user();

    //웹서버로 데이터 내보내기 - POST
    @POST("diary/")
    Call<PostItem> post_diary( @Body PostItem diary);
    @POST("user/")
    Call<User> post_user( @Body User user);

    //구현 전
    @PATCH("diary/{pk}/")
    Call<PostItem> patch_diary(@Path("pk") int pk, @Body PostItem diary);
    //구현 전
    @DELETE("diary/{pk}/")
    Call<PostItem> delete_diary(@Path("pk") int pk);

    @GET("diary/{pk}/")
    Call<PostItem> get_diary_pk(@Path("pk") int pk);
}
