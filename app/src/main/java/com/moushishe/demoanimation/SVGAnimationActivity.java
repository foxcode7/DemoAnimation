package com.moushishe.demoanimation;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.moushishe.demoanimation.tools.AnimatedSvgView;
import com.moushishe.demoanimation.tools.SVGPath;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by foxcoder
 */
public class SVGAnimationActivity extends ActionBarActivity {
    private Button mReset;
    private AnimatedSvgView mAnimatedSvgView;
    private ImageView mSubtitleView;
    private float mInitialLogoOffset;
    private float mInitialSubtitleOffset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg_animation);

        mInitialLogoOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50,
                getResources().getDisplayMetrics());
        mInitialSubtitleOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80,
                getResources().getDisplayMetrics());

        mReset = (Button) findViewById(R.id.reset);
        mAnimatedSvgView = (AnimatedSvgView) findViewById(R.id.animated_svg_view);
        mSubtitleView = (ImageView) findViewById(R.id.svg_sub_title);
        mSubtitleView.setVisibility(View.INVISIBLE);
        ViewHelper.setTranslationY(mAnimatedSvgView, mInitialLogoOffset);

        //设置路径
        mAnimatedSvgView.setGlyphStrings(SVGPath.STUDIO_PATH);

        //设置填充色
        mAnimatedSvgView.setFillPaints(
                new int[] {
                        210
                },
                new int[] {
                        00
                },
                new int[] {
                        180
                },
                new int[] {
                        00
                });

        //设置追踪色
        int traceColor = Color.argb(255, 0, 200, 100);
        int[] traceColors = new int[2]; // 4 glyphs
        //设置残留色
        int residueColor = Color.argb(80, 175, 190, 6);
        int[] residueColors = new int[2]; // 4 glyphs

        //每一个闭合路径颜色一致
        for (int i = 0; i < traceColors.length; i++) {
            traceColors[i] = traceColor;
            residueColors[i] = residueColor;
        }
        mAnimatedSvgView.setTraceColors(traceColors);
        mAnimatedSvgView.setTraceResidueColors(residueColors);

        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAnimatedSvgView.reset();
                ViewHelper.setTranslationY(mAnimatedSvgView, mInitialLogoOffset);
                ViewHelper.setTranslationY(mSubtitleView, 0);
                mSubtitleView.setVisibility(View.INVISIBLE);

                animateLogo();
            }
        });

        mAnimatedSvgView.setOnStateChangeListener(new AnimatedSvgView.OnStateChangeListener() {
            @Override
            public void onStateChange(int state) {
                if (state == AnimatedSvgView.STATE_FILL_STARTED) {
                    ViewHelper.setAlpha(mSubtitleView, 0);

                    mSubtitleView.setVisibility(View.VISIBLE);

                    AnimatorSet set = new AnimatorSet();
                    Interpolator interpolator = new DecelerateInterpolator();
                    ObjectAnimator a1 = ObjectAnimator.ofFloat(mAnimatedSvgView, "translationY", 0);
                    ObjectAnimator a2 = ObjectAnimator.ofFloat(mSubtitleView, "alpha", 0, 1);
                    ObjectAnimator a3 = ObjectAnimator.ofFloat(mSubtitleView, "translationY", mInitialSubtitleOffset);
                    a1.setInterpolator(interpolator);
                    set.setDuration(1000).playTogether(a1, a2, a3);
                    set.start();
                }
            }
        });
    }

    private void animateLogo() {
        mAnimatedSvgView.start();
    }
}
