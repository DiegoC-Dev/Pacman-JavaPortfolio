package model.entity;

public class Dimensions {
	private int left=0; 
	private int superior=0; 
	private int right=0;
	private int bottom=0;
	
	public Dimensions(int left, int superior, int right, int bottom) {
		super();
		this.left = left;
		this.superior = superior;
		this.right = right;
		this.bottom = bottom;
	}

	public int getLeft() {
		return left;
	}

	public int getSuperior() {
		return superior;
	}

	public int getRight() {
		return right;
	}

	public int getBottom() {
		return bottom;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public void setSuperior(int superior) {
		this.superior = superior;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public void setBottom(int bottom) {
		this.bottom = bottom;
	} 
	
	
	
}
