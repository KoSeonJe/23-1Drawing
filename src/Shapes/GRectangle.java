package Shapes;

import java.awt.Rectangle;
import java.util.Vector;

public class GRectangle extends GShape{
	int w;
	int h;

	public GRectangle(){
	}
	@Override
	public void resizingPoint(int x, int y) {
		Rectangle rectangle = (Rectangle)shape;

	     if(anchor==anchors.get(3)) {
			int currentX= Math.abs(x-ox);
			if(x>ox) {
			rectangle.setFrame(ox,oy,currentX, h);
			}else {
			rectangle.setFrame(x,oy,currentX, h);
			}
			w=currentX;
		}else if(anchor==anchors.get(4)) {
			w= Math.abs(ox-x);
			h=Math.abs(oy-y);
		   if(x<=ox && y<=oy) {
			rectangle.setFrame(x,y,w,h);
		   }else if(x<=ox && y>=oy) {
			rectangle.setFrame(x,oy,w,h);
		   }else if(x>=ox && y<=oy) {
			rectangle.setFrame(ox,y,w,h);
		   }else if(x>=ox && y>=oy) {
			rectangle.setFrame(ox,oy,w,h);
		}
		
		}else if(anchor==anchors.get(5)) {
			int currentY= Math.abs(y-oy);
			if(y>oy) {
			rectangle.setFrame(ox,oy,w,currentY);
			}else {
				rectangle.setFrame(ox,y, w, currentY);
			}
			h=currentY;
		}
	}
	@Override
	public void resizePoint(int x2, int y2) {
		Rectangle rectangle = (Rectangle)shape;
		if(x2<=ox && y2<=oy) {
			rectangle.setFrame(x2,y2,ox-x2,oy-y2);
		}else if(x2<=ox && y2>=oy) {
			rectangle.setFrame(x2,oy,ox-x2,y2-oy);
		}else if(x2>=ox && y2<=oy) {
			rectangle.setFrame(ox,y2,x2-ox,oy-y2);
		}else if(x2>=ox && y2>=oy) {
			rectangle.setFrame(ox,oy,x2-ox,y2-oy);
		}
		w= Math.abs(ox-x2);
		h=Math.abs(oy-y2);
		
	}

	@Override
	public void setShape(int x1, int y1, int x2, int y2) {
		ox=x1;
		oy=y1;
		this.shape = new Rectangle(x1,y1,x2-x1,y2-y1);
		  anchors=new Vector<GShape>();

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
		ox=rectangle.x;
		oy=rectangle.y;
		  anchors=new Vector<GShape>();

	}
	
	@Override
	public void setPoint(int x, int y) {
		// TODO Auto-generated method stub
		//도형의 프레스한 점.
		this.px=x;
		this.py=y;

	}


}
