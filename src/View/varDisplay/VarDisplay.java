package View.varDisplay;

import java.util.Map;
import java.util.TreeMap;
import View.FrontEndData;
import View.I_FrontEndModule;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import utils.RawCommand;

/**
 * @author Yuxiang He
 *
 */
public class VarDisplay implements I_FrontEndModule {
	private Map<String, String> map;
	private VBox myVisualContent;
	private final double ENTRY_WIDTH=50;
	private final String SEPERATION_STR=":";
	
	public VarDisplay() {
		map=new TreeMap<String, String>();
		myVisualContent=new VBox();
		updateVisualContent();
	}
	
	/**
	 * Assumes GUI has passed the correct data object
	 * @return updated myVisualContent
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void updateDisplayedData(FrontEndData data) {
		data=(VarDispData) data;
		map=(Map<String, String>) data.getData();
		myVisualContent.getChildren().clear();
		updateVisualContent();
	}

	/**
	 * helper method, updates myVisualContent using map
	 */
	private void updateVisualContent() {
		for(String varName: map.keySet()){
			String varVal=map.get(varName);
			Text varNameText=setUpEntryText(varName);
			Text varValText=setUpEntryText(varVal);
			Text varSepText=setSepText();
			HBox row=new HBox();
			row.getChildren().add(varNameText);
			row.getChildren().add(varSepText);
			row.getChildren().add(varValText);
			myVisualContent.getChildren().add(row);
		}
	}

	/**
	 * 
	 * @param string  specifies the text displayed in the Text
	 * @return Text with the string inside, and formatted
	 */
	private Text setUpEntryText(String string) {
		Text txt=new Text(string);
		txt.setWrappingWidth(ENTRY_WIDTH);
		return txt;
	}
	
	/**
	 * 
	 * @return Text with separation string inside, and formatted
	 */
	private Text setSepText() {
		Text txt=new Text(SEPERATION_STR);
		txt.setWrappingWidth(ENTRY_WIDTH/2);
		return txt;
	}

	@Override
	public RawCommand getUserInteractionResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getVisualizedContent() {
		return myVisualContent;
	}

}
