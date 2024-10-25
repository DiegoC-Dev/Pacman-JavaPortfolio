package model.entity;

public class Coord2D {
	protected double x; 
	protected double y; 
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public Coord2D(double x,double y) {
		this.y=y;
		this.x=x;
	}
/**
 * mueve la cordenada con un angulo dado al centro
 * @param center coordenada sobre la que se quiere rotar
 * @param angle angulo final
 */
	public void rotate(Coord2D center,double angle) {
		double distance = Math.hypot(center.getX()-this.x, center.getY()-this.y);
		this.x=center.x+(distance*Math.cos(angle));
		this.y=center.y+(distance*Math.sin(angle));
		//debe cambiar el valor de x y el valor de y
	}
	public void moveFigure(double distance,double angle) {
		this.x+=(distance*Math.cos(angle));
		this.y+=(distance*Math.sin(angle));
	}
	public double distanceTo(Coord2D coord2d) {
		return Math.hypot(this.x-coord2d.x, this.y-coord2d.y);
	}	
}