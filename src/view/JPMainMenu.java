package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Commands;
import controller.Control;
import utilities.Utilities;

public class JPMainMenu extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JButton btnIncio;
	private JLabel lblSpace1;
	private JButton btnAcerca;
	private JLabel lblSpace;
	private JButton btnSalir;
	private JLabel lblLogo;
	private JLabel lblLogo2;
	private JLabel lblSpace2;
	
	private Control control;
	public JPMainMenu(Control control) {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.BLACK);
		
//		JLabel lblLogo = new JLabel(new ImageIcon(getClass().getResource("/img/pac-man.jpg")));
		
		lblLogo = new JLabel(new ImageIcon(getClass().getResource("/img/letras3.png")));
		lblLogo.setAlignmentX(CENTER_ALIGNMENT);
		add(lblLogo);
		
		lblLogo2 = new JLabel(new ImageIcon(getClass().getResource("/img/pacman-mov.gif")));
		lblLogo2.setAlignmentX(CENTER_ALIGNMENT);
		add(lblLogo2);
		
		btnIncio = new JButton("Iniciar");
		btnIncio.setBackground(Color.yellow);
		btnIncio.setFocusable(false);
//		btnIncio = new MyJButton(null, "Iniciar", null, 10, 10);
		btnIncio.setActionCommand(Commands.PLAY.toString());
		btnIncio.setAlignmentX(CENTER_ALIGNMENT);
		btnIncio.addActionListener((ActionListener) control);
		add(btnIncio);
		
		lblSpace1 = new JLabel(" ");
		lblSpace1.setAlignmentX(CENTER_ALIGNMENT);
		add(lblSpace1);
		
		btnAcerca = new JButton("Acerca de");
		btnAcerca.setBackground(Color.yellow);
		btnAcerca.setFocusable(false);
//		btnAcerca = new MyJButton(null, "Acerca de", null, 10, 10);
		btnAcerca.setActionCommand(Commands.ABOUT.toString());
		btnAcerca.setAlignmentX(CENTER_ALIGNMENT);
		btnAcerca.addActionListener((ActionListener) control);
		add(btnAcerca);
		
		lblSpace = new JLabel(" ");
		lblSpace.setAlignmentX(CENTER_ALIGNMENT);
		add(lblSpace);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBackground(Color.yellow);
		btnSalir.setFocusable(false);
//		btnSalir = new MyJButton(null, "Salir", null, 10, 10);
		btnSalir.setActionCommand(Commands.QUIT.toString());
		btnSalir.setAlignmentX(CENTER_ALIGNMENT);
		btnSalir.addActionListener((ActionListener) control);
		add(btnSalir);
		
		lblSpace2 = new JLabel(" ");
		lblSpace2.setAlignmentX(CENTER_ALIGNMENT);
		add(lblSpace2);
		
	}
	 @Override
	  protected void paintComponent(Graphics g) {
	      Graphics2D g2 = (Graphics2D) g.create();
//	      g.setColor(Utilities.backgroundColor());
	      g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());
	  }
}
