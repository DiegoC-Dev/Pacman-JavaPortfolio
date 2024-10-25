package model.entity;

public class Pacman {
	public int i, j, px, py, gx, gy, fx, fy, cg = 0;
	protected int[][] m = new int[36][28];
	public int validate = 0;

	public Pacman() {
//	public Pacman(int board [][]) {
		super();
//		for (i = 0; i <= 35; i++) {
//			for (j = 0; j <= 27; j++) {
//				m[i][j] = board [i][j];
////				m[i][j] = 0;
//			}
//		}
//		importBoard();
	}
//	public void importBoard() {
//		
//	}
	public int[][] getM() {
		return m;
	}

	public void setM(int[][] m) {
		this.m = m;
	}

	///// en este metodo cada movimiento tiene reglas
	public void Movements(int opcion) {
		if (opcion == 1) {
			if (py + 1 < 11 && m[px][py + 1] != 4) {
				if (m[px][py + 1] == 3) {
					m[px][py] = 0;
					validate--;
				} else {
					if (m[px][py + 1] == 2) {
						cg++;
						m[px][py + 1] = 1;
						m[px][py] = 0;
						py++;
						if (cg == 3) {
							validate++;
						}
					} else {
						m[px][py + 1] = 1;
						m[px][py] = 0;
						py++;
					}
				}
			}
		}

		if (opcion == 2) {
			if (py - 1 >= 0 && m[px][py - 1] != 4) {
				if (m[px][py - 1] == 3) {
					m[px][py] = 0;
					validate--;
				} else {
					if (m[px][py - 1] == 2) {
						cg++;
						m[px][py - 1] = 1;
						m[px][py] = 0;
						py--;
						if (cg == 3) {
							validate++;
						}
					} else {
						m[px][py - 1] = 1;
						m[px][py] = 0;
						py--;
					}
				}
			}
		}

		if (opcion == 3) {
			if (px + 1 < 11 && m[px + 1][py] != 4) {
				if (m[px + 1][py] == 3) {
					m[px][py] = 0;
					validate--;
				} else {
					if (m[px + 1][py] == 2) {
						cg++;
						m[px + 1][py] = 1;
						m[px][py] = 0;
						px++;
						if (cg == 5) {
							validate++;
						}
					} else {
						m[px + 1][py] = 1;
						m[px][py] = 0;
						px++;
					}
				}
			}
		}
		if (opcion == 4) {
			if (px - 1 >= 0 && m[px - 1][py] != 4) {
				if (m[px - 1][py] == 3) {
					m[px][py] = 0;
					validate--;
				} else {
					if (m[px - 1][py] == 2) {
						cg++;
						m[px - 1][py] = 1;
						m[px][py] = 0;
						px--;
						if (cg == 5) {
							validate++;
						}
					} else {
						m[px - 1][py] = 1;
						m[px][py] = 0;
						px--;
					}
				}
			}
		}

		if (opcion == 5) {
			if (px + 1 < 11 && m[px + 1][py + 1] != 4) {
				if (m[px + 1][py + 1] == 3) {
					m[px][py] = 0;
					validate--;
				} else {
					if (m[px + 1][py + 1] == 2) {
						cg++;
						m[px + 1][py + 1] = 1;
						m[px][py] = 0;
						px++;
						py++;
						if (cg == 5) {
							validate++;
						}
					} else {
						m[px + 1][py + 1] = 1;
						m[px][py] = 0;
						px++;
						py++;
					}
				}
			}
		}
		if (opcion == 6) {
			if (py - 1 >= 0 && m[px + 1][py - 1] != 4) {
				if (m[px + 1][py - 1] == 3) {
					m[px][py] = 0;
					validate--;
				} else {
					if (m[px + 1][py - 1] == 2) {
						cg++;
						m[px + 1][py - 1] = 1;
						m[px][py] = 0;
						py--;
						px++;
						if (cg == 5) {
							validate++;
						}
					} else {
						m[px + 1][py - 1] = 1;
						m[px][py] = 0;
						py--;
						px++;
					}
				}
			}
		}
		if (opcion == 7) {
			if (py + 1 < 11 && m[px - 1][py + 1] != 4) {
				if (m[px - 1][py + 1] == 3) {
					m[px][py] = 0;
					validate--;
				} else {
					if (m[px - 1][py + 1] == 2) {
						cg++;
						m[px - 1][py + 1] = 1;
						m[px][py] = 0;
						py++;
						px--;
						if (cg == 5) {
							validate++;
						}
					} else {
						m[px - 1][py + 1] = 1;
						m[px][py] = 0;
						py++;
						px--;
					}
				}
			}
		}
		if (opcion == 8) {
			if (py - 1 >= 0 && m[px - 1][py - 1] != 4) {
				if (m[px - 1][py - 1] == 3) {
					m[px][py] = 0;
					validate--;
				} else {
					if (m[px - 1][py - 1] == 2) {
						cg++;
						m[px - 1][py - 1] = 1;
						m[px][py] = 0;
						py--;
						px--;
						if (cg == 5) {
							validate++;
						}
					} else {
						m[px - 1][py - 1] = 1;
						m[px][py] = 0;
						py--;
						px--;
					}
				}
			}
		}
	}

	/// devuelve la localizacion del pacman en la matriz///////////////////////////////////////
	public int[] pacmanLocation() {
		int[] posiciones = { -1, -1 };
		for (int i = 0; i < 35; i++) {
			for (int j = 0; j < 27; j++) {
				if (m[i][j] == 1) {
					posiciones[0] = i;
					posiciones[1] = j;
				}
			}
		}
		return posiciones;
	}

	/// devuelve la localizacion del fantasma en la matriz///////////////////////////////////////
	public int[] ghostLocation() {
		int[] posiciones = { -1, -1 };
		for (int i = 0; i < 35; i++) {
			for (int j = 0; j < 27; j++) {
				if (m[i][j] == 3) {
					posiciones[0] = i;
					posiciones[1] = j;
				}
			}
		}
		return posiciones;
	}

	// direcciona movimiento positivo o negativo en Y y lo cancela si hay muros
	public void ghostMovementY(boolean movmentY) {
		int[] ghostLocation = ghostLocation();
		int[] pacmanLocation = pacmanLocation();
		if (movmentY == true) {
			if (ghostLocation()[0] > pacmanLocation()[0] && m[ghostLocation()[0] - 1][ghostLocation()[1]] != 4) {
				m[ghostLocation[0]][ghostLocation[1]] = 0;
				m[ghostLocation[0] - 1][ghostLocation[1]] = 3;
			} else {
				if (ghostLocation()[0] < pacmanLocation()[0] && m[ghostLocation()[0] + 1][ghostLocation()[1]] != 4) {
					m[ghostLocation[0]][ghostLocation[1]] = 0;
					m[ghostLocation[0] + 1][ghostLocation[1]] = 3;
				} else {
					if (ghostLocation()[0] == pacmanLocation()[0]
							&& m[ghostLocation()[0]][(ghostLocation()[1] + pacmanLocation()[1]) / 2] == 4) {
						movementWithAnalysisY(movementAnalysis());
					} else {
						movmentY = false;
						ghostMovementX(movmentY);
						movmentY = true;
					}
				}
			}
		}
	}
	// direcciona movimiento positivo o negativo en X y lo cancela si hay muros
	public void ghostMovementX(boolean movmentY) {
		int[] ghostLocation = ghostLocation();
		int[] pacmanLocation = pacmanLocation();
		if (movmentY == false) {
			if (ghostLocation()[1] > pacmanLocation()[1] && m[ghostLocation()[0]][ghostLocation()[1] - 1] != 4) {
				m[ghostLocation[0]][ghostLocation[1]] = 0;
				m[ghostLocation[0]][ghostLocation[1] - 1] = 3;
			} else {
				if (ghostLocation()[1] < pacmanLocation()[1] && m[ghostLocation()[0]][ghostLocation()[1] + 1] != 4) {
					m[ghostLocation[0]][ghostLocation[1]] = 0;
					m[ghostLocation[0]][ghostLocation[1] + 1] = 3;
				} else {
					if (ghostLocation()[1] == pacmanLocation()[1]
							&& m[(ghostLocation()[0] + pacmanLocation()[0]) / 2][ghostLocation()[1]] == 4) {
						movementWithAnalysisX(movementAnalysis());
					} else {
						movmentY = true;
						ghostMovementY(movmentY);
						movmentY = false;
					}
				}
			}
		}
	}

	public void ghostMovement(int turnos) {
		boolean movmentX = true;
		if (turnos % 2 != 0) {
			movmentX = false;
		}
		ghostMovementX(movmentX);
		ghostMovementY(movmentX);
	}

	public boolean pacmanVivo() {
		boolean vivo = false;
		for (int i = 0; i < 35; i++) {
			for (int j = 0; j < 27; j++) {
				if (m[i][j] == 1) {
					vivo = true;
				}
			}
		}
		return vivo;
	}

	public void movementWithAnalysisX(boolean right) {
		int[] ghostLocation = ghostLocation();
		m[ghostLocation[0]][ghostLocation[1]] = 0;
		if (right == true) {
			m[ghostLocation[0]][ghostLocation[1] + 1] = 3;
		} else {
			m[ghostLocation[0]][ghostLocation[1] - 1] = 3;
		}
	}

	public void movementWithAnalysisY(boolean above) {
		int[] ghostLocation = ghostLocation();
		m[ghostLocation[0]][ghostLocation[1]] = 0;
		if (above == true) {
			m[ghostLocation[0] - 1][ghostLocation[1]] = 3;
		} else {
			m[ghostLocation[0] + 1][ghostLocation[1]] = 3;
		}
	}

	public boolean movementAnalysis() {
		boolean basicMovement = false;
		movementAnalysisX();
		movementAnalysisY();
		if (movementAnalysisX() == 1 || movementAnalysisY() == 1) {
			basicMovement = true;
		}
		return basicMovement;
	}

	public int movementAnalysisY() {
		int n = 0;
		if (m[(ghostLocation()[0] + pacmanLocation()[0]) / 2][ghostLocation()[1]] == 4) {
			int j = 0;
			int cuadros;
			int obs = 0;
			int distancia3 = 2;
			int[] cuadrosV = { -1, -1 };
			for (int i = 1; i < 3; i++) {
				boolean basicMovment = true;
				if (i == 2) {
					basicMovment = false;
					distancia3 = -2;
				}
				cuadros = 0;
				if (m[(ghostLocation()[0] + pacmanLocation()[0]) / 2][ghostLocation()[1]] == 4) {
					while (m[(ghostLocation()[0] + pacmanLocation()[0]) / 2][ghostLocation()[1] + j] == 4) {
						if (basicMovment == true) {
							j++;
							cuadros++;
							obs = -1;
						} else {
							j--;
							cuadros--;
							obs = 1;
						}
					}
				}

				cuadrosV[i - 1] = Math
						.abs(cuadros + ((ghostLocation()[1] + cuadros) - pacmanLocation()[1]) + distancia3);
				if (m[pacmanLocation()[0]][ghostLocation()[1] + obs] == 4
						|| m[ghostLocation()[0]][ghostLocation()[1] + obs] == 4) {
					cuadrosV[i - 1] = 20;
				}
			}
			if (cuadrosV[1] > cuadrosV[0]) {
				n = 2;
			} else {
				n = 1;
			}
		}
		return n;
	}

	public int movementAnalysisX() {
		int n = 0;
		if (m[ghostLocation()[0]][(ghostLocation()[1] + pacmanLocation()[1]) / 2] == 4) {
			int j = 0;
			int cuadros = 0;
			int obs = 0;
			int distancia3 = 2;
			int[] cuadrosV = { -1, -1 };
			for (int i = 1; i < 3; i++) {
				boolean basicMovment = true;
				if (i == 2) {
					basicMovment = false;
					distancia3 = -2;
				}
				cuadros = 0;
				if (m[ghostLocation()[0]][(ghostLocation()[1] + pacmanLocation()[1]) / 2] == 4) {
					while (m[ghostLocation()[0] + j][(ghostLocation()[1] + pacmanLocation()[1]) / 2] == 4) {
						if (basicMovment == true) {
							j++;
							cuadros++;
							obs = -1;
						} else {
							j--;
							cuadros--;
							obs = 1;
						}
					}
				}
				cuadrosV[i - 1] = Math
						.abs(cuadros + ((ghostLocation()[0] + cuadros) - pacmanLocation()[0]) + distancia3);
				if (m[ghostLocation()[0] + obs][pacmanLocation()[1]] == 4
						|| m[ghostLocation()[0] + obs][ghostLocation()[1]] == 4) {
					cuadrosV[i - 1] = 20;
				}
			}
			if (cuadrosV[1] > cuadrosV[0]) {
				n = 2;
			} else {
				n = 1;
			}
		}
		return n;
	}
	//retorna el numero de galletas encontradas en la matriz 
	public boolean galletas() {
		boolean galletas = true;
		int galletasEncontradas = 0;
		for (int i = 0; i < 35; i++) {
			for (int j = 0; j < 27; j++) {
				if (m[i][j] == 2) {
//						galletas=true;
					galletasEncontradas++;
				}
			}
		}
		if (galletasEncontradas == 0) {
			galletas = false;
		}
		return galletas;
	}
	//coloca espacios en blanco y galletas en la matriz  
	public int[][] galletaSave() {
		int[][] m2 = new int[36][28];
		for (i = 0; i <= 35; i++) {
			for (j = 0; j <= 27; j++) {
				m2[i][j] = 0;
			}
		}
		for (int i = 0; i < 35; i++) {
			for (int j = 0; j < 27; j++) {
				if (m[i][j] == 2) {
					m2[i][j] = 2;
				}
			}
		}
		return m2;
	}
	//desaparece la galleta cunado pacman pasa sobre ella
	public void renovarGalletasPacman(int[][] m2) {
//			int[][] m2 = galletaSave();
		for (int i = 0; i < 35; i++) {
			for (int j = 0; j < 27; j++) {
				if (m2[i][j] == 2) {
					if (m[i][j] == 1) {
						m2[i][j] = 0;
					}
				}
			}
		}
	}
	//guarda la galleta cuando un fantasma pasa por encima 
	public void galletaTapada(int[][] m2, int[] vg) {
//			int[] posgalleta = {-1,-1,-1};
//			boolean marca= false;
		for (int i = 0; i < 35; i++) {
			for (int j = 0; j < 27; j++) {
				if (m2[i][j] == 2) {
					if (m[i][j] == 3) {
						vg[0] = i;
						vg[1] = j;
						vg[2] = 1;
					}
				}
			}
		}
	}
	//reincorpora las galletas tapadas
	public void reconocerMarca(int[][] m2, int[] vg) {
		int[] m3 = vg;
		if (m3[2] == 1) {
			for (int i = 0; i < 35; i++) {
				for (int j = 0; j < 27; j++) {
					if (m2[i][j] == 2) {
						if (i == m3[0] && j == m3[1]) {
							if (m[i][j] != 3) {
								m[i][j] = 2;
							}
						}
					}
				}
			}
		}
		vg[2] = 0;
	}
	
	public int stringAInt(String numeroSt) {
		int numero = 0;
		int mod = 1;
		for (int i = numeroSt.length(); i > 0; i--) {
			numero = numero + ((numeroSt.charAt(i - 1) - 48) * mod);
			mod = mod * 10;
		}
		return numero;
	}

}
	/////////////////////////////////////