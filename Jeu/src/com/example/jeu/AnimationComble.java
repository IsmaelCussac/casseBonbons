package com.example.jeu;

import android.graphics.Canvas;

public class AnimationComble implements Animation {

	@Override
	public void dessiner(Grid gr, GObjet[] grille, Canvas canvas,int dt) {
		// TODO Auto-generated method stub
		
		gr.anim = new AnimationPause();
	}

}