package model.entity;

public class Space extends Coord2D{
	private Coord2D coord2d;
	private double width;
	private double height;
	
	public Space(Coord2D coord2d, double width, double height) {
		super(coord2d.x, coord2d.y);
		this.height = height;
		this.width = width;
		this.coord2d = coord2d;

	}	
	public boolean outOfRange(Coord2D coord2d,int size) {
		return (
				coord2d.getY()>this.y+this.height-(size) || 
				coord2d.getY()<this.y ||
				coord2d.getX()>this.x+this.width-(size) 
				||  
				coord2d.getX()<this.x
				) ;
	}
	public double getWidth() {
		return width;
	}
	public double getHeight() {
		return height;
	}
	public Coord2D getCoord2d() {
		return coord2d;
	}
}