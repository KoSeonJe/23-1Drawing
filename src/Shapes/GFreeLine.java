package Shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class GFreeLine extends GShape{

	@Override
	public GShape clone() {
		// TODO Auto-generated method stub
		return new GFreeLine();
	}
	@Override
	public void draw(Graphics graphics) {
		this.graphics2d = (Graphics2D) graphics;
		graphics2d.setColor(Color.black);
		if(colorClicked)
		graphics2d.setColor(Color.RED);
		
		graphics2d.drawString("*", px, py);
	}
	@Override
	public boolean onShape(Point p) {
		
		return false;
	}
	@Override
	public void drawBounds() {
		
	}
	@Override
	public void setShape(int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resizePoint(int x, int y) {
		// TODO Auto-generated method stub
		this.px=x;
		this.py=y;
	}

	@Override
	public void movePoint(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPoint(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
