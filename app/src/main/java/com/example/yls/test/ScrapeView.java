package com.example.yls.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by yls on 2017/6/5.
 */

public class ScrapeView extends View{

    private Bitmap bpbackground,bpforeground;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mPaint,contentPaint;
    private String content="刮刮刮";


    public ScrapeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        mPaint = new Paint();
        mPaint.setAlpha(0);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(50);
        mPaint.setXfermode(new PorterDuffXfermode((PorterDuff.Mode.DST_IN)));
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        mPath = new Path();
        bpbackground = BitmapFactory.decodeResource(getResources(),R.drawable.a);
        bpforeground = Bitmap.createBitmap(bpbackground.getWidth(),bpbackground.getHeight(),Bitmap.Config.ARGB_8888);

        mCanvas = new Canvas(bpforeground);

        contentPaint = new Paint();
        contentPaint.setColor(Color.WHITE);
        contentPaint.setTextSize(100);
        contentPaint.setStrokeWidth(20);

        mCanvas.drawColor(Color.GRAY);
        mCanvas.drawText(content,mCanvas.getWidth()/4,mCanvas.getHeight()/2,contentPaint);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(event.getX(),event.getY());
                break;
            case  MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(),event.getY());
                break;
        }

        mCanvas.drawPath(mPath,mPaint);
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bpbackground,0,0,null);
        canvas.drawBitmap(bpforeground,0,0,null);
    }
}
