package com.example.jeu;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Image implements Movable {
	

	/**
	 * @uml.property  name="paint"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	Paint paint;
	/**
	 * @uml.property  name="x"
	 */
	int x;
	/**
	 * @uml.property  name="y"
	 */
	int y;
	/**
	 * @uml.property  name="bitmap"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	Bitmap bitmap ;
	public Image(Bitmap bitmap, int x, int y){
		this.x = x;
		this.y = y;
		this.bitmap = bitmap;
		paint = new Paint();
		
	}
	
	
	
	@Override
	public void paint(Canvas canvas, int dt, int iHeight) {
		canvas.drawBitmap(bitmap, x, y, paint);

	}

	@Override
	public int getColor() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @return
	 * @uml.property  name="y"
	 */
	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}

	/**
	 * @return
	 * @uml.property  name="x"
	 */
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public void setColor(int color) {
		// TODO Auto-generated method stub

	}

}
