package com.jeupoo.animations;

import java.util.List;

import com.jeupoo.main.GridPrinc;
import com.jeupoo.main.Movable;
import com.jeupoo.main.MyView;

import android.graphics.Canvas;

/**
 * 
 * Utilisé à la fin d'une animation. Dessine le Movable à sa place.
 * Appelle la fonction Supprimer de GridPrinc afin de permettre à la vue de suivre le controleur
 *
 */
public class AnimationPause implements Animation{

	public void animer(GridPrinc grid, Canvas canvas, MyView view, int dt) {
		List<Movable> list = grid.trans();
		for (int i = 0; i < list.size(); i++){
			view.dessiner(list.get(i), canvas);
		}
		grid.Supprimer();
	}
}