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
// ���� Ÿ��
	public enum EShape{
		eSelect(new ImageIcon("src\\OIP.jpeg"), new GSelect()),
		eRectangle(new ImageIcon("src\\Rectangle.png"), new GRectangle()),
		eOval(new ImageIcon("src\\Oval.png"), new GOval()),
		eLine(new ImageIcon("src\\Line.png"), new GLine()),
		ePolygon(new ImageIcon("src\\Polygon.png"), new GPolygon()),
		eFreeLine(new ImageIcon("src\\FreeLine.png"), new GFreeLine())
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
	
	private boolean fillClicked;
	public boolean fillClicked() {
		return this.fillClicked;
	}
	private boolean RedColorClicked;
	public boolean RedColorClicked() {
		return this.RedColorClicked;
	}
	private boolean ThickClicked;
	public boolean ThickClicked() {
		return this.ThickClicked;
	}
	public enum EShapeChange{
		eFill("Fill"),
		eColor("RedColor"),
		eThick("Thick");
		private String name;
		private EShapeChange(String name) {
			this.name = name;
		}
		public String getName() {
			return this.name;
		}
	}
	
	public enum ETool{
		eUndo("ShapeDelete"),
		eRedo("ShapeRestore"),
		eClear("Clear"),
		;
		
		private String name;
		private ETool(String name) {
			this.name = name;
		}
		
		public String getName() {
			return this.name;
		}
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
		fillClicked=false;
		ActionHandler ah = new ActionHandler();
		bg = new ButtonGroup();
		
		for(EShape eButtonShape : EShape.values()) {
			if(eButtonShape == EShape.eSelect) {
				continue;
			}
			JButton buttonShape = new JButton();
			buttonShape.setPreferredSize(new Dimension(eButtonShape.getImage().getIconWidth(),eButtonShape.getImage().getIconHeight()));
			buttonShape.setIcon(eButtonShape.getImage());
			this.add(buttonShape);
			//��ư�� ���� �� string���� ������ �� �� �ִ�.
			buttonShape.setActionCommand(eButtonShape.toString());
			bg.add(buttonShape);
			buttonShape.addActionListener(ah);
		}
		resetESelectedShape();
		addSeparator();

		for(EShapeChange eSH : EShapeChange.values()) {
			JRadioButton tool = new JRadioButton(eSH.getName());
			this.add(tool);
			tool.addActionListener(ah);
			tool.setActionCommand(eSH.getName());
		}
		
		addSeparator();
		for(ETool etool : ETool.values()) {
			JButton tool = new JButton(etool.getName());
			this.add(tool);
			tool.addActionListener(ah);
			tool.setActionCommand(etool.getName());
		}
	}
	private class ActionHandler implements ActionListener{
		//�ϳ��� �ڵ鷯�� ��� ��ư ����
		// �̷��� ������ � ��ư�� ���ȴ��� �𸥴�.
		GShape[] gShape= new GShape[100];
		int i=0;
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand()=="ShapeDelete") {
				if(shapes.isEmpty()) {
					return;
				}
				gShape[i]=shapes.pop();
				drawingPanel.repaint();
				i++;
				return;
			}
			if(e.getActionCommand()=="ShapeRestore") {
				if (i <= 0 || gShape[i - 1] == null) {
	                // ������ Undo�� �۾��� ���ų� Redo�� ������ ���� ���
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
			if(e.getActionCommand()=="Fill") {//Ŭ�� �Ǿ����� üũ
				if(fillClicked) {
					fillClicked=false;
					return;
				}
				fillClicked=true;
				return;			
			}
			if(e.getActionCommand()=="RedColor") {//Ŭ���Ǿ����� üũ
				if(RedColorClicked) {
					RedColorClicked=false;
					return;
				}
				RedColorClicked=true;
				return;			
			}
			if(e.getActionCommand()=="Thick") {//Ŭ���Ǿ����� üũ
				if(ThickClicked) {
					ThickClicked=false;
					return;
				}
				ThickClicked=true;
				return;			
			}
			eSelectedShape =EShape.valueOf(e.getActionCommand());
		}
		
	}
	
}
