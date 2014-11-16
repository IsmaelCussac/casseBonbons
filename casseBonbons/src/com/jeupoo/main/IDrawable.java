package com.jeupoo.main;

import android.graphics.Canvas;

public interface IDrawable {
	public void onDraw(Canvas canvas, MyView view, int dt, int iWidth, int iHeight);
}