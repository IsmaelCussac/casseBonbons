package com.jeupoo.activities;

import com.example.jeupoo.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Projet android année 2013-1014
 * Rendu le Mardi 25 Mars 2014
 * @author Sarah Boukris & Ismaël Cussac
 * Licence informatique 3e année à Luminy
 */
public class MainActivity extends Activity {
	
    protected void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setBackgroundDrawableResource(R.drawable.fond_main);  
        
        final Button playButton = (Button) findViewById(R.id.play);
        final Button rulesButton = (Button) findViewById(R.id.rules);
        
        playButton.setOnClickListener(new OnClickListener(){
            
        	public void onClick(View v){
            	Intent intent = new Intent(MainActivity.this, StartPlayActivity.class);
            	startActivity(intent);
            }
        });
        
        rulesButton.setOnClickListener(new OnClickListener(){
            
        	public void onClick(View v){
            	Intent intent = new Intent(MainActivity.this, RulesActivity.class);
            	startActivity(intent);
            }
        });    
    }

	public boolean onCreateOptionsMenu(Menu menu) {
 
    	getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}