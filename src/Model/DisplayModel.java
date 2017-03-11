package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DisplayModel {
	private static RGBColor DEFAULT_BACKGROUND_COLOR = new RGBColor(0, 0, 0);
	private static RGBColor DEFAULT_PEN_COLOR = new RGBColor(255, 255, 255);
	private static double DEFAULT_PEN_SIZE = 2;
	private boolean isScreenClear;
	private double myBackgroundColorIndex;
	private double myPenColorIndex;
	private double myPenSize;
	private int myShapeIndex = 0;
	// private List<Double> myShapeIndexes = new ArrayList<Double>();
	private List<RGBColor> myPalette = new ArrayList<RGBColor>();

	public DisplayModel() {
		this(DEFAULT_BACKGROUND_COLOR, DEFAULT_PEN_COLOR, DEFAULT_PEN_SIZE);

	}

	public DisplayModel(RGBColor background, RGBColor pen, double penSize) {
		isScreenClear = false;
		myPenSize = penSize;
		myPalette.add(background);
		myBackgroundColorIndex = 0;
		myPalette.add(pen);
		myPenColorIndex = 1;
	}
	public void clearScreen(boolean clear){
		isScreenClear = clear;
	}
public boolean isScreenClear(){
	return isScreenClear;
}
	public double setBackground(int index) {
		fillIn(index);
		myBackgroundColorIndex = index;
		return getBackground();
	}

	public double getBackground() {
		return myBackgroundColorIndex;
	}

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

	public double setShape(int shape) {
		myShapeIndex = shape;
		return getShape();
	}

	public double getShape() {
		return myShapeIndex;
	}

	public double addToPalette(int index, List<Double> rgb) {
		RGBColor color = new RGBColor(rgb);
		fillIn(index);
		myPalette.add(index, color);
		return index;
	}

	private void fillIn(int index) {
		while (myPalette.size() - 1 < index) {
			myPalette.add(DEFAULT_BACKGROUND_COLOR);
		}
	}

}

class RGBColor implements Iterator<Integer> {
	// technically one over the maximum value allowed for RGB inputs
	private final int MAX_VALUE = 256;
	private final int NUM_VALUES = 3;
	private int myCurrentIndex = 0;
	private List<Integer> myValues;

	RGBColor(double r, double g, double b) {
		this.setColor(r, g, b);
	}

	RGBColor(List<Double> colorList) {
		this.setColor(colorList);
	}

	@Override
	public boolean hasNext() {
		return (myCurrentIndex + 1) < myValues.size();
	}

	@Override
	public Integer next() {
		return myValues.get(myCurrentIndex);
	}

	protected void setColor(double r, double g, double b) {
		myValues.add(normalize(r));
		myValues.add(normalize(g));
		myValues.add(normalize(b));
	}

	protected void setColor(List<Double> numberValues) {
		for (int i = 0; i < NUM_VALUES; i++) {
			try {
				myValues.add(normalize(numberValues.get(i)));
			} catch (NullPointerException e) {
				myValues.add(0);
			}
		}

	}

	/**
	 * Ensures values are positive integers less than 256
	 * 
	 * @param number
	 *            to convert
	 * @return
	 */
	private int normalize(double num) {
		return (int) Math.abs(num % MAX_VALUE);
	}

	protected List<Integer> getColor() {
		return myValues;
	}

	protected String getString() {
		StringBuffer str = new StringBuffer();
		while (this.hasNext()) {
			str.append(this.next().toString() + ", ");
		}
		return str.append(this.next().toString()).toString();
	}

}
