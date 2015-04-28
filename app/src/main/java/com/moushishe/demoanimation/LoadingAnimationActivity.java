package com.moushishe.demoanimation;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.moushishe.demoanimation.tools.LeafLoadingView;

import java.util.Random;

/**
 * Created by foxcoder
 */
public class LoadingAnimationActivity extends ActionBarActivity implements View.OnClickListener {

    private static final int REFRESH_PROGRESS = 0x10;
    private LeafLoadingView mLeafLoadingView;
    private View mFanView;
    private int mProgress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_animation);
        initViews();

        findViewById(R.id.reset_btn).setOnClickListener(this);
        findViewById(R.id.fengshan_switch_btn).setOnClickListener(this);

        mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS, 3000);
    }

    private void initViews() {
        mFanView = findViewById(R.id.fan_pic);
        RotateAnimation rotateAnimation = initRotateAnimation(false, 1500, true, Animation.INFINITE);
        mFanView.startAnimation(rotateAnimation);

        mLeafLoadingView = (LeafLoadingView) findViewById(R.id.leaf_loading);
    }

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_PROGRESS:
                    if (mProgress < 40) {
                        mProgress += 1;
                        // 随机800ms以内刷新一次
                        mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS,
                                new Random().nextInt(800));
                        mLeafLoadingView.setProgress(mProgress);
                    } else {
                        mProgress += 1;
                        // 随机1200ms以内刷新一次
                        mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS,
                                new Random().nextInt(1200));
                        mLeafLoadingView.setProgress(mProgress);

                    }
                    break;

                default:
                    break;
            }
        }
    };

    public RotateAnimation initRotateAnimation(boolean isClockWise, long duration,
                                                      boolean isFillAfter, int repeatCount) {
        int endAngle;
        if (isClockWise) {
            endAngle = 360;
        } else {
            endAngle = -360;
        }
        RotateAnimation mLoadingRotateAnimation = new RotateAnimation(0, endAngle,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        LinearInterpolator lirInterpolator = new LinearInterpolator();
        mLoadingRotateAnimation.setInterpolator(lirInterpolator);
        mLoadingRotateAnimation.setDuration(duration);
        mLoadingRotateAnimation.setFillAfter(isFillAfter);
        mLoadingRotateAnimation.setRepeatCount(repeatCount);
        mLoadingRotateAnimation.setRepeatMode(Animation.RESTART);
        return mLoadingRotateAnimation;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reset_btn:
                mProgress = 0;
                break;
            case R.id.fengshan_switch_btn:
                mFanView.clearAnimation();
                break;
        }
    }
}
