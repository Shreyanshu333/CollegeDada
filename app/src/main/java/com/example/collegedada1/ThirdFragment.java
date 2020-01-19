package com.example.collegedada1;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ThirdFragment extends Fragment {

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private TextView TotalLectureText,RequiredText,PresentText;
    float attendancePercentage[] = new float[4];
    float case1[]= {90f,80f,72.5f,82.5f};
    float case2[]= {};
    float case3[]= {};
    String Lable [] ={"Physics","Chemistry","Maths","Petroleum"};


    View myView;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.third_layout, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user.getEmail().equals( "eche18001@rgipt.ac.in" )){
            attendancePercentage =case1.clone();
        }else if (user.getEmail().equals( "epe18044@rgipt.ac.in" )){
            attendancePercentage =case2.clone();
        }else if (user.getEmail().equals( "eche18002@rgipt.ac.in" )){
            attendancePercentage =case3.clone();
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("Student Info");
        final Spinner mySpinner = (Spinner) v.findViewById(R.id.spinner1);
        TotalLectureText = (TextView)v.findViewById( R.id.totallectureattendance );
        PresentText = (TextView)v.findViewById( R.id.presentattendance );
        RequiredText = (TextView)v.findViewById( R.id.requiredattendance );
        PieChart chart =(PieChart) v.findViewById(R.id.chartpie);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (i == 1) {
                    databaseReference.child(user.getUid()).child("Physics").child("Attendance").addValueEventListener( new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            TotalLectureText.setText(dataSnapshot.child("TotalLectures").getValue(String.class));
                            PresentText.setText(dataSnapshot.child("Present").getValue(String.class));
                            RequiredText.setText(dataSnapshot.child("Required").getValue(String.class));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    } );

                } else if (i == 2) {
                    databaseReference.child(user.getUid()).child("Chemistry").child("Attendance").addValueEventListener( new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            TotalLectureText.setText(dataSnapshot.child("TotalLectures").getValue(String.class));
                            PresentText.setText(dataSnapshot.child("Present").getValue(String.class));
                            RequiredText.setText(dataSnapshot.child("Required").getValue(String.class));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    } );

                }else if (i == 3) {
                    databaseReference.child(user.getUid()).child("Maths").child("Attendance").addValueEventListener( new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            TotalLectureText.setText(dataSnapshot.child("TotalLectures").getValue(String.class));
                            PresentText.setText(dataSnapshot.child("Present").getValue(String.class));
                            RequiredText.setText(dataSnapshot.child("Required").getValue(String.class));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    } );

                }else if (i == 4) {
                    databaseReference.child(user.getUid()).child("Petroleum").child("Attendance").addValueEventListener( new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            TotalLectureText.setText(dataSnapshot.child("TotalLectures").getValue(String.class));
                            PresentText.setText(dataSnapshot.child("Present").getValue(String.class));
                            RequiredText.setText(dataSnapshot.child("Required").getValue(String.class));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    } );

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        List<PieEntry> pieEntries = new ArrayList<>(  );
        for (int i = 0 ; i<4;i++){
            pieEntries.add( new PieEntry( attendancePercentage[i],Lable[i] ) );

        }

        PieDataSet pieDataSet = new PieDataSet( pieEntries, "Attendance Percentage" );
        pieDataSet.setColors( ColorTemplate.COLORFUL_COLORS );
        PieData data = new PieData( pieDataSet );
        data.setValueTextSize(30f);
        chart.setData( data );
        chart.animateXY( 1000 ,2000);
        chart.invalidate();
        return v;
    }
}