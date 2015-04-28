package com.moushishe.demoanimation;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by foxcoder on 15-4-17.
 */
public class SimpleTransformationActivity extends ActionBarActivity implements View.OnClickListener {
    private Animation animation;
    private ImageView imageView;

    private boolean animationFromXML = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_transformation);

        imageView = (ImageView)findViewById(R.id.doreamon_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SimpleTransformationActivity.this, "点击图片", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.alpha_btn).setOnClickListener(this);
        findViewById(R.id.scale_btn).setOnClickListener(this);
        findViewById(R.id.translate_btn).setOnClickListener(this);
        findViewById(R.id.rotate_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int animSimpleXmlId;
        switch (v.getId()) {
            case R.id.alpha_btn:
                animSimpleXmlId = R.anim.simple_alpha;
                animation = initAlphaAnimation();
                break;
            case R.id.scale_btn:
                animSimpleXmlId = R.anim.simple_scale;
                animation = initScaleAnimation();
                break;
            case R.id.translate_btn:
                animSimpleXmlId = R.anim.simple_translate;
                animation = initTranslateAnimation();
                break;
            case R.id.rotate_btn:
                animSimpleXmlId = R.anim.simple_rotate;
                animation = initRotateAnimation();
                break;
            default:
                animSimpleXmlId = -1;
                break;
        }

        if(animationFromXML) {
            animation = AnimationUtils.loadAnimation(this, animSimpleXmlId);
        }

        imageView.startAnimation(animation);
    }

    //---------------代码控制动画----------------
    private AlphaAnimation initAlphaAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(3000);
        return alphaAnimation;
    }

    private ScaleAnimation initScaleAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.5f, 0.0f, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        Interpolator interpolator = new AccelerateDecelerateInterpolator();
        scaleAnimation.setInterpolator(interpolator);
        scaleAnimation.setDuration(3000);
        return scaleAnimation;
    }

    private TranslateAnimation initTranslateAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 300, 0, 300);
        translateAnimation.setDuration(3000);
        return translateAnimation;
    }

    private RotateAnimation initRotateAnimation() {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(3000);
        return rotateAnimation;
    }

}
