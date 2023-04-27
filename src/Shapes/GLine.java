package Shapes;

import java.awt.Point;
import java.awt.geom.Line2D;

public class GLine extends GShape{
	public GLine(){
	}
	@Override
	public GShape clone() {
		// TODO Auto-generated method stub
		return new GLine();
	}

	@Override
	public void resizePoint(int x2, int y2) {
		// TODO Auto-generated method stub
		Line2D line2D = (Line2D)shape;
		//다 그리고 끝난점 저장.
		line2D.setLine(line2D.getX1(),line2D.getY1(),x2,y2);

	}

	@Override
	public void setShape(int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
		//시작 press점 저장
		this.shape=new Line2D.Double(x1, y1, x2, y2);	

	}
	@Override
	public boolean onShape(Point p) {
		Line2D line2D = (Line2D)shape;
		if(line2D.ptSegDist(p)<50) {
			return true;
		}
		return false;
	}


	@Override
	public void movePoint(int x, int y) {
		Line2D line2D = (Line2D)shape;
			line2D.setLine(line2D.getX1()+x-px,line2D.getY1()+y-py,line2D.getX2()+x-px,line2D.getY2()+y-py);
			this.px=x;
			this.py=y;
	}
	
	@Override
	public void setPoint(int x, int y) {
		this.px=x;
		this.py=y;
		
	}
	 

	
	

}