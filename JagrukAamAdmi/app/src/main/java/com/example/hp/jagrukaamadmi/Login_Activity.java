package com.example.hp.jagrukaamadmi;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class Login_Activity extends AppCompatActivity {
    Button callsignup, calldash ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_);

callsignup =  findViewById(R.id.signup);

calldash = findViewById(R.id.login_btn);



callsignup.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent =  new Intent(Login_Activity.this,SignUp.class);

        startActivity(intent);














    }
});

calldash.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


        Intent intent = new Intent(Login_Activity.this , Dashboard.class);
        startActivity(intent);
    }
});

    }
}
