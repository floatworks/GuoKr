package com.guokr.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextViewRotate extends TextView {

	public CustomTextViewRotate(Context context) {
		super(context);
	} 
	public CustomTextViewRotate(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// 倾斜度45,上下左右居中
		canvas.rotate(45, getMeasuredWidth() / 3, getMeasuredHeight() / 3);
 
		super.onDraw(canvas);
	}

}
