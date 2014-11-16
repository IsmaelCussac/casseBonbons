package com.jeupoo.activities;

import java.util.HashMap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.jeupoo.R;
import com.jeupoo.main.*;

/** 
 * Activité du jeu en cours
 */

public class PlayActivity extends Activity {
	
	private GraphicWorld world;
	float xClic[] = new float[2];
	float yClic[] = new float[2];
	
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		Bundle bundle = this.getIntent().getExtras();
		String reprise = bundle.getString("reprise");
		String nomFichier;
		
		if(reprise.equals("non"))
			nomFichier = "lvl1.txt";
		else if(reprise.equals("oui"))
			nomFichier = "/reprendre.txt";
		else
			nomFichier = reprise+".txt";
		
		configurerFenetre();
        recupererDimensionEcran();
        remplirMapSon();
    	remplirMapIcone();
    	creerJeu(nomFichier);
	}	
	
	/**
	 * Transmet les coordonnées des clics à GridPrinc via GraphicWorld
	 */
	public boolean onTouchEvent(MotionEvent event){  

		switch (event.getAction()){
			case MotionEvent.ACTION_DOWN :
				if(event.getX() > Constantes.nbrH*0.105*Constantes.dimEcran[1] || event.getX() < 0 || event.getY() > 0.1*Constantes.dimEcran[0]+Constantes.nbrV*0.11*Constantes.dimEcran[1] || event.getY() < 0.1*Constantes.dimEcran[0])
					return true ;	
				
				xClic[0] = event.getX();
				yClic[0] = event.getY();
				break;
				
			case MotionEvent.ACTION_MOVE :
				if(event.getX() > Constantes.nbrH*0.105*Constantes.dimEcran[1] || event.getX() < 0 || event.getY() > 0.1*Constantes.dimEcran[0]+Constantes.nbrV*0.11*Constantes.dimEcran[1] || event.getY() < 0.1*Constantes.dimEcran[0])
					return true ;	
		
				xClic[1] = event.getX();
				yClic[1] = event.getY();
				world.setClic(xClic, yClic);
				break;	
		}                            
		return true;	
	}
	
	private void creerJeu(String nomFichier){
		
		MyView view = (MyView) findViewById(R.id.myView);
        TextView score = (TextView) findViewById(R.id.score);
        TextView level = (TextView) findViewById(R.id.level);
        TextView objectif = (TextView) findViewById(R.id.objectif);
        TextView limite = (TextView) findViewById(R.id.limite);     
        Suivi suivi = new Suivi(nomFichier, this);
       
        world = new GraphicWorld(suivi, this, score, limite, level, objectif);
        view.setOnDraw(world, null);
	}
	
	private void recupererDimensionEcran(){
		int[] dimEcran = new int[2];
		DisplayMetrics metrics = new DisplayMetrics();
    	getWindowManager().getDefaultDisplay().getMetrics(metrics);
    	dimEcran[0] = metrics.heightPixels;
    	dimEcran[1] = metrics.widthPixels;
    	Constantes.setDimEcran(dimEcran);

	}
	
	private void configurerFenetre(){
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.play);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setBackgroundDrawableResource(R.drawable.fond_play);   
	}
	
	private void remplirMapSon(){

		HashMap<String, MediaPlayer> soundMap = new HashMap<String, MediaPlayer>();
		
		soundMap.put("choco", MediaPlayer.create(this, R.raw.chocolate_removed));
		soundMap.put("win", MediaPlayer.create(this, R.raw.level_completed));	
		soundMap.put("loose", MediaPlayer.create(this, R.raw.level_failed));	
		soundMap.put("bombeE", MediaPlayer.create(this, R.raw.colour_bomb));	
		soundMap.put("non", MediaPlayer.create(this, R.raw.negative_switch_sound));
		soundMap.put("bombeR", MediaPlayer.create(this, R.raw.bomb_sound));	
		
		Constantes.setSoundMap(soundMap);
	}
	
	private void remplirMapIcone(){
		
		HashMap<Integer, Bitmap> iconMap = new HashMap<Integer, Bitmap>();

		Bitmap iconeBleu = BitmapFactory.decodeResource(this.getResources(), R.drawable.bleu);
        Bitmap iconeRouge = BitmapFactory.decodeResource(this.getResources(), R.drawable.rouge);
        Bitmap iconeVert = BitmapFactory.decodeResource(this.getResources(), R.drawable.vert);
        Bitmap iconeJaune = BitmapFactory.decodeResource(this.getResources(), R.drawable.jaune);
        Bitmap iconeOrange = BitmapFactory.decodeResource(this.getResources(), R.drawable.orange);
        Bitmap iconeViolet = BitmapFactory.decodeResource(this.getResources(), R.drawable.violet);
        Bitmap iconeMalus = BitmapFactory.decodeResource(this.getResources(), R.drawable.bonbon_malus);
        Bitmap iconeBleuRV = BitmapFactory.decodeResource(this.getResources(), R.drawable.bleu_rv);
        Bitmap iconeRougeRV = BitmapFactory.decodeResource(this.getResources(), R.drawable.rouge_rv);
        Bitmap iconeVertRV = BitmapFactory.decodeResource(this.getResources(), R.drawable.vert_rv);
        Bitmap iconeJauneRV = BitmapFactory.decodeResource(this.getResources(), R.drawable.jaune_rv);
        Bitmap iconeOrangeRV = BitmapFactory.decodeResource(this.getResources(), R.drawable.orange_rv);
        Bitmap iconeVioletRV = BitmapFactory.decodeResource(this.getResources(), R.drawable.violet_rv);
        Bitmap iconeBleuRH = BitmapFactory.decodeResource(this.getResources(), R.drawable.bleu_rh);
        Bitmap iconeRougeRH = BitmapFactory.decodeResource(this.getResources(), R.drawable.rouge_rh);
        Bitmap iconeVertRH = BitmapFactory.decodeResource(this.getResources(), R.drawable.vert_rh);
        Bitmap iconeJauneRH = BitmapFactory.decodeResource(this.getResources(), R.drawable.jaune_rh);
        Bitmap iconeOrangeRH = BitmapFactory.decodeResource(this.getResources(), R.drawable.orange_rh);
        Bitmap iconeVioletRH = BitmapFactory.decodeResource(this.getResources(), R.drawable.violet_rh);
        Bitmap iconeBleuE = BitmapFactory.decodeResource(this.getResources(), R.drawable.bleu_e);
        Bitmap iconeRougeE = BitmapFactory.decodeResource(this.getResources(), R.drawable.rouge_e);
        Bitmap iconeVertE = BitmapFactory.decodeResource(this.getResources(), R.drawable.vert_e);
        Bitmap iconeJauneE = BitmapFactory.decodeResource(this.getResources(), R.drawable.jaune_e);
        Bitmap iconeOrangeE = BitmapFactory.decodeResource(this.getResources(), R.drawable.orange_e);
        Bitmap iconeVioletE = BitmapFactory.decodeResource(this.getResources(), R.drawable.violet_e);
        Bitmap iconeChoco = BitmapFactory.decodeResource(this.getResources(), R.drawable.choco);
        Bitmap iconeVide = BitmapFactory.decodeResource(this.getResources(), R.drawable.vide);

        iconMap.put(0, iconeBleu);
		iconMap.put(1, iconeRouge);
		iconMap.put(2, iconeVert);
		iconMap.put(3, iconeJaune);
		iconMap.put(4, iconeOrange);
		iconMap.put(5, iconeViolet);
		iconMap.put(10, iconeBleuRV);
		iconMap.put(11, iconeRougeRV);
		iconMap.put(12, iconeVertRV);
		iconMap.put(13, iconeJauneRV);
		iconMap.put(14, iconeOrangeRV);
		iconMap.put(15, iconeVioletRV);
		iconMap.put(20, iconeBleuRH);
		iconMap.put(21, iconeRougeRH);
		iconMap.put(22, iconeVertRH);
		iconMap.put(23, iconeJauneRH);
		iconMap.put(24, iconeOrangeRH);
		iconMap.put(25, iconeVioletRH);
		iconMap.put(30, iconeBleuE);
		iconMap.put(31, iconeRougeE);
		iconMap.put(32, iconeVertE);
		iconMap.put(33, iconeJauneE);
		iconMap.put(34, iconeOrangeE);
		iconMap.put(35, iconeVioletE);
		iconMap.put(40, iconeChoco);
		iconMap.put(50, iconeVide);
		iconMap.put(60, iconeMalus);
		
		Constantes.setIconMap(iconMap);
	}
}