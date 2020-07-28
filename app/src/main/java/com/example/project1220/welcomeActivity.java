package com.example.project1220;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class welcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome2);

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try{
                    Thread.sleep(2000);
                    startActivity(new Intent().setClass(welcomeActivity.this,MainActivity.class));
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
