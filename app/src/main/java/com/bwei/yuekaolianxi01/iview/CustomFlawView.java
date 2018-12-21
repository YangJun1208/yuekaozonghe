package com.bwei.yuekaolianxi01.iview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class CustomFlawView extends LinearLayout {


    private Context mContext;

    private int maxHeight;
    private int mHeight=20;
    private int mWidth=20;

    public CustomFlawView(Context context) {
        super(context);
        mContext=context;
    }

    public CustomFlawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
    }

    public CustomFlawView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec,heightMeasureSpec);

        findHeightMaxChilderen();

        int left=0,top=0;

        int childCount = getChildCount();
        for (int i=0;i<childCount;i++){
            View childAt = getChildAt(i);
            if(left!=0){
                if(left+childAt.getMeasuredWidth()>sizeWidth){
                    top+=maxHeight+mHeight;
                    left=0;
                }
            }
            left+=childAt.getMeasuredWidth()+mWidth;
        }
        setMeasuredDimension(sizeWidth,(top+maxHeight)>sizeHeight?sizeHeight:top+maxHeight);
    }

    private void findHeightMaxChilderen() {
        maxHeight=0;
        int childCount = getChildCount();
        for (int i=0;i<childCount;i++){
            View childAt = getChildAt(i);
            if(childAt.getMeasuredHeight()>maxHeight){
                maxHeight=childAt.getMeasuredHeight();
            }
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        findHeightMaxChilderen();

        int top=0,left=0;
        int childCount = getChildCount();
        for (int i=0;i<childCount;i++){
            View childAt = getChildAt(i);
            if(left!=0){
                if((left+childAt.getMeasuredWidth()>getWidth())){
                    top+=maxHeight+mHeight;
                    left=0;
                }
            }
            childAt.layout(left,top,left+childAt.getMeasuredWidth(),top+childAt.getMeasuredHeight());
            left+=childAt.getMeasuredWidth()+mWidth;
        }


    }
}
