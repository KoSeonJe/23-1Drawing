package Shapes;

import java.awt.Polygon;

public class GPolygon extends GShape {

	public GPolygon() {
		// TODO Auto-generated constructor stub

	}
	@Override
	public GShape clone() {
		// TODO Auto-generated method stub
		return new GPolygon();
	}
	@Override
	public void resizePoint(int x2, int y2) {//마지막 점 결정
		Polygon polygon = (Polygon)shape;
		polygon.xpoints[polygon.npoints-1] = x2;
		polygon.ypoints[polygon.npoints-1] = y2;
	}
	public void addPoint(int x2,int y2) {
		Polygon polygon = (Polygon)shape;
		polygon.addPoint(x2, y2);
	}

	@Override
	public void setShape(int x1, int y1, int x2, int y2) {
		this.shape = new Polygon();
		Polygon polygon = (Polygon)shape;
		polygon.addPoint(x1, y1);
		polygon.addPoint(x2, y2);

	}
	@Override
	public void movePoint(int x, int y) {
		// TODO Auto-generated method stub
		Polygon polygon = (Polygon)shape;
//		Polygon newPolygon = new Polygon();
		int tx = x-px;
		int ty = y-py;
		polygon.translate(tx,ty);
//		for(int i=0; i<polygon.npoints; i++) {
//newPolygon.addPoint(polygon.xpoints[i]+x-px, polygon.ypoints[i]+y-py);
//			polygon.xpoints[i] = polygon.xpoints[i]+x-px;
//			polygon.ypoints[i] = polygon.ypoints[i]+y-py;
//		}
//		this.shape = polygon;
		px=x;
		py=y;
	}

	@Override
	public void setPoint(int x, int y) {
		// TODO Auto-generated method stub
		this.px=x;
		this.py=y;

	}
	
}
