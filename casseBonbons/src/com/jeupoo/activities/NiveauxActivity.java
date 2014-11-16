package com.jeupoo.activities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.example.jeupoo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class NiveauxActivity extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) {
		 
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.niveaux);
		 getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		 getWindow().setBackgroundDrawableResource(R.drawable.fond_main); 
		 
		 final int level = recupererNomFichier();
		 TextView lvl1 = (TextView) findViewById(R.id.tlvl1);  
		 lvl1.setText("Unlock");
		 
		 TextView lvl2 = (TextView) findViewById(R.id.tlvl2);
		 lvl2.setText(level>=2?"Unlock":"Lock");
		 TextView lvl3 = (TextView) findViewById(R.id.tlvl3);
		 lvl3.setText(level>=3?"Unlock":"Lock");
		 TextView lvl4 = (TextView) findViewById(R.id.tlvl4);
		 lvl4.setText(level>=4?"Unlock":"Lock");
		 TextView lvl5 = (TextView) findViewById(R.id.tlvl5);
		 lvl5.setText(level>=5?"Unlock":"Lock");
		 TextView lvl6 = (TextView) findViewById(R.id.tlvl6);
		 lvl6.setText(level>=6?"Unlock":"Lock");
		 TextView lvl7 = (TextView) findViewById(R.id.tlvl7);
		 lvl7.setText(level>=7?"Unlock":"Lock");
		 TextView lvl8 = (TextView) findViewById(R.id.tlvl8);
		 lvl8.setText(level>=8?"Unlock":"Lock");
		 TextView lvl9 = (TextView) findViewById(R.id.tlvl9);
		 lvl9.setText(level>=9?"Unlock":"Lock");
		 
		 final Button lvl1Button = (Button) findViewById(R.id.lvl1);
		 final Button lvl2Button = (Button) findViewById(R.id.lvl2);
	     final Button lvl3Button = (Button) findViewById(R.id.lvl3);
	     final Button lvl4Button = (Button) findViewById(R.id.lvl4);
		 final Button lvl5Button = (Button) findViewById(R.id.lvl5);
	     final Button lvl6Button = (Button) findViewById(R.id.lvl6);
	     final Button lvl7Button = (Button) findViewById(R.id.lvl7);
		 final Button lvl8Button = (Button) findViewById(R.id.lvl8);
	     final Button lvl9Button = (Button) findViewById(R.id.lvl9);

		 lvl1Button.setOnClickListener(new OnClickListener(){
		        
	    	public void onClick(View v){
	    		if(level >= 1){
		        	Intent intent = new Intent(NiveauxActivity.this, PlayActivity.class);
		        	intent.putExtra("reprise", "lvl1");
		        	startActivity(intent);
	    		}
		    }
		});
		
		lvl2Button.setOnClickListener(new OnClickListener(){
		    
			public void onClick(View v){
				if(level >= 2){
			    	Intent intent = new Intent(NiveauxActivity.this, PlayActivity.class);
			    	intent.putExtra("reprise", "lvl2");
			    	startActivity(intent);
				}
		    }
		});
		
		lvl3Button.setOnClickListener(new OnClickListener(){
		    
			public void onClick(View v){
				if(level >= 3){
			    	Intent intent = new Intent(NiveauxActivity.this, PlayActivity.class);
			    	intent.putExtra("reprise", "lvl3");
			    	startActivity(intent);
				}
		    }
		});
		
		 lvl4Button.setOnClickListener(new OnClickListener(){
		        
	    	public void onClick(View v){
	    		if(level >= 4){
		        	Intent intent = new Intent(NiveauxActivity.this, PlayActivity.class);
		        	intent.putExtra("reprise", "lvl4");
		        	startActivity(intent);
	    		}
		    }
		});
		
		lvl5Button.setOnClickListener(new OnClickListener(){
		    
			public void onClick(View v){
				if(level >= 5){
			    	Intent intent = new Intent(NiveauxActivity.this, PlayActivity.class);
			    	intent.putExtra("reprise", "lvl5");
			    	startActivity(intent);
				}
		    }
		});
		
		lvl6Button.setOnClickListener(new OnClickListener(){
		    
			public void onClick(View v){
				if(level >= 6){
					Intent intent = new Intent(NiveauxActivity.this, PlayActivity.class);
			    	intent.putExtra("reprise", "lvl6");
			    	startActivity(intent);
				}
		    }
		});
		
		 lvl7Button.setOnClickListener(new OnClickListener(){
		        
	    	public void onClick(View v){
	    		if(level >= 7){
		        	Intent intent = new Intent(NiveauxActivity.this, PlayActivity.class);
		        	intent.putExtra("reprise", "lvl7");
		        	startActivity(intent);
	    		}
		    }
		});
		
		lvl8Button.setOnClickListener(new OnClickListener(){
		    
			public void onClick(View v){
				if(level >= 8){
			    	Intent intent = new Intent(NiveauxActivity.this, PlayActivity.class);
			    	intent.putExtra("reprise", "lvl8");
			    	startActivity(intent);
				}
		    }
		});
		
		lvl9Button.setOnClickListener(new OnClickListener(){
		    
			public void onClick(View v){
				if(level >= 9){
			    	Intent intent = new Intent(NiveauxActivity.this, PlayActivity.class);
			    	intent.putExtra("reprise", "lvl9");
			    	startActivity(intent);
				}
		    }
		});
	  }
	
	public int recupererNomFichier() {
		int line = 1 ;
		String fileName = "/lock.txt";
		File file = new File(getFilesDir().getAbsolutePath() + fileName);
		if(!file.exists()){	return line;	}
	
		try {		
			InputStreamReader input = new InputStreamReader(new FileInputStream(getFilesDir().getAbsolutePath() + fileName),"UTF-8");
			line = input.read();
			input.close(); 
		}
		catch (IOException e) {	Log.e("TEST","ERROR DE RECUPERATION DE FICHIER");}
		
		return line;
	}
}