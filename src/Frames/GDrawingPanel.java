package Frames;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Stack;

import javax.swing.JPanel;

import Frames.GToolBar.EShape;
import Shapes.GPolygon;
import Shapes.GShape;

public class GDrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private enum EdrawingState{
		eIdle,
		eDrawing,
		eSelecting,
		eMoving,
		eResizing,
		eRotate
	}
	private EdrawingState eDrawingState;
	private Stack<GShape> shapes;
	private GShape currentShape;
	private GToolBar toolBar;
	public void setToolBar(GToolBar toolBar) {
		this.toolBar = toolBar;
	}
	public GDrawingPanel() {
		super();
		this.eDrawingState= EdrawingState.eIdle;
		this.shapes= new Stack<GShape>();
//		movedRect = new Rectangle(0,0,0,0);

		currentShape = null;
	this.setBackground(Color.WHITE);
	MouseEventHandler meh = new MouseEventHandler();
	//os(jdk)에 객체를 등록시켜주는 작업.
	this.addMouseListener(meh);
	this.addMouseMotionListener(meh);
	}
	public Stack<GShape> getStack(){
		return this.shapes;
	}
	public void setStack(Stack<GShape> shapes){
		this.shapes=shapes;
	}
	
	public void paint(Graphics graphics) {
		//오버라이딩을 시켜버림
		super.paint(graphics);
		for(GShape shape : this.shapes) {
			shape.draw(graphics);
		}
	}
	public GShape onShape(Point point) {
		for(GShape rectangle : shapes) {
			if(rectangle.onShape(point)) {
				return rectangle;
			}
		}
		return null;
	}
	
	public boolean onShape2(Point point) {
		for(GShape rectangle : shapes) {
			if(rectangle.onShape(point)) {
				return true;
			}
		}
		return false;
	}
	
	public void ChangeCursor(Point p) {
		Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
		if(onShape2(p)) {
			cursor = Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);	
			}
		setCursor(cursor);
	}

	public void prepareTransforming(int x, int y) {
		if(eDrawingState == EdrawingState.eDrawing) {
			currentShape = toolBar.GetESelectedShape().getGShape().clone();
			currentShape.setShape(x, y, x, y);
		}else if(eDrawingState == EdrawingState.eSelecting) {
			currentShape = toolBar.GetESelectedShape().getGShape().clone();
			currentShape.setShape(x, y, x, y);
		}else if(eDrawingState ==EdrawingState.eMoving) {
			currentShape.setPoint(x,y);
		}


	}
	public void keepTransforming(int x, int y) {
		Graphics graphics =getGraphics();
		graphics.setXORMode(getBackground());
		if(eDrawingState == EdrawingState.eDrawing) {
		currentShape.draw(graphics);
		currentShape.resizePoint(x,y);
		currentShape.draw(graphics);
		}else if(eDrawingState == EdrawingState.eSelecting) {
			currentShape.draw(graphics);
			currentShape.resizePoint(x,y);
			currentShape.draw(graphics);
		}else if(eDrawingState == EdrawingState.eMoving) {
			if(x<850&&y<600&&x>100&&y>100) {
			currentShape.draw(graphics);
			currentShape.movePoint(x,y);
			currentShape.draw(graphics);
			}
		}
	}
	public void ContinueTransforming(int x, int y) {
		currentShape.addPoint(x, y);
	}
	public void finalizeTransforming(int x, int y) {
		 if(eDrawingState==EdrawingState.eDrawing){
				shapes.add(currentShape);
		 }else if(eDrawingState == EdrawingState.eSelecting) {
			 
		Graphics graphics =getGraphics();
		graphics.setXORMode(getBackground());
		currentShape.draw(graphics);	
		
		}else if(eDrawingState==EdrawingState.eMoving){

		}
		currentShape=null;
		toolBar.resetESelectedShape();
	}
	private class MouseEventHandler implements MouseListener, MouseMotionListener{
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub


			if(eDrawingState == EdrawingState.eDrawing) {
				if(currentShape instanceof GPolygon) {
					return;
				}
				keepTransforming(e.getX(),e.getY());
				}else if(eDrawingState == EdrawingState.eSelecting) {
					keepTransforming(e.getX(),e.getY());
	
				}else if(eDrawingState == EdrawingState.eMoving) {
					if(currentShape.onShape(new Point(e.getX(), e.getY()))){
						repaint();
						keepTransforming(e.getX(),e.getY());
					}
				}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if(eDrawingState == EdrawingState.eDrawing) {
				if(currentShape instanceof GPolygon) {
					keepTransforming(e.getX(),e.getY());
				}
				
				}
			if(eDrawingState == EdrawingState.eIdle&&toolBar.GetESelectedShape()==EShape.eSelect) {
			ChangeCursor(e.getPoint());
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
		if(e.getClickCount()==1) {
			mouse1Clicked(e);
		}else if(e.getClickCount()==2) {
			mouse2Clicked(e);

		}
		}

		private void mouse2Clicked(MouseEvent e) {
			if(eDrawingState == EdrawingState.eDrawing) {
				finalizeTransforming(e.getX(), e.getY());
				eDrawingState = EdrawingState.eIdle;
				}
		}

		private void mouse1Clicked(MouseEvent e) {
			if(eDrawingState == EdrawingState.eIdle) {
			if(toolBar.GetESelectedShape().getGShape() instanceof GPolygon) {
				
				eDrawingState=EdrawingState.eDrawing;
				prepareTransforming(e.getX(), e.getY());
				
			}
			
			}else if(eDrawingState==EdrawingState.eDrawing) {
				if(currentShape instanceof GPolygon) {
					ContinueTransforming(e.getX(),e.getY());
				}
			}else if(eDrawingState==EdrawingState.eMoving) {
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			if(!(eDrawingState == EdrawingState.eIdle)) {
				return;
			}
				if(toolBar.GetESelectedShape() == EShape.eSelect) {
					currentShape = onShape(new Point(e.getX(), e.getY()));
					
					if(currentShape ==null) {
						eDrawingState=EdrawingState.eSelecting;
						prepareTransforming(e.getX(), e.getY());
					}else {
						//resize, rotate,move
						eDrawingState=EdrawingState.eMoving;
						prepareTransforming(e.getX(), e.getY());
					}
					
				}else {
			if(!(toolBar.GetESelectedShape().getGShape() instanceof GPolygon)) {
				eDrawingState=EdrawingState.eDrawing;
				prepareTransforming(e.getX(), e.getY());
			}
				
			}
			

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			if(eDrawingState == EdrawingState.eDrawing) {
				if(currentShape instanceof GPolygon) {
					return;
				}
				
				finalizeTransforming(e.getX(), e.getY());
				eDrawingState=EdrawingState.eIdle;

				
				}else if(eDrawingState == EdrawingState.eSelecting) {
					finalizeTransforming(e.getX(), e.getY());
					eDrawingState=EdrawingState.eIdle;				
				}else if(eDrawingState == EdrawingState.eMoving) {
					finalizeTransforming(e.getX(), e.getY());
					eDrawingState=EdrawingState.eIdle;				
				}


		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
