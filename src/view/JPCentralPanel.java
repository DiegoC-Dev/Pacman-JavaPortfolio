package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;
import controller.Control;
import model.dao.PacmanManager;
import model.entity.Cookie;
import model.entity.Ghost;
import model.entity.Wall;

public class JPCentralPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	MyFigureSet myFigureSet;
//	PacmanGui pacmanGui;
//	private PuckmanGui puckmanGui;
	private int gameTime;
	Color fillColor;
	Color outlinedColor;
	Color extraDetailColor;
//	private Coord2D coord2d;
	private boolean paintFigure;
	private CookieGui [] setCookies;
//	int cont=0;
	public JPCentralPanel (Control control) {
		super();
		setCookies = null;
		paintFigure = false;
		setBackground(Color.cyan);
		this.setLayout(new BorderLayout());
		setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
//		initComponents(control);
		setVisible(true); 
	}
	private void initComponents(Control control) {
		JButton jButton = new JButton();
		jButton.addKeyListener(control);
		add(jButton,BorderLayout.NORTH);
	}
	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		sendGameTime(gameTime);
		int cont=0;
//		cont++;
//		System.out.println("con"+cont);
//		if(paintFigure) {
//			for (MyFigureGUI figureGUI : myFigureSet.getMyFigures()) {
//				figureGUI.draw(graphics, figureGUI.getFigure(), figureGUI.getStatusFigureEnum(),(MyFigure)figureGUI);
////				System.out.println(" dimension sup "+figureGUI.getDimensions().getSuperior());
//				figureGUI.assignDimensions(figureGUI.getDimensions());
//				figureGUI.createVirtualFigure();
//			}
//		}
//		Control.pc.drawPacman(graphics);			
//		Control.pc.drawPacman(graphics);
//		puckmanGui.drawPuckman(graphics);
		
//		Control.pc.drawMap(graphics);					
//		Control.inky.drawInky(graphics);
//		Control.clyde.drawClyde(graphics);
		if(gameTime >1000000) {
			cont++;
//			System.out.println("modo normal");
			PacmanManager.map.drawMap(graphics);			
//			if(cont>500000) {
//				Control.map.drawCherry(graphics);
//			}
//			Control.puckman.deathAnimation(graphics);
			PacmanManager.puckman.drawPuckman(graphics);
			PacmanManager.map.drawCherry(graphics);
			drawCookies(graphics);
			PacmanManager.blinky.drawGhost(graphics);
			PacmanManager.pinky.drawGhost(graphics);
			PacmanManager.inky.drawGhost(graphics);
			PacmanManager.clyde.drawGhost(graphics);
			PacmanManager.puckman.setAnimationFinished(false);
			if(PacmanManager.map.isEndLevel()== true) {
				PacmanManager.map.drawNextLevel(graphics);
			}
		}
//		if(gameTime <=1000000) {
//		else{//sin animacion
		else if(gameTime >0){
			PacmanManager.map.drawMap(graphics);			
			PacmanManager.puckman.drawPuckman2(graphics);
			drawCookies(graphics);
			PacmanManager.blinky.drawStaticGhost(graphics);
			PacmanManager.pinky.drawStaticGhost(graphics);
			PacmanManager.inky.drawStaticGhost(graphics);
			PacmanManager.clyde.drawStaticGhost(graphics);
//			System.out.println("modo espera");
			PacmanManager.map.drawGetReady(graphics);
		}
		else {
//			Control.puckman.drawPuckman(graphics);
			PacmanManager.map.drawMap(graphics);			
			PacmanManager.puckman.deathAnimation(graphics);
			drawCookies(graphics);
			if(PacmanManager.puckman.getLives()==0 && PacmanManager.puckman.isAnimationFinished()==true) {
				PacmanManager.map.drawGameOver(graphics);			
			}
		}
		////
//		Control.ghost_1.drawGhost(graphics);
//		Control.puckman2.drawPuckman(graphics);
		
		PacmanManager.puckman.drawLeftWall(graphics);
		PacmanManager.puckman.drawRightWall(graphics);
		PacmanManager.puckman.drawUpperWall(graphics);
		PacmanManager.puckman.drawBottomWall(graphics);
//		graphics.fillRect(0, 0, 20, 20);	
//		if(cont==1) {
//			Control.pc.draw(graphics);			
//		}
	}
//	private void drawGhost() {
//		for (Ghost ghost : Control.ghosts) {
//			ghost.
//		}
////		Control.ghosts.
//	}
	private void drawCookies(Graphics graphics) {
		for (CookieGui cookieGui : setCookies) {
			cookieGui.drawCookie(graphics);
		}
	}
	public boolean isPaintFigure() {
		return paintFigure;
	}
	public void setPaintFigure(boolean paintFigure) {
		this.paintFigure = paintFigure;
	}
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	public void setOutlinedColor(Color outlinedColor) {
		this.outlinedColor = outlinedColor;
	}
	public void setExtraDetailColor(Color extraDetailColor) {
		this.extraDetailColor = extraDetailColor;
	}
//	public void setPuckman(PuckmanGui puckman) {
////		this.puckmanGui=puckman;		
//	}
	public Cookie[] sendCookies(Cookie[] setCookies) {
		return setCookies;
	}
//	public Cookie[] getSetcoCookies() {
//		return setcoCookies;
//	}
	public void setSetCookies(CookieGui[] setcoCookies) {
		this.setCookies = setcoCookies;
	}
	public void sendWalls(Wall[] setWalls) {
		
	}
	public void sendGameTime(int gameTime) {
		this.gameTime = gameTime;
	}
	
}