package Shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

abstract public class GShape{
	protected Shape shape;
	protected int ox, oy;
	protected int px, py;
	public GShape(){
		
	}
	public void addPoint(int x1, int y1) {
	}
	
  public boolean onShape(Point p) {
		return shape.contains(p);
 
  };

public void draw(Graphics graphics) {
		Graphics2D graphics2d = (Graphics2D) graphics;
		graphics2d.draw(shape);
	}
abstract public GShape clone();
public abstract void setShape(int x1, int y1, int x2, int y2);
public abstract void resizePoint(int x, int y);
public abstract void movePoint(int x, int y);
public abstract void setPoint(int x, int y);
}