package model.entity;

import controller.Control;
import model.dao.PacmanManager;
import view.MapGui;

public class Character {
	protected long deltaTime;
	protected double time;
	protected int size;
	protected Space space;
	protected Coord2D coord2d;
	protected double direction; 
	protected int speed;
	protected MyRectangle2D virtualFigure;
	protected MyRectangle2D secundaryVirtualFigure;
	protected Wall selectedWall;
	protected int motionSaved;
	protected int square;
	protected int referenceSpeed;
	protected int above;
	protected int dawn;
	protected int left;
	protected int right;
	protected Wall[] setWalls;
	protected int moveTimer;
	
	protected Character() {
		super();
		moveTimer = 0;
		setWalls = MapGui.getWalls();
		deltaTime=1;
		time = (double)deltaTime/100000;
		referenceSpeed=PacmanManager.vel;
		this.size = 31;//total
		this.square = 8;
		this.space = new Space(new Coord2D(square-(size*3/4), square-(size*3/4)),
				square*57+(size*5/4), square*72+(size*6/4));
		this.coord2d = new Coord2D(8*27+1, 8*52-8+1);
		this.direction = Math.toRadians(270);
		this.speed = 0;
		this.virtualFigure = new MyRectangle2D(coord2d.getX(), coord2d.getY(),
				size, size);
		this.secundaryVirtualFigure = new MyRectangle2D(coord2d.getX()+size*4/10, coord2d.getY()+size*4/10,
				size-size*8/10, size-size*8/10); 
		this.selectedWall = null;
		this.motionSaved = 0;
	}
	public int getMotionSaved() {
		return motionSaved;
	}
	public int getSquare() {
		return square;
	}
	public int getSize() {
		return size;
	}
	public Space getSpace() {
		return space;
	}
	public Coord2D getCorCoord2d() {
		return coord2d;
	}
	protected double getDirection() {
		return direction;
	}
	public int getSpeed() {
		return speed;
	}
	protected MyRectangle2D getVirtualFigure() {
		return virtualFigure;
	}
	public MyRectangle2D getSecundaryVirtualFigure() {
		return secundaryVirtualFigure;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public void setSpace(Space space) {
		this.space = space;
	}
	public void setCoord2d(Coord2D coord2d) {
		this.coord2d = coord2d;
	}
	public void setVirtualFigure(MyRectangle2D virtualFigure) {
		this.virtualFigure = virtualFigure;
	}
	public Coord2D getCoord2d() {
		return coord2d;
	}
	public Wall getSelectedWall() {
		return selectedWall;
	}
	public void setSelectedWall(Wall selectedWall) {
		this.selectedWall = selectedWall;
	}
	public void setMotionSaved(int motionSaved) {
		this.motionSaved = motionSaved;
	}
	public void setDirection(double direction) {
		if(coord2d.getY()+1>space.getY()+space.getHeight()-(size) ) {
			this.direction = direction!= Math.toRadians(90)?direction:this.direction;
		}
		else if(coord2d.getY()-1<space.y) {
			this.direction = direction!= Math.toRadians(270)?direction:this.direction;
		}
		else if(coord2d.getX()+1>space.getX()+space.getWidth()-(size)) {
			this.direction = direction!= Math.toRadians(0)?direction:this.direction;
		}
		else if(coord2d.getX()-1<space.getX()) {
			this.direction = direction!= Math.toRadians(180)?direction:this.direction;
		}
		else
			this.direction = direction;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getReferenceSpeed() {
		return referenceSpeed;
	}
	
	
	public void movetimer() {
		moveTimer++;
		if(moveTimer>900) {
			moveTimer=1;
		}
	}
	public boolean movementAllowed(int increaseX,int increaseY) {
		MyRectangle2D newVirtualFigure = new MyRectangle2D(this.coord2d.x+(2*increaseX),
				this.coord2d.y+(2*increaseY), size, size); 
		for (int i = 0; i < setWalls.length; i++) {
			if(setWalls[i].getVirtualFigure().intersection(newVirtualFigure)){
				return false;
			}
		}
		return true;
	}
	protected Wall wallIntersection(){
		for (int i = 0; i < setWalls.length; i++) {
			if(setWalls[i].getVirtualFigure().intersection(virtualFigure)) {
				return setWalls[i];
			}
		}
		return null;
	}
}