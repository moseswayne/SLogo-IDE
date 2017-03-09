package View.cmdHistory;


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
	private Stack<RawCommand> history;
	private ObservedDisplay<Button> myVisualContent;
	private ScrollPane container;
	private Dimension size;
	private final int SCROLL_BAR_WIDTH = 30;
	private RawCommand bufferedCommand;
	
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
		history = new Stack<RawCommand>();
		fillHistory();
	}
	
	/**
	 * helper method, updates myVisualContent using map
	 */
	private void updateVisualContent() {
		myVisualContent.clear();
		Stack<RawCommand> histCopy=new Stack<>();
		histCopy.addAll(history);
		while(!histCopy.isEmpty()){
			RawCommand newCmd=histCopy.pop();
			myVisualContent.add(createButton(newCmd));
		}
	}
	
	private void fillHistory() {
		for(int i=0; i<20; i++){
			history.add(new RawCommand(""+i, "English"));
		}	
		updateVisualContent();
	}

	private Button createButton(RawCommand str) {
		CmdHistoryButton cmdHistButton = new CmdHistoryButton(str);
		cmdHistButton.setPrefWidth(size.getWidth()-SCROLL_BAR_WIDTH);
		cmdHistButton.setOnMouseClicked(action -> {
			bufferedCommand=cmdHistButton.getRawCommand();
		});
		return cmdHistButton;
	}

	@Override
	public void updateDisplayedData(FrontEndData data) {
		history.add(new RawCommand(data.getCommandName(), data.getMyLanguage()));
		updateVisualContent();
	}

	@Override
	public RawCommand getUserInteractionResult() {
		RawCommand copy =  new RawCommand(bufferedCommand);
		bufferedCommand = null;
		return new RawCommand(copy);
	}

	@Override
	public boolean hasBufferedUserInteraction() {
		return bufferedCommand!=null;
	}

	@Override
	public Node getVisualizedContent() {
		// TODO Auto-generated method stub
		return container;
	}

}
