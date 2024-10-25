package view;

import javax.swing.JOptionPane;

public class IoManager {

	public int readInt(String message) {
		String linea = JOptionPane.showInputDialog(message);
		int numero = Integer.parseInt(linea);
		return numero;
	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public short readShort(String message) {
		short value = (short) Integer.parseInt(JOptionPane.showInputDialog(message));
		return value;
	}

	public double readDouble(String message) {
		double value = (double) Double.parseDouble(JOptionPane.showInputDialog(message));
		return value;
	}

	public String readString(String message) {
		return JOptionPane.showInputDialog(message);
	}

	String entry;

	public void showMessage(String message, String nameTable) {
		JOptionPane.showMessageDialog(null, message, nameTable, JOptionPane.INFORMATION_MESSAGE);
	}

	public String readMenu(String message, String nameTable, String[] menu) {
		entry = (String) JOptionPane.showInputDialog(null, message, nameTable, JOptionPane.QUESTION_MESSAGE, null, menu,
				menu[0]);
		return entry;
	}

	public void showmatrix(int[][] matriz, String message) {
		String processMatriz = "";
		String[][] matrizShow = new String[matriz.length][matriz[0].length];
		for (int i = 0; i < matriz.length; i++) {
			for (int a = 0; a < matriz[i].length; a++) {
				String formatLine = "%1$-3s";
				matrizShow[i][a] = String.format(formatLine, matriz[i][a]);
				System.out.print("[" + matrizShow[i][a] + "]");// Consola
				processMatriz += "[" + matrizShow[i][a] + "]";
			}
			System.out.println();// Consola
			processMatriz += "\n";
		}
		JOptionPane.showMessageDialog(null, processMatriz);
	}

	public void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
	}
	
	public int readMenu(){
        String menuText = "\nMENU MOVIMIENTOS\n"
                + "[1] Derecha\n"
                + "[2] Izquierda\n"
                + "[3] Abajo\n"
                + "[4] Arriba\n"
                + "[5] Inferior derecha\n"
                + "[6] Inferior izquierda\n"
                + "[7] Superior derecha\n"
                + "[8] Superior izquierda\n"
                + "[9] Salir\n"
                ;
        return readInt(menuText);
    }
	
	public String showMenu(){
		String menuText = "\nMENU MOVIMIENTOS\n"
                + "[1] Derecha\n"
                + "[2] Izquierda\n"
                + "[3] Abajo\n"
                + "[4] Arriba\n"
                + "[5] Inferior derecha\n"
                + "[6] Inferior izquierda\n"
                + "[7] Superior derecha\n"
                + "[8] Superior izquierda\n"
                + "[9] Salir\n";
		return menuText;
	}
}
