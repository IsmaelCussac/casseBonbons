package com.jeupoo.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;


public class MyView extends View {


	IDrawable iDraw = null;
	IDrawable iDraw1 = null;
	private int FPS = 20;
	double lastRefresh = 0;
	double dt;
	
	public MyView(Context context) {
		super(context);
	}
	
	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setOnDraw(IDrawable iDraw, IDrawable iDraw1){	
		this.iDraw = iDraw;
		this.iDraw1 = iDraw1;
	}

	@SuppressLint("WrongCall")
	protected void onDraw(Canvas c) {
		super.onDraw(c);
		int iWidth = c.getWidth(); 
        int iHeight = c.getHeight(); 
        int periode = 1000/FPS;
        double temps = 0;
		dt = System.currentTimeMillis() - lastRefresh;
		
		if(dt > 100)
			dt = 100;
		
		lastRefresh = System.currentTimeMillis();
		double debut = System.currentTimeMillis();

		if(iDraw != null)
			iDraw.onDraw(c, this, (int)dt, iWidth, iHeight);
		if(iDraw1 != null)
			iDraw1.onDraw(c,this, (int)dt, iWidth, iHeight);
		
		double fin = System.currentTimeMillis();
		temps = fin - debut ;
		postInvalidateDelayed(periode - (int)temps);	
	}
	
	/**
	 * Dessine le Movable à sa place
	 */
	public void dessiner(Movable movable, Canvas canvas) {
		canvas.drawBitmap(movable.getBitmap(), movable.getTo().x, movable.getTo().y, null);
	}

	/**
	 * Dessine le Movable dans les 4 directions
	 */
	public void dessinerVersDroite(Movable mCase, Canvas canvas) {
		mCase.getFrom().x += dt/1.5;
		canvas.drawBitmap(mCase.getBitmap(), mCase.getFrom().x, mCase.getFrom().y, null);
	}

	public void dessinerVersBas(Movable mCase, Canvas canvas) {
		mCase.getFrom().y += dt/1.5;
		canvas.drawBitmap(mCase.getBitmap(), mCase.getFrom().x, mCase.getFrom().y, null);
	}

	public void dessinerVersGauche(Movable mCase, Canvas canvas) {
		mCase.getFrom().x -= dt/1.5;
		canvas.drawBitmap(mCase.getBitmap(), mCase.getFrom().x, mCase.getFrom().y, null);
	}

	public void dessinerVersHaut(Movable mCase, Canvas canvas) {
		mCase.getFrom().y -= dt/1.5;
		canvas.drawBitmap(mCase.getBitmap(), mCase.getFrom().x, mCase.getFrom().y, null);
	}
}