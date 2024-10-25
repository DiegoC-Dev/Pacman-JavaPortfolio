package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import controller.Control;
import model.dao.PacmanManager;
import model.entity.Map;
import model.entity.Wall;

public class MapGui extends Map{
	private static MapGui instance;
	private static Wall [] setWalls;
	Graphics graphics;
	int x;
	int y;
	private MapGui() {
		timer=0;
		endLevel=false;
		setWalls =getSetWalls();
		victory= false;
	}
	private void activateTimer(){
		cycle= timer >= 50?cycle+1:cycle;
		timer= timer >= 50?0:timer;
	}
	public void drawMap(Graphics graphics) {
		this.graphics=graphics;
		y = SQUARE;
		x = SQUARE;
		
		activateTimer();
		timer=victory==true && endLevel==false?timer+1:0;
//		System.out.println("timer"+timer);
//		System.out.println(victory);
//		System.out.println("cyclos   -----"+cycle);
		for (int i = 0; i < matrix.length; i++) {
//			for (int j = 0; j < m[0].length; j++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if(matrix[i][j]==0) {
					graphics.setColor(Color.black);
//					drawVoid(x, y);
				}
				if(matrix[i][j]==1) {
//					graphics.setColor(Color.black);
//					drawPacman(x, y);
				}
				if(matrix[i][j]==2) {
//					drawGost(x, y);
				}
				if(matrix[i][j]==3) {
//					drawcookie(x, y);
				}
				if(matrix[i][j]==4) {
//					graphics.setColor(Color.blue);
//					if(victory==true && cycle%2!=0) {
//						graphics.setColor(Color.white);
//					}
					graphics.setColor(cycle%2==0?Color.blue:Color.white);
//					drawWall(x, y);
				}
				graphics.fillRect(x, y, SQUARE, SQUARE);	
				x=x+SQUARE;
			}
			x=SQUARE;
			y=y+SQUARE;
		}
//		graphics.setColor(Color.GREEN);
//		graphics.fillRect(0, 0, 40, 40);	
//		graphics.fillRect(50, 50, 20, 20);
		setFont();
		drawScore(graphics);
		drawLives(graphics);
		
		if(cycle==18){
			endLevel=true;
//			drawNextLevel(graphics);
		}

	}
	private void setFont() {
		graphics.setFont(new Font("SansSerif", Font.PLAIN, 20));
	}
	public void drawGetReady(Graphics graphics) {
		this.graphics = graphics;
		setFont();
		graphics.setColor(Color.green);
		graphics.drawString("GET READY!",8*21, 8*43);
	}
	public void drawGameOver(Graphics graphics) {
		this.graphics = graphics;
		setFont();
		graphics.setColor(Color.red);
		graphics.drawString("GAME OVER",8*21, 8*43);
	}
	public void drawNextLevel(Graphics graphics) {
		this.graphics = graphics;
		setFont();
		graphics.setColor(Color.YELLOW);
		graphics.drawString("NEXT LEVEL",8*21, 8*43);
	}
	private void drawLives(Graphics graphics) {
		graphics.setColor(Color.green);
		graphics.drawString("Lives",8*28, 8*72-4);
		if(PacmanManager.puckman.getLives()>1 ) {
			graphics.setColor(Color.yellow);		
			graphics.fillOval(8*35,8*70-4, 23, 23);
			graphics.setColor(Color.black);
			graphics.fillPolygon(
					new int[]{(int)(8*35+23/3),
							(int)(8*35+23),
							(int)(8*35+23)},
					new int[]{(int)(8*70-4+(23/2)),
							(int)(8*70-4+(23*2.5/8)),
							(int)(8*70-4+(23*5.5/8))},3);			
		}
		if(PacmanManager.puckman.getLives()>2) {
			graphics.setColor(Color.yellow);		
			graphics.fillOval(8*39-4,8*70-4, 23, 23);
			graphics.setColor(Color.black);
			graphics.fillPolygon(
					new int[]{(int)(8*39-4+23/3),
							(int)(8*39-4+23),
							(int)(8*39-4+23)},
					new int[]{(int)(8*70-4+(23/2)),
							(int)(8*70-4+(23*2.5/8)),
							(int)(8*70-4+(23*5.5/8))},3);
		}
	}
	private void drawScore(Graphics graphics) {
		graphics.setColor(Color.green);
		graphics.setFont(new Font("serif", Font.LAYOUT_NO_START_CONTEXT, 20));
		graphics.drawString("Score",8*4, 8*72-4);
		graphics.setColor(Color.yellow);
		graphics.drawString(""+PacmanManager.score,8*13, 8*72-4);
	}
	public void drawCherry(Graphics graphics) {
		this.graphics = graphics;
		graphics.setColor(Color.red);
		PacmanManager.cherry.activateTimer();
		graphics.fillOval((int)PacmanManager.cherry.getCoord2d().getX(),(int) PacmanManager.cherry.getCoord2d().getY(),
				(int)PacmanManager.cherry.getSize(),(int)PacmanManager.cherry.getSize());
		drawCherryLabellScore(graphics);
	}
	private void drawCherryLabellScore(Graphics graphics) {
		graphics.setFont(new Font("SansSerif", Font.PLAIN, 16));
		if(PacmanManager.cherry.labelScore == true) {
//			if(TimerLabelScore == 1) {
//			Control.cherry.labelScoredCoordinates = new Coord2D(Control.cherry.getCoord2d().getX()+4,
//					Control.cherry.getCoord2d().getY()+16);
//			Control.cherry.score = Control.ghostScore/2;
//			}
			graphics.setColor(Color.green);
			graphics.drawString(""+PacmanManager.cherry.getScore(),
					(int)PacmanManager.cherry.labelScoredCoordinates.getX(),
					(int)PacmanManager.cherry.labelScoredCoordinates.getY());
		}
	}
	public static MapGui getInstance() {
		instance = instance== null? new MapGui():instance;
		return instance;
	}
	public static Wall [] getWalls() {
		return setWalls;
	}	
}
