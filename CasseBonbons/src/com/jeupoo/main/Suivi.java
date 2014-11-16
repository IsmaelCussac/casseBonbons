package com.jeupoo.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import com.jeupoo.animations.Animation;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

/**
* Contient les classes de gestions des fichiers et des conditions d'arrêt
 */
public class Suivi {
	
	private String file;
	boolean debutChrono = false;
	private Context context;
	public GridPrinc grid;
	private HashMap<String,String> config = new HashMap<String, String>();
	
	public Suivi(String file, Context context){
		this.file = file;
		this.context = context;
	}
	
	/**
	 * Charge le niveau: si le fichier reprendre.txt n'existe pas encore, charge le niveau 1
	 * Sinon lit le niveau dans le fichier reprendre.txt puis fait lireFichier()
	 */
	public void chargerNiveau(){
		setDebutChrono(false);
		String fileName = "/reprendre.txt";
		clearConfig();
		if(getFile().equals(fileName)){
			recupererNomFichier();
			if(getFile().equals(fileName))
				setFile("lvl1.txt");
			lireFichier();
		}
		else
			lireFichier();
		
		int nbrV = getConfigI("hauteur");
		int nbrH = getConfigI("largeur");
		Constantes.setDimGrille(nbrV, nbrH);
		
		grid = new GridPrinc(getConfigI("col"), getConfigI("coups"), getConfigI("malus"));
		grid.initModel();
	}
	
	public boolean conditionArretWin(long enc, long end, int objPoints ){
		if(!getConfigS("coups").equals("-1") && grid.getnbrDplcmt() <= 0 && grid.point.getPoints() >= objPoints)
			return true;
		else if(!getConfigS("temps").equals("-1") && getConfigI("temps")-(int)((enc - end)/1000) == 0 && grid.point.getPoints() >= objPoints)
			return true;
		else if(getConfigS("coups").equals("-1") && getConfigS("temps").equals("-1") && grid.point.getPoints() >= objPoints)
			return true;
		return false;		
	}
	
	public void conditionDiffic()
	{	
		if(getConfigI("difficulte")>0)
			Constantes.setAlea(getConfigI("col")+grid.point.getPoints()/Math.abs(getConfigI("difficulte")));

	}
	public boolean conditionArretLoose(long enc, long end, int objPoints){
		if(grid.getnbrDplcmt() == 0 && grid.point.getPoints() < objPoints)
			return true;
		else if(getConfigI("temps")-(int)((enc - end)/1000) == 0 && grid.point.getPoints() < objPoints)
			return true;
		return false;
	}
	
	public void winPartie(){
			putConfig("temps", "-1");
			try {	ecrireEtat();	} 
			catch (IOException e) {
				Log.e("TEST1","ERROR DE CREATION DE FICHIER");
			}
			try {	ecrireUnlock();	} 
			catch (IOException e) {
				Log.e("TEST1","ERROR DE CREATION DE FICHIER");
			}
	} 
	
	public void loosePartie(){
			putConfig("temps", "-1");
	}
	
	/**
	 * Récupère le nom du fichier qui doit être lu pour charger le niveau dans le fichier reprendre.txt
	 * Si le fichier n'existe pas return
	 */
	public void recupererNomFichier() {

		String fileName = "/reprendre.txt";
		File file = new File(context.getFilesDir().getAbsolutePath() + fileName);
		if(!file.exists()){	return;	}
		
		try {		
			InputStreamReader input = new InputStreamReader(new FileInputStream(context.getFilesDir().getAbsolutePath() + fileName),"UTF-8");
			
			int line;
			while((line = input.read()) > 0) {
				String fichier = "lvl"+String.valueOf(line)+".txt";
				setFile(fichier);
			}
			input.close(); 
		}
		catch (IOException e) {	Log.e("TEST","ERROR DE RECUPERATION DE FICHIER");}
	}
	
	/** 
	 * Lit le fichier du niveau et charge les paramètres dans la map
	 */
	public void lireFichier(){
		String line;
		AssetManager assetManager = context.getAssets();
		try {		
			BufferedReader buff = new BufferedReader(new InputStreamReader(assetManager.open(getFile())));
			while ((line = buff.readLine()) != null) {
				String[] result = line.split("::");
				if (result[0].length() > 0)
					putConfig(result[0], result[1]);
			}
			buff.close(); 
		}
		catch (IOException e) {	Log.e("TEST","ERROR DE LECTURE DE FICHIER");}
	}
	
	/**
	 * A la fin d'un niveau, écrit dans le fichier reprendre le niveau suivant
	 */
	public void ecrireEtat() throws IOException{
	
		String fileName = "/reprendre.txt";
		try {
			FileOutputStream output = new FileOutputStream(context.getFilesDir().getAbsolutePath() + fileName,false);
			int lvl = getConfigI("lvl");  
			lvl++;
			output.write(lvl);
			output.close();
		} 
		catch (FileNotFoundException e) {Log.e("TEST","ERROR DE CREATION DE DOSSIER");}
	}
	
	public void ecrireUnlock() throws IOException{
		
		String fileName = "/lock.txt";
		int level = 1;
		File file = new File(context.getFilesDir().getAbsolutePath() + fileName);
		int lvl = getConfigI("lvl"); 
		if(file.exists()){
			level = lireUnlock();	}
		
		if(level <= lvl){
			try {
				FileOutputStream output = new FileOutputStream(context.getFilesDir().getAbsolutePath() + fileName,false);
				
				lvl++;
				output.write(lvl);
				output.close();
			} 
			catch (FileNotFoundException e) {Log.e("TEST","ERROR DE CREATION DE DOSSIER");}
		}
	}
	
	public int lireUnlock(){
		int line = 1;
		String fileName = "/lock.txt";
		File file = new File(context.getFilesDir().getAbsolutePath() + fileName);
		if(!file.exists()){	return line;	}
	
		try {		
			InputStreamReader input = new InputStreamReader(new FileInputStream(context.getFilesDir().getAbsolutePath() + fileName),"UTF-8");
			line = input.read();
			input.close(); 
		}
		catch (IOException e) {	Log.e("TEST","ERROR DE RECUPERATION DE FICHIER");}
		return line;
	}
	
	public Animation getGridAnimation(){
		return grid.getAnimation();
	}
	
	public void  setFile(String file){ 
		this.file = file;
	}
	
	public String  getFile(){ 
		return file;
	}
	
	public String getConfigS(String key){ 
		return config.get(key); 
	}
	
	public int getConfigI(String key){ 
		return Integer.parseInt(config.get(key));
	}
	
	public void putConfig(String key, String value){ 
		config.put(key, value);
	}
	
	public void clearConfig(){ 
		config.clear();
	}
	
	public void setDebutChrono(boolean bool){ 
		debutChrono = bool;}
	
	public boolean getDebutChrono(){ 
		return debutChrono;
	}
	
	public boolean getBloquer(){
		return grid.bloque;
	}
}