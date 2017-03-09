package utils;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;


public class ColorManager {

	private Map<String, Color> deafaultPalettes;
	private Map<String, Color> userDefinedPalettes;
	private ObservableList<String> names;
	
	
	public ColorManager() {
		deafaultPalettes=new TreeMap<>();
		userDefinedPalettes=new TreeMap<>();
		names = FXCollections.observableArrayList(new ArrayList<String>());
		fillInDeafaultValues();
	}

	private void fillInDeafaultValues() {
		addColorToMap(0.0, 0.0, 1.0, "blue", deafaultPalettes);
		addColorToMap(1.0, 0.0, 0.0, "red", deafaultPalettes);
		addColorToMap(0.0, 1.0, 0.0, "green", deafaultPalettes);
		addColorToMap(1.0, 1.0, 1.0, "white", deafaultPalettes);
		addColorToMap(0.0, 0.0, 0.0, "black", deafaultPalettes);
		addColorToMap(1.0, 0.89411765, 0.76862746, "bisque", deafaultPalettes);
		addColorToMap(0.5019608, 0.5019608, 0.5019608, "grey", deafaultPalettes);
	}
	
	private void addColorToMap(double r, double g, double b, String name, Map<String, Color> map){
		if(map.keySet().contains(name)){
			throw new IllegalArgumentException("Name for the color is already taken");
		} else {
			map.put(name, Color.color(r, g, b));
			names.clear();
			names.addAll(deafaultPalettes.keySet());
			names.addAll(userDefinedPalettes.keySet());
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

	public ObservableList<String> getObservedColorNames(){
		return names;
	}
}
