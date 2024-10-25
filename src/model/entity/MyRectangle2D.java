package model.entity;

public class MyRectangle2D {
	private double x;
	private double y; 
	private double height;
	private double width;
	
	public MyRectangle2D(double x, double y, double width, double height) {
		super();
		this.x = x;
		this.y = y;
		this.height = width;
		this.width = height;
	}
	public boolean intersection(MyRectangle2D virtualFigure) {
//		int increase = 10;
//		System.out.println("interesecciono");
//		System.out.println("pacman "+virtualFigure.getY());
//		System.out.println("muro virt"+(this.getY()+this.getHeight()));
//		System.out.println("muro real"+(this.getY()+setWalls[i].getVirtualFigure().getHeight()));
		return (
		this.x+this.width>virtualFigure.getX()//incremento inecesario 
				&& 
		this.x+this.width<virtualFigure.getX()+virtualFigure.getWidth()//choque derecha y izquierda pac der
		&&
		this.y+this.height>virtualFigure.getY() && this.y+this.height<virtualFigure.getY()+virtualFigure.getHeight()//choque arriba y abajo pac inf
//		choque inf der------------
		||
		this.x+this.width>virtualFigure.getX() && this.x+this.width<virtualFigure.getX()+virtualFigure.getWidth()//choque derecha y izquierda pac der
		&& 
		this.y>virtualFigure.getY() && this.y<virtualFigure.getY()+virtualFigure.getHeight()//choque arriba y abajo pac sup
////		choque sup der------------
////		choque sup e inf derecho-------------------		
		||
		this.x>virtualFigure.getX() && this.x<virtualFigure.getX()+virtualFigure.getWidth()//choque derecha y izquierda pac izq
		&& 
		this.y+this.height>virtualFigure.getY() && this.y+this.height<virtualFigure.getY()+virtualFigure.getHeight()//choque arriba y abajo pac inf
//		choque inf izq------------
		||
		this.x>virtualFigure.getX() && this.x<virtualFigure.getX()+virtualFigure.getWidth()//choque derecha y izquierda pac der
		&& 
		this.y>virtualFigure.getY() && this.y<virtualFigure.getY()+virtualFigure.getHeight()//choque arriba y abajo pac sup
//		choque sup izq------------
		);
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getHeight() {
		return height;
	}
	public double getWidth() {
		return width;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	
}
