package com.jeupoo.bonbons;

import com.jeupoo.main.GridPrinc;
import com.jeupoo.main.Movable;

import android.graphics.Bitmap;

/**
 * Bonbons normaux
 */
public class BonbonDeBase extends Movable{
	
	/**
	 * Valeur ajoutée lorque le bonbon est supprimé
	 */
	private final int valeur = 10;
	
	public BonbonDeBase(Bitmap bitmap, int indice) {
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
			grid.gridSup[y][x] = grid.model.getIndice(y,x);
			grid.getPoint().Add(valeur);
		}
	}
}