package utils;

import javafx.scene.paint.Color;

public class SLogoColor {
	private Color color;
	private String name;
	
	public SLogoColor(double r, double g, double b, String _name) {
		color=Color.color(r, g, b);
		name=_name;
	}
	
	public Color getColor(){
		return color;
	}
	
	public String getName(){
		return name;
	}

}
