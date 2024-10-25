package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DialogAbout extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JPanel northPanel;
	private JPanel centerPanel;
	private JPanel southPanel;
	private JPanel rightPanel;
	private JLabel lbl1;
	private JLabel lbl2;
	private JLabel lbl3;
	private JLabel lbl4;

	public DialogAbout(ActionListener actionListener) {
		setSize(600, 500);
		setTitle("Acerca de");
		setLocationRelativeTo(null);
		setResizable(false);
		initComponents(actionListener);
		setVisible(false);
	}

	private void initComponents(ActionListener actionListener) {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		northPanel = new JPanel();
		centerPanel = new JPanel();
		southPanel = new JPanel();
		rightPanel = new JPanel();
		
		northPanel.setBackground(Color.WHITE);
		centerPanel.setBackground(Color.WHITE);
		southPanel.setBackground(Color.WHITE);
		rightPanel.setBackground(Color.WHITE);
		
		lbl1 = new JLabel("<html><h2>Creador original:</h2> Tōru Iwatani<br/>"
						+ "<h2>Creador de esta version:</h2> Diego Cepeda<br/>"
						+ "<h2>Version:</h2> 9.0</html>");
		lbl1.setForeground(Color.BLUE);
		lbl2 = new JLabel("<html><h2>Acerca de este juego</h2>"
						+ "<div>"
						+ "Es un videojuego creado por el diseñador de "
						+ "<br/>videojuegos Toru Iwatani de la empresa Namco, "
						+ "<br/>y distribuido por Midway Games al mercado "
						+ "<br/>estadounidense a principios de los años 1980. "	
						+ "</div></html>");
		lbl2.setForeground(Color.RED);
		lbl3 = new JLabel("<html><h2>Fuentes de los recursos</h2>"
						+ "a) ps://www.taringa.net/+paranormal/el-origen-de-pacman_ibqzi <br/>"
						+ "b) https://www.freepng.es/hd-png/pacman.html <br/>"
						+ "c) https://www.insights.la/2018/01/17/iconic-brands-pacman-clasico-37-anos<br/>"
						+ "d) www.gamasutra.com"
						+ "</html>");
		lbl3.setForeground(Color.GREEN);
		lbl4 = new JLabel("No tiene librerias");		
		centerPanel.setLayout(new GridLayout(4, 1));
		centerPanel.add(lbl1);
		centerPanel.add(lbl2);
		centerPanel.add(lbl3);
		centerPanel.add(lbl4);
		
		northPanel.add(new JLabel("<html><h1>THE PACMAN Y SU DESARROLLADOR</h1></html>"));
		
		rightPanel.setLayout(new BorderLayout());
		rightPanel.add(new JLabel(new ImageIcon(getClass().getResource("/images/right.jpeg")))
				, BorderLayout.CENTER);
		
		mainPanel.add(rightPanel, BorderLayout.WEST);
		mainPanel.add(northPanel, BorderLayout.PAGE_START);
		mainPanel.add(new JScrollPane(centerPanel), BorderLayout.CENTER);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		setContentPane(mainPanel);
	}
}