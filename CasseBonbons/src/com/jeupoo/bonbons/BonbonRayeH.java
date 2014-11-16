package com.jeupoo.bonbons;

import com.jeupoo.main.Constantes;
import com.jeupoo.main.GridPrinc;
import com.jeupoo.main.Movable;
import android.graphics.Bitmap;

/**
 * Obtenu grâce à une ligne de 4 bonbons
 * Bonbon qui doit être intégré dans un schéma pour être supprimé
 * Supprime la ligne qui le contient
 */
public class BonbonRayeH extends Movable{
	/**
	 * Valeur ajoutée lorque le bonbon est supprimé
	 */
	private final int valeur = 250;
	
	public BonbonRayeH(Bitmap bitmap, int indice) {
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
			for(int l = 0; l < Constantes.nbrH; l++)
				if(!grid.model.getMovable(y,l).isChoco())
					grid.model.getMovable(y,l).bombe(grid, gridSup, y, l);
			
			grid.getPoint().Add(valeur);
			Constantes.soundMap.get("bombeR").start();
			gridSup[y][x] = grid.model.getIndice(y,x) - Constantes.DEB_RH;
		}
	}
}