package com.jeupoo.activities;

import com.example.jeupoo.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * Activité des règles du jeu
 */
public class RulesActivity extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.rules);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setBackgroundDrawableResource(R.drawable.fond_regle);   
	}
}