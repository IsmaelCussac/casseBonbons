package com.example.jeu;

import android.graphics.Canvas;

public interface IDrawable {
	public void onDraw(Canvas canvas, int dt, int iWidth,int iHeight);
}