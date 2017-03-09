package View;

import java.util.HashMap;

public class ColorInterpreter {
	private HashMap<String, float[]> translationMap;
	
	public ColorInterpreter() {
		translationMap=new HashMap<>();
		translationMap.put("blue", new float[]{0.0f, 0.0f, 1.0f});
		translationMap.put("red", new float[]{1.0f, 0.0f, 0.0f});
		translationMap.put("green", new float[]{0.0f, 1.0f, 0.0f});
		translationMap.put("white", new float[]{1.0f, 1.0f, 1.0f});
		translationMap.put("black", new float[]{0.0f, 0.0f, 0.0f});
		translationMap.put("bisque", new float[]{1.0f, 0.89411765f, 0.76862746f});
		translationMap.put("grey", new float[]{0.5019608f, 0.5019608f, 0.5019608f});
	}
	
	public float getRValue(String color){
		return translationMap.get(color)[0];
	}
	
	public float getGValue(String color){
		return translationMap.get(color)[1];
	}
	
	public float getBValue(String color){
		return translationMap.get(color)[2];
	}
}
