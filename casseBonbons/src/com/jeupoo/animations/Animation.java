package com.jeupoo.animations;

import com.jeupoo.main.GridPrinc;
import com.jeupoo.main.MyView;

import android.graphics.Canvas;

public interface Animation{
	
	public void animer(GridPrinc grid, Canvas canvas, MyView view, int dt);
}