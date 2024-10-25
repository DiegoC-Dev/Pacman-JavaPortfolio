package model.entity;

public class Puckman extends Character{
	int savedSpeed;
	protected int deathTimer;
	protected int cycle;
	protected int lives;
	private boolean animationFinished;

//	Wall []setWalls; 
//	double time;
//	public static MyRectangle2D PacmanVirtualfig = new MyRectangle2D(x, y, width, height); 
	protected Puckman() {
		super();
		animationFinished = false;
		lives = 3;
//		setWalls=MapGui.getWalls();
		savedSpeed =(int)(this.referenceSpeed);
		speed = savedSpeed;
//		time = (double)deltaTime/100000;
		above = 38;
		dawn = 40;
		left = 37;
		right = 39;
		this.coord2d = new Coord2D(217, 416);
		deathTimer = 0;
		cycle = 0;
//		this.coord2d = new Coord2D(this.coord2d.getX(), this.coord2d.getY()+8);
//		this.secundaryVirtualFigure = new MyRectangle2D(coord2d.getX()+size*5/10, coord2d.getY()+size*5/10,
//				size-size*10/10, size-size*10/10); 
		this.secundaryVirtualFigure = new MyRectangle2D(coord2d.getX()+size*4.5/10, coord2d.getY()+size*4.5/10,
				size-size*9/10, size-size*9/10); 
		this.direction = Math.toRadians(0);	
		selectedWall=null;
	}
	public void move() {
		moveTimer++;
		if(moveTimer>900) {
			moveTimer=1;
		}
		if(speed==0) {
			moveTimer--;
		}
//		System.out.println("velocidad pacman "+speed);
//	public void move(double time) {
//	public void move(double time,Wall[] setWalls,int above,int dawn,int left,int right) {
		selectedWall=wallIntersection();
		this.speed = space.outOfRange(coord2d, size)?0:this.speed;
		this.speed = movementAllowed(0, 0)?this.speed:0;
		executeSaveMotion();
		coord2d.moveFigure(this.speed*time, this.direction);
		updateVirtualFigures();
		if(this.speed==0) {
			relocate();
		}
	}
	public void deathTimer() {
		deathTimer++;
		if(deathTimer>1000000) {
			deathTimer=1;
		}
	}
	public void updateVirtualFigures(){
		this.virtualFigure = new MyRectangle2D(this.coord2d.getX(), this.coord2d.getY(),
				size, size);
		this.secundaryVirtualFigure = new MyRectangle2D(coord2d.getX()+size*4.5/10, coord2d.getY()+size*4.5/10,
				size-size*9/10, size-size*9/10); 

//		this.secundaryVirtualFigure = new MyRectangle2D(coord2d.getX()+size*5/10, coord2d.getY()+size*5/10,
//				size-size*10/10, size-size*10/10); 
//		this.secundaryVirtualFigure = new MyRectangle2D(this.virtualFigure.getX()+size*4/10, this.virtualFigure.getY()+size*4/10,
//				size-(size*8/10), size-(size*8/10)); 
	}
	public void relocate() {
		if(selectedWall!=null) {
//			System.out.println("relocalizo");
			if(this.direction==Math.toRadians(90) ) {
				setCoord2d(new Coord2D(this.coord2d.getX(), selectedWall.getCoord2d().getY()-this.size));//arriba
			}
			if(this.direction==Math.toRadians(270)) {
				setCoord2d(new Coord2D(this.coord2d.getX(), selectedWall.getCoord2d().getY()+selectedWall.getHeigth()));//abajo
			}
			if(this.direction==Math.toRadians(180)) {
				setCoord2d(new Coord2D(selectedWall.getCoord2d().getX()+selectedWall.getWidth(), this.coord2d.getY()));//derecha
			}
			if(this.direction==Math.toRadians(0)) {
				setCoord2d(new Coord2D(selectedWall.getCoord2d().getX()-this.size, this.coord2d.getY()));//izquierda
			}
		}
		else {
			if(coord2d.getY()>space.getY()+space.getHeight()-(size)) {
				coord2d.setY(space.getY());//teletransporte
				setDirection(Math.toRadians(90));
				setSpeed(savedSpeed);
			}
			if(coord2d.getY()<space.y) {
				coord2d.setY(space.getY()+space.getHeight()-(size));//teletransporte
				setDirection(Math.toRadians(270));
				setSpeed(savedSpeed);
			}
			if(coord2d.getX()>space.getX()+space.getWidth()-(size)) {
				coord2d.setX(space.getX());//teletransporte
				setDirection(Math.toRadians(180));
				setSpeed(savedSpeed);
			}
			if(coord2d.getX()<space.getX()) {
				coord2d.setX(space.getX()+space.getWidth()-(size));//teletransporte
				setDirection(Math.toRadians(0));
				setSpeed(savedSpeed);
			}
		}
	}
	
	protected void executeSaveMotion() {////////////////
		if (motionSaved == above && movementAllowed(0,-1)) {
			setDirection(Math.toRadians(270));//arriba
		}
		if (motionSaved == left && movementAllowed(-1,0)) {
			setDirection(Math.toRadians(180));//izquierda
		}
		if (motionSaved == dawn && movementAllowed(0,1)) {
			setDirection(Math.toRadians(90));//abajo
		}
		if (motionSaved == right && movementAllowed(1,0)) {
			setDirection(Math.toRadians(0));//derecha
		}
	}
	public int getSavedSpeed() {
		return savedSpeed;
	}	
//	@Override
	protected MyRectangle2D getVirtualFigure() {
		return super.getVirtualFigure();
	}
	public void setDeathTimer(int deathTimer) {
		this.deathTimer = deathTimer;
	}
	public int getCycle() {
		return cycle;
	}
	public void setCycle(int cycle) {
		this.cycle = cycle;
	}
	public int getLives() {
		return lives;
	}
	public void setLives(int lives) {
		this.lives = lives;
	}
	
	public boolean isAnimationFinished() {
		return animationFinished;
	}
	public void setAnimationFinished(boolean animationFinished) {
		this.animationFinished = animationFinished;
	}	
//	public MyRectangle2D getSecundaryVirtualFigure() {
//		return getSecundaryVirtualFigure();
//	}
//	public static PuckmanGui getInstance(){
//		if(instance==null) {
//			instance=new PuckmanGui();
//			System.out.println("se ha creado a pacman");
//		}
//		else
//		System.out.println("pacman ya ha sido creado");
//		return instance;
//	}
//	public void setVel(int vel) {
//		this.vel = vel;
//	}
}