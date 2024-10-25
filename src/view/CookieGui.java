package view;

import java.awt.Color;
import java.awt.Graphics;

import model.entity.Cookie;
import model.entity.Coord2D;

public class CookieGui extends Cookie{
	Graphics graphics;
	public CookieGui(Coord2D coord2d, boolean superCookie) {
		super(coord2d, superCookie);
	}
	public void drawCookie(Graphics graphics) {
		this.graphics=graphics;
		graphics.setColor(Color.green);
		graphics.fillOval((int)coord2d.getX(),(int) coord2d.getY(), size, size);
//		if(superCookie) {
//			drawSuperCookie();
//		}
//		else {
//			drawSmallCookie();
//		}
	}
//	public void drawSmallCookie(){
////		graphics.fillOval(coord2d.getX(), coord2d.getY(), size, size);
//
//	}
//	public void drawSuperCookie(){
//		
//	}

}
