package com.example.self_service_gate.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MaxCountLayoutManager extends LinearLayoutManager {

    private int maxCount = -1;

    public MaxCountLayoutManager(Context context) {
        super(context);
    }

    public MaxCountLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public MaxCountLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    @Override
    public void setMeasuredDimension(int widthSize, int heightSize) {
        int maxWidth = getMaxWidth();
        if (maxWidth > 0 && maxWidth < widthSize) {
            super.setMeasuredDimension(maxWidth, heightSize);
        } else {
            super.setMeasuredDimension(widthSize, heightSize);
        }
    }

    private int getMaxWidth() {
        if (getChildCount() == 0 || maxCount <= 0) {
            return 0;
        }

        View child = getChildAt(0);
        int width = child.getWidth();
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();
        width += 60;
        return width * maxCount + getPaddingLeft() + getPaddingRight();
    }
}
