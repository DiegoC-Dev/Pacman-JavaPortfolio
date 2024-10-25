package model.entity;

public class SetWalls {
	private Wall[] setWalls;

	public SetWalls(Wall[] setWalls) {
		super();
		this.setWalls = setWalls;
	}
	public Wall[] getSetWalls() {
		return setWalls;
	}
//	public void wallCrash(Coord2D coord2d,int size) {
//		Wall wall;
//		for (int i = 0; i < setWalls.length; i++) {
//			wall=setWalls[i];
//			if(
//			coord2d.getY()>wall.getCoord2d().getY()+wall.getHeigth()-(size) || 
//			coord2d.getY()<this.y ||
//			coord2d.getX()>this.x+this.width-(size) 
//			||  
//			coord2d.getX()<this.x
//			) {
//				
//			}
//		}
		
//		return (
//				coord2d.getY()>this.y+this.height-(size) || 
//				coord2d.getY()<this.y ||
//				coord2d.getX()>this.x+this.width-(size) 
//				||  
//				coord2d.getX()<this.x
//				) ;
//	}
}
