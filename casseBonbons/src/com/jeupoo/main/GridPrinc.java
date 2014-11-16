package com.jeupoo.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.jeupoo.animations.*;

/**
 * GridPrinc est le controleur il transforme le model et transmet les informations à la vue
 */
public class GridPrinc {

	public GridModel model;
	/** Contient l'animation */
	public Animation anim;
	/** Compte les points de la partie	 */
	Score point = new Score();
	/**	Grille utilisée pour stocker les bonbons à supprimer */
	public int [][]gridSup ;
	/** Liste utilisée dans l'animation pour remplir la grille de Case	 */
	public List<Movable> listeRemplir = new ArrayList<Movable>();
	/** Compte le nombre de déplacements dans la partie	 */
	int nbrDplcmt;
	public boolean bloque;
	int malus;

	public GridPrinc(int alea,int coups, int malus){
		model = new GridModel();
		gridSup = new int[Constantes.nbrV][Constantes.nbrH];
		nbrDplcmt = coups;
		Constantes.alea = alea;
		this.bloque = false;
		this.malus = malus;
	}
	
	public void  initGridSup(){
		for(int i = 0 ; i < Constantes.nbrV; i++)
			for(int j = 0; j < Constantes.nbrH; j++)
				gridSup[i][j] = -1;
	}
	
	public void initModel(){
			model.initGrilleCase();
			generationGrille();
	}
	
	/**
	 * Génère la grille initiale avec des bonbons normaux
	 */
	public void generationGrille(){

		Random rnd = new Random();
		int nombre = 0, maxV, maxH;
		

		for (int i = 0; i < Constantes.nbrV; i++)
			for (int j = 0; j < Constantes.nbrH; j++){
				maxV = 5; maxH = 5;
						
				while(maxH > 2 || maxV > 2){
					nombre = rnd.nextInt(Constantes.alea);
					maxV = Verification.verificationVerticaleHaut(this, nombre, i, j)+1;
					maxH = Verification.verificationHorizontaleGauche(this, nombre, i, j)+1;
					model.creationCase(nombre, i, j);
				}
			}
		for (int i=0;i<malus; i++)
		{
			int l = rnd.nextInt(Constantes.nbrH);
			int k = rnd.nextInt(Constantes.nbrV);
			model.creationCase(60, k, l);
		}
		setAnimation(new AnimationGenerate());	

		if(!Automate.deplacementPossible(this))
			bloque = true;
	}
	
	/**
	 * Construit une liste avec tous les éléments de la matrice
	 */
	public List<Movable> trans(){
		List<Movable> list = new ArrayList<Movable>();

		for(int i = Constantes.nbrV-1; i >= 0; i--)
			for(int j = 0; j < Constantes.nbrH; j++){
				list.add(model.getMovable(i,j));
			}
		return list;
	}
	
	/**
	 * Teste si les 2 bonbons sont cote à cote puis vérifie si un schéma peut être créé
	 * Si oui, les échange. Sinon les remet à leur place
	 */
	public void clique(float[] xClic, float[] yClic){	
		initGridSup();
		int[] x = new int[2];
		int[] y = new int[2];
	
		for (int i = 0; i < xClic.length; i++){
			x[i] = (int) ((xClic[i] - Constantes.margeHori) / Constantes.largeurCase);
			y[i] = (int) ((yClic[i] - Constantes.margeVerti) / Constantes.hauteurCase) ;
		}

		if(x[0] == x[1] && y[0] == y[1])
			return ;
		
		if(Verification.verificationCoteACote(x, y, this)) {
			switchPlace(model.getCase(y[0], x[0]), model.getCase(y[1], x[1]));
			
			if(testChoco(x,y))
				nbrDplcmt--;
	
			else if (!Verification.verificationLigne(this, y, x)){	
				switchPlace(model.getCase(y[0], x[0]), model.getCase(y[1], x[1]));
				Constantes.soundMap.get("non").start();
			}
			
			else {
				nbrDplcmt--;
				setAnimation(new AnimationDeplacer(model.getMovable(y[0],x[0]), model.getMovable(y[1],x[1])));
			}
		}
}
	/**
	 * Teste si un bonbon est le bonus chocolat
	 */
	public boolean testChoco(int[] x, int[] y){
		
		if (model.getMovable(y[0],x[0]).isChoco()&& !model.getMovable(y[0],x[0]).getMoved()){
			gridSup[y[0]][x[0]] = model.getIndice(y[1],x[1]);
			model.setEmpty(y[0],x[0]);
			model.getMovable(y[0],x[0]).bombe(this, gridSup, y[1], x[1]);
			switchPlace(model.getCase(y[0],x[0]),model.getCase(y[0],x[0]));
			model.setEmpty(y[1],x[1]);
			model.getMovable(y[0],x[0]).setMoved(true);
			return true;
		}
		
		else if (model.getMovable(y[1],x[1]).isChoco() && !model.getMovable(y[0],x[0]).getMoved()){
			gridSup[y[1]][x[1]] = model.getIndice(y[0],x[0]);
			model.setEmpty(y[1],x[1]);
			model.getMovable(y[1],x[1]).bombe(this, gridSup, y[0], x[0]);
			switchPlace(model.getCase(y[0],x[0]), model.getCase(y[1],x[1]));
			model.setEmpty(y[0],x[0]);;
			model.getMovable(y[0],x[0]).setMoved(true);
			return true;
		}
		return false;
	}
	
	/**
	 * Echange les positions de 2 Movables
	 */
	public void switchPlace(Case case1, Case case2){
		Movable tmp;
		int c1Y = case1.getMovable().getY();
		int c1X = case1.getMovable().getX();
		int c2Y = case2.getMovable().getY();
		int c2X = case2.getMovable().getX();
		
		case1.getMovable().setPosition(c1X, c1Y, c2X, c2Y);
		case2.getMovable().setPosition(c2X, c2Y, c1X, c1Y);
		
		tmp = case1.getMovable();
		case1.setMovable(case2.getMovable());
		case2.setMovable(tmp);
		setAnimation(new AnimationDeplacer(case1.getMovable(), case2.getMovable()));
	}

	/**
	 * Appelle l'automate de parcours de la grille pour trouver les schémas à supprimer
	 * Ensuite, lit gridSup puis supprime les bonbons ou les fait évoluer.
	 * Rempli ensuite la grille
	 */
	public void Supprimer(){
		initGridSup();
		gridSup = Automate.parcours(this);
	
		int compt = 0;
		listeRemplir = new ArrayList <Movable>();
		
		for(int i = Constantes.nbrV -1 ; i >= 0; i--){
			for(int j = Constantes.nbrH -1 ; j >= 0; j--){
				if(gridSup[i][j] != -1){
					model.setEmpty(i,j);
					compt++;
				}
				
				if (isBonbonSpe(gridSup[i][j]))
					model.creationCase(gridSup[i][j], i, j);
			}
		}	
		descendreBitmap();
		rajouterBitmap();

		if (compt > 0)
			setAnimation(new AnimationRempli(listeRemplir));			
	
		else
			if(!Automate.deplacementPossible(this))
				bloque = true;
	}
	
	/**
	 * Fait descendre les bonbons pour combler les trous
	 */
	public void descendreBitmap(){
		
		for(int k = 0; k < Constantes.nbrH; k++)
			for(int i = 1; i < Constantes.nbrV; i++)
				for(int j = 0 ; j < Constantes.nbrH; j++)
					if(model.isEmpty(i,j) && !model.isEmpty(i-1,j)){
						model.descendreCase(i, j);
						listeRemplir.add(model.getMovable(i,j));
					}
	}
	
	/**
	 * Rajoute des bonbons pour complèter la grille
	 */
	public void rajouterBitmap(){

		int nombre = 5;
		Random rnd = new Random();

		for(int i = Constantes.nbrV - 1; i >= 0; i--)
			for(int j = Constantes.nbrH -1 ; j >= 0; j--)
				if(model.isEmpty(i,j)){
					nombre = rnd.nextInt(Constantes.alea);
					model.creationCase(nombre, i, j);
					listeRemplir.add(model.getMovable(i,j));
				}
	}

	public Animation getAnimation(){
		return anim;
	}
	
	public void setAnimation(Animation anim){
		this.anim = anim;
	}
	
	public int getnbrDplcmt() {
		return nbrDplcmt;
	}
	
	public Score getPoint(){
		return point;
	}
	
	/**
	 * Tests pour déterminer le type du bonbon
	 */
	public boolean isBonbonSpe(int indice)
	{
		if(indice >= Constantes.DEB_RV && indice <= Constantes.CHOCO)
			return true;
		return false;
	}
	public boolean isChoco(int indice){
		if(indice == Constantes.CHOCO)
			return true;
		return false;
	}
}