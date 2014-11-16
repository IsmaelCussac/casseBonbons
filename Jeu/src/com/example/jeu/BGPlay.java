package com.example.jeu;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class BGPlay implements GObjet{

	/**
	 * @uml.property  name="paint"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	Paint paint;
	
	public BGPlay()
	{
		paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setStyle(Style.STROKE);
	}
	@Override
	public void paint(Canvas canvas, int dt, int iHeight) {
		
		for(int i = 0; i < 10; i++){
			canvas.drawLine(24,100+80*i,744,100+80*i, paint);
			canvas.drawLine(24+80*i,100,24+80*i,820, paint);
		}
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

}