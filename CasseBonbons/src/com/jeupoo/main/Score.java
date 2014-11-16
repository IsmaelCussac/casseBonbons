package com.jeupoo.main;

public class Score {
	
	int score ;
	
	public Score(){
		score = 0;
	}
	
	/** return le score	*/
	public int getPoints(){
		return score;
	}
	
	/** Ajoute le nombre de point au score	*/
	public void Add(int points){
		score += points;
	}
}