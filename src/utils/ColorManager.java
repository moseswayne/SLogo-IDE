package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;


public class ColorManager {

	private Map<String, Color> deafaultPalettes;
	private Map<String, Color> userDefinedPalettes;
	private ObservableList<String> content;
	
	
	public ColorManager() {
		deafaultPalettes=new TreeMap<>();
		userDefinedPalettes=new TreeMap<>();
		content = FXCollections.observableArrayList(new ArrayList<String>());
		fillInDeafaultValues();
	}

	private void fillInDeafaultValues() {
		addColorToMap(0.0f, 0.0f, 1.0f, "blue", deafaultPalettes);
		addColorToMap(1.0f, 0.0f, 0.0f, "red", deafaultPalettes);
		addColorToMap(0.0f, 1.0f, 0.0f, "green", deafaultPalettes);
		addColorToMap(1.0f, 1.0f, 1.0f, "white", deafaultPalettes);
		addColorToMap(0.0f, 0.0f, 0.0f, "black", deafaultPalettes);
		addColorToMap(1.0f, 0.89411765f, 0.76862746f, "bisque", deafaultPalettes);
		addColorToMap(0.5019608f, 0.5019608f, 0.5019608f, "grey", deafaultPalettes);
	}
	
	private void addColorToMap(double r, double g, double b, String name, Map<String, Color> map){
		if(map.keySet().contains(name)){
			throw new IllegalArgumentException("Name for the color is already taken");
		} else {
			map.put(name, Color.color(r, g, b));
			content.clear();
			content.addAll(deafaultPalettes.keySet());
			content.addAll(userDefinedPalettes.keySet());
		}
	}
	
	/**
	 * add a new color to the existing colors using r g b 0-255
	 * @param r
	 * @param g
	 * @param name
	 * @param b
	 */
	public void addColor(double r, double g, double b, String name){
		double redNorm=r/255, greenNorm=g/255, blueNorm=b/255;
		addColorToMap(redNorm, greenNorm, blueNorm, name, userDefinedPalettes);
	}
	
	/**
	 * get the color with the specified name
	 * @param name
	 * @return
	 */
	public Color getColor(String name){
		if(deafaultPalettes.keySet().contains(name)){
			return deafaultPalettes.get(name);
		} else if(userDefinedPalettes.keySet().contains(name)){
			return userDefinedPalettes.get(name);
		} else {
			throw new IllegalArgumentException("Color name does not exist");
		}
	}

	public ObservableList<String> getColorNames(){
		return content;
	}
}
