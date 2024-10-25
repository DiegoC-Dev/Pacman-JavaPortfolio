package model.entity;

public class Fruit extends Food{
	protected int timer;
	protected boolean visibility;
	public boolean labelScore;
	protected int timerLabelScore;
	public  Coord2D labelScoredCoordinates;
	protected int score;
	public Fruit() {
		score= 100;
		timerLabelScore = 0;
		labelScore= false;
		this.visibility = false;
		this.timer = 0;
		this.coord2d = new Coord2D(-10, -10);
//		this.coord2d = new Coord2D(20, 20);
		this.size = 12;
		this.virtualFigure = new MyRectangle2D(getCoord2d().getX(), getCoord2d().getY(), this.size, this.size);
	}
	public void activateTimer() {
		if(visibility==true) {
			timer++;
//			System.out.println(timer);
			if(timer==40000) {
				setVisibility(false);
			}
		}
		else {
			timer=0;
		}
	}
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
		labelScoredCoordinates = new Coord2D(coord2d.getX(), coord2d.getY());
		this.coord2d = visibility?new Coord2D(217+9, 416+9):new Coord2D(-10, -10);
//		this.coord2d = visibility?new Coord2D(217+9, 416+9):new Coord2D(20, 20);
		this.virtualFigure = new MyRectangle2D(getCoord2d().getX(), getCoord2d().getY(), this.size, this.size);
	}
	public boolean isLabelScore() {
		return labelScore;
	}
	public void setLabelScore(boolean labelScore) {
		this.labelScore = labelScore;
	}
	public void scoreFruit() {
		if(labelScore == true) {
			timerLabelScore++;
			if(timerLabelScore>4000) {
				labelScore= false;
				timerLabelScore = 0;
			}
		}
	}
	public int getScore() {
		return score;
	}
//	public void setScore(int score) {
//		this.score = score;
//	}
	
}
