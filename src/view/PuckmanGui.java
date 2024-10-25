package view;

import java.awt.Color;
import java.awt.Graphics;

import model.entity.MyRectangle2D;
import model.entity.Puckman;
import model.entity.Wall;

public class PuckmanGui extends Puckman{
	Graphics graphics;
	private static MyRectangle2D virtualfigure;
	private static PuckmanGui instance;
	private static double direction;
	public PuckmanGui() {
		super();
		virtualfigure = getVirtualFigure();
//		virtualfigure = this.secundaryVirtualFigure;
	}
	public void drawPuckman(Graphics graphics){
		this.graphics=graphics;
//		virtualFigure = this.secundaryVirtualFigure;
		virtualfigure = getVirtualFigure();
		direction = Math.toDegrees(getDirection());
		
//		graphics.fillRect(50, 50, 50, 20);
//		graphics.fillOval((int)coord2d.getX()-((size)/2),(int) coord2d.getY()-((size)/2), 19, 19);
//		graphics.fillOval((int)coord2d.getX()-((size-1)/2),(int) coord2d.getY()-((size-1)/2), 19, 19);
//		graphics.fillOval((int)coord2d.getX(),(int) coord2d.getY(), 26, 26);
//		graphics.fillOval((int)coord2d.getX(),(int) coord2d.getY(), this.size, this.size);
		drawBody(graphics);
		drawPositions(graphics);
//		graphics.drawPolygon(new int[]{1,2,1}, new int[]{1,2,3}, 3);
//		graphics.setColor(Color.red);
//		graphics.fillOval((int)coord2d.getX()+size*4/10, (int)coord2d.getY()+size*4/10,(int)size-size*8/10,(int)size-size*8/10);//vrt2
//		this.secundaryVirtualFigure = new MyRectangle2D(coord2d.getX()+size/3, coord2d.getY()+size/3,size-size*2/3, size-size*2/3); 

//		graphics.fillOval((int)coord2d.getX(),(int) coord2d.getY(), this.size, this.size);//original grande
//		graphics.fillOval((int)coord2d.getX()+2,(int) coord2d.getY()+2, this.size-10, this.size-10);//modelo

//		graphics.fillOval(x, y, width, height);
//		graphics.setColor(Color.black);
//		graphics.fillRect((int)secundaryVirtualFigure.getX(),(int) secundaryVirtualFigure.getY(),
//				(int)secundaryVirtualFigure.getWidth(),(int) secundaryVirtualFigure.getHeight());

	}
	private void drawPositions(Graphics graphics) {
		graphics.setColor(Color.black);
		if(moveTimer>450) {
			graphics.fillPolygon(
					this.direction==0?
					new int[]{(int)(coord2d.getX()+virtualFigure.getWidth()/3),
							(int)(coord2d.getX()+virtualFigure.getWidth()),
							(int)(coord2d.getX()+virtualFigure.getWidth())}:
					this.direction==180?
					new int[]{(int)(coord2d.getX()+virtualFigure.getWidth()*2/3),
							(int)(coord2d.getX()),
							(int)(coord2d.getX())}
					:
						new int[]{(int)(coord2d.getX()+virtualFigure.getWidth()/2),
								(int)(coord2d.getX()),
								(int)(coord2d.getX()+virtualFigure.getWidth())},
					
					this.direction==270?
							new int[]{(int)(coord2d.getY()+virtualFigure.getHeight()*2/3),
									(int)(coord2d.getY()),
									(int)(coord2d.getY())}:
					this.direction==90?					
					new int[]{(int)(coord2d.getY()+virtualFigure.getHeight()/3),
							(int)(coord2d.getY()+virtualFigure.getHeight()),
							(int)(coord2d.getY()+virtualFigure.getHeight())}
					:
					
					new int[]{(int)(coord2d.getY()+virtualFigure.getHeight()/2),
							(int)(coord2d.getY()),
							(int)(coord2d.getY()+virtualFigure.getHeight())},
					3);
		}
		else
		{
			graphics.fillPolygon(this.direction==0?
					new int[]{(int)(coord2d.getX()+virtualFigure.getWidth()/3),
							(int)(coord2d.getX()+virtualFigure.getWidth()),
							(int)(coord2d.getX()+virtualFigure.getWidth())}:
					this.direction==180?
					new int[]{(int)(coord2d.getX()+virtualFigure.getWidth()*2/3),
							(int)(coord2d.getX()),
							(int)(coord2d.getX())}:
								
								new int[]{(int)(coord2d.getX()+virtualFigure.getWidth()/2),
										(int)(coord2d.getX()+virtualFigure.getWidth()*3/8),
										(int)(coord2d.getX()+virtualFigure.getWidth()*5/8)},								 
					this.direction==270?
							new int[]{(int)(coord2d.getY()+virtualFigure.getHeight()*2/3),
							(int)(coord2d.getY()),
							(int)(coord2d.getY())}:
					this.direction==90?					
							new int[]{(int)(coord2d.getY()+virtualFigure.getHeight()/3),
							(int)(coord2d.getY()+virtualFigure.getHeight()),
							(int)(coord2d.getY()+virtualFigure.getHeight())}
							:
					new int[]{(int)(coord2d.getY()+virtualFigure.getHeight()/2),
							(int)(coord2d.getY()+virtualFigure.getHeight()*3/8),
							(int)(coord2d.getY()+virtualFigure.getHeight()*5/8)},
					3);
		}
	}
	private void drawBody(Graphics graphics) {
		graphics.setColor(Color.yellow);
		graphics.fillOval((int)coord2d.getX()+4,(int) coord2d.getY()+4, this.size-8, this.size-8);
	}
	public void drawPuckman2(Graphics graphics){
		graphics.setColor(Color.yellow);
		this.graphics = graphics;
		graphics.fillOval((int)coord2d.getX()+4,(int) coord2d.getY()+4, this.size-8, this.size-8);//original
		graphics.setColor(Color.BLACK);
		graphics.fillPolygon(
				new int[]{(int)(coord2d.getX()+virtualFigure.getWidth()/3),
						(int)(coord2d.getX()+virtualFigure.getWidth()),
						(int)(coord2d.getX()+virtualFigure.getWidth())},
				new int[]{(int)(coord2d.getY()+virtualFigure.getHeight()/2),
						(int)(coord2d.getY()+virtualFigure.getHeight()*3/8),
						(int)(coord2d.getY()+virtualFigure.getHeight()*5/8)},
				3);
		
	}
	public void deathAnimation(Graphics graphics) {
		int divisor = 4000;
		int increase =7;
		this.graphics = graphics;
		drawBody(graphics);
//		System.out.println(deathTimer/divisor);
		graphics.setColor(Color.black);
		
		if(deathTimer/divisor > increase) {
			deathTimer=0;
			cycle++;
		}
		rigthTransition(graphics, divisor, increase);
		leftTransition(graphics, divisor, increase);
		topFill(graphics, increase);
		middleTopFill(graphics, increase);
		middleBottomFill(graphics, increase);
		bottomFill(graphics, increase);	
		graphics.setColor(Color.yellow);
		if(cycle==4 || cycle==7) {
//		if(cycle==4 && deathTimer/divisor==3.5 || cycle==6 && deathTimer/divisor==0) {
			graphics.drawOval((int)this.coord2d.getX()+this.size/2-1, (int)this.coord2d.getY()+this.size/2-1, 2, 2);
//			graphics.drawOval((int)this.coord2d.getX()+this.size/2-2, (int)this.coord2d.getY()+this.size/2-2, 4, 4);
			graphics.drawOval((int)this.coord2d.getX()+this.size/2-3, (int)this.coord2d.getY()+this.size/2-3, 6, 6);
		}
		if(cycle==5 || cycle==8) {
//		if(cycle==5 && deathTimer/divisor==0|| cycle==6 && deathTimer/divisor==3.5) {
			graphics.drawOval((int)this.coord2d.getX()+this.size/2-5, (int)this.coord2d.getY()+this.size/2-5, 10, 10);
//			graphics.drawOval((int)this.coord2d.getX()+this.size/2-6, (int)this.coord2d.getY()+this.size/2-6, 12, 12);
			graphics.drawOval((int)this.coord2d.getX()+this.size/2-7, (int)this.coord2d.getY()+this.size/2-7, 14, 14);
		}
		if(cycle==6 || cycle==9) {
//		if(cycle==5 && deathTimer/divisor==3.5 || cycle==7 && deathTimer/divisor==0) {
			graphics.drawOval((int)this.coord2d.getX()+this.size/2-9, (int)this.coord2d.getY()+this.size/2-9, 18, 18);
			graphics.drawOval((int)this.coord2d.getX()+this.size/2-10, (int)this.coord2d.getY()+this.size/2-10, 20, 20);
			graphics.drawOval((int)this.coord2d.getX()+this.size/2-11, (int)this.coord2d.getY()+this.size/2-11, 22, 22);
		}
		if(cycle==10 ) {
//		if(cycle==8  ) {
			this.setAnimationFinished(true);
		}
	}
	private void rigthTransition(Graphics graphics, int divisor, int increase) {
		graphics.fillPolygon(
			new int[]{(int)(coord2d.getX()+virtualFigure.getWidth()/2),//
			(int)(coord2d.getX()+(cycle==0?virtualFigure.getWidth()/2: //
			cycle==1?virtualFigure.getWidth()-increase://
			cycle==2?-(deathTimer/divisor<=increase?deathTimer/divisor:increase)+
					virtualFigure.getWidth()-increase+increase://
			-(deathTimer/divisor<=increase?deathTimer/divisor:increase)+virtualFigure.getWidth()/2+increase)),
			
			(int)(coord2d.getX()+virtualFigure.getWidth()+   
					(cycle==0?(deathTimer/divisor<=increase?deathTimer/divisor:increase)-(2*increase): //
					 cycle==1?(deathTimer/divisor<=increase?deathTimer/divisor:increase)-increase://
					 cycle==2?0:-increase)//
					)},//d
			new int[]{(int)(coord2d.getY()+virtualFigure.getHeight()/2),
			(int)(coord2d.getY()+(cycle==0?0:
				cycle==1?increase:
				cycle==2?(deathTimer/divisor<=increase?deathTimer/divisor:increase)+
						virtualFigure.getHeight()-(2*increase):
				(deathTimer/divisor<=increase?deathTimer/divisor:increase)+
							virtualFigure.getHeight()-increase)),
			
			(int)(coord2d.getY()+(cycle==0?(deathTimer/divisor<=increase?deathTimer/divisor:increase):
			  cycle==1?(deathTimer/divisor<=increase?deathTimer/divisor:increase)+
					  (virtualFigure.getHeight()/2)-increase:
			  cycle==2?virtualFigure.getHeight()/2:virtualFigure.getHeight()-increase)
			)},
				3);
	}
	private void leftTransition(Graphics graphics, int divisor, int increase) {
		graphics.fillPolygon(
				new int[]{(int)(coord2d.getX()+virtualFigure.getWidth()/2),//
				(int)(coord2d.getX()+(cycle==0?virtualFigure.getWidth()/2: //
				cycle==1?+increase://
				cycle==2?+(deathTimer/divisor<=increase?deathTimer/divisor:increase)+
						-0://
				+(deathTimer/divisor<=increase?deathTimer/divisor:increase)+virtualFigure.getWidth()/2-increase)),
				
				(int)(coord2d.getX()+   
						(cycle==0?-(deathTimer/divisor<=increase?deathTimer/divisor:increase)+(2*increase): //
						 cycle==1?-(deathTimer/divisor<=increase?deathTimer/divisor:increase)+increase://
						 cycle==2?0:+increase)//
						)},//d
				new int[]{(int)(coord2d.getY()+virtualFigure.getHeight()/2),
				(int)(coord2d.getY()+(cycle==0?0:
					cycle==1?increase:
					cycle==2?(deathTimer/divisor<=increase?deathTimer/divisor:increase)+
							virtualFigure.getHeight()-(2*increase):
					(deathTimer/divisor<=increase?deathTimer/divisor:increase)+
								virtualFigure.getHeight()-increase)),
				
				(int)(coord2d.getY()+(cycle==0?(deathTimer/divisor<=increase?deathTimer/divisor:increase):
				  cycle==1?(deathTimer/divisor<=increase?deathTimer/divisor:increase)+
						  (virtualFigure.getHeight()/2)-increase:
				  cycle==2?virtualFigure.getHeight()/2:virtualFigure.getHeight()-increase)
				)},
					3);
	}
	private void topFill(Graphics graphics, int increase) {
		if(cycle>0) {
			graphics.fillPolygon(
					new int[]{(int)(coord2d.getX()+virtualFigure.getWidth()/2),
							(int)(coord2d.getX()+virtualFigure.getWidth()/2),
							(int)(coord2d.getX()+virtualFigure.getWidth()-increase)},
					new int[]{(int)(coord2d.getY()+virtualFigure.getHeight()/2),
							(int)(coord2d.getY()),
							(int)(coord2d.getY()+increase)},
					3);
			graphics.fillPolygon(
					new int[]{(int)(coord2d.getX()+virtualFigure.getWidth()/2),
							(int)(coord2d.getX()+virtualFigure.getWidth()/2),
							(int)(coord2d.getX()+increase)},
					new int[]{(int)(coord2d.getY()+virtualFigure.getHeight()/2),
							(int)(coord2d.getY()),
							(int)(coord2d.getY()+increase)},
					3);
				
		}
	}
	private void middleTopFill(Graphics graphics, int increase) {
		if(cycle>1) {
			graphics.fillPolygon(
					new int[]{(int)(coord2d.getX()+virtualFigure.getWidth()/2),
							(int)(coord2d.getX()+virtualFigure.getWidth()-increase),
							(int)(coord2d.getX()+virtualFigure.getWidth())},
					new int[]{(int)(coord2d.getY()+virtualFigure.getHeight()/2),
							(int)(coord2d.getY())+increase,
							(int)(coord2d.getY()+virtualFigure.getHeight()/2)},
					3);
			graphics.fillPolygon(
					new int[]{(int)(coord2d.getX()+virtualFigure.getWidth()/2),
							(int)(coord2d.getX()+increase),
							(int)(coord2d.getX())},
					new int[]{(int)(coord2d.getY()+virtualFigure.getHeight()/2),
							(int)(coord2d.getY())+increase,
							(int)(coord2d.getY()+virtualFigure.getHeight()/2)},
					3);
		}
	}
	private void middleBottomFill(Graphics graphics, int increase) {
		if(cycle>2) {
			graphics.fillPolygon(
					new int[]{(int)(coord2d.getX()+virtualFigure.getWidth()/2),
							(int)(coord2d.getX()+virtualFigure.getWidth()-increase),
							(int)(coord2d.getX()+virtualFigure.getWidth())},
					new int[]{(int)(coord2d.getY()+virtualFigure.getHeight()/2),
							(int)(coord2d.getY()+virtualFigure.getHeight()-increase),
							(int)(coord2d.getY()+virtualFigure.getHeight()/2)},
					3);
			graphics.fillPolygon(
					new int[]{(int)(coord2d.getX()+virtualFigure.getWidth()/2),
							(int)(coord2d.getX()+increase),
							(int)(coord2d.getX())},
					new int[]{(int)(coord2d.getY()+virtualFigure.getHeight()/2),
							(int)(coord2d.getY()+virtualFigure.getHeight()-increase),
							(int)(coord2d.getY()+virtualFigure.getHeight()/2)},
					3);
		}
	}
	private void bottomFill(Graphics graphics, int increase) {
		if(cycle>3) {
			graphics.fillPolygon(
					new int[]{(int)(coord2d.getX()+virtualFigure.getWidth()/2),
							(int)(coord2d.getX()+virtualFigure.getWidth()/2),
							(int)(coord2d.getX()+virtualFigure.getWidth()-increase)},
					new int[]{(int)(coord2d.getY()+virtualFigure.getHeight()/2),
							(int)(coord2d.getY()+virtualFigure.getHeight()),
							(int)(coord2d.getY()+virtualFigure.getHeight()-increase)},
					3);	
			graphics.fillPolygon(
					new int[]{(int)(coord2d.getX()+virtualFigure.getWidth()/2),
							(int)(coord2d.getX()+virtualFigure.getWidth()/2),
							(int)(coord2d.getX()+increase)},
					new int[]{(int)(coord2d.getY()+virtualFigure.getHeight()/2),
							(int)(coord2d.getY()+virtualFigure.getHeight()),
							(int)(coord2d.getY()+virtualFigure.getHeight()-increase)},
					3);	
		}
	}
	public void drawLeftWall(Graphics graphics) {
//		graphics.setColor(Color.red);
		graphics.setColor(Color.cyan);
		graphics.fillRect(0, 0, 8, 8*73);
	}
	public void drawRightWall(Graphics graphics) {
//		graphics.setColor(Color.red);
		graphics.setColor(Color.cyan);
		graphics.fillRect(8*57, 0, size*3/4, 8*73);
	}
	public void drawBottomWall(Graphics graphics) {
//		graphics.setColor(Color.red);
		graphics.setColor(Color.cyan);
		graphics.fillRect(0, 8*73, 8*57+size*3/4, size*3/4);
//		graphics.fillRect(x, y, width, height);
	}
	public void drawUpperWall(Graphics graphics) {
//		graphics.setColor(Color.red);
		graphics.setColor(Color.cyan);
		graphics.fillRect(0, 0, 8*57+size*3/4, 8);
	}
	public static PuckmanGui getInstance(){
		if(instance==null) {
			instance=new PuckmanGui();
//			System.osut.println("se ha creado a pacman");
		}
//		else
//			System.out.println("pacman ya ha sido creado");
		return instance;
	}
	public static MyRectangle2D getVirtualPacman() {
		return virtualfigure;
	}
	public static double getPacmanDirection() {
		return direction;
	}
}
