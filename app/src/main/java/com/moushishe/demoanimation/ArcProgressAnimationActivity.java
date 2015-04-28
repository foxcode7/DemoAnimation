package com.moushishe.demoanimation;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.moushishe.demoanimation.tools.ArcProgressView;
import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by foxcoder
 */
public class ArcProgressAnimationActivity extends ActionBarActivity implements View.OnClickListener {
    private ArcProgressView arcProgressView;
    private TextView scoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arc_progress_animation);

        arcProgressView = (ArcProgressView) findViewById(R.id.arc_progress_view);
        scoreTextView = (TextView) findViewById(R.id.score_txt);

        findViewById(R.id.start_btn).setOnClickListener(this);
        findViewById(R.id.reverse_btn).setOnClickListener(this);

        arcProgressView.getmDrawAnimator().addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                scoreTextView.setText(Float.valueOf((arcProgressView.getPhase() + 0.001f) * 100).intValue() + "");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_btn:
                arcProgressView.startAnim();
                break;
            case R.id.reverse_btn:
                arcProgressView.reverseAnim();
                break;
        }
    }
}
