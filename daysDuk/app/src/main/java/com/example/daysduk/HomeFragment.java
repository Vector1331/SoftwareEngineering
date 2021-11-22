package com.example.daysduk;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daysduk.model.PostItem;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView contentRecyclerView;
    LinearLayoutManager linearLayoutManager;
    ContentsAdapter adapter;
    ArrayList<PostItem> items = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        contentRecyclerView = view.findViewById(R.id.list);
        linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        adapter = new ContentsAdapter(items);
        contentRecyclerView.setLayoutManager(linearLayoutManager);
        contentRecyclerView.setAdapter(adapter);

//        items.add(new ContentsInfo(
//                "2021", "11", "8", "월",
//                getResources().getDrawable(R.drawable.ic_launcher_background),
//                getResources().getDrawable(R.mipmap.gallery2),
//                "일기 내용 아이템 추가 테스트3"
//        ));
//        items.add(new ContentsInfo(
//                "2021", "11", "7", "일",
//                getResources().getDrawable(R.drawable.ic_launcher_background),
//                getResources().getDrawable(R.mipmap.gallery2),
//                "일기 내용 아이템 추가 테스트2"
//        ));
//        items.add(new ContentsInfo(
//                "2021", "11", "6", "토",
//                getResources().getDrawable(R.drawable.ic_launcher_background),
//                getResources().getDrawable(R.mipmap.gallery2),
//                "일기 내용 아이템 추가 테스트1"
//        ));
        return view;
    }
}