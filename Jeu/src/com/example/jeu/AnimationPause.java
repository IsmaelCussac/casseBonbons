package com.example.jeu;

import android.graphics.Canvas;

public class AnimationPause implements Animation{

	@Override
	public void dessiner(Grid gr, GObjet[] grille, Canvas canvas,int dt) {
		// TODO Auto-generated method stub
		
		for (int i=0;i<grille.length;i++)
		{
			grille[i].paint(canvas, dt, grille[i].getY());
		}
		
	}

}