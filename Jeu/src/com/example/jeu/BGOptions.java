package com.example.jeu;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class BGOptions implements GObjet{
	
	/**
	 * @uml.property  name="paint"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	Paint paint;
	public BGOptions(){
		paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setStyle(Style.STROKE);
	}
	
	@Override
	public void paint(Canvas canvas, int dt, int iHeight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

}
