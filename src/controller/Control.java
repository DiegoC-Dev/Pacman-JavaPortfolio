package controller;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.font.NumericShaper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel.MapMode;
import java.util.Iterator;

import javax.sound.midi.Patch;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JButton;

import exception.ValidateException;
import model.dao.PacmanManager;
import model.entity.Blinky;
import model.entity.Cherry;
import model.entity.Cookie;
import model.entity.Coord2D;
import model.entity.Dimensions;
import model.entity.Fruit;
import model.entity.Ghost;
import model.entity.Inky;
import model.entity.Mode;
import model.entity.MyRectangle2D;
import model.entity.Puckman;
import model.entity.Wall;
import view.BlinkyGui;
import view.ClydeGui;
import view.CookieGui;
import view.DialogAbout;
import view.GhostGui;
import view.InkyGui;
import view.IoManager;
import view.JFMainMenu;
import view.JFmainFrame;
import view.JWWindowProgress;
import view.MapGui;
import view.PacmanGui;
import view.PinkyGui;
import view.PuckmanGui;

public class Control implements KeyListener,ActionListener{
	
	boolean init;
	JFmainFrame jFmainFrame;
	PacmanManager pacmanManager;
	JFMainMenu jFMainMenu;
	private DialogAbout dialogAbout;
//	JWWindowProgress
	public Control() {
		
		pacmanManager = new PacmanManager();
		pacmanManager.createCookies();
		jFMainMenu = new JFMainMenu(this);
		new JWWindowProgress(this);
		dialogAbout = new DialogAbout(this);
//		jFmainFrame = new JFmainFrame(this);
//		
//		setCookies();
//		animate();
		attachSound();
	}

	public void init() throws NumberFormatException, InterruptedException {
//		this.game();
	}
	private static void playSound(String path) {
		final int BUFFER_SIZE = 128000;
//		String path = "src/Inicio.wav";
//		String path = "src/final.wav";
		File soundFile = null;
		AudioInputStream audioStream = null;
		AudioFormat audioFormat;
		SourceDataLine sourceLine = null;
		
		try {
			soundFile = new File(path);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		try {
			audioStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (Exception e){
			e.printStackTrace();
			System.exit(1);
		}
		
		audioFormat = audioStream.getFormat();
		
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		try {
			sourceLine = (SourceDataLine) AudioSystem.getLine(info);
			sourceLine.open(audioFormat);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		sourceLine.start();
		
		int nBytesRead = 0;
		byte[] abData = new byte[BUFFER_SIZE];
		while (nBytesRead != -1) {
			try {
				nBytesRead = audioStream.read(abData, 0, abData.length);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (nBytesRead >= 0) {
				@SuppressWarnings("unused")
				int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
			}
		}
		sourceLine.drain();
		sourceLine.close();
	}

	private void attachSound() {
//		System.out.println("inicio");
		Thread thread = new Thread(new Runnable() {
//			boolean init = true;

			@Override
			public void run() {
				while (pacmanManager.puckman.getLives()!=0) {
					System.out.println(" ");
					if(pacmanManager.isInitialSound()== true) {
//						playSound("src/Inicio.wav");
						playSound("resources/Inicio.wav");
						pacmanManager.setInitialSound(false);
					}
					if(pacmanManager.isCookieDeathSound()== true) {
//						playSound("src/CookieDeath.wav");
						playSound("resources/CookieDeath.wav");
						pacmanManager.setCookieDeathSound(false);
					}
					if(pacmanManager.isPacmanDeathSound()== true) {
//						playSound("src/Final.wav");
						playSound("resources/Final.wav");
						pacmanManager.setPacmanDeathSound(false);
					}				
					if(pacmanManager.isNextLevelSoundSound()== true) {
//						playSound("src/pacman_intermission.wav");
						playSound("resources/pacman_intermission.wav");
						pacmanManager.setNextLevelSoundSound(false);
					}

				}	
			}
		});
		thread.start();
	}
	private void animate(){
//		initialSound = true;
//		long deltaTime=1;
		pacmanManager.setGameTime(0);
		Thread thread = new Thread(new Runnable() {
			boolean init = true;
			@Override
			public void run() {
				while (init) {
					pacmanManager.setFruitTime(pacmanManager.getGameTime()>1000000?
							pacmanManager.getFruitTime()+1:pacmanManager.getFruitTime());
//					fruitTime=gameTime >1000000?fruitTime+1:fruitTime;
					pacmanManager.setGameTime(pacmanManager.puckman.getLives()!=0?
							pacmanManager.getGameTime()+1:-1);
//					gameTime=puckman.getLives()!=0?gameTime+1:-1;
//					System.out.println(gameTime/100000);
					if (pacmanManager.getNumberCookies()==20) {
						pacmanManager.cherry.setVisibility(true);
					}
					if (pacmanManager.getNumberCookies()==30) {
						pacmanManager.freezeCharacters();
						pacmanManager.map.setVictory(true);
						if(pacmanManager.map.isEndLevel()==false) {
							pacmanManager.setNextLevelSoundSound(true);
						}
//						gameTime = 0;
					}
					sendGameTime();
					if(pacmanManager.isInitialSound()==true?pacmanManager.getGameTime()>1400000:
						pacmanManager.getGameTime()>1000000) {
						pacmanManager.cherry.activateTimer();
						pacmanManager.blinky.move();
						pacmanManager.pinky.move();
						pacmanManager.inky.move();
						pacmanManager.clyde.move();					
						pacmanManager.puckman.move();//pacman
					}
					else {
						pacmanManager.blinky.movetimer();
						pacmanManager.pinky.movetimer();
						pacmanManager.inky.movetimer();
						pacmanManager.clyde.movetimer();
						pacmanManager.puckman.deathTimer();
//						puckman.movetimer();
					}
					pacmanManager.fruitDeath();
					pacmanManager.scoreFruit();
					pacmanManager.ghostDeath();
					pacmanManager.scoreGhost();
					pacmanManager.coockieDeath();
					pacmanManager.ghostRebirth();
					pacmanManager.pacmanDeath();
					pacmanManager.pacmanRebirth();
					jFmainFrame.repaint();
					pacmanManager.threadSleep();
				}
			}
		});
		thread.start();		
	}
	public void setCookies() {
		jFmainFrame.setCookies(pacmanManager.getSetCookies());
	}
	public void sendGameTime() {
		jFmainFrame.sendGameTime(pacmanManager.getGameTime());
	} 
	
//	public void sendWalls() {
//		JFmainFrame.sendWalls(setWalls);
//	}
//	private void createWalls() {
//		int numberWalls=0;
////		int[][] m = pc.getM();
//		int[][] m = map.getMatrix();
//		int squre=8; 
//		int x= squre;
//		int y = squre;
//		int cont=0;
//		for (int i = 0; i < m.length; i++) {
//			for (int j = 0; j < m[0].length; j++) {
//				if(m[i][j]==4) {
//					setWalls[numberWalls++]=new Wall(new Coord2D(x,y), squre, squre);
//				}
////				if(m[i][j]==0) {
////					cont++;
////				}
////				System.out.println("num "+cont);
//				x+=squre;
//			}
//			y+=squre;
//			x=squre;
//		}
////		System.out.println("control - leng "+numberWalls);
////		System.out.println("control - "+setWalls[0].getCoord2d().getY());
//	}

	@Override
	public void keyTyped(KeyEvent e) {
//		int key = e.getKeyCode();
//		System.out.println("Presiono algo " + key);
//		
//		
//		 
//		  if (key == 10) {
//		 
//			  System.out.println("Presionó Enter!" + (char) key);
//		  }  
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		init = true;
		pacmanManager.puckman.setMotionSaved(key);
//		puckman2.setMotionSaved(key);
//		ghost_1.setMotionSaved(key);
//		System.out.println("Presiono 2 algo " + key);
		for (int i = 0; i <3; i++) {			
			if (key == 38 && pacmanManager.puckman.movementAllowed(0,-1)) {
				pacmanManager.puckman.setDirection(Math.toRadians(270));//arriba
			}
			if (key == 37 && pacmanManager.puckman.movementAllowed(-1,0)) {
				pacmanManager.puckman.setDirection(Math.toRadians(180));//izquierda
			}
			if (key == 40 && pacmanManager.puckman.movementAllowed(0,1)) {
				pacmanManager.puckman.setDirection(Math.toRadians(90));//abajo
			}
			if (key == 39 && pacmanManager.puckman.movementAllowed(1,0)) {
				pacmanManager.puckman.setDirection(Math.toRadians(0));//derecha
			}
//			 87, 83, 65, 68
//			if (key == 87 && ghost_1.movementAllowed(setWalls,0,-1)) {
//				ghost_1.setDirection(Math.toRadians(270));//arriba
//			}
//			if (key == 65 && ghost_1.movementAllowed(setWalls,-1,0)) {
//				ghost_1.setDirection(Math.toRadians(180));//izquierda
//			}
//			if (key == 83 && ghost_1.movementAllowed(setWalls,0,1)) {
//				ghost_1.setDirection(Math.toRadians(90));//abajo
//			}
//			if (key == 68 && ghost_1.movementAllowed(setWalls,1,0)) {
//				ghost_1.setDirection(Math.toRadians(0));//derecha
//			}
			///////
//			if (key == 84 && puckman2.movementAllowed(setWalls,0,-1)) {
//				puckman2.setDirection(Math.toRadians(270));//arriba
//			}
//			if (key == 70 && puckman2.movementAllowed(setWalls,-1,0)) {
//				puckman2.setDirection(Math.toRadians(180));//izquierda
//			}
//			if (key == 71 && puckman2.movementAllowed(setWalls,0,1)) {
//				puckman2.setDirection(Math.toRadians(90));//abajo
//			}
//			if (key == 72 && puckman2.movementAllowed(setWalls,1,0)) {
//				puckman2.setDirection(Math.toRadians(0));//derecha
//			}
//			puckman2.setSpeed(vel);
//			ghost_1.setSpeed(ghost_1.getCurrentSpeed());
			pacmanManager.puckman.setSpeed(pacmanManager.puckman.getSavedSpeed());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
//		int key = e.getKeyCode();
//		System.out.println("Solto algo " + key);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch ( Commands.valueOf( e.getActionCommand() ) ) {
		case PLAY:
			dialogAbout.setVisible(false);
			jFMainMenu.setVisible(false);
			jFmainFrame = new JFmainFrame(this);			
			setCookies();
			animate();
			attachSound();

			break;
			case ABOUT:
				dialogAbout.setVisible(true);
			break;
			case QUIT:
				System.exit(0);
				break;
		default:
			break;
		}
	}
	
	public void setvisibleFrame() {
		jFMainMenu.setVisible(true);
	}

}
