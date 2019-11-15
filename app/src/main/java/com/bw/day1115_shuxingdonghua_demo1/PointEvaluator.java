package com.bw.day1115_shuxingdonghua_demo1;

import android.animation.TypeEvaluator;

/**
 * Copyright (C)
 * <p>
 * FileName: PointEvaluator
 * <p>
 * Author: zhaozhipeng
 * <p>
 * Date: 2019/11/15 13:43
 * <p>
 * Description:
 */
public class PointEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        //始末值强转为Point对象
        //开始
        Point startPoint = (Point) startValue;
        //结束
        Point endPoint = (Point) endValue;

        //通过fraction计算当前动画的坐标值x,y
        float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());
        float y = startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());
        //返回上述x,y组装的新的point对象
        Point point = new Point(x, y);
        return point;
    }
}
