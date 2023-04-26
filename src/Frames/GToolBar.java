package Frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

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
		eSelect("select", new GSelect()),
		eRectangle("Rectangle", new GRectangle()),
		eOval("Oval", new GOval()),
		eLine("Line", new GLine()),
		ePolygon("Polygon", new GPolygon());
		
		private String name;
		private GShape gShape;
		private EShape(String name, GShape gShape) {
			this.name = name;
			this.gShape= gShape;
		}
		public String getName() {
			return this.name;
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
	public GToolBar() {
		super();
		ActionHandler ah = new ActionHandler();
		bg = new ButtonGroup();
		
		for(EShape eButtonShape : EShape.values()) {
			if(eButtonShape == EShape.eSelect) {
				continue;
			}
			JRadioButton buttonShape = new JRadioButton(eButtonShape.getName());
			this.add(buttonShape);
			//버튼이 누를 때 string값을 나오게 할 수 있다.
			buttonShape.setActionCommand(eButtonShape.toString());
			bg.add(buttonShape);
			buttonShape.addActionListener(ah);
		}
		resetESelectedShape();


	}
	private class ActionHandler implements ActionListener{
		//하나의 핸들러에 모든 버튼 공유
		// 이러면 문제는 어떤 버튼이 눌렸는지 모른다.
		@Override
		public void actionPerformed(ActionEvent e) {
			eSelectedShape =EShape.valueOf(e.getActionCommand());
		
			
		}
		
	}
	
}
