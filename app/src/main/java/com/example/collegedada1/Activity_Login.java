package com.example.collegedada1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Activity_Login extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout rellay1, rellay2;
    private Button LSignUp,LLogin;
    private EditText LUsername,LPassword;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout0);
        layout.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent ev)
            {
                hideKeyboard(view);
                return false;
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(this, MainActivity.class));
        }

        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);

        handler.postDelayed(runnable, 1700);

        progressDialog = new ProgressDialog(this);

        LSignUp=(Button)findViewById(R.id.lsignup);
        LLogin=(Button)findViewById(R.id.llogin);
        LUsername=(EditText)findViewById(R.id.lusername);
        LPassword=(EditText)findViewById(R.id.lpassword);
        LSignUp.setOnClickListener(this);
        LLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == LSignUp){
            startActivity(new Intent(Activity_Login.this, SignUpActivity.class));
        }
        if (view == LLogin){
            userLogin();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
        startActivity(intent);
    }

    private void userLogin() {
        String username = LUsername.getText().toString().trim();
        String password = LPassword.getText().toString().trim();

        if (TextUtils.isEmpty(username)){
            Toast.makeText(this, "Please Enter Username", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Enter Password",Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Logging In...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(username,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else {
                            Toast.makeText(Activity_Login.this,"Log In Unsuccessful Please Try Again",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    protected void hideKeyboard(View view)
    {
        InputMethodManager in = (InputMethodManager) getSystemService( Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow( view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
