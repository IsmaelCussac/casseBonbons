package com.example.jeu;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

	/**
	 * @uml.property  name="iDraw"
	 * @uml.associationEnd  
	 */
	IDrawable iDraw=null;
	/**
	 * @uml.property  name="iDraw1"
	 * @uml.associationEnd  
	 */
	IDrawable iDraw1=null;
	
	/**
	 * @uml.property  name="fPS"
	 */
	private int FPS=20;
	public MyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
        setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_FULLSCREEN );
	}

	
	public void setOnDraw(IDrawable iDraw, IDrawable iDraw1)
	{
		this.iDraw = iDraw;
		this.iDraw1 = iDraw1;
	}
	
	
	
	/**
	 * @uml.property  name="lastRefresh"
	 */
	double lastRefresh=0;
	@Override
	protected void onDraw(Canvas c) {
		//c.drawBitmap(R.drawable.bleu, src, dst, paint);
		
		int iWidth = c.getWidth(); // Largeur
        int iHeight = c.getHeight(); // Hauteur
		double dt = System.currentTimeMillis() - lastRefresh;
		if(dt>100)
			dt=100;
		lastRefresh = System.currentTimeMillis();
		double temps=0;
		double debut = System.currentTimeMillis();
		
		int periode = 1000/FPS;
		

		if(iDraw != null)
			iDraw.onDraw(c, (int) dt,iWidth,iHeight);

		if(iDraw1 != null)
			iDraw1.onDraw(c, (int) dt,iWidth,iHeight);
		

		
		double fin = System.currentTimeMillis();
		
		temps = fin-debut ;
		//System.out.println(dt);
		
		postInvalidateDelayed(periode -(int)temps);
		
	}
	

}