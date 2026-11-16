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
//		attachSound();
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
			if (!soundFile.exists()) {
				soundFile = new File("/app/" + path);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		try {
			audioStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (Exception e){
			e.printStackTrace();
			return;
		}
		
		audioFormat = audioStream.getFormat();
		
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		try {
			sourceLine = (SourceDataLine) AudioSystem.getLine(info);
			sourceLine.open(audioFormat);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
			return;
		} catch (Exception e) {
			e.printStackTrace();
			return;
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

	private boolean soundThreadStarted = false;
	private volatile boolean powerPelletPlaying = false;
	private volatile boolean cookieSoundPlaying = false;

	private static void playSoundAsync(final String path) {
		Thread sfxThread = new Thread(new Runnable() {
			@Override
			public void run() {
				playSound(path);
			}
		});
		sfxThread.setDaemon(true);
		sfxThread.start();
	}

	private void playCookieSoundAsync() {
		if (cookieSoundPlaying) {
			return;
		}
		cookieSoundPlaying = true;
		Thread cookieThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					playSound("resources/CookieDeath.wav");
				} finally {
					cookieSoundPlaying = false;
				}
			}
		});
		cookieThread.setDaemon(true);
		cookieThread.start();
	}

	private void startPowerPelletSound() {
		if (powerPelletPlaying) {
			return;
		}
		powerPelletPlaying = true;
		Thread sirenThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (pacmanManager.areGhostsVulnerable()
							&& pacmanManager.getGameTime() > 1000000
							&& pacmanManager.getNumberCookies() < 30 && !pacmanManager.map.isEndLevel()
							&& pacmanManager.puckman.getLives() != 0) {
						File soundFile = new File("resources/pacman_power_pellet.wav");
						if (!soundFile.exists()) {
							soundFile = new File("/app/resources/pacman_power_pellet.wav");
						}
						AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
						AudioFormat audioFormat = audioStream.getFormat();
						DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
						SourceDataLine sourceLine = (SourceDataLine) AudioSystem.getLine(info);
						sourceLine.open(audioFormat);
						sourceLine.start();
						byte[] abData = new byte[4096];
						int nBytesRead = 0;
						while (nBytesRead != -1
								&& pacmanManager.areGhostsVulnerable()
								&& pacmanManager.getGameTime() > 1000000
								&& pacmanManager.getNumberCookies() < 30 && !pacmanManager.map.isEndLevel()
								&& pacmanManager.puckman.getLives() != 0) {
							nBytesRead = audioStream.read(abData, 0, abData.length);
							if (nBytesRead >= 0) {
								sourceLine.write(abData, 0, nBytesRead);
							}
						}
						sourceLine.stop();
						sourceLine.close();
						audioStream.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					powerPelletPlaying = false;
				}
			}
		});
		sirenThread.setDaemon(true);
		sirenThread.start();
	}

	private void attachSound() {
//		System.out.println("inicio");
		if (soundThreadStarted) {
			return;
		}
		soundThreadStarted = true;
		Thread thread = new Thread(new Runnable() {
//			boolean init = true;

			@Override
			public void run() {
				while (pacmanManager.puckman.getLives()!=0) {
					if(pacmanManager.isInitialSound()== true) {
//						playSound("src/Inicio.wav");
						playSound("resources/Inicio.wav");
						pacmanManager.setInitialSound(false);
					}
					if(pacmanManager.isSuperCookieSound()== true) {
						pacmanManager.setSuperCookieSound(false);
						startPowerPelletSound();
					}
					if(pacmanManager.isGhostDeathSound()== true) {
						pacmanManager.setGhostDeathSound(false);
						playSoundAsync("resources/pacman_eatghost.wav");
					}
					if(pacmanManager.isFruitDeathSound()== true) {
						pacmanManager.setFruitDeathSound(false);
						playSoundAsync("resources/pacman_eatfruit.wav");
					}
					if(pacmanManager.isPacmanDeathSound()== true) {
//						playSound("src/Final.wav");
						pacmanManager.setPacmanDeathSound(false);
						playSoundAsync("resources/Final.wav");
					}				
					if(pacmanManager.isNextLevelSoundSound()== true) {
//						playSound("src/pacman_intermission.wav");
						pacmanManager.setNextLevelSoundSound(false);
						playSoundAsync("resources/pacman_intermission.wav");
					}
					if(pacmanManager.isCookieDeathSound()== true) {
//						playSound("src/CookieDeath.wav");
						pacmanManager.setCookieDeathSound(false);
						playCookieSoundAsync();
					}
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						break;
					}
				}	
			}
		});
		thread.setDaemon(true);
		thread.start();
	}
	private void animate(){
//		initialSound = true;
//		long deltaTime=1;
		pacmanManager.setGameTime(0);
		Thread thread = new Thread(new Runnable() {
			boolean init = true;
			boolean cherrySpawned = false;
			boolean victoryTriggered = false;
			private static final int TICKS_PER_SECOND = 2800;
			private static final long NANOS_PER_TICK = 1_000_000_000L / TICKS_PER_SECOND;
			private static final long NANOS_PER_FRAME = 1_000_000_000L / 60;
			private static final int WAIT_STEP = 120;

			@Override
			public void run() {
				long lastTime = System.nanoTime();
				long lastRenderTime = lastTime;
				long accumulator = 0;

				while (init) {
					long now = System.nanoTime();
					long elapsed = now - lastTime;
					lastTime = now;
					if (elapsed > 100_000_000L) {
						elapsed = 100_000_000L;
					}
					accumulator += elapsed;

					while (accumulator >= NANOS_PER_TICK) {
						accumulator -= NANOS_PER_TICK;

						int currentGameTime = pacmanManager.getGameTime();
						boolean isPlayingPhase = !pacmanManager.isInitialSound() && currentGameTime > 1000000;

						pacmanManager.setFruitTime(isPlayingPhase?
								pacmanManager.getFruitTime()+1:pacmanManager.getFruitTime());
//					fruitTime=gameTime >1000000?fruitTime+1:fruitTime;
						if (pacmanManager.puckman.getLives()!=0) {
							if (isPlayingPhase) {
								pacmanManager.setGameTime(currentGameTime+1);
							} else {
								int step = pacmanManager.isInitialSound() ? 82 : WAIT_STEP;
								int nextGameTime = currentGameTime + step;
								if (currentGameTime < 0 && nextGameTime >= 0) {
									nextGameTime = 0;
								}
								if (pacmanManager.isInitialSound() && nextGameTime > 1000000) {
									nextGameTime = 1000000;
								}
								pacmanManager.setGameTime(nextGameTime);
							}
						} else {
							pacmanManager.setGameTime(-1);
						}
//					gameTime=puckman.getLives()!=0?gameTime+1:-1;
//					System.out.println(gameTime/100000);
						if (pacmanManager.getNumberCookies()==20 && !cherrySpawned) {
							cherrySpawned = true;
							pacmanManager.cherry.setVisibility(true);
						}
						if (pacmanManager.getNumberCookies()==30) {
							pacmanManager.freezeCharacters();
							pacmanManager.map.setVictory(true);
							if(!victoryTriggered) {
								victoryTriggered = true;
								pacmanManager.setCookieDeathSound(false);
								pacmanManager.setNextLevelSoundSound(true);
							}
//						gameTime = 0;
						}
						sendGameTime();
						if(isPlayingPhase) {
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
							for (int i = 0; i < WAIT_STEP; i++) {
								pacmanManager.puckman.deathTimer();
							}
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

						if (pacmanManager.isThreadSleep()) {
							jFmainFrame.repaint();
							pacmanManager.threadSleep();
							lastTime = System.nanoTime();
							accumulator = 0;
							break;
						}
					}

					if (now - lastRenderTime >= NANOS_PER_FRAME) {
						jFmainFrame.repaint();
						lastRenderTime = now;
					}

					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						break;
					}
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
			pacmanManager.setInitialSound(true);
			jFmainFrame = new JFmainFrame(this);			
			setCookies();
			attachSound();
			animate();

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
