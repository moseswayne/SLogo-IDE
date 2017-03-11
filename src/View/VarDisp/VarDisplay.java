package View.VarDisp;

import java.awt.Dimension;
import java.util.Map;
import java.util.TreeMap;

import View.I_FrontEndModule;
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
		return bufferedCommandStr!=null;
	}
	

	@Override
	public void setLanguage(Language language) {
		return;
	}
}
