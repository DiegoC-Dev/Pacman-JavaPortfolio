package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import model.entity.Pacman;

public class PacmanGui extends Pacman {
	private Graphics graphics;
	public int y;
	public int x;
	public int square;
	public PacmanGui() {
		super();
//		square = 19;
//		y = square;
//		x = square;
	}
	public void drawPacman(Graphics graphics2) {
		this.graphics=graphics;
		drawPac();
//		graphics.fillRect(50, 50, 50, 20);
//		graphics.fillOval(-7+50+14*28, 7+50+14*36, 26, 26);
//		graphics.fillOval(x, y, width, height);
//		graphics.fillOval(-7+50+14*28, 50, 23, 23);//tamaño correcto fuera del mapa
//		graphics.fillOval(8*28, 8*52, 31-8, 31-8);//tamaño correcto
		
//		graphics.fillOval(20,100, 31-8, 31-8);//original


//		graphics.fillOval(-7+50+14*28, 50, 21, 21);//tamaño modelo		
//		graphics.fillOval(-7+50+14*28, 50, 20, 20);
	}
	public void drawMap(Graphics graphics) {
		this.graphics=graphics;
//		graphics.fillRect(50, 50, 19, 19);
		square = 8;
		y = square;
		x = square;

		for (int i = 0; i < m.length; i++) {
//			for (int j = 0; j < m[0].length; j++) {
			for (int j = 0; j < m[0].length; j++) {
				if(m[i][j]==0) {
					drawVoid(x, y);
				}
				if(m[i][j]==1) {
					drawPacman(x, y);
				}
				if(m[i][j]==2) {
					drawGost(x, y);
				}
				if(m[i][j]==3) {
					drawcookie(x, y);
				}
				if(m[i][j]==4) {
					drawWall(x, y);
				}
				graphics.fillRect(x, y, square, square);	
				x=x+square;
			}
			x=square;
			y=y+square;
		}
	}
	public void draw(Graphics graphics) {
		this.graphics=graphics;
		System.out.println("metodo draw");
		graphics.setColor(Color.white);
		graphics.fillRect(0, 0, 40, 40);	
		graphics.fillRect(50, 50, 20, 20);
//		System.out.println(m[0].length);
//		System.out.println(m.length);
//		System.out.println("prueba en pacman gui "+this.m[0][0]);

		for (int i = 0; i < m.length-2; i++) {
//			for (int j = 0; j < m[0].length; j++) {
			for (int j = 0; j < m[0].length; j++) {
				if(m[i][j]==0) {
					drawVoid(x, y);
				}
				if(m[i][j]==1) {
					drawPacman(x, y);
				}
				if(m[i][j]==2) {
					drawcookie(x, y);
				}
				if(m[i][j]==3) {
					drawGost(x, y);
				}
				if(m[i][j]==4) {
					drawWall(x, y);
				}
				graphics.fillRect(x, y, square, square);	
				x=x+square;
			}
//			System.out.println("nueva fila");
//			System.out.println(m[0][0]);
			x=square;
			y=y+square;
		}
//		System.out.println("pacman gui");
	}
	public void drawVoid(int x, int y) {
		graphics.setColor(Color.black);
	}
	public void drawPacman(int x, int y) {
//		graphics.setColor(Color.yellow);
		graphics.setColor(Color.yellow);
	}
	public void drawPac() {
		graphics.setColor(Color.magenta);
	}
	public void drawGost(int x, int y) {
		graphics.setColor(Color.lightGray);
//		graphics.setColor(Color.red);
	}
	public void drawcookie(int x, int y) {
		graphics.setColor(Color.red);
//		graphics.setColor(Color.lightGray);
	}
	public void drawWall(int x, int y) {
		graphics.setColor(Color.blue);
	}
}