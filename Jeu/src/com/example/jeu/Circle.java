package com.example.jeu;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Circle implements Movable {

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
	 * @uml.property  name="color"
	 */
	int color ;
	public Circle(int color, int x, int y){
		this.x = x;
		this.y = y;
		this.color = color;
		paint = new Paint();
		
	}
	
	public void paint(Canvas canvas, int dt, int iHeight) {
		paint.setColor(color);
		canvas.drawCircle(x, iHeight, 35, paint);
	}
	
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return
	 * @uml.property  name="x"
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * @return
	 * @uml.property  name="y"
	 */
	public int getY(){
		return y;
	}

	/**
	 * @return
	 * @uml.property  name="color"
	 */
	public int getColor() {
		return color;
	}

	/**
	 * @param color
	 * @uml.property  name="color"
	 */
	public void setColor(int color) {
		// TODO Auto-generated method stub
		this.color = color; 
		
	}


}