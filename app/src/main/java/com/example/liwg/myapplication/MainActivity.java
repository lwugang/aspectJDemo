package com.example.liwg.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @CheckLogin
    public void checkLogin(View v){
        Log.e("onCheckLogin", "onCheckLogin: 登录成功" );
    }
}
