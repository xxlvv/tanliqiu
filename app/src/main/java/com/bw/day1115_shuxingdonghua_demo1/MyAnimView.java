package com.bw.day1115_shuxingdonghua_demo1;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;

import androidx.annotation.Nullable;

/**
 * Copyright (C)
 * <p>
 * FileName: MyAnimView
 * <p>
 * Author: zhaozhipeng
 * <p>
 * Date: 2019/11/15 13:48
 * <p>
 * Description:
 */
public class MyAnimView extends View {

    //常量 设置圆的半径
    public static final float RADIUS = 50f;

    //当前Point，记录当前动画的值
    private Point curPoint;

    //画笔
    private Paint paint;

    public MyAnimView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //初始化画笔
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //第一次绘制时
        if (curPoint == null) {
            //初始化坐标为（50f,50f）；
            curPoint = new Point(RADIUS, RADIUS);
            Log.e("TAG", "onDraw: "+curPoint.toString() );
            //画圆
            drawCircle(canvas);
            //开始动画
            startAnimation();
        } else {
            //非第一次绘制
            drawCircle(canvas);
        }
    }

    /* @Override
    protected void onDraw(Canvas canvas) {
        //第一次绘制时
        if (curPoint != null) {
            //初始化坐标为（50f,50f）；
            curPoint = new Point(RADIUS, RADIUS);
            //画圆
            drawCircle(canvas);
            //开始动画
            startAnimation();
        } else {
            //非第一次绘制
            drawCircle(canvas);
        }
    }*/

    //才当前坐标处绘制一个半径为50f的圆
    private void drawCircle(Canvas canvas) {
        float x = curPoint.getX();
        float y = curPoint.getY();
        canvas.drawCircle(x, y, RADIUS, paint);
    }

    //开始动画
    private void startAnimation() {
        //设置 起始值&结束值  Point是坐标点

        // 起始值为左上角
        Point startPoint = new Point(RADIUS, RADIUS);
        //结束值为右下角 (屏幕宽度-50f，屏幕高度-50f)
        Point endPoint = new Point(getWidth() - RADIUS, getHeight() - RADIUS);

        ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        //设置差值器
        animator.setInterpolator(new BounceInterpolator());
        //设置监听
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            //每当Point的值有改变的时候，就会调用onAnimationUpdate方法
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //更新坐标
                curPoint = (Point) animation.getAnimatedValue();
                //刷新,重新调用onDraw()方法
                invalidate();
            }
        });
        //设置持续播放时间
        animator.setDuration(5000);
        //开始
        animator.start();
    }
}
