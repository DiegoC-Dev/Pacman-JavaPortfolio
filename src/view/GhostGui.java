package view;

import java.awt.Color;
import java.awt.Graphics;

import model.entity.Ghost;

public class GhostGui extends Ghost{
	private Graphics graphics;
//	private Color color;
	private Color actualColor ; 
	public GhostGui(Color color) {
		super();
		this.color = color;
		// TODO Auto-generated constructor stub
	}
	public void drawGhost(Graphics graphics) {
		this.graphics = graphics;
//		actualColor = state==0?color:Color.blue;
//		actualColor = state==0?color: state==1?Color.blue:  state==2?Color.white:Color.cyan;//or
		actualColor = mode.equals(mode.CHASE)?color: mode.equals(mode.ESCAPE)?Color.cyan:  
			mode.equals(mode.ESCAPE_ENDING)?Color.white:Color.black;
		
//		int inc = returnMode?16*15:16*5; //or
		int inc = mode.equals(mode.RETURN)?16*15:16*5; 
		
		graphics.setColor(actualColor);//rango de vision
//		graphics.drawRect((int)this.coord2d.getX()-16*5,(int) this.coord2d.getY()-16*5,
//				(int)this.size+16*10,(int) this.size+16*10);
		graphics.drawRect((int)this.coord2d.getX()-inc,(int) this.coord2d.getY()-inc,
				(int)this.size+inc*2,(int) this.size+inc*2);
		
//		graphics.drawOval(this.coord2d.getX()+(this.coord2d.getX()-PacmanVirtualfig.getX()),
//				this.coord2d.getY()+(this.coord2d.getY()-PacmanVirtualfig.getY()),
//				PacmanVirtualfig.getWidth(), PacmanVirtualfig.getHeight());
//		graphics.setColor(color.magenta);
//		graphics.fillOval((int)this.coord2d.getX()-16*6,(int) this.coord2d.getY()-16*6,
//				(int)this.size+16*12,(int) this.size+16*12);
//		graphics.fillOval((int)getViewingRange().getX(), (int)getViewingRange().getX(), (int)getViewingRange().getWidth(), (int)getViewingRange().getHeight());
		graphics.setColor(actualColor);
		graphics.fillOval((int)coord2d.getX()+4,(int) coord2d.getY()+4, this.size-8, this.size-8);//original
		graphics.fillRect((int)coord2d.getX()+4,+((int) coord2d.getY()+4+(this.size-8)/2), (this.size-8), (this.size-8)/2);
	}
}
