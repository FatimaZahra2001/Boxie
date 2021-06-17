package com.fatimazahra.game.gamestate;

import java.awt.Graphics;
import com.fatimazahra.game.entities.Player;
import com.fatimazahra.game.objects.Block;

public class Level1State extends GameState{
	
	private Player player;
	private Block[] b;
	
	public Level1State(GameStateManager gsm) {
		super(gsm);
	}
	public void init() {
		player = new Player(30,30);
		
		b = new Block[3];
		
		b[0] = new Block(500, 400);
		b[1] = new Block(450, 450);
		b[2] = new Block(400, 500);
	}

	public void keyPressed(int k) {
		player.keyPressed(k);
	}
	
	public void tick() {
		
		for(int i = 0; i < b.length; i++) {
			 b[i].tick();
		}
		player.tick(b);
		
	}

	public void draw(Graphics g) {
		player.draw(g);
		for(int i = 0; i < b.length; i++) {
			 b[i].draw(g);
		}
		
	}
	
	public void keyReleased(int k) {
		player.keyReleased(k);
		
	}
	
	

}
