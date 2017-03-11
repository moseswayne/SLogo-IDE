package Model.backEndUtils;

import java.util.List;

import utils.Language;

public class BackEndSettings {
	
	private Language myLanguage;
	private List<List<Integer>> myPallete;
	private Integer myPenColor;
	private Integer myBackgroundColor;
	private Double penSize;
	private String imagePath;
	
	public BackEndSettings(Language lang, List<List<Integer>> pallete, Integer pen, Integer back, Double size, String img) {
		myLanguage = lang;
		myPallete = pallete;
		myPenColor = pen;
		myBackgroundColor = back;
		penSize = size;
		imagePath = img;
	}

}
