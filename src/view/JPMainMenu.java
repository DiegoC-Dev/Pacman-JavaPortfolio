package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.Box;
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
	private JButton btnAcerca;
	private JButton btnSalir;
	private JLabel lblLogo;
	private JLabel lblLogo2;
	
	private Control control;
	public JPMainMenu(Control control) {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.BLACK);
		
		Dimension winSize = Utilities.getAdaptiveWindowSize();
		double ratio = Math.min(1.0, (double) winSize.height / 515.0);
		int logoW = (int) Math.round(350 * ratio);
		int logoH = (int) Math.round(59 * ratio);
		
		add(Box.createVerticalGlue());
		ImageIcon rawLogo = new ImageIcon(getClass().getResource("/img/letras3.png"));
		Image scaledLogo = rawLogo.getImage().getScaledInstance(logoW, logoH, Image.SCALE_SMOOTH);
		lblLogo = new JLabel(new ImageIcon(scaledLogo));
		lblLogo.setAlignmentX(CENTER_ALIGNMENT);
		add(lblLogo);
		
		add(Box.createVerticalGlue());
		lblLogo2 = new JLabel(new ImageIcon(getClass().getResource("/img/pacman-mov.gif")));
		lblLogo2.setAlignmentX(CENTER_ALIGNMENT);
		add(lblLogo2);
		
		add(Box.createVerticalGlue());
		btnIncio = new JButton("Iniciar");
		btnIncio.setBackground(Color.yellow);
		btnIncio.setFocusable(false);
		btnIncio.setActionCommand(Commands.PLAY.toString());
		btnIncio.setAlignmentX(CENTER_ALIGNMENT);
		btnIncio.addActionListener((ActionListener) control);
		add(btnIncio);
		
		add(Box.createVerticalStrut(winSize.height < 470 ? 6 : 12));
		
		btnAcerca = new JButton("Acerca de");
		btnAcerca.setBackground(Color.yellow);
		btnAcerca.setFocusable(false);
		btnAcerca.setActionCommand(Commands.ABOUT.toString());
		btnAcerca.setAlignmentX(CENTER_ALIGNMENT);
		btnAcerca.addActionListener((ActionListener) control);
		add(btnAcerca);
		
		add(Box.createVerticalStrut(winSize.height < 470 ? 6 : 12));
		
		btnSalir = new JButton("Salir");
		btnSalir.setBackground(Color.yellow);
		btnSalir.setFocusable(false);
		btnSalir.setActionCommand(Commands.QUIT.toString());
		btnSalir.setAlignmentX(CENTER_ALIGNMENT);
		btnSalir.addActionListener((ActionListener) control);
		add(btnSalir);
		
		add(Box.createVerticalGlue());
		
	}
	 @Override
	  protected void paintComponent(Graphics g) {
	      Graphics2D g2 = (Graphics2D) g.create();
	      g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());
	  }
}