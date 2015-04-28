package com.moushishe.demoanimation.tools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by foxcoder
 */
public class ArcProgressView extends View {
    private static final int START_COLOR = Color.rgb(234, 85, 74);
    private static final int END_COLOR = Color.rgb(234, 165, 74);
    private static final int CIRCLE_BG_COLOR = Color.rgb(210, 210, 210);

    private RectF mCircleBox = new RectF();

    private Paint mArcPaint;

    private float mStartAngle = 180f;

    private float mAngle = 180f;
    private float mPhase = 0f;

    private boolean mBoxSetup = false;

    private ObjectAnimator mDrawAnimator;

    //进度条的宽度
    private int mStrokeWidth = UiUtils.dipToPx(getContext(), 13);


    public ArcProgressView(Context context) {
        super(context);
        init();
    }

    public ArcProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ArcProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mBoxSetup = false;

        //初始化弧形画笔
        mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth(mStrokeWidth);

        //初始化绘制动画
        mDrawAnimator = ObjectAnimator.ofFloat(this, "phase", mPhase, 1.0f).setDuration(5000);
        mDrawAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!mBoxSetup) {
            mBoxSetup = true;
            setupBox();//这时才得到getWidth()
        }

        //灰色的轨迹
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(mStrokeWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(CIRCLE_BG_COLOR);
        canvas.drawArc(mCircleBox, mStartAngle, mStartAngle, false, paint);

        //进度的轨迹
        drawArc(canvas);
    }

    private void drawArc(Canvas c) {
        int[] colors = {START_COLOR, END_COLOR};

        //梯度渲染
        Shader gradient = new SweepGradient(mCircleBox.centerX(), mCircleBox.centerY(), colors, null);
        mArcPaint.setShader(gradient);

        //扫过的角度
        float angle = mAngle * mPhase;
        c.drawArc(mCircleBox, mStartAngle, angle, false, mArcPaint);
    }

    private void setupBox() {
        final int width = getWidth();
        final int padding = mStrokeWidth / 2;
        mCircleBox = new RectF(padding, padding, width - padding, width - padding);
    }

    public void startAnim() {
        mDrawAnimator.start();
        invalidate();
    }

    public void reverseAnim() {
        mDrawAnimator.reverse();
        invalidate();
    }

    public void setPhase(float phase) {
        mPhase = phase;
        invalidate();
    }

    public float getPhase() {
        return mPhase;
    }

    public void setAnimDuration(int durationmillis) {
        mDrawAnimator.setDuration(durationmillis);
    }

    public ObjectAnimator getmDrawAnimator() {
        return mDrawAnimator;
    }
}
