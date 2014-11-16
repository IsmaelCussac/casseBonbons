package com.jeupoo.main;

/**
 * Classe Possedant que des fonctions static 
 */
public class Automate {

	private static int [][] gridIndice;
	
	/** 
	 * Parcourt la grille à la recherche de schéma à supprimer
	 * @return gridSup, matrice contenant les bonbons à supprimer
	 */
	public static int[][] parcours(GridPrinc grid){

		gridIndice = new int [Constantes.nbrV][Constantes.nbrH];
		
		initGridIndice(grid);
		ligne(grid);
		return grid.gridSup;
	}
	
	/**
	 * Initialise une matrice d'indices récupérés depuis la grille
	 * Utilisée pour simplifier le code
	 */
	public static void initGridIndice(GridPrinc grid){
		for(int i = 0; i < Constantes.nbrV; i++)
			for(int j = 0; j < Constantes.nbrH; j++)
				gridIndice[i][j] = grid.model.getIndice(i, j);
	}

	/**
	 * Fonction qui teste pour chaque bonbon s'il appartient à un schéma
	 * Si oui, rempli gridSup avec les éléments du schéma et marque les bonus
	 */
	public static void ligne(GridPrinc grid){
		int comptVB, comptVH, comptHD, comptHG;
		
		for (int i = 0; i < Constantes.nbrV; i++)
			for (int j = 0; j < Constantes.nbrH; j++){
				comptVB = 1;
				comptHD = 1;
				comptVH = 1;
				comptHG = 1;
				
				if (!grid.isChoco(gridIndice[i][j])){
					comptVH += Verification.verificationVerticaleHaut(grid, gridIndice[i][j], i, j);
					comptVB += Verification.verificationVerticaleBas(grid, gridIndice[i][j], i, j);
					comptHG += Verification.verificationHorizontaleGauche(grid, gridIndice[i][j], i, j);
					comptHD += Verification.verificationHorizontaleDroite(grid, gridIndice[i][j], i, j);
				}
				transformation(comptVB,comptVH,comptHD,comptHG,grid,i,j);
			}
	}
	/**
	 * Appelée après les verifications. 
	 * Elle modifie le GridSup et appelle la bombe de certains bonbons s'ils doivent être supprimés
	 */
	public static void transformation(int comptVB,int comptVH,int comptHD,int comptHG, GridPrinc grid,int i,int j){
		int H = comptHD + comptHG -1;
		int V = comptVB + comptVH -1;
	
		if((H >= 3 || V >=3 ) && !grid.isChoco(gridIndice[i][j]))
			grid.model.getMovable(i,j).bombe(grid, grid.gridSup, i, j); 
	
		if(comptHD >= 3 && comptHG >= 3)
			grid.gridSup[i][j] = Constantes.CHOCO;
		
		else if(comptVB >= 3 && comptVH >= 3 )
			grid.gridSup[i][j] = Constantes.CHOCO;
		
		else if (H >= 3 && V >= 3 && gridIndice[i][j] <= Constantes.FIN_NM)            
				grid.gridSup[i][j] = gridIndice[i][j] + Constantes.DEB_E;
		
		
		else if (comptHD == 4 && comptHG != 2 && gridIndice[i][j] <= Constantes.FIN_NM)
			grid.gridSup[i][j] = gridIndice[i][j] + Constantes.DEB_RV;
		
		else if (comptVB == 4 && comptVH != 2 && gridIndice[i][j] <= Constantes.FIN_NM)
			grid.gridSup[i][j] = gridIndice[i][j] + Constantes.DEB_RH;
	}
	
	/**
	* Teste s'il reste des possibilités de mouvement dans la grille
	*/
	public static boolean deplacementPossible(GridPrinc grid){
		gridIndice = new int [Constantes.nbrV][Constantes.nbrH];
		initGridIndice(grid);
		int compt0, compt1;
		for (int i = 0; i < Constantes.nbrV; i++)
			for (int j = 0; j < Constantes.nbrH; j++){
				if(grid.isChoco(gridIndice[i][j]))
					return true;
				compt0=0; compt1=0;
				compt0 = Verification.verificationVerticaleHaut(grid, gridIndice[i][j], i-1, j)+1;
				if(i!=0)
					compt1 = Verification.verificationHorizontal(grid, gridIndice[i][j], i-1, j);
				if(compt0 >=3 || compt1 >=3)
					return true;
					
				compt0=0; compt1=0;
				compt0 = Verification.verificationVerticaleBas(grid, gridIndice[i][j], i+1, j)+1;
				if(i+1<Constantes.nbrV)
					compt1 = Verification.verificationHorizontal(grid, gridIndice[i][j], i+1, j);
				if(compt0 >=3 || compt1 >=3)
					return true;
				
				compt0=0; compt1=0;
				compt0 = Verification.verificationHorizontaleGauche(grid, gridIndice[i][j], i, j-1)+1;
				if(j!=0)
				compt1 = Verification.verificationVerticale(grid, gridIndice[i][j], i, j-1);
				if(compt0 >=3 || compt1 >=3)
					return true;
				
				compt0=0; compt1=0;
				compt0 = Verification.verificationHorizontaleDroite(grid, gridIndice[i][j], i, j+1)+1;
				if(j+1<Constantes.nbrH)
				compt1 = Verification.verificationVerticale(grid, gridIndice[i][j], i, j+1);
				if(compt0 >=3 || compt1 >=3)
					return true;
			}
		return false;
	}
}