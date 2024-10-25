package model.entity;

import view.BlinkyGui;
import view.PuckmanGui;

public class Inky extends Ghost{
	protected MyRectangle2D virtualPacman;
	protected MyRectangle2D virtualBlinky;
	protected double pacmanDirection;
//	protected double blinkyDirection;
	protected MyRectangle2D pacmanTarget;
	public Inky() {
		this.color = color.cyan;
		this.coord2d = new Coord2D(this.coord2d.getX()-16*2, this.coord2d.getY());
//		mode = mode.ESCAPE_ENDING;

	}
	
	public void move() {
		moveTimer++;
		if(moveTimer>900) {
			moveTimer=1;
		}
		pacmanDirection = PuckmanGui.getPacmanDirection();
		virtualPacman = PuckmanGui.getVirtualPacman(); 
//		blinkyDirection = BlinkyGui.getBlinkyDirection();//tengo que crear a blinky
		virtualBlinky = BlinkyGui.getvirtualBlinky();
		pacmanTarget = new MyRectangle2D(pacmanDirection==0?virtualPacman.getX()+16*2: 
			pacmanDirection==180?virtualPacman.getX()-16*2:virtualPacman.getX(),
					pacmanDirection==90?virtualPacman.getY()+16*2:
						pacmanDirection==270?virtualPacman.getY()-16*2:virtualPacman.getY(), 
								virtualPacman.getWidth(), virtualPacman.getHeight());
		target = new MyRectangle2D(pacmanTarget.getX()+(pacmanTarget.getX()-virtualBlinky.getX()),
				pacmanTarget.getY()+(pacmanTarget.getY()-virtualBlinky.getY()), 
				virtualPacman.getWidth(), virtualPacman.getHeight());
//		target = pacmanTarget; 
		timerConditions();
		if(mode.equals(mode.RETURN)) {		
			this.viewingRange = new MyRectangle2D(this.coord2d.getX()-16*12, this.coord2d.getY()-16*15,
					this.size+16*30, this.size+16*30);
			target = pacmanInitialPosition;
		}
//		randomNumber = 1+random.nextInt(100);//seguimiento de entre 10 y 3 segundos
		randomNumber = 1;//seguimiento de entre 10 y 3 segundos
		if(target.intersection(getViewingRange())) {
			if(secundaryVirtualFigure.intersection(target) && mode.equals(mode.CHASE)){
				setMode(mode.STALK);
			}
			if(randomNumber==1) {
				directedMovement(target);
			}
			else {
				randomMovement();
			}
		}
		else {
			randomMovement();

		}
	}
	public void directedMovement(MyRectangle2D Target) {
		if(circleWall) {
			saveSecundaryMotion();
		}
		else {
			if(mode.equals(mode.CHASE) || mode.equals(mode.RETURN)) {
				attackMovement(Target);				
			}
			else if(mode.equals(mode.STALK)) {
				randomMovement();
			}
			else {
				escapeMovement(Target);
			}
		}//or
		selectedWall=wallIntersection();
//		this.speed = space.outOfRange(coord2d, size)?0:this.speed;
		this.speed = movementAllowed(0, 0)?this.speed:0;
		executeSaveMotion();
		coord2d.moveFigure(this.speed*this.time, this.direction);
		updateVirtualFigures();
		if(this.speed==0) {
			relocate();
			circleWall();///aca activo rodear muro				
			if(this.speed==0) {
//				this.speed = savedSpeed;
//				this.direction = this.direction+Math.toRadians(180);
				System.out.println("caso raro de bug inky");
//				System.exit(0);
			}
		}
	}
	
	private void attackMovement(MyRectangle2D PacmanVirtualfig) {
		MyRectangle2D newVirtualFigure = new MyRectangle2D(this.secundaryVirtualFigure.getX(),PacmanVirtualfig.getY(),
				this.secundaryVirtualFigure.getWidth(), this.secundaryVirtualFigure.getHeight());
		if(newVirtualFigure.getY()+newVirtualFigure.getHeight()/2>
		this.virtualFigure.getY()+this.virtualFigure.getHeight()/2 
		&& newVirtualFigure.intersection(PacmanVirtualfig)
				) {
			setMotionSaved(83);//abajo --------
		}
		if(newVirtualFigure.getY()+newVirtualFigure.getHeight()/2<
				this.virtualFigure.getY()+this.virtualFigure.getHeight()/2 
				&& newVirtualFigure.intersection(PacmanVirtualfig)
				) {
			setMotionSaved(87);//arriba -------
		}
		newVirtualFigure = new MyRectangle2D(PacmanVirtualfig.getX(),this.secundaryVirtualFigure.getY(),
				this.secundaryVirtualFigure.getWidth(), this.secundaryVirtualFigure.getHeight());
		if(newVirtualFigure.getX()+newVirtualFigure.getWidth()/2>
		this.virtualFigure.getX()+this.virtualFigure.getWidth()/2 
		&& newVirtualFigure.intersection(PacmanVirtualfig)
				) {
			setMotionSaved(68);//derechad						
		}
		if(newVirtualFigure.getX()+newVirtualFigure.getWidth()/2<
				this.virtualFigure.getX()+this.virtualFigure.getWidth()/2 
				&& newVirtualFigure.intersection(PacmanVirtualfig)
				) {
			setMotionSaved(65);//izquierda
		}
	}
	private void escapeMovement(MyRectangle2D PacmanVirtualfig) {
		MyRectangle2D newVirtualFigure = new MyRectangle2D(this.secundaryVirtualFigure.getX(),PacmanVirtualfig.getY(),
				this.secundaryVirtualFigure.getWidth(), this.secundaryVirtualFigure.getHeight());
		if(newVirtualFigure.getY()+newVirtualFigure.getHeight()/2>
		this.virtualFigure.getY()+this.virtualFigure.getHeight()/2 
		&& newVirtualFigure.intersection(PacmanVirtualfig)
				) {
			setMotionSaved(87);//arriba 
		}
		if(newVirtualFigure.getY()+newVirtualFigure.getHeight()/2<
				this.virtualFigure.getY()+this.virtualFigure.getHeight()/2 
				&& newVirtualFigure.intersection(PacmanVirtualfig)
				) {
			setMotionSaved(83);//abajo
		}
		newVirtualFigure = new MyRectangle2D(PacmanVirtualfig.getX(),this.secundaryVirtualFigure.getY(),
				this.secundaryVirtualFigure.getWidth(), this.secundaryVirtualFigure.getHeight());
		if(newVirtualFigure.getX()+newVirtualFigure.getWidth()/2>
		this.virtualFigure.getX()+this.virtualFigure.getWidth()/2 
		&& newVirtualFigure.intersection(PacmanVirtualfig)
				) {
			setMotionSaved(65);//izquierda
		}
		if(newVirtualFigure.getX()+newVirtualFigure.getWidth()/2<
				this.virtualFigure.getX()+this.virtualFigure.getWidth()/2 
				&& newVirtualFigure.intersection(PacmanVirtualfig)
				) {
			setMotionSaved(68);//derechad		
		}
	}
	public void randomMovement() {
		selectedWall=wallIntersection();
		this.speed = space.outOfRange(coord2d, size)?0:this.speed;
		this.speed = movementAllowed(0, 0)?this.speed:0;
		executeSaveMotion();
		coord2d.moveFigure(this.speed*time, this.direction);
		updateVirtualFigures();
		erraticMovement(setWalls);
		
		if(this.speed==0) {
			relocate();
			if(this.speed==0) {
				this.speed = savedSpeed;
				this.direction = this.direction+Math.toRadians(180);
			}
		}
	}

	public void updateVirtualFigures(){
		inc = mode.equals(mode.RETURN)?16*14:16*30;
		this.virtualFigure = new MyRectangle2D(this.coord2d.getX(), this.coord2d.getY(),
				size, size);
		this.secundaryVirtualFigure = new MyRectangle2D(this.virtualFigure.getX()+size*2/10, this.virtualFigure.getY()+size*2/10,
				size-(size*4/10), size-(size*4/10)); 
//		this.viewingRange = new MyRectangle2D(this.coord2d.getX()-16*5, this.coord2d.getY()-16*5,
//				this.size+16*10, this.size+16*10);
		this.viewingRange = new MyRectangle2D(this.coord2d.getX()-inc, this.coord2d.getY()-inc,
				this.size+inc*2, this.size+inc*2);
	}
}
