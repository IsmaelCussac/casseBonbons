package com.jeupoo.main;

/**
 * Element qui constitue la grille
 * Peut contenir un Movable ou être vide
 */
public class Case {
	
	/**
	 * Précise si la Case est vide ou non
	 */
	private boolean empty = true;
	
	/**
	 * Movable contenu par la Case
	 */
	private Movable mCase;

	public boolean isEmpty(){
		return empty ;	
	}
	
	public void setEmpty(){
		this.empty = true;
	}
	
	public void setFull(){
		this.empty = false;
	}
	
	public Movable getMovable(){
		return mCase;
	}
	
	public int getIndice(){
		return mCase.getIndice();
	}

	public void setMovable(Movable mCase) {
		this.mCase = mCase;
	}
}