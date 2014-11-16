package com.jeupoo.main;

import com.jeupoo.bonbons.*;

public class GridModel {

	/**
	 * Modèle. Matrice de Cases sur lesquelles se déplacent les Movables
	 */
	private Case[][] grille ;
	
	public GridModel(){
		grille = new Case[Constantes.nbrV][Constantes.nbrH];
	}
	
	
	public void initGrilleCase(){
		for (int i = 0; i < Constantes.nbrV; i++)
			for (int j = 0; j < Constantes.nbrH; j++){
				grille[i][j] = new Case();
				grille[i][j].setEmpty();
			}
	}
	/**
	 * Cree un nouveau bonbon dans une case
	 */
	public void creationCase(int nombre,int i, int j){

		if(nombre <= Constantes.FIN_NM)
			grille[i][j].setMovable(new BonbonDeBase(Constantes.iconMap.get(nombre), nombre));
		else if(nombre <= Constantes.FIN_RV)
			grille[i][j].setMovable(new BonbonRayeV(Constantes.iconMap.get(nombre), nombre));
		else if(nombre <= Constantes.FIN_RH)
			grille[i][j].setMovable(new BonbonRayeH(Constantes.iconMap.get(nombre), nombre));
		else if (nombre <= Constantes.FIN_E)
			grille[i][j].setMovable(new BonbonEmballe(Constantes.iconMap.get(nombre), nombre));
		else if((nombre <= Constantes.CHOCO))
			grille[i][j].setMovable(new BonbonChocolat(Constantes.iconMap.get(nombre), nombre));
		else 
			grille[i][j].setMovable(new BonbonMalus(Constantes.iconMap.get(nombre), nombre));
		grille[i][j].setFull();
		getMovable(i,j).setPosition((int)(Constantes.margeHori+j*Constantes.largeurCase), 0, (int)(Constantes.margeHori+j*Constantes.largeurCase), (int)(Constantes.margeVerti+i*Constantes.hauteurCase));
	}
	
	/**
	 * Fait descendre un bonbon
	 */
	public void descendreCase(int i, int j){
		getMovable(i-1, j).setPosition((int)(Constantes.margeHori+j*Constantes.largeurCase), (int)(Constantes.margeVerti+(i-1)*Constantes.hauteurCase), (int)(Constantes.margeHori+j*Constantes.largeurCase), (int)(Constantes.margeVerti+i*Constantes.hauteurCase));
		grille[i][j].setMovable(getMovable(i-1, j));
		setFull(i, j);
		setEmpty(i-1, j);
	}
	
	public Movable getMovable(int i, int j){
		return grille[i][j].getMovable();
	}
	
	public void setEmpty(int i, int j){
		grille[i][j].setEmpty();
	}
	
	public boolean isEmpty(int i, int j){
		return grille[i][j].isEmpty();
	}
	
	public void setFull(int i, int j){
		grille[i][j].setFull();
	}
	
	public Case getCase(int i, int j){
		return grille[i][j];
	}
	
	public int getIndice(int i, int j){
		return grille[i][j].getMovable().getIndice();
	}
	
}
