package Shapes;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class GOval extends GShape{
	public GOval(){

	}
	int w;
	int h;

	public Shape newAnchor(int x, int y) {
		Shape ellipse2D= new Ellipse2D.Double(x, y, 16, 16);
		return ellipse2D;
	}
	@Override
	public void resizePoint(int x2, int y2) {
		Ellipse2D ellipse2D = (Ellipse2D)shape;
		if(x2<=ox && y2<=oy) {
			ellipse2D.setFrame(x2,y2,ox-x2,oy-y2);
		}else if(x2<=ox && y2>=oy) {
			ellipse2D.setFrame(x2,oy,ox-x2,y2-oy);
		}else if(x2>=ox && y2<=oy) {
			ellipse2D.setFrame(ox,y2,x2-ox,oy-y2);
		}else if(x2>=ox && y2>=oy) {
			ellipse2D.setFrame(ox,oy,x2-ox,y2-oy);
		}
		w=(int) (x2-ellipse2D.getX());
		h= (int) (y2-ellipse2D.getY());
		
	}


	@Override
	public void setShape(int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
		this.shape= new Ellipse2D.Double(x1, y1, x2-x1, y2-y1);
		this.ox=x1;
		this.oy=y1;
	}


	@Override
	public GShape clone() {
		// TODO Auto-generated method stub
		return new GOval();
	}


	@Override
	public void movePoint(int x, int y) {
		// TODO Auto-generated method stub
		Ellipse2D ellipse2D = (Ellipse2D)shape;
		ellipse2D.setFrame(x-(px-ox),y-(py-oy),w,h);
		
	}


	@Override
	public void setPoint(int x, int y) {
		// TODO Auto-generated method stub
		Ellipse2D ellipse2D = (Ellipse2D)shape;
		this.px=x;
		this.py=y;
		this.ox=(int) ellipse2D.getX();
		this.oy=(int) ellipse2D.getY();
	
	}

}