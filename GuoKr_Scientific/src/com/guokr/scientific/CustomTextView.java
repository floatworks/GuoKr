package com.guokr.scientific;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextView extends TextView {

	public CustomTextView(Context context) {
		super(context);
	} 
	public CustomTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// 倾斜度45,上下左右居中
		canvas.rotate(45, getMeasuredWidth() / 3, getMeasuredHeight() / 3);
 
		super.onDraw(canvas);
	}

}
