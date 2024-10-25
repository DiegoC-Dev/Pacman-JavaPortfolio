package model.entity;

import java.awt.Color;

import view.PuckmanGui;

public class Clyde extends Ghost{
	
	public Clyde() {
		this.color = Color.orange;
		this.coord2d = new Coord2D(this.coord2d.getX()+16*2, this.coord2d.getY());
//		this.direction = Math.toDegrees(270);
//		mode = mode.ESCAPE_ENDING;
	}
	public void move() {
		movetimer();
		target = PuckmanGui.getVirtualPacman();
		timerConditions();
		if(mode.equals(mode.RETURN)) {
			this.viewingRange = new MyRectangle2D(this.coord2d.getX()-16*12, this.coord2d.getY()-16*15,
					this.size+16*30, this.size+16*30);
			target = pacmanInitialPosition;
		}
//		randomNumber = 1+random.nextInt(100);//seguimiento de entre 10 y 3 segundos
		randomNumber = 1;//seguimiento de entre 10 y 3 segundos
		if(target.intersection(getViewingRange()) && !mode.equals(mode.RETURN)) {
//			if(randomNumber==1) {
				randomMovement();
//			}
//			else {
//				randomMovement();
//			}
		}
		else {
			directedMovement(target);
			
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
				System.out.println("caso raro de bug clyde");
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
		inc = mode.equals(mode.RETURN)?16*14:16*5;
		this.virtualFigure = new MyRectangle2D(this.coord2d.getX(), this.coord2d.getY(),
				size, size);
		this.secundaryVirtualFigure = new MyRectangle2D(this.virtualFigure.getX()+size*2/10, this.virtualFigure.getY()+size*2/10,
				size-(size*4/10), size-(size*4/10)); 
//		this.viewingRange = new MyRectangle2D(this.coord2d.getX()-16*5, this.coord2d.getY()-16*5,
//				this.size+16*10, this.size+16*10);
		this.viewingRange = new MyRectangle2D(this.coord2d.getX()-inc, this.coord2d.getY()-inc,
				this.size+inc*2, this.size+inc*2);
	}
	protected MyRectangle2D getVirtualFigure() {
		return super.getVirtualFigure();
	}
}
