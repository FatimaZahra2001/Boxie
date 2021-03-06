package com.fatimazahra.game.gamestate;

import java.awt.Graphics;

public abstract class GameState {
	
	public GameStateManager gsm;
	public static double xOffset,
					 	 yOffset;

	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
		
		xOffset = 0;
		yOffset = 0;
		init();
	}

	public abstract void keyPressed(int k);

	public abstract void tick();

	public abstract void draw(Graphics g);

	public abstract void keyReleased(int k);
	
	public abstract void init();

}
