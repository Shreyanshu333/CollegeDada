package com.example.collegedada1;

import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FeedFragment extends Fragment{


    List<Product> productList;

    //the recyclerview
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.nav_feed_layout, container, false);

        recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        //initializing the productlist
        productList = new ArrayList<>();


        //adding some items to our list
        productList.add(
                new Product(
                        1,
                        "ENERGIA IN 3 WEEKS!!!",
                        "1ST-3RD MARCH",
                        "SPORTS",
                        "Annual Sports Fest",
                        R.drawable.about_2));

        productList.add(
                new Product(
                        1,
                        "Institute Day",
                        "1st Nov",
                        "Institute",
                        "Annual Presentation Day",
                        R.drawable.institute));

        productList.add(
                new Product(
                        1,
                        "END SEM EXAMINATION POSTPONED",
                        "15TH-30TH MAY",
                        "ACADEMIC",
                        "ANNUAL EXAMINATIONS",
                        R.drawable.event_14));

        //creating recyclerview adapter
        ProductAdapter adapter = new ProductAdapter(getActivity(), productList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
        return rootView;
    }

}