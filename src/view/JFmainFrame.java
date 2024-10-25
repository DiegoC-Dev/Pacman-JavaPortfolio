package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ComponentAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


import controller.Control;
import model.entity.Cookie;
import model.entity.Wall;
import utilities.Utilities;

public class JFmainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private JPNorthPanel jPnorthPanel;
	private JPCentralPanel jPCenterPanel;
//	private JPSouthPanel jPSouthPanel;
	private JPanel mainPanel;
	private Control control;
	public JFmainFrame (Control control){
		this.control = control;
//		setSize(Utilities.getDysplaySize());
		setResizable(false);
		setSize(470, 630);
		setLocationRelativeTo(this);
//		System.out.println("frame--> "+"w "+getWidth()+" - h "+getHeight());
		setTitle("pacman vrs 9.0 ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image icon = new ImageIcon(getClass().getResource("/img/icon.jpg")).getImage();
		setLayout(new BorderLayout());
		initialComponents(control);
		setVisible(true);
		setIconImage(icon);
	}

	private void initialComponents(Control control) {
		JButton jButton = new JButton();
		jButton.addKeyListener(control);
		add(jButton,BorderLayout.NORTH);

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(Color.yellow);
			jPCenterPanel = new JPCentralPanel(control);
			mainPanel.add(jPCenterPanel, BorderLayout.CENTER);
		add(mainPanel,BorderLayout.CENTER);	
	}
	public void createBoard(){
		jPCenterPanel.setVisible(true);
	}
	public void repaint() {
		jPCenterPanel.repaint();
	}

	public void setCookies(CookieGui []setCookies) {
		jPCenterPanel.setSetCookies(setCookies);
	}

	public void sendWalls(Wall[] setWalls) {
		jPCenterPanel.sendWalls(setWalls);		
	}

	public void sendGameTime(int gameTime) {
		jPCenterPanel.sendGameTime(gameTime);		
	}

//	public void setPuckman(PuckmanGui puckman) {
//		jPCenterPanel.setPuckman(puckman);		
//	}
}