package Main;
import java.awt.BorderLayout;

import javax.swing.JFrame;

import Frames.GDrawingPanel;
import Frames.GMenuBar;
import Frames.GToolBar;

public class GMainFrame extends JFrame {

	//Components
	private GMenuBar menuBar;
	private GToolBar toolBar;
	private GDrawingPanel drawingPanel;
	private static final long serialVersionUID = 1L;

	public GMainFrame() {
		//자기의 정보는 자기 안에 놓아야한다.
		//바깥에서 세팅하는 것은 굉장히 안좋은 습관. 내부에서!!
		//내부 속성은 내부에서 해결해라!


		//components
		this.menuBar = new GMenuBar();
		this.setJMenuBar(menuBar);
		
		this.setLayout(new BorderLayout());

		this.toolBar = new GToolBar();
		this.add(this.toolBar, BorderLayout.NORTH);

		
		this.drawingPanel = new GDrawingPanel();
		this.add(drawingPanel, BorderLayout.CENTER);
		drawingPanel.setToolBar(toolBar);
		//attribute
		toolBar.setGDrawingPanel(drawingPanel);
		
		this.setSize(1000,800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}
}
