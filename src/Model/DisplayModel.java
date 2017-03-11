package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DisplayModel {
	private final RGBColor DEFAULT_BACKGROUND_COLOR = new RGBColor(0,0,0);
	private final RGBColor DEFAULT_PEN_COLOR = new RGBColor(255,255,255);
	private final double DEFAULT_PEN_SIZE = 2;
	private boolean isScreenClear;
	private RGBColor myBackgroundColor;
	private RGBColor myPenColor;
	private double myPenSize;
	private int myShapeIndex = 0;
	private List<RGBColor> myPalette = new ArrayList<RGBColor>();


	public DisplayModel() {
		isScreenClear = false;
		myBackgroundColor= DEFAULT_BACKGROUND_COLOR;
		 myPenColor = DEFAULT_PEN_COLOR;
		myPenSize = DEFAULT_PEN_SIZE;
		
	}
	public double setBackground(int index){
		try{
		myBackgroundColor = myPalette.get(index);
		} catch (NullPointerException e){
			this.addToPalette(index, DEFAULT_BACKGROUND_COLOR);
			this.setBackground(index);
		}
		return index;
	}
	
	public double addToPalette(double index, RGBColor color){
		while(myPalette.size()-1 < index){
			myPalette.add(DEFAULT_BACKGROUND_COLOR);
		}
		myPalette.add(color);
		return index;
	}
	
}

class RGBColor implements Iterator<Integer> {
	// technically one over the maximum value allowed for RGB inputs
	private final int MAX_VALUE = 256;
	private final int NUM_VALUES = 3;
	private int myCurrentIndex = 0;
	private List<Integer> myValues;

	RGBColor(double r, double g, double b){
		this.setColor(r,g,b);
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
	 * @param number to convert
	 * @return 
	 */
	private int normalize(double num){
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
