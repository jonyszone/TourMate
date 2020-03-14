package com.nub.tourmate.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.nub.tourmate.LoginActivity;
import com.nub.tourmate.R;

public class splashActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        firebaseAuth = FirebaseAuth.getInstance();


        Thread thread =new Thread(){
            @Override
            public void run() {
                try {

                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);


                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();


    }
}
