package com.example.jeu;

import android.graphics.Canvas;

public class AnimationGenerate implements Animation{

	/**
	 * @uml.property  name="posY"
	 */
	float posY = 140;
	/**
	 * @uml.property  name="j"
	 */
	int j = 0;
	@Override
	public void dessiner(Grid gr,GObjet[] grille, Canvas canvas, int dt) {
		// TODO Auto-generated method stub
		
		for (int i=0; i<j;i++)
			grille[i].paint(canvas, dt, grille[i].getY());
		
		for(int i = j; i <j+ 9; i++)
			grille[i].paint(canvas, dt, (int)posY);

		if( posY < grille[j].getY())
			posY += dt*2;///2.0;
		
		else
		{
			posY=140;
			j=j+9;
			if(j==72)
			{
				gr.anim = new AnimationPause();
			}
		}
		
	}

}