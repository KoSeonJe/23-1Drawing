package Frames;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import Shapes.GFreeLine;
import Shapes.GLine;
import Shapes.GOval;
import Shapes.GPolygon;
import Shapes.GRectangle;
import Shapes.GSelect;
import Shapes.GShape;

public class GToolBar extends JToolBar {

	private static final long serialVersionUID = 1L;
// 서수 타입
	public enum EShape{
		eSelect(new ImageIcon("C:\\Users\\USER\\OneDrive\\바탕 화면\\패턴 중심 전공 자료\\OIP.jpeg"), new GSelect()),
		eRectangle(new ImageIcon("C:\\Users\\USER\\OneDrive\\바탕 화면\\패턴 중심 전공 자료\\Rectangle.png"), new GRectangle()),
		eOval(new ImageIcon("C:\\Users\\USER\\OneDrive\\바탕 화면\\패턴 중심 전공 자료\\Oval.png"), new GOval()),
		eLine(new ImageIcon("C:\\Users\\USER\\OneDrive\\바탕 화면\\패턴 중심 전공 자료\\Line.png"), new GLine()),
		ePolygon(new ImageIcon("C:\\Users\\USER\\OneDrive\\바탕 화면\\패턴 중심 전공 자료\\Polygon.png"), new GPolygon()),
		eFreeLine(new ImageIcon("C:\\Users\\USER\\OneDrive\\바탕 화면\\패턴 중심 전공 자료\\FreeLine.png"), new GFreeLine())
		;
		
		private ImageIcon image;
		private GShape gShape;
		private EShape(ImageIcon image, GShape gShape) {
			this.image = image;
			this.gShape= gShape;
		}
		public ImageIcon getImage() {
			return this.image;
		}
		public GShape getGShape() {
			return this.gShape;
		}
	}
	private EShape eSelectedShape;
	private ButtonGroup bg;
	public EShape GetESelectedShape() {
		return this.eSelectedShape;
	}
	public void resetESelectedShape() {
		this.bg.clearSelection();
		this.eSelectedShape=EShape.eSelect;
	
	}
	
	private GDrawingPanel drawingPanel;
	private Stack<GShape> shapes;
	public void setGDrawingPanel(GDrawingPanel drawingPanel) {
		this.drawingPanel=drawingPanel;
		shapes =drawingPanel.getStack();
	}
	
	public GDrawingPanel getGDrawingPanel() {
		return drawingPanel;
	}
	public GToolBar() {
		super();
		ActionHandler ah = new ActionHandler();
		bg = new ButtonGroup();
		
		for(EShape eButtonShape : EShape.values()) {
			if(eButtonShape == EShape.eSelect) {
				continue;
			}
			JRadioButton buttonShape = new JRadioButton();
			buttonShape.setPreferredSize(new Dimension(eButtonShape.getImage().getIconWidth(),eButtonShape.getImage().getIconHeight()));
			buttonShape.setIcon(eButtonShape.getImage());
			this.add(buttonShape);
			//버튼이 누를 때 string값을 나오게 할 수 있다.
			buttonShape.setActionCommand(eButtonShape.toString());
			bg.add(buttonShape);
			buttonShape.addActionListener(ah);
		}
		resetESelectedShape();
		JButton undo = new JButton("Undo");
		this.add(undo);
		undo.addActionListener(ah);
		undo.setActionCommand("Undo");
		
		JButton redo = new JButton("Redo");
		this.add(redo);
		redo.addActionListener(ah);
		redo.setActionCommand("Redo");
		
		JButton clear = new JButton("Clear");
		this.add(clear);
		clear.addActionListener(ah);
		clear.setActionCommand("Clear");
	}
	private class ActionHandler implements ActionListener{
		//하나의 핸들러에 모든 버튼 공유
		// 이러면 문제는 어떤 버튼이 눌렸는지 모른다.
		GShape[] gShape= new GShape[100];
		int i=0;
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand()=="Undo") {
				if(shapes.isEmpty()) {
					return;
				}
				gShape[i]=shapes.pop();
				drawingPanel.repaint();
				i++;
				return;
			}
			if(e.getActionCommand()=="Redo") {
				if (i <= 0 || gShape[i - 1] == null) {
	                // 이전에 Undo된 작업이 없거나 Redo할 도형이 없는 경우
	                return;
	            }
	            shapes.add(gShape[i - 1]);
	            drawingPanel.setStack(shapes);
	            drawingPanel.repaint();
	            i--;
				return;
			}
			if(e.getActionCommand()=="Clear") {
				shapes.clear();

				drawingPanel.repaint();
				return;
			}
			eSelectedShape =EShape.valueOf(e.getActionCommand());
			
		}
		
	}
	
}
