package com.jeupoo.animations;

import java.util.List;

import com.jeupoo.main.GridPrinc;
import com.jeupoo.main.Movable;
import com.jeupoo.main.MyView;



import android.graphics.Canvas;

/**
 * 
 * Animation utilisée pour remplir la grille une fois que la fonction Supprimer de GridPrinc est terminée.
 * Appelle AnimationPause pour stoper l'affichage
 *
 */
public class AnimationRempli implements Animation{

	List<Movable> list;
	
	public AnimationRempli(List<Movable> list){
		this.list = list;
	}

	public void animer(GridPrinc grid, Canvas canvas, MyView view, int dt) {
		
		for (int i=0;i<grid.trans().size();i++)
			if(!list.contains(grid.trans().get(i)))
				view.dessiner(grid.trans().get(i), canvas);
			
		for (int i = 0 ; i < list.size(); i++){
			view.dessinerVersBas(list.get(i), canvas);
			if (list.get(i).getFrom().y >= list.get(i).getTo().y )
				list.remove(i);
		}
		
		if(list.isEmpty())
			grid.setAnimation(new AnimationPause());
	}
}