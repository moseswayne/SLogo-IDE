package View.cmdHistory;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import View.FrontEndData;
import View.I_FrontEndModule;
import View.ObservedDisplay;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import utils.RawCommand;
import java.awt.Dimension;

public class CmdHistoryDisplay implements I_FrontEndModule{
	private Stack<String> history;
	private ObservedDisplay<Button> myVisualContent;
	private ScrollPane container;
	private Dimension size;
	private final int SCROLL_BAR_WIDTH = 30;
	
	public CmdHistoryDisplay(int width, int height) {
		size=new Dimension(width, height);
		myVisualContent = new ObservedDisplay<Button>();
		setUpHistory();
		container=new ScrollPane(myVisualContent.getDisplay());
		container.setBorder(new Border(
				new BorderStroke(Color.CHARTREUSE, BorderStrokeStyle.SOLID, new CornerRadii(2.5), new BorderWidths(5.0))));
		container.setPrefWidth(size.getWidth());
	}


	private void setUpHistory() {
		history = new Stack<String>();
		fillHistory();
	}
	
	/**
	 * helper method, updates myVisualContent using map
	 */
	private void updateVisualContent() {
		myVisualContent.clear();
		Stack<String> newHist=new Stack<>();
		newHist.addAll(history);
		while(!newHist.isEmpty()){
			String newCmd=newHist.pop();
			System.out.println(newCmd);
			myVisualContent.add(createButton(newCmd));
		}
	}
	
	private void fillHistory() {
		for(int i=0; i<20; i++){
			history.add(""+i);
		}	
		updateVisualContent();
	}

	private Button createButton(String str) {
		Button button = new Button(str);
		button.setPrefWidth(size.getWidth()-SCROLL_BAR_WIDTH);
//		button.setOnMouseClicked(action -> {
//			but
//		});
		return button;
	}

	@Override
	public void updateDisplayedData(FrontEndData data) {
		history.add(data.getCommandName());
		updateVisualContent();
	}

	@Override
	public RawCommand getUserInteractionResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasBufferedUserInteraction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Node getVisualizedContent() {
		// TODO Auto-generated method stub
		return container;
	}

}
