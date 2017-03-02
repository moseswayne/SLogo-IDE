package View.varDisplay;

import java.util.Map;
import java.util.TreeMap;
import View.FrontEndData;
import View.I_FrontEndModule;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
		fillMap();
		myVisualContent=new VBox();
		
		myVisualContent.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(2.5), new BorderWidths(5.0))));
		updateVisualContent();
	}
	
	/**
	 * TESTING ONLY
	 */
	//TODO: remove this
	private void fillMap() {
		map.put("x", "12");
		map.put("y", "13");
		map.put("x", "14");
		map.put("a", "15");
		map.put("b", "17");
		map.put("c", "129");
		map.put("d", "22");
		map.put("e", "156");
	}

	/**
	 * 
	 * @return updated myVisualContent
	 */
	@Override
	public void updateDisplayedData(FrontEndData data) {
		map=data.getVars();
		if(map==null){
			return;
		}
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

	//TODO pretty dangerous...maybe don't return the instance variable here...
	@Override
	public Node getVisualizedContent() {
		return myVisualContent;
	}

}
