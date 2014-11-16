package com.jeupoo.animations;
import android.graphics.Canvas;

import com.jeupoo.main.Constantes;
import com.jeupoo.main.GridModel;
import com.jeupoo.main.GridPrinc;
import com.jeupoo.main.Movable;
import com.jeupoo.main.MyView;

/**
 * 
 * Animation utilisée uniquement pour déplacer les deux Movable lors d'un clic
 *
 */
public class AnimationDeplacer implements Animation{

	private Movable m1;
	private Movable m2;
	
	public AnimationDeplacer(Movable m1, Movable m2){
		this.m1 = m1;
		this.m2 = m2;
	}
	
	public void animer(GridPrinc grid, Canvas canvas, MyView view, int dt) {
		GridModel model = grid.model;

		for(int i = Constantes.nbrV-1; i >= 0; i--)
			for(int j = Constantes.nbrH-1; j >= 0; j--)
				if (model.getMovable(i,j) != m1 && model.getMovable(i,j) != m2 )
					view.dessiner(model.getMovable(i,j), canvas);
				
			if (m1.getTo().x > m1.getFrom().x || m1.getTo().y > m1.getFrom().y){
				echangeMoins(m1, canvas, view);
				echangePlus(m2, canvas, view);
			}	
			
			else{
				echangeMoins(m2, canvas, view);
				echangePlus(m1, canvas, view);
			}	
			
		if(m2.getFrom().x == m2.getTo().x && m2.getFrom().y == m2.getTo().y)
			grid.setAnimation(new AnimationPause());
	}
	
	/**
	 * dessine un movable si  il vient de Gauche ou du Haut vers l'oppose
	 */
	public void echangeMoins(Movable movable, Canvas canvas, MyView view){
		
		if(movable.getFrom().x < movable.getTo().x)	
			 view.dessinerVersDroite(movable, canvas);
		 
	 	else if(movable.getFrom().y < movable.getTo().y)
	 		view.dessinerVersBas(movable, canvas);
		 
		if(movable.getFrom().x > movable.getTo().x ||movable.getFrom().y > movable.getTo().y ) {
			 movable.getFrom().x = movable.getTo().x;
			 movable.getFrom().y = movable.getTo().y;	
		}
	}
	
	/**
	 * dessine un movable si  il vient de Droite ou du Bas vers l'oppose
	 */
	public void echangePlus(Movable movable, Canvas canvas, MyView view){
	
		if(movable.getFrom().x > movable.getTo().x)	
		 		view.dessinerVersGauche(movable, canvas);
	
		else if(movable.getFrom().y > movable.getTo().y)
		 		view.dessinerVersHaut(movable, canvas);
	 
		if(movable.getFrom().x < movable.getTo().x ||movable.getFrom().y < movable.getTo().y ) {
				 movable.getFrom().x = movable.getTo().x;
				 movable.getFrom().y = movable.getTo().y;	
		}		
	}
}