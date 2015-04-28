package com.moushishe.demoanimation;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;

import com.moushishe.demoanimation.tools.UiUtils;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by foxcoder
 */
public class HeightChangeAnimationActivity extends ActionBarActivity implements View.OnClickListener {
    private ImageView stickImage;
    private int function = FUNCTION_THREE;

    private static final int FUNCTION_ONE = 1;
    private static final int FUNCTION_TWO = 2;
    private static final int FUNCTION_THREE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height_change_animation);

        stickImage = (ImageView)findViewById(R.id.stick_image);

        findViewById(R.id.big_btn).setOnClickListener(this);
        findViewById(R.id.small_btn).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.big_btn:
                if(function == FUNCTION_ONE) {
                    stickChangeOne(UiUtils.dipToPx(this, 350));
                } else if(function == FUNCTION_TWO) {
                    stickChangeTwo(UiUtils.dipToPx(this, 350));
                } else if(function == FUNCTION_THREE) {
                    stickChangeThree(stickImage.getHeight(), UiUtils.dipToPx(this, 350));
                }
                break;
            case R.id.small_btn:
                if(function == FUNCTION_ONE) {
                    stickChangeOne(UiUtils.dipToPx(this, 200));
                } else if(function == FUNCTION_TWO) {
                    stickChangeTwo(UiUtils.dipToPx(this, 200));
                } else if(function == FUNCTION_THREE) {
                    stickChangeThree(stickImage.getHeight(), UiUtils.dipToPx(this, 200));
                }
                break;
        }
    }

    //-------------Function One 错误的用法---------------
    private void stickChangeOne(int target) {
        ObjectAnimator.ofInt(stickImage, "height", target).setDuration(3000).start();
    }

    //-------------Function Two-------------------------
    private void stickChangeTwo(int target) {
        ViewWrapper wrapper = new ViewWrapper(stickImage);
        ObjectAnimator.ofInt(wrapper, "height", target).setDuration(3000).start();
    }

    private static class ViewWrapper {
        private View mTarget;

        public ViewWrapper(View target) {
            mTarget = target;
        }

        public int getHeight() {
            return mTarget.getLayoutParams().height;
        }

        public void setHeight(int height) {
            mTarget.getLayoutParams().height = height;
            mTarget.requestLayout();
        }
    }

    //-------------Function Three-------------------------
    private void stickChangeThree(final int start, final int end) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(1, 100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentValue = (Integer)animation.getAnimatedValue();
                float fraction = currentValue / 100f;
                stickImage.getLayoutParams().height = (Float.valueOf(start + fraction * (end - start))).intValue();
                stickImage.requestLayout();
            }
        });
        valueAnimator.setDuration(3000).start();
    }
}
