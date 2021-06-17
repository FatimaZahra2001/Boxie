package com.fatimazahra.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.fatimazahra.game.gamestate.GameState;

public class Block extends Rectangle{
	private static final long serialVersionUID = 1L;
	public final static int blockSize = 70;
	
	public Block(int x, int y) {
		setBounds(x, y, blockSize, blockSize);
	}
	public void tick() {
		x = x - (int)GameState.xOffset;
		y = y - (int)GameState.yOffset;
		
	}
	public void draw(Graphics g) {
		g.fillRect(x, y, width, height);
	}
	

}
