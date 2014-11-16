package com.example.jeu;

import android.graphics.Canvas;

public interface Animation {
	public void dessiner (Grid gr,GObjet[] grille, Canvas canvas,int dt);
}