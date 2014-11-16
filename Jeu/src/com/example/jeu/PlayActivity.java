package com.example.jeu;

import java.util.HashMap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;


public class PlayActivity extends Activity {

	/**
	 * @uml.property  name="grid"
	 * @uml.associationEnd  
	 */
	Grid grid;
	/**
	 * @uml.property  name="icon"
	 * @uml.associationEnd  qualifier="valueOf:java.lang.Integer android.graphics.Bitmap"
	 */
	HashMap<Integer, Bitmap> icon = new HashMap<Integer, Bitmap>();
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);
        MyView view = (MyView) findViewById(R.id.myView1);
        
        Bitmap blueIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.bleu);
        Bitmap redIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.rouge);
        Bitmap greenIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.vert);
        Bitmap yellowIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.jaune);

       
        
        icon.put(0, blueIcon);
		icon.put(1, redIcon);
		icon.put(2, greenIcon);
		icon.put(3, yellowIcon);
        
        
        
        grid = new Grid(icon,9,9);
        grid.generationGrille();
        GObjet [] grille = grid.trans();
       
        GraphicWorld world = new GraphicWorld(grid,new BackGround(), grille);
       
        getWindow().setBackgroundDrawableResource(R.drawable.fondfinal);

   
       view.setOnDraw(world, null);
	}
	/**
	 * @uml.property  name="xClic" multiplicity="(0 -1)" dimension="1"
	 */
	float[] xClic = new float[2];
	/**
	 * @uml.property  name="yClic" multiplicity="(0 -1)" dimension="1"
	 */
	float[] yClic = new float[2];
	/**
	 * @uml.property  name="i"
	 */
	int i=0;
	
	public boolean onTouchEvent(MotionEvent event){   
		if (event.getAction() == MotionEvent.ACTION_UP){              	
			xClic[i] = event.getX();
			yClic[i++] = event.getY();
			//Toast.makeText(getBaseContext(),"x = " +String.valueOf(xClic[0]) + "," + "  y = " +String.valueOf(yClic[0]) ,Toast.LENGTH_SHORT).show();
			if(i==2)
			{
				grid.Clique(xClic, yClic);
				i=0;
			}
		}                            
		return false;
	}
}