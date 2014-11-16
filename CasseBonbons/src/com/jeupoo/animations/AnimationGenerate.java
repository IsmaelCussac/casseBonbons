package com.jeupoo.animations;

import java.util.List;

import com.jeupoo.main.Constantes;
import com.jeupoo.main.GridPrinc;
import com.jeupoo.main.Movable;
import com.jeupoo.main.MyView;

import android.graphics.Canvas;

/**
 * 
 * Animation utilisée uniquement pour générer la grille de départ
 *
 */
public class AnimationGenerate implements Animation{

	private int j = 0;

	public void animer(GridPrinc grid, Canvas canvas, MyView view, int dt) {
		
		List<Movable> list = grid.trans();
		
		for (int i = 0; i < j; i++)
			view.dessiner(list.get(i), canvas);
		
		for(int i = j; i < j+Constantes.nbrH; i++)
			view.dessinerVersBas(list.get(i), canvas);

		if(list.get(j).getFrom().y >= list.get(j).getTo().y)
			j = j + Constantes.nbrH;

		if(j == Constantes.nbrH*Constantes.nbrV - Constantes.nbrH){
			grid.setAnimation(new AnimationPause());
		}		
	}
}