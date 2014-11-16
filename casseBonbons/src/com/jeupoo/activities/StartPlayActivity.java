package com.jeupoo.activities;

import com.example.jeupoo.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Activité où on choisi de reprendre ou réinitialiser
 */
public class StartPlayActivity extends Activity {
	 protected void onCreate(Bundle savedInstanceState) {
		 
	    	requestWindowFeature(Window.FEATURE_NO_TITLE);
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.startplay);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        getWindow().setBackgroundDrawableResource(R.drawable.fond_main);
	        
	        final Button reprendreButton = (Button) findViewById(R.id.reprendre);
	        final Button nouvelleButton = (Button) findViewById(R.id.nouvelle);
	        final Button niveauxButton = (Button) findViewById(R.id.niveaux);

	        reprendreButton.setOnClickListener(new OnClickListener(){
	            
	        	public void onClick(View v){
	            	Intent intent = new Intent(StartPlayActivity.this, PlayActivity.class);
	            	intent.putExtra("reprise", "oui");
	            	startActivity(intent);
	            }
	        });
	        
	        nouvelleButton.setOnClickListener(new OnClickListener(){
	            
	        	public void onClick(View v){
	            	Intent intent = new Intent(StartPlayActivity.this, PlayActivity.class);
	            	intent.putExtra("reprise", "non");
	            	startActivity(intent);
	            }
	        });
	        
	        niveauxButton.setOnClickListener(new OnClickListener(){
	            
	        	public void onClick(View v){
	            	Intent intent = new Intent(StartPlayActivity.this, NiveauxActivity.class);
	            	startActivity(intent);
	            }
	        });
	    }
}