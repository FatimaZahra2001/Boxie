package com.fatimazahra.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.fatimazahra.game.gamestate.GameState;
import com.fatimazahra.game.gamestate.GameStateManager;
import com.fatimazahra.game.gamestate.Level1State;

public class MenuState extends GameState{
	private String[] options = {"Start", "Help", "Quit"};
	private int currentSelection = 0;
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {}
	public void tick() {}
	
	public void draw(Graphics g) {
		g.setColor(new Color(51, 153, 255));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		for(int i = 0; i < options.length; i++) {
			if(i == currentSelection) {
				g.setColor(Color.MAGENTA);
			} else {
				g.setColor(Color.BLACK);
			}
			g.setFont(new Font("Ariel", Font.PLAIN, 50));
			g.drawString(options[i], GamePanel.WIDTH/2 - 70, 150 + i * 120);
		}
	}
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_DOWN) {
			currentSelection++;
			if(currentSelection >= options.length) {
				currentSelection = 0;
			}
		}
		else if(k == KeyEvent.VK_UP) {
			currentSelection--;
			if(currentSelection < 0) {
				currentSelection = options.length - 1;
			}
		}
		if(k == KeyEvent.VK_ENTER) {
			if(currentSelection == 0) {
				gsm.states.push(new Level1State(gsm));
			}
			else if(currentSelection == 1) {
				//help
			}
			else if(currentSelection == 2) {
				System.exit(0);

			}
		}
		
	}

	public void keyReleased(int k) {
		
	}

}
