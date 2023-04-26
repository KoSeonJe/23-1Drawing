package Frames;

import javax.swing.JMenuBar;

public class GMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	
	private GFileMenu filemenu;
	public GMenuBar() {
		filemenu= new GFileMenu("File");
		
		this.add(filemenu);
	}
}
