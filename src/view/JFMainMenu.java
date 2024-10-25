package view;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import controller.Control;

public class JFMainMenu extends JFrame{
	JPMainMenu jpMainMenu;
	Control control;
	public JFMainMenu(Control control) {
		setResizable(false);
		setSize(470, 680);
		setLocationRelativeTo(this);
		setTitle("pacman vrs 9.0 ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image icon = new ImageIcon(getClass().getResource("/img/icon.jpg")).getImage();
		setIconImage(icon);
		setLayout(new BorderLayout());
		jpMainMenu = new JPMainMenu(control);
		initialComponents(control);
//		setVisible(true);

	}
	private void initialComponents(Control control2) {
		add(jpMainMenu,BorderLayout.CENTER);
	}
}
