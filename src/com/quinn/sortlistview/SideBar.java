package com.quinn.sortlistview;

import com.quinn.myutilapp.R;
import android.R.integer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class SideBar extends View{

	//触摸事件
	private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
	//26个字母
	public static String[] b = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z", "#" };
	
	private int choose = -1; //选中
	private Paint paint = new Paint();
	
	private TextView mTextDialog;
	
	public SideBar(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public SideBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public SideBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//获取焦点改变字体颜色
		int height = getHeight(); //获取高度
		int width = getWidth(); //获取宽度
		int singleHeight = height / b.length; //单个字母的高度
		
		for(int i = 0; i < b.length; i ++) {
			//设置画笔颜色
			paint.setColor(Color.rgb(33, 65, 98));
			paint.setTypeface(Typeface.DEFAULT_BOLD);
			paint.setAntiAlias(true);
			paint.setTextSize(20);
			
			if(i == choose) {
				paint.setColor(Color.parseColor("#3399ff"));
				paint.setFakeBoldText(true);
			}
			
			// x坐标为View宽度的一般 - 字符串宽度的一半
			float xPos = width / 2 - paint.measureText(b[i]) / 2;
			float yPost = singleHeight * i + singleHeight;
			canvas.drawText(b[i], xPos, yPost, paint);
			paint.reset(); //重置画笔
		}
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		final int action = event.getAction();
		final float yPos = event.getY();
		final int oldChoose = choose;
		final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
		//判断哪个字母被点击了
		int c = (int) (yPos / getHeight() * b.length);
		
		switch (action) {
		case MotionEvent.ACTION_UP:
			setBackgroundDrawable(new ColorDrawable(0x00000000));
			choose = -1;
			invalidate();
			if (mTextDialog != null) {
				mTextDialog.setVisibility(View.INVISIBLE);
			}
			break;
		default:
			setBackgroundResource(R.drawable.sidebar_background);
			if(oldChoose != c) {
				if(c >= 0 && c < b.length) {
					if(listener != null) {
						listener.onTouchingLetterChanged(b[c]);
					}
					if(mTextDialog != null) {
						mTextDialog.setText(b[c]);
						mTextDialog.setVisibility(View.VISIBLE);
					}
					choose = c;
					invalidate();
				}
			}
			break;
		}
		return true;
	}
	
	public interface OnTouchingLetterChangedListener {
		public void onTouchingLetterChanged(String s);
	}

	public void setOnTouchingLetterChangedListener(
			OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
		this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
	}

	public void setmTextDialog(TextView mTextDialog) {
		this.mTextDialog = mTextDialog;
	}
	
}
