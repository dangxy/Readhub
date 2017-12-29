package com.dangxy.readhub.base;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by andly.gao on 2017/12/4.
 */

public class LayoutLoadingView extends View {
    private Paint paint1;
    private Paint paint2;
    private Paint mPaintText;
    private int color1 = Color.parseColor("#ffd90051");
    private int color2 = Color.parseColor("#ffd90051");

    private boolean init = false;
    private ValueAnimator valueAnimator;
    private float numb = 0;

    private boolean stop = false;

    private int R = 0;


    public LayoutLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //添加文字的画加入以下代码
        mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintText.setColor(ContextCompat.getColor(context, android.R.color.white));
        mPaintText.setTextSize(20F);
        mPaintText.setTextAlign(Paint.Align.CENTER);
        paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint1.setColor(color1);
        paint2.setColor(color2);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!init) {
            init = true;
            R = getWidth() / 16;
            start();
        }
        numb = (float) valueAnimator.getAnimatedValue();
        if (numb < 0) {
            canvas.drawCircle((getWidth() - 2 * R) * (1 - Math.abs(numb)) + R, getHeight() / 2, R - 5, paint2);
            canvas.drawCircle((getWidth() - 2 * R) * Math.abs(numb) + R, getHeight() / 2, R - 5 * (float) Math.abs(Math.abs(numb) - 0.8), paint1);
        } else {
            canvas.drawCircle((getWidth() - 2 * R) * (1 - Math.abs(numb - 1)) + R, getHeight() / 2, R - 5, paint1);
            canvas.drawCircle((getWidth() - 2 * R) * Math.abs(numb - 1) + R, getHeight() / 2, R - 5 * (float) Math.abs(Math.abs(numb) - 0.8), paint2);
        }
        if (valueAnimator.isRunning()) {
            invalidate();
        }
    }

    public void start() {
        if (valueAnimator == null) {
            valueAnimator = getValueAnimator();
        } else {
            valueAnimator.start();
        }
        if (stop == false) {
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    start();
                    invalidate();
                }
            }, valueAnimator.getDuration());
        }
    }

    public void stop() {
        this.stop = true;
    }

    private ValueAnimator getValueAnimator() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(-1f, 1f);
        valueAnimator.setDuration(1500);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.start();
        return valueAnimator;
    }

}
