package com.example.jeu;

import java.util.HashMap;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;

public class Grid {
	/**
	 * @uml.property  name="grille" multiplicity="(0 -1)" dimension="2"
	 */
	Movable[][] grille ;
	/**
	 * @uml.property  name="nbrv"
	 */
	int nbrv;
	/**
	 * @uml.property  name="nbrh"
	 */
	int nbrh;
	/**
	 * @uml.property  name="anim"
	 * @uml.associationEnd  
	 */
	Animation anim;
	/**
	 * @uml.property  name="icon"
	 * @uml.associationEnd  qualifier="valueOf:java.lang.Integer android.graphics.Bitmap"
	 */
	HashMap<Integer, Bitmap> icon;
	/**
	 * @uml.property  name="couleur"
	 * @uml.associationEnd  qualifier="valueOf:java.lang.Integer java.lang.Integer"
	 */
	HashMap<Integer, Integer> couleur = new HashMap<Integer, Integer>();
	public Grid(HashMap<Integer, Bitmap> icon, int nbrv, int nbrh)
	{
		grille = new Movable[nbrv][nbrh];
		this.nbrh = nbrh;
		this.nbrv = nbrv;
		this.icon = icon;
		couleur.put(0, Color.BLUE);
		couleur.put(1, Color.RED);
		couleur.put(2, Color.GREEN);
		couleur.put(3, Color.YELLOW);
	}
	

	public void generationGrille ()
	{
		anim = new AnimationGenerate();
		Random rnd = new Random();
		int nombre=0;
		for (int i=0; i<nbrv; i++)
			for (int j=0; j<nbrh; j++)
			{
				int vv = 5, vh = 5;
				
				while(vh > 2 || vv > 2)
				{
					nombre = rnd.nextInt(4);
					vv = Verification.verificationVerticaleHaut(this,couleur.get(nombre),i,j);
					vh = Verification.verificationHorizontalGauche(this,couleur.get(nombre),i,j);
//					System.out.println(vv+" "+vh);
				}
				//grille[i][j] = new Circle(couleur.get(nombre),64+j*80,140+i*80);
				grille[i][j] = new Image(icon.get(nombre),24+j*80,140+i*80);
			}
	}
	
	
	
	public Movable[] trans()
	{
		Movable[] gr = new Movable[nbrv*nbrh];
		int k =0;
		
		for(int i=nbrv-1; i>=0; i--)
			for(int j=0; j<nbrh; j++)
			{	
				gr[k]=grille[i][j];
				k++;
			}
		return gr;
	}
	
	/**
	 * @return
	 * @uml.property  name="anim"
	 */
	public Animation getAnim()
	{
		return anim;
	}
	
	public void Clique(float[] xClic, float[] yClic)
	{	
		int[] x = new int[2];
		int[] y = new int[2];
		
		for (int i = 0; i<xClic.length; i++)
		{
			x[i] = (int) ((xClic[i] - 24) / 80);
			System.out.println(yClic[i]);
			y[i] = (int) ((yClic[i] - 100) / 80) ;
		}
		
		 System.out.println(x[0]+"    "+x[1]);
		 System.out.println(y[0]+"    "+y[1]);
		 
		int tmpcol1 = grille[y[0]][x[0]].getColor();
		int tmpcol2 = grille[y[1]][x[1]].getColor();
		
		if(Verification.verificationCoteACote(x, y,this)) 
		{
			//anim = new AnimationChange();
			grille[y[0]][x[0]].setColor(tmpcol2);
			grille[y[1]][x[1]].setColor(tmpcol1);
			
			if (!Verification.verificationLigne(this, y, x))
			{
				grille[y[0]][x[0]].setColor(tmpcol1);
				grille[y[1]][x[1]].setColor(tmpcol2);
			}
		}
		
	}

	private Context getBaseContext() {
		return null;
	}
}