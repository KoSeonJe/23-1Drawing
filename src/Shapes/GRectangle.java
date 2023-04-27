package Shapes;

import java.awt.Rectangle;

public class GRectangle extends GShape{
	

	public GRectangle(){
	}

	@Override
	public void resizePoint(int x2, int y2) {
		Rectangle rectangle = (Rectangle)shape;
		rectangle.setFrame(rectangle.getX(), rectangle.getY(),x2-rectangle.getX(), y2-rectangle.getY());
		
	}

	@Override
	public void setShape(int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
//		ox=x1;
//		oy=y1;
		this.shape = new Rectangle(x1,y1,x2-x1,y2-y1);
	}

	@Override
	public GShape clone() {
		return new GRectangle();
	}

	@Override
	public void movePoint(int x, int y) {
		
		
		Rectangle rectangle = (Rectangle)shape;
		rectangle.setLocation(x-(px-rectangle.x),y-(py-rectangle.y));
		this.px=x;
		this.py=y;

	}
	

	@Override
	public void setPoint(int x, int y) {
		// TODO Auto-generated method stub
		//도형의 프레스한 점.
//		Rectangle rectangle = (Rectangle)shape;
		this.px=x;
		this.py=y;
//		this.ox=(int) rectangle.getX();
//		this.oy=(int) rectangle.getY();
	
	}
	

}
