package Shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.Vector;

abstract public class GShape{
	protected Shape shape;
	protected int ox, oy;
	protected int px, py;
	protected Graphics2D graphics2d;
//	protected Vector<GOval> anchors;
	public GShape(){
	}
	public void addPoint(int x1, int y1) {
	}
//	public Point getPoint(Point point) {
//		return point;
//	}
	
  public boolean onShape(Point p) {
		return shape.contains(p);
 
  };
  boolean Fillchecked = false;
  public void Fill() {
	  if(!Fillchecked) {
		this.graphics2d.fill(this.shape);
	  }
		Fillchecked=true;
  };
  boolean colorClicked;
  public void setColorClicked(boolean colorClicked) {
	  this.colorClicked=colorClicked;
  }
  boolean thickClicked;
  public void setThickClicked(boolean thickClicked) {
	  this.thickClicked=thickClicked;
  }
  
//  boolean selcted;
//  public void getSelected(boolean selected) {
//	  this.selcted=selected;
//  }
  
  public void drawBounds() {
	  this.graphics2d.setColor(Color.black);
		graphics2d.setStroke(new BasicStroke(1,BasicStroke.CAP_SQUARE,0));
	  this.graphics2d.draw(this.shape.getBounds());
  }
  
  public void drawAnchor() {
	  Rectangle bounds = this.shape.getBounds();
	  int baseX=bounds.x-8;
	  int baseY=bounds.y-8;
	   makeAnchor(baseX, baseY);
	   makeAnchor(baseX+bounds.width/2, baseY);
	   makeAnchor(baseX+bounds.width, baseY);
	   makeAnchor(baseX+bounds.width, baseY+bounds.height/2);
	   makeAnchor(baseX+bounds.width, baseY+bounds.height);
	   makeAnchor(baseX+bounds.width/2, baseY+bounds.height);
	   makeAnchor(baseX, baseY+bounds.height);
	   makeAnchor(baseX, baseY+bounds.height/2);
  }
  public void makeAnchor(int x, int y) {
	  GOval anchor = new GOval();
	  this.graphics2d.setColor(Color.gray);
//	    this.graphics2d.draw(anchor.newAnchor(x, y));
		this.graphics2d.fill(anchor.newAnchor(x, y));
//		anchors.add(anchor);
  }
//  public Vector<GOval> getAnchors(){
//	  for(int i=0; i<anchors.size(); i++) {
//		  System.out.println(anchors.get(i));
//	  }
//	  return this.anchors;
//  }
  


public void draw(Graphics graphics) {
//	    anchors=new Vector<GOval>();
		this.graphics2d = (Graphics2D) graphics;
		
		graphics2d.setColor(Color.black);
		graphics2d.setStroke(new BasicStroke(1,BasicStroke.CAP_SQUARE,0));
		
		if(thickClicked)
		graphics2d.setStroke(new BasicStroke(10,BasicStroke.CAP_SQUARE,0));
		
		if(colorClicked) 
		graphics2d.setColor(Color.RED);
		
		if(Fillchecked)
			this.graphics2d.fill(this.shape);
		
		
		graphics2d.draw(shape);
		
	}
abstract public GShape clone();
public abstract void setShape(int x1, int y1, int x2, int y2);
public abstract void resizePoint(int x, int y);
public abstract void movePoint(int x, int y);
public abstract void setPoint(int x, int y);
}