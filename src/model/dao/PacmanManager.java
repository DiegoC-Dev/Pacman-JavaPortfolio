package model.dao;

import model.entity.Cherry;
import model.entity.Cookie;
import model.entity.Coord2D;
import model.entity.Food;
import model.entity.Mode;
import model.entity.Wall;
import view.BlinkyGui;
import view.ClydeGui;
import view.CookieGui;
import view.GhostGui;
import view.InkyGui;
import view.IoManager;
import view.JFmainFrame;
import view.MapGui;
import view.PacmanGui;
import view.PinkyGui;
import view.PuckmanGui;

public class PacmanManager {
	boolean init;
//	JFmainFrame jFmainFrame;
	public static MapGui map;
	
	private int direction;
	public static PuckmanGui puckman;
	public static BlinkyGui blinky;
	public static PinkyGui pinky;
	public static InkyGui inky;
	public static ClydeGui clyde;
	public static Cherry cherry;
	
	private Wall []setWalls;
	private CookieGui []setCookies;
	private int numberWalls;
	public static int score =0;
//	public static int vel=1500;or
	public static int vel=2000;
	public static int ghostScore;
	private boolean threadSleep;
	private int gameTime;
	private boolean pacmanDeath;
	public static int lives;
	private int fruitTime;
	private int numberCookies;
	private boolean initialSound;
	private boolean cookieDeathSound;
	private boolean pacmanDeathSound;
	private boolean nextLevelSoundSound;
	
	public PacmanManager() {
		initialSound = false;
		cookieDeathSound = false;
		pacmanDeathSound = false;
		nextLevelSoundSound = false;
		
		numberCookies=0;
		cherry = new Cherry();
		fruitTime = 0;
		pacmanDeath=false;
		lives = 3;
		gameTime = 0;
		threadSleep= false;
		ghostScore = 200;
		init = false;
		numberWalls=0;
		map = MapGui.getInstance();
		puckman = PuckmanGui.getInstance();
		blinky = BlinkyGui.getInstance();
		pinky = PinkyGui.getInstance();
		inky = InkyGui.getInstance();
		clyde = ClydeGui.getInstance();
		
//		System.out.println(inky==null?"nulo":"funciono");
		
		setWalls = MapGui.getWalls();
		
//		setCookies = new CookieGui[26*8*2+2*26-2+11+11 ];//or
		setCookies = new CookieGui[244];
		
		
		direction = 0;
		initialSound = true;


	}
	

	public void freezeCharacters() {
		puckman.setSpeed(0);
		blinky.setSpeed(0);
		inky.setSpeed(0);
		pinky.setSpeed(0);
		clyde.setSpeed(0);
	}
	public void fruitDeath(){
//		for (int i = 0; i < setCookies.length; i++) {
			if(puckman.getSecundaryVirtualFigure().intersection(cherry.getVirtualFigure()) 
					) {
				score = score+cherry.getScore();
				cherry.setVisibility(false);
				threadSleep = true;
				cherry.setLabelScore(true);
			}
//		}
	}
	public void pacmanRebirth() {
		if(gameTime==0 && pacmanDeath==true) {
			puckman.setCoord2d(new Coord2D(217, 416));
			puckman.setDirection(Math.toRadians(0));
			pacmanDeath=false;
		}
	}
	public void threadSleep() {
		if(threadSleep==true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		threadSleep = false;
	}
	public void ghostRebirth() {
		if(blinky.getSecundaryVirtualFigure().intersection(blinky.getPacmanInitialPosition()) 
				&& blinky.getMode().equals(Mode.RETURN)){
			blinky.setMode(Mode.CHASE);
		}
		if(pinky.getSecundaryVirtualFigure().intersection(pinky.getPacmanInitialPosition()) 
		&& pinky.getMode().equals(Mode.RETURN)){
			pinky.setMode(Mode.CHASE);
		}
		if(inky.getSecundaryVirtualFigure().intersection(inky.getPacmanInitialPosition()) 
		&& inky.getMode().equals(Mode.RETURN)){
			inky.setMode(Mode.CHASE);
		}
		if(clyde.getSecundaryVirtualFigure().intersection(clyde.getPacmanInitialPosition()) 
		&& clyde.getMode().equals(Mode.RETURN)){
			clyde.setMode(Mode.CHASE);
		}		
	}
	public void ghostDeath() {
		if(puckman.getSecundaryVirtualFigure().intersection(blinky.getSecundaryVirtualFigure()) 
		&& blinky.getMode().equals(Mode.ESCAPE) || 
		puckman.getSecundaryVirtualFigure().intersection(blinky.getSecundaryVirtualFigure()) 
		&& blinky.getMode().equals(Mode.ESCAPE_ENDING)) {
			blinky.setMode(Mode.RETURN);
			score = score+ghostScore;
			ghostScore = ghostScore*2;
		}
		if(puckman.getSecundaryVirtualFigure().intersection(pinky.getSecundaryVirtualFigure()) 
		&& pinky.getMode().equals(Mode.ESCAPE) 
		|| 
		puckman.getSecundaryVirtualFigure().intersection(pinky.getSecundaryVirtualFigure()) 
		&& pinky.getMode().equals(Mode.ESCAPE_ENDING)) {
			pinky.setMode(Mode.RETURN);
			score = score+ghostScore;
			ghostScore = ghostScore*2;
		}
		if(puckman.getSecundaryVirtualFigure().intersection(inky.getSecundaryVirtualFigure()) 
		&& inky.getMode().equals(Mode.ESCAPE) 
		|| 
		puckman.getSecundaryVirtualFigure().intersection(inky.getSecundaryVirtualFigure()) 
		&& inky.getMode().equals(Mode.ESCAPE_ENDING)) {
			inky.setMode(Mode.RETURN);
			score = score+ghostScore;
			ghostScore = ghostScore*2;
		}
		if(puckman.getSecundaryVirtualFigure().intersection(clyde.getSecundaryVirtualFigure()) 
		&& clyde.getMode().equals(Mode.ESCAPE) 
		|| 
		puckman.getSecundaryVirtualFigure().intersection(clyde.getSecundaryVirtualFigure()) 
		&& clyde.getMode().equals(Mode.ESCAPE_ENDING)) {
			clyde.setMode(Mode.RETURN);
			score = score+ghostScore;
			ghostScore = ghostScore*2;
		}		
//		System.out.println("score gh "+ghostScore);
			
	}
	public void scoreFruit() {
		cherry.scoreFruit();
	}
	public void scoreGhost() {
//		if(blinky.getMode().equals(Mode.CHASE) && pinky.getMode().equals(Mode.CHASE) &&
//				inky.getMode().equals(Mode.CHASE) && clyde.getMode().equals(Mode.CHASE)) {
//			ghostScore=0;
//		}
		
		blinky.setTimerLabelScore(blinky.getMode().equals(Mode.RETURN)?(blinky.getTimerLabelScore()+1):0);		
		if(blinky.getMode().equals(Mode.RETURN) && blinky.getTimerLabelScore()==1) {
			blinky.setLabelScore(true);
			threadSleep = true;
		}
		blinky.setLabelScore(blinky.getTimerLabelScore()>4000?false:blinky.isLabelScore());
		
		pinky.setTimerLabelScore(pinky.getMode().equals(Mode.RETURN)?(pinky.getTimerLabelScore()+1):0);		
		if(pinky.getMode().equals(Mode.RETURN) && pinky.getTimerLabelScore()==1) {
			pinky.setLabelScore(true);
			threadSleep = true;
		}
		pinky.setLabelScore(pinky.getTimerLabelScore()>4000?false:pinky.isLabelScore());

		inky.setTimerLabelScore(inky.getMode().equals(Mode.RETURN)?(inky.getTimerLabelScore()+1):0);		
		if(inky.getMode().equals(Mode.RETURN) && inky.getTimerLabelScore()==1) {
			inky.setLabelScore(true);
			threadSleep = true;
		}
		inky.setLabelScore(inky.getTimerLabelScore()>4000?false:inky.isLabelScore());

		clyde.setTimerLabelScore(clyde.getMode().equals(Mode.RETURN)?(clyde.getTimerLabelScore()+1):0);		
		if(clyde.getMode().equals(Mode.RETURN) && clyde.getTimerLabelScore()==1) {
			clyde.setLabelScore(true);
			threadSleep = true;
		}
		clyde.setLabelScore(clyde.getTimerLabelScore()>4000?false:clyde.isLabelScore());
	}
	public void pacmanDeath() {
		if(puckman.getSecundaryVirtualFigure().intersection(blinky.getSecundaryVirtualFigure()) 
				&& blinky.getMode().equals(Mode.CHASE)
				||
				puckman.getSecundaryVirtualFigure().intersection(blinky.getSecundaryVirtualFigure()) 
				&& blinky.getMode().equals(Mode.STALK) ||
				puckman.getSecundaryVirtualFigure().intersection(pinky.getSecundaryVirtualFigure()) 
				&& pinky.getMode().equals(Mode.CHASE)
				||
				puckman.getSecundaryVirtualFigure().intersection(pinky.getSecundaryVirtualFigure()) 
				&& pinky.getMode().equals(Mode.STALK) ||
				puckman.getSecundaryVirtualFigure().intersection(inky.getSecundaryVirtualFigure()) 
				&& inky.getMode().equals(Mode.CHASE)
				||
				puckman.getSecundaryVirtualFigure().intersection(inky.getSecundaryVirtualFigure()) 
				&& inky.getMode().equals(Mode.STALK) ||
				puckman.getSecundaryVirtualFigure().intersection(clyde.getSecundaryVirtualFigure()) 
				&& clyde.getMode().equals(Mode.CHASE)
				||
				puckman.getSecundaryVirtualFigure().intersection(clyde.getSecundaryVirtualFigure()) 
				&& clyde.getMode().equals(Mode.STALK)
				){
			threadSleep = true;
			threadSleep();
			puckman.setDeathTimer(0);
			puckman.setCycle(0);
			gameTime=-1000000;
			blinky.setCoord2d(new Coord2D(217, 225));
			blinky.updateVirtualFigures();
			blinky.setDirection(Math.toRadians(270));
			pinky.setCoord2d(new Coord2D(217, 225));
			pinky.updateVirtualFigures();
			pinky.setDirection(Math.toRadians(270));
			inky.setCoord2d(new Coord2D(217, 225));
			inky.updateVirtualFigures();
			inky.setDirection(Math.toRadians(270));
			clyde.setCoord2d(new Coord2D(217, 225));
			clyde.updateVirtualFigures();
			clyde.setDirection(Math.toRadians(270));
			pacmanDeath=true;
			puckman.setLives(puckman.getLives()-1);
			pacmanDeathSound = true;
		} 	
	}
	public void coockieDeath(){
		GhostGui gostGui; 		
		for (int i = 0; i < setCookies.length; i++) {
			if(puckman.getSecundaryVirtualFigure().intersection(setCookies[i].getVirtualFigure()) 
//					|| setCookies[i].getVirtualFigure().intersection(puckman.getSecundaryVirtualFigure()) 
					) {
				if(setCookies[i].isSuperCookie()) {
					blinky.setTimer(0);
					pinky.setTimer(0);
					inky.setTimer(0);
					clyde.setTimer(0);
					blinky.setMode(!blinky.getMode().equals(Mode.RETURN)?Mode.ESCAPE:Mode.RETURN); 
					pinky.setMode(!pinky.getMode().equals(Mode.RETURN)?Mode.ESCAPE:Mode.RETURN); 
					inky.setMode(!inky.getMode().equals(Mode.RETURN)?Mode.ESCAPE:Mode.RETURN); 
					clyde.setMode(!clyde.getMode().equals(Mode.RETURN)?Mode.ESCAPE:Mode.RETURN); 
					
//					}
				}
				score = setCookies[i].isSuperCookie()?score+50:score+10;
				setCookies[i]=new CookieGui(new Coord2D(-10, -10), true);
				numberCookies++;
				cookieDeathSound = true;
//				playSound("src/CookieDeath.wav");
			}
		}
	}
	
	public void createCookies() {
		for (int i = 0; i < setCookies.length; i++) {
			setCookies[i]= new CookieGui(new Coord2D(-10, -10), true);
		}
		int x = 8*4;
		int y = 8*10;
		Cookie temporaryCookie;
		int cookiesNumber =0;
		int cont=0;
		boolean avaliable= true;
		for (int k = 0; k < 2; k++) {//or
//		for (int k = 1; k < 2; k++) {
			for (int i = 0; i < (k==0?8:10); i++) {//or
//			for (int i = 0; i < (k==0?8:2); i++) {
				for (int j = 0; j < 26; j++) {//or
//				for (int j = 0; j < 2; j++) {
					if(k==1 && i==3 && j==12 || k==1 && i==3 && j==13) {
					}
					else {
						temporaryCookie= new Cookie(new Coord2D(x, y), true);
						for (int l = 0; l < setWalls.length; l++) {
							if(setWalls[l].getVirtualFigure().intersection(temporaryCookie.getVirtualFigure())) {
								avaliable=false;
							}
						}
						if(avaliable == true) {
							setCookies[cookiesNumber++]=new CookieGui(new Coord2D(x, y), cookiesNumber==31 || 
									cookiesNumber==36 || cookiesNumber==137 ||cookiesNumber==156 ?true:false);//con 4 galletas grandes
//							setCookies[cookiesNumber++]=new CookieGui(new Coord2D(x, y),true);//galletas grandes

						}
						else
						avaliable = true;
					}	
					x+=16;
				}
				x=8*4;
				y+=16;
			}			
			y=8*48;
		}
		y=8*26;
		x=8*14;
//		System.out.println(" salio");
//		System.out.println(cookiesNumber);
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 11; j++) {
				setCookies[cookiesNumber++]=new CookieGui(new Coord2D(x, y), false);
				y+=16;
			}
			y=8*26;
			x=8*44;
		}
//		setCookies();
	}


	public boolean isInit() {
		return init;
	}


	public void setInit(boolean init) {
		this.init = init;
	}


	public static MapGui getMap() {
		return map;
	}


	public static void setMap(MapGui map) {
		PacmanManager.map = map;
	}

	public int getDirection() {
		return direction;
	}


	public void setDirection(int direction) {
		this.direction = direction;
	}


	public static PuckmanGui getPuckman() {
		return puckman;
	}


	public static void setPuckman(PuckmanGui puckman) {
		PacmanManager.puckman = puckman;
	}


	public static BlinkyGui getBlinky() {
		return blinky;
	}


	public static void setBlinky(BlinkyGui blinky) {
		PacmanManager.blinky = blinky;
	}


	public static PinkyGui getPinky() {
		return pinky;
	}


	public static void setPinky(PinkyGui pinky) {
		PacmanManager.pinky = pinky;
	}


	public static InkyGui getInky() {
		return inky;
	}


	public static void setInky(InkyGui inky) {
		PacmanManager.inky = inky;
	}


	public static ClydeGui getClyde() {
		return clyde;
	}


	public static void setClyde(ClydeGui clyde) {
		PacmanManager.clyde = clyde;
	}


	public static Cherry getCherry() {
		return cherry;
	}


	public static void setCherry(Cherry cherry) {
		PacmanManager.cherry = cherry;
	}


	public Wall[] getSetWalls() {
		return setWalls;
	}


	public void setSetWalls(Wall[] setWalls) {
		this.setWalls = setWalls;
	}


	public CookieGui[] getSetCookies() {
		return setCookies;
	}


	public void setSetCookies(CookieGui[] setCookies) {
		this.setCookies = setCookies;
	}


	public int getNumberWalls() {
		return numberWalls;
	}


	public void setNumberWalls(int numberWalls) {
		this.numberWalls = numberWalls;
	}


	public static int getScore() {
		return score;
	}


	public static void setScore(int score) {
		PacmanManager.score = score;
	}


	public static int getVel() {
		return vel;
	}


	public static void setVel(int vel) {
		PacmanManager.vel = vel;
	}


	public static int getGhostScore() {
		return ghostScore;
	}


	public static void setGhostScore(int ghostScore) {
		PacmanManager.ghostScore = ghostScore;
	}


	public boolean isThreadSleep() {
		return threadSleep;
	}


	public void setThreadSleep(boolean threadSleep) {
		this.threadSleep = threadSleep;
	}


	public int getGameTime() {
		return gameTime;
	}


	public void setGameTime(int gameTime) {
		this.gameTime = gameTime;
	}


	public boolean isPacmanDeath() {
		return pacmanDeath;
	}


	public void setPacmanDeath(boolean pacmanDeath) {
		this.pacmanDeath = pacmanDeath;
	}


	public static int getLives() {
		return lives;
	}


	public static void setLives(int lives) {
		PacmanManager.lives = lives;
	}


	public int getFruitTime() {
		return fruitTime;
	}


	public void setFruitTime(int fruitTime) {
		this.fruitTime = fruitTime;
	}


	public int getNumberCookies() {
		return numberCookies;
	}


	public void setNumberCookies(int numberCookies) {
		this.numberCookies = numberCookies;
	}


	public boolean isInitialSound() {
		return initialSound;
	}


	public void setInitialSound(boolean initialSound) {
		this.initialSound = initialSound;
	}


	public boolean isCookieDeathSound() {
		return cookieDeathSound;
	}


	public void setCookieDeathSound(boolean cookieDeathSound) {
		this.cookieDeathSound = cookieDeathSound;
	}


	public boolean isPacmanDeathSound() {
		return pacmanDeathSound;
	}


	public void setPacmanDeathSound(boolean pacmanDeathSound) {
		this.pacmanDeathSound = pacmanDeathSound;
	}


	public boolean isNextLevelSoundSound() {
		return nextLevelSoundSound;
	}


	public void setNextLevelSoundSound(boolean nextLevelSoundSound) {
		this.nextLevelSoundSound = nextLevelSoundSound;
	}
	
	
}
