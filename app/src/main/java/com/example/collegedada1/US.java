package com.example.collegedada1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class US extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_us );
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(US.this, MainActivity.class);
        startActivity(intent);
    }
}