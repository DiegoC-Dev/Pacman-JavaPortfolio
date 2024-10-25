package model.entity;

public class Cookie extends Food{
	protected boolean superCookie;
	
	public Cookie(Coord2D coord2d, boolean superCookie) {
		super();
		this.size = superCookie?16:8;
		this.coord2d = new Coord2D(coord2d.getX()-size/2, coord2d.getY()-size/2);
//		this.coord2d = new Coord2D(coord2d.getX(), coord2d.getY());
		this.superCookie = superCookie;
		this.virtualFigure = new MyRectangle2D(coord2d.getX()-size/2, coord2d.getY()-size/2,size, size);
//		this.virtualFigure = new MyRectangle2D(coord2d.getX(), coord2d.getY(),size, size);
	}
	public boolean isSuperCookie() {
		return superCookie;
	}
}