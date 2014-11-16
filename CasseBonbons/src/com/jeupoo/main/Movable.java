package com.jeupoo.main;


import android.graphics.Bitmap;
import android.graphics.Point;

/**
 * Classe abstraite qu'etendent les bonbons
 */
public abstract class Movable{
	
	/** Image du bonbon */
	protected Bitmap bitmap ;
	/** Code associé à la couleur et au type du bonbon, voir Map dans PlayActivity	*/
	protected int indice;
	/** Points utilisés lors d'un déplacement du Movable	*/
	private Point from;
	private Point to;
	/** Averti si le Movable doit être déplacé ou non 	*/
	public boolean isMoved;
	
	public int getY() {
		return to.y;
	}

	public int getX() {
		return to.x;
	}
	
	public boolean getMoved(){
		return isMoved;
	}
	
	public void setMoved(boolean bool){
		isMoved = bool;
	}
	
	public void setPosition(int cx, int cy, int x, int y) {	
		from = new Point(cx, cy);
		to = new Point(x,y);	
	}
	
	public Point getFrom(){
		return from;
	}
	
	public Point getTo(){
		return to;
	}

	public int getIndice() {	
		return indice;
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	/**
	 * Tests pour déterminer le type du bonbon
	 */
	public boolean isChoco(){
		if(getIndice() == Constantes.CHOCO)
			return true;
		return false;
	}
	
	/**
	 * Renvoi la valeur du bonbon
	 */
	public abstract int getValeur();
	
	/**
	 * Met la case à vide pour qu'elle soit supprimée.
	 * Ajoute la valeur du bonbon
	 * Applique les bonus s'il y en a
	 * Rempli gridDup
	 */
	public abstract void bombe(GridPrinc grid, int[][] gridSup, int y, int x);
}