package com.example.collegedada1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PapersFragment extends AppCompatActivity {
    DatabaseReference reference;
    RecyclerView recyclerView;
    PaperAdapter paperAdapter;
    private ProgressDialog progressDialog;
    ArrayList<Papers> list;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_papers_fragment);

        recyclerView = (RecyclerView) findViewById(R.id.paper_recycler_view);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent( this, MainActivity.class));
        }

        reference = FirebaseDatabase.getInstance().getReference("Papers");
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<Papers>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Papers p = dataSnapshot1.getValue(Papers.class);
                    list.add(p);
                }
                paperAdapter = new PaperAdapter(PapersFragment.this,list);
                recyclerView.setAdapter(paperAdapter);
                progressDialog.dismiss();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( PapersFragment.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PapersFragment.this, MainActivity.class);
        startActivity(intent);
    }
}