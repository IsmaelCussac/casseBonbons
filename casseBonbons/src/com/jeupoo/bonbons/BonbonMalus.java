/**
 * 
 */
package com.jeupoo.bonbons;

import android.graphics.Bitmap;

import com.jeupoo.main.GridPrinc;
import com.jeupoo.main.Movable;

/**
 * @author ismael
 *
 */
public class BonbonMalus extends Movable {

	private int  valeur = -1000;

	public BonbonMalus(Bitmap bitmap, int indice) {
		this.indice = indice;
		this.bitmap = bitmap;
		isMoved = false;
	}
	
	public int getValeur() {
		// TODO Auto-generated method stub
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
