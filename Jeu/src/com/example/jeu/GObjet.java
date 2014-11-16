package com.example.jeu;

import android.graphics.Canvas;

public interface GObjet {

	public void paint(Canvas canvas, int dt, int iHeight);
	public int getY();
}