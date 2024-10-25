package model.entity;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
//import javafx.geometry.Rectangle2D;
//import javafx.geometry.Rectangle2D;

public class Wall {
	private Coord2D coord2d;
	private double width;
	private double heigth;
	private MyRectangle2D virtualFigure ;
	public Wall(Coord2D coord2d, double width, double heigth) {
		super();
		this.coord2d = coord2d;
		this.width = width;
		this.heigth = heigth;
		this.virtualFigure = new MyRectangle2D((coord2d.getX()),(coord2d.getY()), 
				width, heigth);
//		this.virtualFigure = new MyRectangle2D(coord2d.getX(), coord2d.getY(), width, heigth); 
	}
	
	public Coord2D getCoord2d() {
		return coord2d;
	}
	
//	public void setCoord2d(Coord2D coord2d) {
//		this.coord2d = coord2d;
//	}

	public MyRectangle2D getVirtualFigure() {
		return virtualFigure;
	}

	public double getWidth() {
		return width;
	}
	public double getHeigth() {
		return heigth;
	}

	public void setVirtualFigure(MyRectangle2D virtualFigure) {
		this.virtualFigure = virtualFigure;
	}
}
