package com.example.jiangzhiapp.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2017-04-13.
 */
public class LibraryLinearLayout extends LinearLayout {

    private Paint mPaint;
    private Path mPath;

    private int mTriangleWidth;

    private static final float RADIO_TRIANGLE_WIDTH = 1/3F;

    private int mInitTranslationX;
    private int mTranslationX;


    public LibraryLinearLayout(Context context) {
        this(context,null);
    }

    public LibraryLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        //初始化画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#df3da4e9"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setPathEffect(new CornerPathEffect(3));
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

        canvas.save();

        canvas.translate(mInitTranslationX+mTranslationX,getHeight());
        canvas.drawPath(mPath,mPaint);

        canvas.restore();

        super.dispatchDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTriangleWidth = (int)(w * RADIO_TRIANGLE_WIDTH);

        mInitTranslationX = w /2/2 - mTriangleWidth/2;
        
        initTriangle();
    }

    /**
     * 绘画矩形
     * */
    private void initTriangle() {

        mPath = new Path();
        mPath.moveTo(0,0);

        mPath.lineTo(mTriangleWidth,0);
        mPath.lineTo(mTriangleWidth,-5);
        mPath.lineTo(0,-5);
        mPath.close();

    }

    /***
     * 指示器跟随手指进行滚动
     */

    public void scroll(int position, float offset) {

        int tabWidth = getWidth() /2;
        mTranslationX = (int) (tabWidth * (offset + position));

        invalidate();

    }



    /**
     * 设置选中TextView颜色
     * */

    public void highLightTextView(int pos) {

        resetTextViewColor();

        View view = getChildAt(pos);
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(Color.parseColor("#df3da4e9"));
        }
    }

    /**
     * 重置颜色
     * */
    private void resetTextViewColor() {
        for (int i =0;i<getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(Color.parseColor("#dd454444"));
            }
        }
    }

    /**
     * 设置点击监听
     * */
    public void setItemClickEvent(final ViewPager mViewPager){
        int cCount = getChildCount();

        for (int i=0;i<cCount;i++) {
            View view = getChildAt(i);

            final int finalI = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    mViewPager.setCurrentItem(finalI);
                }
            });
        }
    }

}
