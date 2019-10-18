package com.example.samsung_home_work;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyDraw extends View {

    int N = 50;

    float[] x  = new float[N];
    float[] y  = new float[N];
    float[] vx = new float[N];
    float[] vy = new float[N];

    public MyDraw(Context context) {

        super(context);
        for (int i = 0; i < N; i++) {
            x[i] = (float) (Math.random() * 500);
            y[i] = (float) (Math.random() * 500);
            vx[i] = (float) (Math.random() * 6 - 3);
            vy[i] = (float) (Math.random() * 6 - 3);
        }
    }

    Paint paint = new Paint();

    void drawBalls(Canvas canvas){
        for (int i = 0; i < N; i++) {
            canvas.drawCircle(x[i], y[i], 20, paint);
        }
    }

    void addValues(float [] a, float [] av){
        for (int i = 0; i < N; i++) {
            a[i] += av[i];
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBalls(canvas);

        addValues(x, vx);
        addValues(y, vy);

        invalidate();
    }
}