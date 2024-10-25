package model.entity;

import java.awt.Color;
import java.util.Random;

import controller.Control;
import model.dao.PacmanManager;
import view.PuckmanGui;

public class Ghost extends Character{
	protected MyRectangle2D target;
//	private Wall [] setWalls;
	protected Mode mode;
	protected Color color;
	private int movementOptions;
	Random random;
	int randomNumber;
	int countMovementsAlowed;
	int changeMovement;
//	int cont =0;
	boolean circleWall;
//	int num =0;
	int secundaryMotionSaved;
	public int timer;
//	int timer2=0;
	protected MyRectangle2D viewingRange;
	int normalSpeed; 
	int savedSpeed;
	int escapeSpeed;
	int returnSpeed;
	MyRectangle2D pacmanInitialPosition;
	protected Color actualColor;
	protected int inc ;
	protected int increaseX;
	protected int increaseY;
	protected boolean labelScore;
	protected int TimerLabelScore;
	protected Coord2D labelScoredCoordinates;
	protected int ghostScore;
	protected Coord2D initialPosition;
	
	protected Ghost() {
		super();
		TimerLabelScore = 0;
//		labelScore = false;
		above = 87;
		dawn = 83;
		left = 65;
		right = 68;
		target = PuckmanGui.getVirtualPacman();
		timer = 0;
//		pacmanVirtualfig = null;
//		setWalls = MapGui.getWalls();
		mode = mode.CHASE;
		this.color = color;
		secundaryMotionSaved = 0;
		circleWall = false;
		changeMovement =0;
		movementOptions = 7;
		random = new Random();
		this.direction = Math.toRadians(270);
		returnSpeed = (int)(this.referenceSpeed*3);
		normalSpeed = (int)(this.referenceSpeed*0.975);
		escapeSpeed = (int)(this.referenceSpeed*0.2);
//		savedSpeed= 1;
		savedSpeed= normalSpeed;
		this.speed = this.savedSpeed;
		this.coord2d = new Coord2D(this.coord2d.getX(), this.coord2d.getY()-8*18);
//		this.secundaryVirtualFigure = new MyRectangle2D(this.secundaryVirtualFigure.getX()-3,
//				this.secundaryVirtualFigure.getY()-3, this.secundaryVirtualFigure.getWidth()+6,
//				this.secundaryVirtualFigure.getHeight()+6);
		this.viewingRange = new MyRectangle2D(this.coord2d.getX()-16*5, this.coord2d.getY()-16*5,
				this.size+16*10, this.size+16*10);
		this.pacmanInitialPosition = new MyRectangle2D(8*27+1, 8*52-8*18, 31, 31);
	}
	protected void timerConditions() {
		if(mode.equals(mode.ESCAPE) || mode.equals(mode.ESCAPE_ENDING)) {
			timer+=4;
			if(timer == 60000 || timer == 65000 || timer == 70000 
					|| timer == 75000 || timer == 80000) {
				setMode(mode.ESCAPE_ENDING);
			}
			if(timer == 62500 || timer == 67500 || timer == 72500 
					|| timer == 77500 ) {
				setMode(mode.ESCAPE);
			}
			if(timer >= 82500) {
				timer = 0;
				PacmanManager.ghostScore=200;
				setMode(mode.CHASE);
			}
//			if(timer >= 82500) {
//				timer = 0;
//				Control.ghostScore=200;
//				setMode(mode.CHASE);
//			}
//			System.out.println(timer/10000);
		}
		if(mode.equals(mode.RETURN) ) {
			timer = 0;
		}
		if (mode.equals(mode.STALK)) {
			timer+=4;
//			System.out.println(timer);
			if(timer>=4000) {
				timer=0;
				setMode(mode.CHASE);
			}
		}
//		setMode(escapeTimer==60000 || escapeTimer==65000 || escapeTimer==70000 
//				|| escapeTimer==75000 || escapeTimer==80000 ?mode.ESCAPE_ENDING:
//				escapeTimer==62500 || escapeTimer==67500 || escapeTimer==72500 
//				|| escapeTimer==77500 ?mode.ESCAPE:getMode() );
	}
	protected void saveSecundaryMotion() {
		if(secundaryMotionSaved==68) {//derecha
			circleWall = movementAllowed(1, 0)?false:true;				
		}
		if(secundaryMotionSaved==65) {//izquierda
			circleWall = movementAllowed(-1, 0)?false:true;				
		}
		if(secundaryMotionSaved==87) {//arriba
			circleWall = movementAllowed(0, -1)?false:true;				
		}
		if(secundaryMotionSaved==83) {//abajo
			circleWall = movementAllowed(0, 1)?false:true;				
		}
		if(!circleWall) {
			setMotionSaved(secundaryMotionSaved);
//				}
		}
	}
//	private void circleWallChase(MyRectangle2D PacmanVirtualfig, Wall[] setWalls) {
//		
//		if(this.direction==Math.toRadians(0)) {
//			setMotionSaved(calculateVerticalDistance(PacmanVirtualfig, setWalls));//arriba 
//			secundaryMotionSaved = 68;//derecha
//		}
//		if(this.direction==Math.toRadians(180)) {
//			setMotionSaved(calculateVerticalDistance(PacmanVirtualfig, setWalls));//arriba 
//			secundaryMotionSaved = 65;//izquierda
//		}
//		if(this.direction==Math.toRadians(270)) {
////			setMotionSaved(68);//derecha
//			setMotionSaved(calculateHorizontalDistance(PacmanVirtualfig, setWalls));//derecha
//			secundaryMotionSaved = 87;//arriba
//		}
//		if(this.direction==Math.toRadians(90)) {
//			setMotionSaved(calculateHorizontalDistance(PacmanVirtualfig, setWalls));//derecha
//			secundaryMotionSaved = 83;//abajo
//		}
//		this.speed = savedSpeed;
//		circleWall = true;
//	}
	protected void circleWall() {
		if(this.direction==Math.toRadians(0)) {
			setMotionSaved(calculateVerticalDistance());//arriba 
			secundaryMotionSaved = 68;//derecha
		}
		if(this.direction==Math.toRadians(180)) {
			setMotionSaved(calculateVerticalDistance());//arriba 
//			setMotionSaved(87);//arriba 
			secundaryMotionSaved = 65;//izquierda
		}
		if(this.direction==Math.toRadians(270)) {
//			setMotionSaved(68);//derecha
			setMotionSaved(calculateHorizontalDistance());//derecha
			secundaryMotionSaved = 87;//arriba
		}
		if(this.direction==Math.toRadians(90)) {
			setMotionSaved(calculateHorizontalDistance());//derecha
			secundaryMotionSaved = 83;//abajo
		}
		this.speed = savedSpeed;
		circleWall = true;
	}
	private boolean virtualMovementAllowed(int increaseX,int increaseY,MyRectangle2D target) {
		MyRectangle2D newTarget = new MyRectangle2D(target.getX()+(2*increaseX),
				target.getY()+(2*increaseY), size, size); 
		for (int i = 0; i < setWalls.length; i++) {
			if(setWalls[i].getVirtualFigure().intersection(newTarget)){
				return false;
			}
		}
		return true;
	}
	//////
	public int calculateVerticalDistance() {
		MyRectangle2D newTarget;
		int i = 0;
		int distance1=0;
		int distance2 =0;
		newTarget = new MyRectangle2D(this.coord2d.getX(),
				this.coord2d.getY(), size, size);
		for (int j = 0; j < 2; j++) {
			while (!virtualMovementAllowed(this.direction == Math.toRadians(0) ?1:-1, 0, newTarget)
//			while (!virtualMovementAllowed(setWalls, this.direction == Math.toRadians(0) && escapeMode== false?1:-1, 0, newVirtualFigure)
//			while (!virtualMovementAllowed(setWalls,1, 0, newVirtualFigure)
					) {
				i=!virtualMovementAllowed(0, 0, newTarget)?i+10000:i;
				i++;
				newTarget.setY(j==0?this.coord2d.getY()-i:this.coord2d.getY()+i);
			}
			distance1 = j==0?i:distance1;
			distance2 = j==1?i:distance2; 
			i=0;
			newTarget.setY(this.coord2d.getY());
		}
//		System.out.println(distance1+"----"+distance2 );
//		return distance1<distance2?87:83;
		return distance1>=10000 && distance2>=10000? distance1>distance2?87:83 : distance1<distance2?87:83;
	}
	public int calculateHorizontalDistance() {
		MyRectangle2D newtarget;
		int i = 0;
		int distance1=0;
		int distance2 =0;
//		if(this.direction == Math.toRadians(90) || this.direction == Math.toRadians(270)) {
		newtarget = new MyRectangle2D(this.coord2d.getX(),
				this.coord2d.getY(), size, size);
		for (int j = 0; j < 2; j++) {
			while (!virtualMovementAllowed(0, this.direction == Math.toRadians(90)?1:-1, newtarget)
//			while (!virtualMovementAllowed(setWalls, 0, this.direction == Math.toRadians(90) && escapeMode == false ?1:-1, newVirtualFigure)
//			while (!virtualMovementAllowed(setWalls, 0, 1, newVirtualFigure)
					) {
				i=!virtualMovementAllowed(0, 0, newtarget)?i+10000:i;
				i++;
				newtarget.setX(j==0?this.coord2d.getX()-i:this.coord2d.getX()+i);
			}
			distance1 = j==0?i:distance1;
			distance2 = j==1?i:distance2; 
			i=0;
			newtarget.setX(this.coord2d.getX());
		}
//		System.out.println(distance1+"----"+distance2 );
//		return distance1>=10000 && distance2>=10000? distance1<distance2?65:68 :-1;
//		return distance1<distance2?65:68 ;
		return distance1>=10000 && distance2>=10000? distance1>distance2?65:68 : distance1<distance2?65:68;
	}	
	protected void erraticMovement(Wall[] setWalls) {
		changeMovement = amountMovements(setWalls);
		if(changeMovement!=movementOptions) {
//			cont++;s
			movementOptions=changeMovement;
//			System.out.println(cont);
			randomNumber = 1+random.nextInt(3);
			if(this.speed==0 || !movementAllowed(0, -1) ) {//arriba
				if(randomNumber==1 && this.direction!=Math.toRadians(0)) {
					setMotionSaved(65);//izquierda						
				}
				if(randomNumber==2 && this.direction!=Math.toRadians(180) ) {
					setMotionSaved(68);//derechad						
				}
				if(randomNumber>2 && this.direction!=Math.toRadians(90)) {
					setMotionSaved(87);//arriba
				}
			}
			if(this.speed==0 || !movementAllowed(1, 0)) {//derecha
				if(randomNumber==1 && this.direction!=Math.toRadians(90)) {
					setMotionSaved(87);//arriba
				}
				if(randomNumber==2 && this.direction!=Math.toRadians(270)) {
					setMotionSaved(83);//abajo
				}
				if(randomNumber>2 && this.direction!=Math.toRadians(0)) {
					
					setMotionSaved(65);//izquierda						
				}
			}
			if(this.speed==0 || !movementAllowed(-1, 0)) {//izquierda
				if(randomNumber==1 && this.direction!=Math.toRadians(90)) {
					setMotionSaved(87);//arriba
				}
				if(randomNumber==2 && this.direction!=Math.toRadians(270)) {
					setMotionSaved(83);//abajo
				}
				if(randomNumber>2 && this.direction!=Math.toRadians(180) ) {
					setMotionSaved(68);//derechad						
				}
			}
			if(this.speed==0 || !movementAllowed(0, 1)) {//abajo
				if(randomNumber==1 && this.direction!=Math.toRadians(0)) {
					setMotionSaved(65);//izquierda						
				}
				if(randomNumber==2 && this.direction!=Math.toRadians(180) ) {
					setMotionSaved(68);//derechad						
				}
				if(randomNumber>2 && this.direction!=Math.toRadians(90)) {
					setMotionSaved(87);//arriba
				}
			}
		}
//		else
//		cont=0;
	}
	private int amountMovements(Wall [] setWalls) {
		countMovementsAlowed = 0;
		if(movementAllowed(1, 0)) {
			countMovementsAlowed++;
		}
		if(movementAllowed(-1, 0)) {
			countMovementsAlowed = countMovementsAlowed+2;
		}
		if(movementAllowed(0, 1)) {
			countMovementsAlowed = countMovementsAlowed+3;
		}
		if(movementAllowed(0, -1)) {
			countMovementsAlowed = countMovementsAlowed+4;
		}
//		System.out.println(countMovementsAlowed);
		return countMovementsAlowed;
	}
	public MyRectangle2D getViewingRange() {
		return viewingRange;
	}
	public int getNormalSpeed() {
		return normalSpeed;
	}
	public int getSavedSpeed() {
		return savedSpeed;
	}
	public void setSavedSpeed(int vel) {
		this.savedSpeed = vel;
	}
//	public boolean isEscapeMode() {
//		return escapeMode;
//	}
//	public void setEscapeMode(boolean escapeMode) {
//		this.escapeMode = escapeMode;
//	}
	protected void executeSaveMotion2(Wall[] setWalls,int above,int dawn,int left,int right) {////////////////
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
	public void relocate() {
		if(selectedWall!=null) {
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
//	public int getState() {
//		return state;
//	}
//	public void setState(int state) {
//////		this.speed = this.returnSpeed;
//		if(state == 3) {
//			this.returnMode = true;
////			this.escapeMode = false;
//			this.savedSpeed = this.returnSpeed;			
//		}
//		if(state == 1) {
//			this.escapeMode = true ;
//			this.savedSpeed = escapeSpeed;
//		}
//		this.state = state;
//		
////		this.escapeMode = false;
////		ghost_1.setEscapeMode(false);
////		ghost_1.setReturnMode(true);
////		ghost_1.setCurrentSpeed(ghost_1.getReturnSpeed());
//	}
	public int getEscapeSpeed() {
		return escapeSpeed;
	}
//	public boolean isReturnMode() {
//		return returnMode;
//	}
//	public void setReturnMode(boolean returnMode) {
//		this.returnMode = returnMode;
//	}
	public int getReturnSpeed() {
		return returnSpeed;
	}
//	public void setReturnSpeed(int returnSpeed) {
//		this.returnSpeed = returnSpeed;
//	}
	public MyRectangle2D getPacmanInitialPosition() {
		return pacmanInitialPosition;
	}
//	public void setInitialPosition(MyRectangle2D initialPosition) {
//		this.initialPosition = initialPosition;
//	}
	public Mode getMode() {
		return mode;
	}
	public void setMode(Mode mode) {
		this.speed=0;
//		this.timer= mode.equals(mode.ESCAPE)?0:this.timer;
//		this.savedSpeed=1;
//		System.out.println(mode);
		this.savedSpeed=mode.equals(mode.ESCAPE) || mode.equals(mode.ESCAPE_ENDING)?escapeSpeed:
			mode.equals(mode.RETURN)?returnSpeed:normalSpeed;
//		this.currentSpeed = mode.equals(mode.CHASE)?escapeSpeed :
//			mode.equals(mode.ESCAPE) || mode.equals(mode.ESCAPE_ENDING)?escapeSpeed:
//			returnSpeed;
		this.mode = mode;
	}
	public void setTimer(int timer) {
		this.timer = timer;
	}

	public int getTimer() {
		return timer;
	}

	public boolean isLabelScore() {
		return labelScore;
	}

	public void setLabelScore(boolean labelScore) {
		this.labelScore = labelScore;
	}

	public int getTimerLabelScore() {
		return TimerLabelScore;
	}

	public void setTimerLabelScore(int timerLabelScore) {
		TimerLabelScore = timerLabelScore;
	}
	public void setInitialPosition(Coord2D initialPosition) {
		this.initialPosition = initialPosition;
	}
	public Coord2D getInitialPosition() {
		return initialPosition;
	}

	
	
//	setMotionSaved(68);//derechad						
//	setMotionSaved(65);//izquierda		
//	setMotionSaved(87);//arriba
//	setMotionSaved(83);//abajo
}