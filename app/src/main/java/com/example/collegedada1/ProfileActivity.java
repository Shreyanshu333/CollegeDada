package com.example.collegedada1;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout rellay1;
    private EditText PName;
    private EditText PRollNo;
    private EditText PPhoneNo;
    private Button PFnish;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;


    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout2);
        layout.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent ev)
            {
                hideKeyboard(view);
                return false;
            }
        });
        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        handler.postDelayed(runnable, 1700);

        databaseReference = FirebaseDatabase.getInstance().getReference("Student Info");
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, Activity_Login.class));
        }

        PName=(EditText)findViewById(R.id.pname);
        PRollNo=(EditText)findViewById(R.id.prollno);
        PPhoneNo=(EditText)findViewById(R.id.pphoneno);
        PFnish=(Button)findViewById(R.id.pfnish);
        PFnish.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view ==PFnish){
            saveUserInfromation();
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
        startActivity(i);
    }


    private void saveUserInfromation() {
        String Name = PName.getText().toString().trim().toUpperCase();
        String RollNo = PRollNo.getText().toString().trim().toUpperCase();
        String PhoneNo = PPhoneNo.getText().toString().trim().toUpperCase();
        String CPI = "0";
        String LinkedIn = "0";
        String Semester = "0";
        String SPI = "0";
        String Project = "";
        String LAB = "";
        String Quiz1 = "";
        String Quiz2 = "";
        String TotalLectures = "0";
        String Present = "0";
        String Required ="0";
        String Total = "0";
        String Obtained = "0";


        UserInformation userInformation =new UserInformation( Name, RollNo, PhoneNo, CPI, SPI,Semester,LinkedIn);
        MarksInformation marksInformation =new MarksInformation(Total,Obtained);
        QuizInformation quizInformation =new QuizInformation(Quiz1,Quiz2);
        AttendanceInformation attendanceInformation =new AttendanceInformation(TotalLectures,Present,Required);
        InternalAssessmentInformation internalAssessmentInformation =new InternalAssessmentInformation(Project,LAB);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).child("About").setValue(userInformation);
        databaseReference.child(user.getUid()).child("Maths").child("InternalAssessment").setValue(internalAssessmentInformation);
        databaseReference.child(user.getUid()).child("Maths").child("Quiz").setValue(quizInformation);
        databaseReference.child(user.getUid()).child("Maths").child("MidSem").setValue(marksInformation);
        databaseReference.child(user.getUid()).child("Maths").child("EndSem").setValue(marksInformation);
        databaseReference.child(user.getUid()).child("Maths").child("Attendance").setValue(attendanceInformation);
        databaseReference.child(user.getUid()).child("Chemistry").child("InternalAssessment").setValue(internalAssessmentInformation);
        databaseReference.child(user.getUid()).child("Chemistry").child("Quiz").setValue(quizInformation);
        databaseReference.child(user.getUid()).child("Chemistry").child("MidSem").setValue(marksInformation);
        databaseReference.child(user.getUid()).child("Chemistry").child("EndSem").setValue(marksInformation);
        databaseReference.child(user.getUid()).child("Chemistry").child("Attendance").setValue(attendanceInformation);
        databaseReference.child(user.getUid()).child("Physics").child("InternalAssessment").setValue(internalAssessmentInformation);
        databaseReference.child(user.getUid()).child("Physics").child("Quiz").setValue(quizInformation);
        databaseReference.child(user.getUid()).child("Physics").child("MidSem").setValue(marksInformation);
        databaseReference.child(user.getUid()).child("Physics").child("EndSem").setValue(marksInformation);
        databaseReference.child(user.getUid()).child("Physics").child("Attendance").setValue(attendanceInformation);
        databaseReference.child(user.getUid()).child("Petroleum").child("InternalAssessment").setValue(internalAssessmentInformation);
        databaseReference.child(user.getUid()).child("Petroleum").child("Quiz").setValue(quizInformation);
        databaseReference.child(user.getUid()).child("Petroleum").child("MidSem").setValue(marksInformation);
        databaseReference.child(user.getUid()).child("Petroleum").child("EndSem").setValue(marksInformation);
        databaseReference.child(user.getUid()).child("Petroleum").child("Attendance").setValue(attendanceInformation);

        Toast.makeText(this, "Information Saved", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this,Photo.class));
    }
    protected void hideKeyboard(View view)
    {
        InputMethodManager in = (InputMethodManager) getSystemService( Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow( view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}