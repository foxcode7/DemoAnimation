package com.moushishe.demoanimation;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by foxcoder on 15-4-17.
 */
public class FrameAnimationActivity extends ActionBarActivity implements View.OnClickListener {
    private ImageView imageView;
    private boolean animationFromXML = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_animation);

        imageView = (ImageView) findViewById(R.id.fox_image);
        findViewById(R.id.start_btn).setOnClickListener(this);
        findViewById(R.id.stop_btn).setOnClickListener(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if(animationFromXML) {
            imageView.setBackgroundResource(R.anim.frame_animation);
            AnimationDrawable anim = (AnimationDrawable) imageView.getBackground();
            anim.start();
        } else {
            initFrameAnimation();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_btn:
                startFrameAnimation();
                break;
            case R.id.stop_btn:
                stopFrameAnimation();
                break;
        }
    }

    private void startFrameAnimation() {
        AnimationDrawable anim = (AnimationDrawable) imageView.getBackground();
        if(!anim.isRunning()) {
            anim.start();
        }
    }

    private void stopFrameAnimation() {
        AnimationDrawable anim = (AnimationDrawable) imageView.getBackground();
        if(anim.isRunning()) {
            anim.stop();
        }
    }

    //---------------代码控制动画----------------
    private void initFrameAnimation() {
        AnimationDrawable anim = new AnimationDrawable();
        for (int i = 1; i <= 8; i++) {
            int id = getResources().getIdentifier("bird" + i, "mipmap", getPackageName());
            Drawable drawable = getResources().getDrawable(id);
            anim.addFrame(drawable, 120);
        }
        anim.setOneShot(false);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
            imageView.setBackgroundDrawable(anim);
        } else {
            imageView.setBackground(anim);
        }
        anim.start();
    }
}
