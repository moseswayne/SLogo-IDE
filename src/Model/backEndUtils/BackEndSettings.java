package Model.backEndUtils;

import java.util.ArrayList;
import java.util.List;

import utils.Language;

public class BackEndSettings {
	
	private Language myLanguage;
	private List<List<Integer>> myPalette = new ArrayList<List<Integer>>();
	private List<String> myShapes = new ArrayList<String>();
	private Integer myPenColorIndex;
	private Integer myBackgroundColorIndex;
	private Double myPenSize;
	private Integer myShapeIndex;
	
	public BackEndSettings(Language lang, List<List<Integer>> pallete, Integer pen, Integer back, Double size, String img) {
		myLanguage = lang;
		myPalette = pallete;
		myPenColorIndex = pen;
		myBackgroundColorIndex = back;
		myPenSize = size;
		myShapes.add(img);
		myShapeIndex = myShapes.indexOf(img);
	}
	public BackEndSettings(Language lang, List<Integer> backgroundColor, List<Integer> penColor, Double penSize, String shape){
		myLanguage = lang;
		
		myPalette.add(backgroundColor);
		myBackgroundColorIndex = myPalette.indexOf(backgroundColor);
		myPalette.add(penColor);
		myPenColorIndex = myPalette.indexOf(penColor);
		
		myPenSize = penSize;
		
		myShapes.add(shape);
		myShapeIndex = myShapes.indexOf(shape);
		
		
	}

}
