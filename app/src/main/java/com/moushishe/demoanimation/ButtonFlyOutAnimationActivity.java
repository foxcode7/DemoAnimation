package com.moushishe.demoanimation;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by foxcoder
 */
public class ButtonFlyOutAnimationActivity extends ActionBarActivity implements View.OnClickListener {
    private boolean isMenuOpen = false;

    private ImageView menuImage;
    private ImageView[] itemImages = new ImageView[8];

    private int[] itemIds = new int[]{R.id.item1, R.id.item2, R.id.item3, R.id.item4, R.id.item5, R.id.item6, R.id.item7, R.id.item8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_fly_out_animation);

        initView();
    }

    private void initView(){
        menuImage = (ImageView)findViewById(R.id.menu);
        menuImage.setOnClickListener(this);

        for(int i = 0; i < itemIds.length; i++) {
            itemImages[i] = (ImageView)findViewById(itemIds[i]);
            itemImages[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if(v == menuImage) {
            if(!isMenuOpen) {
                isMenuOpen = true;
                for(int i = 0 ; i < itemImages.length; i++) {
                    doAnimateOpen(itemImages[i], i, itemImages.length, 300);
                }
            } else {
                isMenuOpen = false;
                for(int i = 0 ; i < itemImages.length; i++) {
                    doAnimateClose(itemImages[i], i, itemImages.length, 300);
                }
            }
        } else {
            Toast.makeText(this, "你点击了" + v, Toast.LENGTH_SHORT).show();
        }
    }

    private void doAnimateOpen(View view, int index, int total, int radius) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        double degree = 360 / total * Math.PI / 180 * index;
        int translationX = (int) (radius * Math.cos(degree));
        int translationY = (int) (radius * Math.sin(degree));

        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", 0, translationX),
                ObjectAnimator.ofFloat(view, "translationY", 0, translationY),
                ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f),
                ObjectAnimator.ofFloat(view, "alpha", 0f, 1),
                ObjectAnimator.ofFloat(view, "rotation", 0f, 1080f));
        set.setDuration(500).start();
    }

    private void doAnimateClose(final View view, int index, int total,
                                int radius) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        double degree = 360 / total * Math.PI / 180 * index;
        int translationX = (int) (radius * Math.cos(degree));
        int translationY = (int) (radius * Math.sin(degree));

        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", translationX, 0),
                ObjectAnimator.ofFloat(view, "translationY", translationY, 0),
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f),
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 0f),
                ObjectAnimator.ofFloat(view, "alpha", 1f, 0f),
                ObjectAnimator.ofFloat(view, "rotation", 0f, -1080f));

        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }
        });

        set.setDuration(500).start();
    }
}
