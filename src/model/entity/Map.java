package model.entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Map {
	protected boolean victory;
	protected int cycle;
	protected int timer;
	protected boolean endLevel;

	
	protected static final int WIDTH = 28;
	protected static final int HEIGHT = 36;
	protected static final int SQUARE = 8;
//	private static final String PATH = "src/tableroPacVacio3.txt";
	private static final String PATH = "resources/tableroPacVacio3.txt";
	protected int[][] matrix = new int[HEIGHT][WIDTH];
	
	protected Wall []setWalls;
	private int numberWalls;
	
	protected Map() {
		numberWalls = 0;
		importMap();
		setWalls = new Wall[numberWalls];
		createWalls();
	}
	
	public void importMap(){
//		int[][] m = new int[36][28];
		int[][] m = new int[72][56];
		File file;
		FileReader fileReader = null;
		BufferedReader bufferedReader;
		try {
			file = new File (PATH);
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			int row =0;
			int column =0;
//			3107+923 = 4032
			String linea;
			while ((linea = bufferedReader.readLine())!=null) {
				for (int i = 0; i < m[0].length; i++) {
					m[row][column]=linea.charAt(column)-48;
					numberWalls=m[row][column]==4?numberWalls+1:numberWalls;
					column++;					
				}
				column=0;
				row++;
			}
			this.matrix = m;
//			map.setMatrix(m);
//			pc.setM(m);
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			try{                    
				if( null != fileReader ){   
					fileReader.close();     
				}                  
			}catch (Exception e2){ 
				e2.printStackTrace();
			}
		}	
	}

	public int[][] getMatrix() {
		return matrix;
	}
	
	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}
	private void createWalls() {
		int numberWalls=0;
//		int[][] m = pc.getM();
		int[][] m = matrix;
		int squre=8; 
		int x= squre;
		int y = squre;
		int cont=0;
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				if(m[i][j]==4) {
					setWalls[numberWalls++]=new Wall(new Coord2D(x,y), squre, squre);
				}
//				if(m[i][j]==0) {
//					cont++;
//				}
//				System.out.println("num "+cont);
				x+=squre;
			}
			y+=squre;
			x=squre;
		}
//		System.out.println("control - leng "+numberWalls);
//		System.out.println("control - "+setWalls[0].getCoord2d().getY());
	}

	public Wall[] getSetWalls() {
		return setWalls;
	}

//	public void setSetWalls(Wall[] setWalls) {
//		this.setWalls = setWalls;
//	}
	public void setVictory(boolean victory) {
		this.victory = victory;
	}
	public boolean isEndLevel() {
		return endLevel;
	}
	public void setEndLevel(boolean endLevel) {
		this.endLevel = endLevel;
	}
}
