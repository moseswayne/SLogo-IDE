package Model.backEndUtils;

import java.util.ArrayList;
import java.util.List;

import utils.Language;

public class SLogoSettings {
	private final int MAX_VALUE = 256;
	private final int LENGTH = 3;
	
	private String myFile;
	private Language myLanguage;
	private List<List<Integer>> myPalette = new ArrayList<List<Integer>>();
	private List<String> myShapes = new ArrayList<String>();
	private Integer myPenColorIndex = 1;
	private Integer myBackgroundColorIndex = 0;
	private Double myPenSize = 2.;
	private Integer myShapeIndex = 0;
	private List<Integer> DEFAULT_COLOR = new ArrayList<Integer>() {
		{
			add(0);
			add(0);
			add(0);
		}
	};
	private boolean isScreenClear;

	public SLogoSettings(String propertyFileName) {
		myFile = propertyFileName;
		setUpFromFile(propertyFileName);
	}

	public SLogoSettings(Language lang, List<List<Integer>> pallete, Integer pen, Integer back, Double size,
			String img) {
		myLanguage = lang;
		myPalette = pallete;
		myPenColorIndex = pen;
		myBackgroundColorIndex = back;
		myPenSize = size;
		myShapes.add(img);
		myShapeIndex = myShapes.indexOf(img);
	}
	
	private void setUpFromFile(String propertyFileName) {
		// TODO Auto-generated method stub
		
	}

	public SLogoSettings(Language lang, List<Integer> backgroundColor, List<Integer> penColor, Double penSize,
			String shape) {
		myLanguage = lang;

		myPalette.add(backgroundColor);
		myBackgroundColorIndex = myPalette.indexOf(backgroundColor);
		myPalette.add(penColor);
		myPenColorIndex = myPalette.indexOf(penColor);

		myPenSize = penSize;

		myShapes.add(shape);
		myShapeIndex = myShapes.indexOf(shape);

	}
	public Language setLanguage(Language gibberish){
		myLanguage = gibberish;
		return getLanguage();
	}
	public Language getLanguage(){
		return myLanguage;
	}

	/**
	 * Clears screen
	 * 
	 * @param clear
	 */
	public void clearScreen(boolean clear) {
		isScreenClear = clear;
	}

	public boolean isScreenClear() {
		return isScreenClear;
	}

	/**
	 * Background
	 */
	public double setBackground(int index) {
		fillIn(index);
		myBackgroundColorIndex = index;
		return getBackground();
	}

	public double getBackground() {
		return myBackgroundColorIndex;
	}

	/**
	 * Pen
	 * 
	 * @param index
	 * @return
	 */

	public double setPen(int index) {
		fillIn(index);
		myPenColorIndex = index;
		return getPen();
	}

	public double getPen() {
		return myPenColorIndex;
	}

	public double setPenSize(double size) {
		myPenSize = size;
		return getPenSize();
	}

	public double getPenSize() {
		return myPenSize;
	}

	public List<List<Integer>> getPalette() {
		return myPalette;
	}

	public int addColor(ParameterObject params) {
		int index = params.next().intValue();
		List<Integer> paramValues = new ArrayList<Integer>();
		while(params.hasNext()){
			paramValues.add(params.next().intValue());
		}
		myPalette.add(index, normalize(paramValues));
		//return myPalette.size() - 1;
		return index;
	}

	/**
	 * Shape
	 * 
	 * @return
	 */
	public int getShapeIndex() {
		return myShapeIndex;
	}

	public int setShape(int shape) {
		myShapeIndex = shape;
		return getShapeIndex();
	}

	public List<String> getShapes() {
		return myShapes;
	}

	public int addShape(String shapeName) {
		myShapes.add(shapeName);
		return myShapes.indexOf(shapeName);
	}

	private void fillIn(int index) {
		while (myPalette.size() - 1 < index) {
			myPalette.add(DEFAULT_COLOR);
		}
	}

	private List<Integer> normalize(List<Integer> rawValues) {
		List<Integer> newColor = new ArrayList<Integer>();
		for (int i = 0; i < LENGTH && i < rawValues.size(); i++) {
			newColor.add(Math.abs(rawValues.get(i) % MAX_VALUE));
		}
		while (newColor.size() < LENGTH) {
			newColor.add(DEFAULT_COLOR.get(LENGTH - 1));
		}
		return newColor;
	}
}
