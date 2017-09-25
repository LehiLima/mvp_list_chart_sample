package com.example.lehiteixeira.banco_neon.View.Activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.lehiteixeira.banco_neon.Custom.CustomDialogSucessFail;
import com.example.lehiteixeira.banco_neon.R;
import com.example.lehiteixeira.banco_neon.Util.Util;

import butterknife.Bind;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.userimage)
    ImageView ivUserImage;

    @Bind(R.id.userimagecontainer)
    RelativeLayout userimagecontainer;

    @OnClick(R.id.btntransferlog)
    public void GoToTransferLog() {
        Intent intent = new Intent(MainActivity.this, TransferLogActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btnSendMoney)
    public void GoToSendMoney() {
        Intent intent = new Intent(MainActivity.this, SendMoneyActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        overridePendingTransition(R.anim.alpha_out_right ,R.anim.alpha_in_left);
        initialize();
        ckeckConnection();

        animatedUserImage();
    }

    private void initialize(){
        setUserImage(R.drawable.eu);
    }

    private void setUserImage(int img){
        ivUserImage.setImageDrawable(Util.cutRooundedImage(this,img));
    }
    private void animatedUserImage() {

        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation s = new ScaleAnimation(
                0f, 1f, // Start and end values for the X axis scaling
                0f, 1f, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
        s.setDuration(2000);

        TranslateAnimation a = new TranslateAnimation(
                Animation.ABSOLUTE,Animation.ABSOLUTE, Animation.ABSOLUTE,Animation.ABSOLUTE,
                Animation.ABSOLUTE,-1000, Animation.ABSOLUTE,0);
        a.setDuration(2000);

        animationSet.addAnimation(a);
        a.setFillAfter(true); // Needed to keep the result of the animation

        animationSet.addAnimation(s);

        userimagecontainer.startAnimation(animationSet);
    }

    private void ckeckConnection() {
        if (!Util.isOnline(this)){
            CustomDialogSucessFail cdd=new CustomDialogSucessFail(this,this, getResources().getString(R.string.connection_error),R.drawable.warning);
            cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            cdd.getWindow().getAttributes().windowAnimations = R.style.animationdialog;
            cdd.show();
        }
    }

}
