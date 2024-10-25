package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import controller.Control;
import model.dao.PacmanManager;
import model.entity.Blinky;
import model.entity.Coord2D;
import model.entity.MyRectangle2D;

public class BlinkyGui extends Blinky{
	Graphics graphics;
	private static BlinkyGui instance;
	private static MyRectangle2D virtualBlinky;
	
	private BlinkyGui() {
		super();
		virtualBlinky = getVirtualFigure();
	}
	public void drawGhost(Graphics graphics){
		initialConditions(graphics);
		
		drawBody(graphics);
		drawClasicEyes(graphics);
		drawScaredFace(graphics);
		drawPositions(graphics);
		drawLabellScore(graphics);
	}
	public void drawStaticGhost(Graphics graphics){
		initialConditions(graphics);
		drawBody(graphics);
		drawClasicEyes(graphics);  
		drawPositions(graphics);
	}
	public void initialConditions(Graphics graphics) {
		this.graphics = graphics;
		virtualBlinky = getVirtualFigure();
		actualColor = mode.equals(mode.CHASE)?this.color: mode.equals(mode.ESCAPE)?Color.blue:  
			mode.equals(mode.ESCAPE_ENDING)?Color.white:Color.DARK_GRAY;
		int inc = mode.equals(mode.RETURN)?16*14:16*12; 
		graphics.setColor(actualColor);
	}
	private void drawLabellScore(Graphics graphics) {
		graphics.setFont(new Font("SansSerif", Font.PLAIN, 16));
		if(labelScore == true) {
			if(TimerLabelScore == 1) {
				labelScoredCoordinates = new Coord2D(coord2d.getX()+4,coord2d.getY()+16);
				ghostScore = PacmanManager.ghostScore/2;
			}
			graphics.setColor(Color.green);
			graphics.drawString(""+ghostScore,(int)labelScoredCoordinates.getX(),(int)labelScoredCoordinates.getY());
		}
	}
	public void drawPositions(Graphics graphics) {
		this.graphics = graphics;
		graphics.setColor(color.black);
//		System.out.println(moveTimer);
		if(moveTimer>450  && !mode.equals(mode.RETURN)) {
			graphics.fillRect((int)coord2d.getX()+2+(this.size-8)/2,(int)(coord2d.getY()+7+(this.size/2)), (this.size-8)*3/23+3,
					(int)((this.size-8)/2)-3-(this.size-8)*3/23);
			graphics.fillPolygon(
					new int[]{(int)(coord2d.getX()+4),(int)(coord2d.getX()+4+3),
							(int)(coord2d.getX()+4+6)},
					new int[]{(int)(coord2d.getY()+(this.size)/2+(this.size-8)/2+1),
							(int)(coord2d.getY()+(this.size)/2+6+1),
							(int)(coord2d.getY()+(this.size)/2+(this.size-8)/2)+1},3);
			graphics.fillPolygon(
					new int[]{(int)(coord2d.getX()+4+17),(int)(coord2d.getX()+4+3+17),
							(int)(coord2d.getX()+4+6+17)},
					new int[]{(int)(coord2d.getY()+(this.size)/2+(this.size-8)/2+1),
							(int)(coord2d.getY()+(this.size)/2+6+1),
							(int)(coord2d.getY()+(this.size)/2+(this.size-8)/2)+1},3);
		}if(moveTimer<450 && !mode.equals(mode.RETURN)) {
			graphics.fillPolygon(
					new int[]{(int)(coord2d.getX()+3),(int)(coord2d.getX()+3),
							(int)(coord2d.getX()+3+4)},
					new int[]{(int)(coord2d.getY()+(this.size)/2+(this.size-8)/2+1),
							(int)(coord2d.getY()+(this.size)/2+6),
							(int)(coord2d.getY()+(this.size)/2+(this.size-8)/2)+1},3);
			
			graphics.fillPolygon(
					new int[]{(int)(coord2d.getX()+3)-4+8,(int)(coord2d.getX()+3+8),
							(int)(coord2d.getX()+3+4+8)},
					new int[]{(int)(coord2d.getY()+(this.size)/2+(this.size-8)/2+1),
							(int)(coord2d.getY()+(this.size)/2+6),
							(int)(coord2d.getY()+(this.size)/2+(this.size-8)/2)+1},3);
			
			graphics.fillPolygon(
					new int[]{(int)(coord2d.getX()+3)-4+16,(int)(coord2d.getX()+3+16),
							(int)(coord2d.getX()+3+4+16)},
					new int[]{(int)(coord2d.getY()+(this.size)/2+(this.size-8)/2+1),
							(int)(coord2d.getY()+(this.size)/2+6),
							(int)(coord2d.getY()+(this.size)/2+(this.size-8)/2)+1},3);
			graphics.fillPolygon(
					new int[]{(int)(coord2d.getX()+3)-4+25,(int)(coord2d.getX()+3+25),
							(int)(coord2d.getX()+4+25)},
					new int[]{(int)(coord2d.getY()+(this.size)/2+(this.size-8)/2+1),
							(int)(coord2d.getY()+(this.size)/2+6),
							(int)(coord2d.getY()+(this.size)/2+(this.size-8)/2)+1},3);
		}
	}
	private void drawScaredFace(Graphics graphics) {
		if(mode.equals(mode.ESCAPE) || mode.equals(mode.ESCAPE_ENDING)) {
			graphics.setColor(mode.equals(mode.ESCAPE)?color.white:color.red);
			graphics.fillOval((int)coord2d.getX()+3-2+2+(this.size-8)/3,
					(int) coord2d.getY()+4-2-1+(this.size-8)/3, 5, 5);//original
			graphics.fillOval((int)coord2d.getX()+3-2+9+(this.size-8)/3,
					(int) coord2d.getY()+4-2-1+(this.size-8)/3, 5, 5);//original
			graphics.drawPolyline(new int[] {(int)coord2d.getX()+4+3,
					(int)coord2d.getX()+4+5,
					(int)(coord2d.getX()+4+((this.size-7)/2)-3),
					(int)(coord2d.getX()+4+((this.size-7)/2)),
					(int)(coord2d.getX()+4+((this.size-7)/2)+3),
					(int)(coord2d.getX()+4+(this.size-7)-5),
					(int)(coord2d.getX()+4+(this.size-7)-3)},
					new int[] {(int)(coord2d.getY()+(this.size)/2+6-2),
							(int)(coord2d.getY()+(this.size)/2+6-5),
							(int)(coord2d.getY()+(this.size)/2+6-2),
							(int)(coord2d.getY()+(this.size)/2+6-5),
							(int)(coord2d.getY()+(this.size)/2+6-2),
							(int)(coord2d.getY()+(this.size)/2+6-5),
							(int)(coord2d.getY()+(this.size)/2+6-2)},
					7);
		}
	}
	private void drawClasicEyes(Graphics graphics) {
		if(!mode.equals(mode.ESCAPE) && !mode.equals(mode.ESCAPE_ENDING)) {
			graphics.setColor(color.white);
			increaseX = Math.toDegrees(direction)==0?2: Math.toDegrees(direction)==180?-2:0;
			increaseY = Math.toDegrees(direction)==90?2: Math.toDegrees(direction)==270?-2:0;

			graphics.fillOval((int)coord2d.getX()+4+3+increaseX,(int) coord2d.getY()+4+4+increaseY, 7, (this.size-8)/3+2);//original
			graphics.fillOval((int)coord2d.getX()+4+13+increaseX,(int) coord2d.getY()+4+4+increaseY, 7, (this.size-8)/3+2);//original			
			graphics.setColor(color.blue);
			graphics.fillOval((int)coord2d.getX()+3-2+(this.size-8)/3+increaseX*2,
					(int) coord2d.getY()+4-2-1+(this.size-8)/3+2+increaseY*2, 5, 5);
			graphics.fillOval((int)coord2d.getX()+3-2+10+(this.size-8)/3+increaseX*2,
					(int) coord2d.getY()+4-2-1+(this.size-8)/3+2+increaseY*2, 5, 5);
		}
	}
	public void drawBody(Graphics graphics) {
		if(!mode.equals(mode.RETURN)) {
		graphics.fillOval((int)coord2d.getX()+4,(int) coord2d.getY()+4, this.size-8, this.size-8);
		graphics.fillRect((int)coord2d.getX()+4,(int)(coord2d.getY()+(this.size)/2), (this.size-7), (this.size-8)/2+1);
		}
	}
	public static BlinkyGui getInstance() {
		instance = instance==null?new BlinkyGui():instance;
		return instance;
	}
//	public static double getBlinkyDirection() {
//		return blinkyDirection;
//	}
	public static MyRectangle2D getvirtualBlinky() {
		return virtualBlinky;
	}
	
}