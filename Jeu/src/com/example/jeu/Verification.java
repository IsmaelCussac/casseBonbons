package com.example.jeu;

public class Verification {
	
	public static int verificationVerticaleHaut(Grid grille,int nombre, int i,int j)
	{
		int nbr = 1;
		int k=i-1;
		while (k>=0 && grille.grille[k][j].getColor() == nombre)
		{
			nbr++;
			k--;
		}
		System.out.println("haut    "+nbr);
		return nbr;
	}
	public static int verificationHorizontalGauche(Grid grille,int nombre, int i,int j)
	{
		int nbr=1;
		int k=j-1;
		while (k>=0 && grille.grille[i][k].getColor() == nombre)
		{
			nbr++;
			k--;
		}
		System.out.println("gauche    "+nbr);
		return nbr;
	}
	/*public static int verificationVerticaleBas(Grid grille,int nombre, int i,int j)
	{
		int nbr = 0;
		int k=i+1;
		while (k<grille.nbrv && grille.grille[k][j].getColor() == nombre)
		{
			nbr++;
			k++;
		}
		System.out.println("bas    "+nbr);
		return nbr;
	}
	
	public static int verificationHorizontalDroite(Grid grille,int nombre, int i,int j)
	{
		int nbr=0;
		int k=j+1;
		while (k<grille.nbrh && grille.grille[i][k].getColor() == nombre)
		{
			nbr++;
			k++;
		}
		System.out.println("droite    "+nbr+ "      "+k);
		return nbr;
	}*/
	
	
	public static int verificationVerticale(Grid grille, int couleur, int y, int x){
		
		int total = 1;
		int j=y-1;
		
		while(j>=0 && grille.grille[j][x].getColor()==couleur)
		{
			total++;
			j--;
		}
		j=y+1;
		while(j<grille.nbrv && grille.grille[j][x].getColor()==couleur)
		{
			total++;
			j++;
		}
		return total;
	}
	
public static int verificationHorizontal(Grid grille, int couleur, int y, int x){
		
		int total = 1;
		int j=x-1;
		
		while(j>=0 && grille.grille[y][j].getColor()==couleur)
		{
			total++;
			j--;
		}
		j=x+1;
		while(j<grille.nbrv && grille.grille[y][j].getColor()==couleur)
		{
			total++;
			j++;
		}
		return total;
	}
	
	public static boolean verificationCoteACote(int[] x, int[] y, Grid grille )
	{
			
		if( ((Math.abs(x[1] - x[0]) == 1) && (y[1]==y[0])) || ((Math.abs(y[1] - y[0]) == 1) && (x[1]==x[0]))){
			
			if (grille.grille[x[0]][y[0]].getColor() != grille.grille[x[1]][y[1]].getColor())
				
					return true;
			
		}
		
		return false ;
		
	}
	
	public static boolean verificationLigne(Grid grille, int[] y,int[] x)
	{
		
		int []nbrh = new int[2];
		int []nbrv = new int[2];
		
		for (int i=0; i<2; i++)
		{
			nbrh[i]= verificationHorizontal(grille,grille.grille[y[i]][x[i]].getColor(),y[i],x[i]);
			nbrv[i]= verificationVerticale(grille,grille.grille[y[i]][x[i]].getColor(),y[i],x[i]);
			if (nbrh[i]>=3 || nbrv[i]>=3)
				return true;
		}
		/*
		int []nbrhd = new int[2];
		int []nbrhg = new int[2];
		int []nbrvh = new int[2];
		int []nbrvb = new int[2];
		
		int j=0;
		
		for (int i=0; i<2; i++)
		{
			/*if(i==0)
				j=1;
			else
				j=0;
			
			nbrhd[i]= verificationHorizontalDroite(grille,grille.grille[x[i]][y[i]].getColor(),y[i],x[i]);
			nbrhg[i] = verificationHorizontalGauche(grille,grille.grille[x[i]][y[i]].getColor(),y[i],x[i]);
			nbrvh[i]= verificationVerticaleHaut(grille,grille.grille[x[i]][y[i]].getColor(),y[i],x[i]);
			nbrvb[i]= verificationVerticaleBas(grille,grille.grille[x[i]][y[i]].getColor(),y[i],x[i]);
			if ((nbrhd[i]+nbrhg[i]>=3) || (nbrvh[i]+nbrvb[i]>=3))
					return true;
		}*/
		
		
		
		
		
		return false;
	 
		 
	}

}