package com.jeupoo.main;

/**
 * Classe qui permet de vérifier l'existence et la couleur des voisins d'un Movable
 * Elle détermine si une ligne peut être créée ou non
 */
public class Verification {
	
	public static int verificationVerticaleHaut(GridPrinc grid,int couleur, int i,int j){
		
		int nbr = 0;
		int k = i-1;
		
		while (k >= 0 && couleur(grid.model.getIndice(k,j), couleur)){
			nbr++;
			k--;
		}
		return nbr;
	}
	
	public static int verificationHorizontaleGauche(GridPrinc grid, int couleur, int i, int j){
		
		int nbr = 0;
		int k = j-1;

		while (k >= 0 && couleur(grid.model.getIndice(i,k), couleur)){
			nbr++;
			k--;
		}
		return nbr;
	}
	
	public static int verificationVerticaleBas(GridPrinc grid, int couleur, int i, int j)
	{
		int nbr = 0;
		int k = i+1;
		
		while (k < Constantes.nbrV && couleur(grid.model.getIndice(k,j), couleur)){
			nbr++;
			k++;
		}
		return nbr;
	}
	
	public static int verificationHorizontaleDroite(GridPrinc grid, int couleur, int i, int j){
		int nbr = 0;
		int k = j+1;
		
		while (k < Constantes.nbrH && couleur(grid.model.getIndice(i,k), couleur)){
			nbr++;
			k++;
		}
		return nbr;
	}
	
	public static int verificationVerticale(GridPrinc grid, int couleur, int y, int x){
		
		int total = 1;
		
		total += verificationVerticaleBas(grid, couleur, y, x);
		total += verificationVerticaleHaut(grid, couleur, y, x);
		return total;
	}
	
	public static int verificationHorizontal(GridPrinc grid, int couleur, int y, int x){
		
		int total = 1;
		
		total += verificationHorizontaleDroite(grid,couleur, y, x);
		total += verificationHorizontaleGauche(grid,couleur, y, x);
		return total;
	}
	
	public static boolean verificationCoteACote(int[] x, int[] y, GridPrinc grid){
			
		if((Math.abs(x[1] - x[0]) == 1 && y[1]==y[0]) || (Math.abs(y[1] - y[0]) == 1 && x[1] == x[0]))
			if(grid.model.getIndice(y[0],x[0]) != grid.model.getIndice(y[1],x[1]))		
				return true;
		
		return false ;
	}
	
	/**
	 * Si nbrh ou/et nbrv sont bien supérieurs ou égaux à 3 on a une ligne.
	 */
	public static boolean verificationLigne(GridPrinc grid, int[] y, int[] x){
		
		int []nbrh = new int[2];
		int []nbrv = new int[2];
		
		for (int i = 0; i < 2; i++){
			nbrh[i] = verificationHorizontal(grid, grid.model.getIndice(y[i],x[i]), y[i], x[i]);
			nbrv[i] = verificationVerticale(grid, grid.model.getIndice(y[i],x[i]), y[i], x[i]);
			if (nbrh[i] >= 3 || nbrv[i] >= 3)
				return true;	
		}
		return false;
	}
	
	/**
	 * Vérifie que le bon soit bien de la même couleur que son voisin
	 */
	public static boolean couleur(int indice, int couleur){
		for(int i = 0; i < Constantes.NB_BONUS; i++)
			if(indice == couleur + Constantes.TRANCHE*i || indice == couleur - Constantes.TRANCHE*i)
				return true;
		return false;
	}
}