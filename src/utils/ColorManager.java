package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;


public class ColorManager {

	private Map<String, SLogoColor> deafaultColors;
	
	private Collection<SLogoColor> colors;
	private HashSet<String> existingNames;
	
	
	public ColorManager() {
		colors=new ArrayList<>();
		fillInDeafaultValues();
	}

	private void fillInDeafaultValues() {
		addColor(0.0f, 0.0f, 1.0f, "blue");
		addColor(1.0f, 0.0f, 0.0f, "red");
		addColor(0.0f, 1.0f, 0.0f, "green");
		addColor(1.0f, 1.0f, 1.0f, "white");
		addColor(0.0f, 0.0f, 0.0f, "black");
		addColor(1.0f, 0.89411765f, 0.76862746f, "bisque");
		addColor(0.5019608f, 0.5019608f, 0.5019608f, "grey");
	}
	
	public void addColor(double r, double g, double b, String name){
		if(existingNames.contains(name)){
			throw new IllegalArgumentException("Name for the color is already taken");
		} else {
			colors.add(new SLogoColor(r, g, b, name));
		}
	}

}
