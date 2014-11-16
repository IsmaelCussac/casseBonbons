package com.jeupoo.main;

import java.util.HashMap;

import android.graphics.Bitmap;
import android.media.MediaPlayer;

/**
 * Constantes dont on a besoin tout au long du jeu ou d une partie
 */
public final class Constantes {
	/**
	 * Intervalles qui délimitent chaque type de bonbons
	 */
	public static final int DEB_NM = 0; 
	public static final int FIN_NM = 9; 
	public static final int DEB_RV = 10; 
	public static final int FIN_RV = 19; 
	public static final int DEB_RH = 20; 
	public static final int FIN_RH = 29; 
	public static final int DEB_E = 30; 
	public static final int FIN_E = 39; 
	public static final int CHOCO = 40; 
	
	/**
	 * Nombre de bonbons bonus
	 */
	public static final int NB_BONUS = 5;
	
	/**
	 * Ecart minimum entre deux bonbons de même couleur mais de type différent
	 */
	public static final int TRANCHE = 10;
	
	public static int alea;
	public static void setAlea(int alea){
		Constantes.alea= alea;
	}
	
	/**
	 * Dimensions de la grille
	 */
	public static int nbrV;
	public static int nbrH;
	
	public static void setDimGrille(int nbrv, int nbrh){
		Constantes.nbrV = nbrv;
		Constantes.nbrH = nbrh;
	}
	
	/** Dimensions de l'écran de l'appareil	 */
	public static int[] dimEcran;
	public static void setDimEcran(int[] dimEcran){
		Constantes.dimEcran = dimEcran;
		
		float margeHori = (float) (0.015*Constantes.dimEcran[1]);
		float margeVerti = (float) (0.1*Constantes.dimEcran[0]);
		Constantes.setDimMarge(margeHori, margeVerti);
		float hauteurCase = (float) (0.11*Constantes.dimEcran[1]);
		float largeurCase = (float) (0.105*Constantes.dimEcran[1]);
		Constantes.setDimCase(hauteurCase, largeurCase);
	}
	
	/** Dimensions d'une Case */
	public static float hauteurCase;
	public static float largeurCase;
	public static void setDimCase(float hauteurCase, float largeurCase){
		Constantes.hauteurCase = hauteurCase;
		Constantes.largeurCase = largeurCase;
		
	}
	
	/**	Marges depuis le bord de la vue	 */
	public static float margeHori;
	public static float margeVerti;
	public static void setDimMarge(float margeHori, float margeVerti){
		Constantes.margeHori = margeHori;
		Constantes.margeVerti = margeVerti;
	}
	
	/** Map qui contient les différents icones	*/
	public static HashMap<Integer,Bitmap> iconMap;
	public static void setIconMap(HashMap<Integer,Bitmap> iconMap){
		Constantes.iconMap = iconMap;
	}
	
	/** Map qui contient les différents isons	*/
	public static HashMap<String,MediaPlayer> soundMap;
	public static void setSoundMap(HashMap<String,MediaPlayer> soundMap){
		Constantes.soundMap = soundMap;
	}
}
