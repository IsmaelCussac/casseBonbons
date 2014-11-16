package com.jeupoo.bonbons;

import com.jeupoo.main.Constantes;
import com.jeupoo.main.GridPrinc;
import com.jeupoo.main.Movable;

import android.graphics.Bitmap;

/**
 * Obtenu grâce à une ligne ou colonne de 5 bonbons
 * Bonbon à combiner avec un autre bonbon pour être supprimé
 * Tous les bonbons identiques seront supprimés de la grille
 */
public class BonbonChocolat extends Movable{
	
	/**
	 * Valeur ajoutée lorque le bonbon est supprimé
	 */
	private final int valeur = 500;		
	
	public BonbonChocolat(Bitmap bitmap, int indice) {
		this.indice = indice;
		this.bitmap = bitmap;
		isMoved = false;
	}

	public int getValeur() {
		return valeur;
	}
	
	public void bombe(GridPrinc grid, int[][] gridSup, int y, int x) {
		if(!grid.model.isEmpty(y,x)){
			for (int i = 0; i < Constantes.nbrV; i++)
				for (int j=0; j < Constantes.nbrH; j++)
					if (grid.model.getIndice(i,j) == grid.model.getIndice(y,x))
						grid.model.getMovable(i,j).bombe(grid, gridSup, i, j);
					
			grid.getPoint().Add(valeur);
			Constantes.soundMap.get("choco").start();
			grid.model.setEmpty(y,x);
		}
	}
}