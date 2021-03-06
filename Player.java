package com.fatimazahra.game.entities;

import java.awt.*;
import java.awt.event.KeyEvent;

import com.fatimazahra.game.gamestate.GameState;
import com.fatimazahra.game.main.GamePanel;
import com.fatimazahra.game.objects.Block;
import com.fatimazahra.game.physics.Collision;

public class Player{
	
	//movement booleans
	private boolean right = false,
					left = false,
					jumping = false,
					falling = false,
					topCollision = false;
	//bounds 
	private double x,y;
	private int width, height;
	
	//jump speed
	private double jumpSpeed = 5,
				   currentJumpSpeed = jumpSpeed;
	//fall speed
	private double maxFallSpeed = 5,
			       currentFallSpeed = 0.1;
	//move speed
	private double moveSpeed = 2.5;
					
	public Player(int width, int height) {
		x = GamePanel.WIDTH / 2;
		y = GamePanel.HEIGHT / 2;
		this.width = width;
		this.height = height;
	}
	
	public void tick(Block[] b) {
		int iX = (int)x;
		int iY = (int)y;
		//right
		for(int i = 0; i < b.length; i++) {
			//right
			if(Collision.playerBlock(new Point(iX + width + (int)GameState.xOffset, iY + (int)GameState.yOffset + 2), b[i]) ||
					Collision.playerBlock(new Point(iX + width + (int)GameState.xOffset, iY + height + (int)GameState.yOffset - 1), b[i])) {
					right = false;
				}
			//left
			if(Collision.playerBlock(new Point(iX + (int)GameState.xOffset - 1, iY + (int)GameState.yOffset + 2), b[i]) ||
					Collision.playerBlock(new Point(iX + (int)GameState.xOffset - 1, iY + height + (int)GameState.yOffset - 1), b[i])) {
					left = false;
				}
			//top
			if(Collision.playerBlock(new Point(iX + (int)GameState.xOffset + 1, iY + (int)GameState.yOffset), b[i]) ||
					Collision.playerBlock(new Point(iX + width + (int)GameState.xOffset - 1, iY + (int)GameState.yOffset), b[i])) {
					jumping = false;
				}
			//bottom
			if(Collision.playerBlock(new Point(iX + (int)GameState.xOffset + 2, iY + height + (int)GameState.yOffset + 1), b[i]) ||
					Collision.playerBlock(new Point(iX + width + (int)GameState.xOffset - 1, iY + height + (int)GameState.yOffset + 1), b[i])) {
				y = b[i].getY() - height - GameState.yOffset;	
				falling = false;
				topCollision = true;
				}
			else {
				if(!topCollision && !jumping) {
					falling = true;
				}
			}
		}
		if(right) {
			GameState.xOffset += moveSpeed;
		}
		if(left) {
			GameState.yOffset-= moveSpeed;
		}
		if(jumping) {
			GameState.yOffset -= currentJumpSpeed;
			currentJumpSpeed -= 0.1;
			if(currentJumpSpeed <= 0) {
				currentJumpSpeed = jumpSpeed;
				jumping = false;
				falling = true;
			}
		}
		if(falling) {
			GameState.yOffset += currentFallSpeed;
			if(currentFallSpeed < maxFallSpeed) {
				currentFallSpeed += 0.1;
			}
		}
		if(!falling) {
			currentFallSpeed = 0.1;
		}
	}
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect((int)x, (int)y, width, height);
	}
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_D)
			right = true;
		if(k == KeyEvent.VK_A)
			left = true;
		if(k == KeyEvent.VK_SPACE && !jumping && !falling) {
			jumping = true;
		}
	}
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_D)
			right = false;
		if(k == KeyEvent.VK_A)
			left = false;
	}

}
