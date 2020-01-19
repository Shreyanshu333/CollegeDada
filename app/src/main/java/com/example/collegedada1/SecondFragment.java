package com.example.collegedada1;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class SecondFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<FruitModel> imageModelArrayList;
    private FruitAdapter adapter;

    private int[] myImageList = new int[]{R.drawable.maths, R.drawable.chemistry,R.drawable.physics, R.drawable.petroleum};
    private String[] myImageNameList = new String[]{"Maths","Chemistry" ,"Physics","Petroleum"};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)  {

        View rootView = inflater.inflate(R.layout.second_layout, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);
        imageModelArrayList = eatFruits();
        adapter = new FruitAdapter(getActivity(), imageModelArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        return rootView;
    }

    private ArrayList<FruitModel> eatFruits(){

        ArrayList<FruitModel> list = new ArrayList<>();

        for(int i = 0; i < 4; i++){
            FruitModel fruitModel = new FruitModel();
            fruitModel.setName(myImageNameList[i]);
            fruitModel.setImage_drawable(myImageList[i]);
            list.add(fruitModel);
        }
        return list;
    }
}
