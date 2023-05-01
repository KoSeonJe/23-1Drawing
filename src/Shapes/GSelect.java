package Shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GSelect extends GRectangle{
	
	public GSelect(){
	}
	@Override
	public GShape clone() {
		// TODO Auto-generated method stub
		return new GSelect();
	}
	@Override
	public void draw(Graphics graphics) {
		this.graphics2d=(Graphics2D) graphics;
		graphics2d.setColor(Color.LIGHT_GRAY);
		graphics2d.fill(this.shape);
	}


}
