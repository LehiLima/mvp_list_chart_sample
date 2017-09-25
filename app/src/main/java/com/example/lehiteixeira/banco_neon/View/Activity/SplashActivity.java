package com.example.lehiteixeira.banco_neon.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.lehiteixeira.banco_neon.BasePresenterContract;
import com.example.lehiteixeira.banco_neon.Data.DataManager;
import com.example.lehiteixeira.banco_neon.NeonApplication;
import com.example.lehiteixeira.banco_neon.Presenter.BasePresenter;
import com.example.lehiteixeira.banco_neon.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity implements BasePresenterContract{

    private BasePresenter mBasePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        mBasePresenter = new BasePresenter(DataManager.getInstance(getBaseContext()) , "Lehi Lima Fonseca Teixeira" , "lehilima@gmail.com",this);
        ((NeonApplication) this.getApplication()).setPersons();
        mBasePresenter.start();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }


    @Override
    public void start() {

    }
}