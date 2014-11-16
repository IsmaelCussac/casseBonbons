package com.jeupoo.main;

import com.jeupoo.activities.MainActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.widget.TextView;
import com.jeupoo.animations.*;

/**
 * Gère tous les affichages: MyView, TextView et AlertDialog
 */
public class GraphicWorld implements IDrawable{
	
	Suivi suivi;
	public int objPoints = 0, objCoups = 0, objTemps = 0, objDifficulte = 0;
	private long begin, end, enc;
	private boolean win = false, loose = false;
	private TextView points, limite, level, objectif;
	private AlertDialog dialogWin, dialogLos, dialogBloque;
	Context context;
	
	
	public GraphicWorld(Suivi suivi, Context context, TextView points, TextView limite,  TextView level, TextView objectif){
		this.dialogWin = new AlertDialog.Builder(context).create();
		this.dialogLos = new AlertDialog.Builder(context).create();
		this.dialogBloque = new AlertDialog.Builder(context).create();
		this.suivi = suivi;
		this.context = context;
		chargerNiveau();
		this.points = points;
		this.limite = limite;
		this.level = level;
		this.objectif = objectif;
	}
	
	public void onDraw(Canvas canvas, MyView view, int dt,int iWidth, int iHeight) {
		enc = System.currentTimeMillis();
		
		affichageEtatJeu();
		affichageObjectifs();
		suivi.conditionDiffic();
		testFinPartie();
		testBloquer();


		Animation anim = suivi.getGridAnimation();
		anim.animer(suivi.grid, canvas, view, dt);
	}
	
	public void testFinPartie(){
		
		if(suivi.conditionArretWin(enc, end, objPoints)){
			if(!win)
				Constantes.soundMap.get("win").start();
			win = true;
			limite.setText("0");
			suivi.winPartie();
			dialogWin();
		} 
		else if(suivi.conditionArretLoose(enc, end, objPoints)){
			if(!loose)
				Constantes.soundMap.get("loose").start();
			loose = true;
			limite.setText("0");
			suivi.loosePartie();
			dialogLoose();
		}
	}
	
	public void testBloquer(){
		if(suivi.getBloquer())
			dialogBloquer();
	}

	private void affichageEtatJeu(){
		if(!suivi.getDebutChrono() && !suivi.getConfigS("temps").equals("-1"))
			limite.setText(""+suivi.getConfigS("temps"));
		
		else if(suivi.getConfigS("temps").equals("-1") && !suivi.getConfigS("coups").equals("-1"))
			limite.setText(""+suivi.grid.getnbrDplcmt());
			
		else if(!suivi.getConfigS("temps").equals("-1")){	
			int c = suivi.getConfigI("temps");
			limite.setText(""+ (c -(int)((enc - end)/1000)));
		}
		else if(suivi.getConfigS("temps").equals("-1") && suivi.getConfigS("coups").equals("-1"))
			limite.setText("");

		points.setText(""+suivi.grid.point.getPoints());
		level.setText( suivi.getConfigS("lvl"));
	}
	
	private void affichageObjectifs(){
		String txt = "";
		
		if(suivi.getConfigI("points") != -1){
			objPoints = suivi.getConfigI("points");
			txt += suivi.getConfigI("points")+"points ";
		}
		if(suivi.getConfigI("coups") != -1){
			objCoups = suivi.getConfigI("coups");
			txt += " en "+suivi.getConfigI("coups")+"coups ";
		}
		if(suivi.getConfigI("temps") != -1){
			objTemps = suivi.getConfigI("temps");
			txt += " en "+suivi.getConfigI("temps")+"sec ";
		}
		objectif.setText(""+txt);
	}
	
	/**
	 * Lors d'un clic, démarre le chrono si début de partie
	 * Donne les coordonnées de déplacement à gridPrinc
	 */
	public void setClic(float[] xClic, float[] yClic){
		if(!suivi.getDebutChrono() && !suivi.getConfigS("temps").equals("-1")){
			begin = System.currentTimeMillis();
			end = begin + suivi.getConfigI("temps");
		}
		suivi.setDebutChrono(true);
		suivi.grid.clique(xClic, yClic);
	}
	
	/**
	 * Dialog en cas de non possibilité de mouvements
	 * Relance une nouvelle grille en conservant le score
	 */
	private void dialogBloquer() {
		
		dialogWin.setTitle("Oups!");
		dialogBloque.setMessage("Vous êtes bloqué!");
		
		dialogBloque.setButton(DialogInterface.BUTTON_NEUTRAL,"Nouvelle grille", new DialogInterface.OnClickListener() {
			/** Retour au menu	*/
			public void onClick(DialogInterface dialog, int which) {
				suivi.grid.generationGrille();
				suivi.grid.bloque = false;
				
			}
		} );	
		dialogBloque.show();	
	}
	
	/**
	 * Affichage en cas de victoire
	 */
	private void dialogWin(){
		dialogWin.setTitle("Bravo!!");
		dialogWin.setMessage("Objectif atteint.\n\t"+suivi.grid.point.getPoints()+" points.");
		
		dialogWin.setButton(DialogInterface.BUTTON_NEGATIVE,"Menu", new DialogInterface.OnClickListener() {
			/** Retour au menu	*/
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(context, MainActivity.class);
            	context.startActivity(intent);
			}
		} );
		
		dialogWin.setButton(DialogInterface.BUTTON_POSITIVE, "Niveau suivant", new DialogInterface.OnClickListener() {
			/**	
			 * Lance le niveau suivant s'il existe sinon retourne au menu
			 */
			public void onClick(DialogInterface dialog, int which) {
				suivi.setFile(suivi.getConfigS("suiv"));
				if(suivi.getFile().equals("fin")){
					Intent intent = new Intent(context, MainActivity.class);
            		context.startActivity(intent);
				}
				else	
					chargerNiveau();
			}
		} );
		dialogWin.show();	
	}
	
	/**
	 * Affichage en cas de défaite
	 */
	private void dialogLoose(){
		dialogLos.setTitle("Echec!!");
		dialogLos.setMessage("Vous avez Perdu.");
		
		dialogLos.setButton(DialogInterface.BUTTON_NEGATIVE,"Menu", new DialogInterface.OnClickListener() {
			/** Retour au menu */
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(context, MainActivity.class);
            	context.startActivity(intent);
				
			}
		} );
		
		dialogLos.setButton(DialogInterface.BUTTON_POSITIVE,"Recommencer", new DialogInterface.OnClickListener() {
			/**
			 * Relance le niveau
			 */
			public void onClick(DialogInterface dialog, int which) {
				suivi.setFile("lvl"+suivi.getConfigS("lvl")+".txt");
				if(suivi.getFile().equals("fin")){
					Intent intent = new Intent(context, MainActivity.class);
            		context.startActivity(intent);
				}
				else	
					chargerNiveau();
			}
		} );
		dialogLos.show();
	}
	
	/**
	 * Charge le niveau demandé
	 * Si clic sur bouton "Continuer", nomFichier est initialisé à "/reprendre.txt"
	 * Cherche le nom du fichier et le met dans nomFichier
	 * Si nomFichier n'est pas modifié, donc "/reprendre.txt" n'existe pas encore, charge le niveau 1
	 * Sinon charge le niveau demandé
	 * 
	 * Si clic sur le bouton "Jouer" charge le niveau 1
	 */
	public void chargerNiveau(){
		win = false;
		loose = false;
		suivi.chargerNiveau();
	}
}