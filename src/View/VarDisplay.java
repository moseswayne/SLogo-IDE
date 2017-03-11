package View;

import java.awt.Dimension;
import java.util.Map;
import java.util.TreeMap;

import View.viewUtils.FrontEndData;
import View.viewUtils.ObservedDisplay;
import View.viewUtils.VarDispButton;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import utils.Language;
import utils.RawCommand;

/**
 * @author Yuxiang He
 *
 */
public class VarDisplay implements I_FrontEndModule {
	private final int SCROLL_BAR_WIDTH = 30;
	private Map<String, String> map;
	private ObservedDisplay<Button> myVisualContent;
	private ScrollPane container;
	private String bufferedCommandStr;
	private Dimension size;
	private final Language MY_LANGUAGE=Language.English;

	public VarDisplay(int width, int height) {
		size=new Dimension(width, height);
		map = new TreeMap<String, String>();
		fillMap();
		myVisualContent = new ObservedDisplay<Button>();
		container=new ScrollPane(myVisualContent.getDisplay());
		container.setBorder(new Border(
				new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(2.5), new BorderWidths(5.0))));
		container.setPrefWidth(size.getWidth());
		container.setPrefHeight(size.getHeight());
		updateVisualContent();
	}

	private Button createButton(String varName, String varVal) {
		VarDispButton button=new VarDispButton(varName, varVal);
		button.setPrefWidthForAll(size.getWidth()-SCROLL_BAR_WIDTH);
		button.setTextActionAfterEnter(()->{
			bufferedCommandStr=String.format("set %s %s", button.getVarName(), button.getVarVal());
		});
		return button;
	}

	/**
	 * 
	 * @return updated myVisualContent
	 */
	@Override
	public void updateDisplayedData(FrontEndData data) {
		map = data.getVars();
		if (map == null) {
			return;
		}
		myVisualContent.clear();
		updateVisualContent();
	}

	/**
	 * helper method, updates myVisualContent using map
	 */
	private void updateVisualContent() {
		for (String varName : map.keySet()) {
			String varVal = map.get(varName);
			Button button=createButton(varName, varVal);	
			myVisualContent.add(button);
		}
	}

	@Override
	public RawCommand getUserInteractionResult() {
		String rawCmdStr = ""+bufferedCommandStr;
		bufferedCommandStr = null;
		return new RawCommand(rawCmdStr, MY_LANGUAGE);
	}


	@Override
	public Node getVisualizedContent() {
		return container;
	}

	@Override
	public boolean hasBufferedUserInteraction() {
		// TODO Auto-generated method stub
		return bufferedCommandStr!=null;
	}
	
	
	/**
	 * TESTING ONLY
	 */
	// TODO: remove this
	private void fillMap() {
		map.put("x", "12");
		map.put("y", "13");
		map.put("z", "14");
		map.put("a", "15");
		map.put("b", "17");
		map.put("c", "129");
		map.put("d", "22");
		map.put("e", "156");
		map.put("as", "12");
		map.put("sd", "13");
		map.put("df", "14");
		map.put("gt", "15");
		map.put("ere", "17");
		map.put("uty", "129");
		map.put("yt", "22");
		map.put("yryrt", "156");
		map.put("43", "14");
		map.put("noname", "15");
		map.put("lol", "17");
		map.put("haha", "129");
		map.put("hey", "22");
		map.put("testing", "156");
	}

	@Override
	public void setLanguage(Language language) {
		return;
	}
}
