package com.jeupoo.bonbons;

import com.jeupoo.main.Constantes;
import com.jeupoo.main.GridPrinc;
import com.jeupoo.main.Movable;
import android.graphics.Bitmap;

/**
 * Obtenu grâce à un schéma en T, + ou L
 * Bonbon qui doit être intégré dans un schéma.
 * Supprime les 8 bonbons qui l'entourrent
 */
public class BonbonEmballe extends Movable {
	/**
	 * Valeur ajoutée lorque le bonbon est supprimé
	 */
	private final int valeur = 400;	
	
	public BonbonEmballe(Bitmap bitmap, int indice) {
		this.indice = indice;
		this.bitmap = bitmap;
		isMoved = false;
	}

	public int getValeur() {
		return valeur;
	}

	public void bombe(GridPrinc grid, int[][] gridSup, int y, int x) {
		if(!grid.model.isEmpty(y,x)){
			grid.model.setEmpty(y,x);
			
			/** Vérifie que les 3 au dessus existent */
			if(y - 1 >= 0){
				if(!grid.model.isEmpty(y-1,x) && !grid.model.getMovable(y-1,x).isChoco())
					grid.model.getMovable(y-1,x).bombe(grid, gridSup, y-1, x);
				
				if(x - 1 >= 0)
					if(!grid.model.isEmpty(y-1,x-1) && !grid.model.getMovable(y-1,x-1).isChoco())
						grid.model.getMovable(y-1,x-1).bombe(grid, gridSup, y-1, x-1);
				
				if(x + 1 < Constantes.nbrH)
					if(!grid.model.isEmpty(y-1,x+1) && !grid.model.getMovable(y-1,x+1).isChoco())
						grid.model.getMovable(y-1,x+1).bombe(grid, gridSup, y-1, x+1);
			}
			
			/** Vérifie que les 3 en dessous existent */
			if(y + 1 < Constantes.nbrV){
				if(!grid.model.isEmpty(y+1,x) && !grid.model.getMovable(y+1,x).isChoco())
					grid.model.getMovable(y+1,x).bombe(grid, gridSup, y+1, x);
				
				if(x - 1 >= 0)
					if(!grid.model.isEmpty(y+1,x-1) && !grid.model.getMovable(y+1,x-1).isChoco())
						grid.model.getMovable(y+1,x-1).bombe(grid, gridSup, y+1, x-1);
				
				if(x + 1 < Constantes.nbrH){
					if(!grid.model.isEmpty(y+1,x+1) && !grid.model.getMovable(y+1,x+1).isChoco())
						grid.model.getMovable(y+1,x+1).bombe(grid, gridSup, y+1, x+1);
				}
			}
			
			/** Vérifie que celui de gauche existe */
			if(x - 1 >= 0)
				if(!grid.model.isEmpty(y,x-1) && !grid.model.getMovable(y,x-1).isChoco())
					grid.model.getMovable(y,x-1).bombe(grid, gridSup, y, x-1);
			
			/** Vérifie que celui de droite existe */
			if(x + 1 < Constantes.nbrH)
				if(!grid.model.isEmpty(y,x+1) && !grid.model.getMovable(y,x+1).isChoco())
					grid.model.getMovable(y,x+1).bombe(grid, gridSup, y, x+1);
			
			grid.getPoint().Add(valeur);
			Constantes.soundMap.get("bombeE").start();
			gridSup[y][x] = grid.model.getIndice(y,x) - Constantes.DEB_E;
		}
	}
}