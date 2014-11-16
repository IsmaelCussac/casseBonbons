package com.example.jeu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
  
        setContentView(R.layout.activity_main);
        
        final Button rulesButton = (Button) findViewById(R.id.rules);
        final Button playButton = (Button) findViewById(R.id.play);
        final Button optionsButton = (Button) findViewById(R.id.options);
        
        rulesButton.setOnClickListener(new OnClickListener(){
        
        	public void onClick(View v){
            	Intent intent = new Intent(MainActivity.this, RulesActivity.class);
            	startActivity(intent);
            }
        });
        
        playButton.setOnClickListener(new OnClickListener(){
            
        	public void onClick(View v){
            	Intent intent = new Intent(MainActivity.this, PlayActivity.class);
            	startActivity(intent);
            }
        });
        
        optionsButton.setOnClickListener(new OnClickListener(){
            
        	public void onClick(View v){
            	Intent intent = new Intent(MainActivity.this, OptionsActivity.class);
            	startActivity(intent);
            }
        });
    }
   
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}