package com.example.jeu;


import android.graphics.Canvas;

public class GraphicWorld implements IDrawable
{
	/**
	 * @uml.property  name="grille"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	Grid grille;
	/**
	 * @uml.property  name="gObjet"
	 * @uml.associationEnd  multiplicity="(0 -1)"
	 */
	GObjet[] GObjet = null;
	/**
	 * @uml.property  name="bk"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	BackGround bk;
	
	public GraphicWorld(Grid gr, BackGround bk,GObjet ... grille )
	{
		this.GObjet=grille;
		this.bk=bk;
		this.grille=gr;
	}
	/**
	 * @uml.property  name="t"
	 */
	int t =0;
	@Override
	public void onDraw(Canvas canvas, int dt,int iWidth,int iHeight) {
		// TODO Auto-generated method stub
		bk.paint(canvas, iWidth, iHeight);		
		Animation a = grille.getAnim();
		a.dessiner(grille,GObjet, canvas, dt);

	}
	
	public void add(GObjet gObjet){
		//GObjet.add(gObjet);
	}

}