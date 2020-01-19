package com.example.collegedada1;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;


public class FirstFragment extends Fragment{

    private ImageView FProfilePic;
    private TextView FName,FCPI,FPhone,FSPI,FSemester,FEmail,FLinkedin;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.first_layout, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference("Student Info");
        firebaseAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        FProfilePic =(ImageView)myView.findViewById(R.id.ffprofilepic);
        FName =(TextView)myView.findViewById(R.id.ffname);
        FEmail =(TextView)myView.findViewById(R.id.ffemail);
        FPhone =(TextView)myView.findViewById(R.id.ffphone);
        FCPI =(TextView)myView.findViewById(R.id.ffcpi);
        FSPI =(TextView)myView.findViewById(R.id.ffspi);
        FSemester =(TextView)myView.findViewById(R.id.ffsemester);
        FLinkedin =(TextView)myView.findViewById(R.id.link_din);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        FEmail.setText(user.getEmail());
        databaseReference.child(user.getUid()).child("About").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                FName.setText(dataSnapshot.child("Name").getValue(String.class));
                FSemester.setText(dataSnapshot.child("Semester").getValue(String.class));
                FLinkedin.setText(dataSnapshot.child("LinkedIn").getValue(String.class));
                FPhone.setText(dataSnapshot.child("PhoneNo").getValue(String.class));
                FCPI.setText(dataSnapshot.child("CPI").getValue(String.class));
                FSPI.setText(dataSnapshot.child("SPI").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        String userUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        storageReference.child("Profile Pic/").child(userUid).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Got the download URL for 'users/me/profile.png'
                Picasso.get().load(uri).into(FProfilePic);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
        return myView;
    }
}