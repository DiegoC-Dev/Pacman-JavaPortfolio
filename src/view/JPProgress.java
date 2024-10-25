package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JPProgress extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPProgress() {
		setOpaque(false);
	}
	@Override
	public void paint(Graphics g) {
		Dimension dimension = this.getSize();
		ImageIcon icon = new ImageIcon(getClass().getResource("/img/pac-man.jpg"));
		g.drawImage(icon.getImage(), 0, 0, dimension.width, dimension.height, null);
		super.paint(g);
	
	}
}
