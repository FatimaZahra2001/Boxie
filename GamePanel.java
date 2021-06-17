package com.fatimazahra.game.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import com.fatimazahra.game.gamestate.GameStateManager;

public class GamePanel extends JPanel implements Runnable, KeyListener{
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 600;
	
	private Thread thread;
	private boolean isRunning = false;
	
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	
	private GameStateManager gsm;
	
	public GamePanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		addKeyListener(this);
		setFocusable(true);
		
		start();
	}
	public void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	public void run() {
		long start; long elapsed; long wait;
		gsm = new GameStateManager();
		while(isRunning) {
			start = System.nanoTime();
			tick();
			repaint();
			elapsed = System.nanoTime() - start;
			wait = targetTime - elapsed / 1000000;
			if(wait <= 0) {
				wait = 5;
			}
			try {
				Thread.sleep(wait);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void tick() {
		gsm.tick();
		//System.out.println("Running...");
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//graphics.draw3DRect(5, 5, 10, 5, true);
		gsm.draw(g);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		gsm.keyPressed(e.getKeyCode());
	}
	@Override
	public void keyReleased(KeyEvent e) {
		gsm.keyReleased(e.getKeyCode());
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
