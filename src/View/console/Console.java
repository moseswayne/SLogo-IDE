package View.console;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import View.FrontEndData;
import View.I_FrontEndModule;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import utils.RawCommand;

public class Console implements I_FrontEndModule {
	private VBox container;
	private VBox outputDisplay;
	private VBox inputContainer;
	private TextArea inputField;
	private Dimension consoleSize;
	private String bufferedCommandStr;
	private ObservableList<String> printedLines;
	private final double OUTPUT_DISP_HEIGHT_RATIO = 0.6;
	private final String BUTTON_TEXT = "Execute";

	
	
	public Console(int width, int height) {
		consoleSize = new Dimension(width, height);
		outputDisplay = new VBox();
		printedLines=  FXCollections.observableArrayList(new ArrayList<String>());
		inputContainer = new VBox();
		setupOutDisp();
		setupInputContainer();
		container = new VBox(outputDisplay, inputContainer);
	}

	private void setupOutDisp() {
		outputDisplay.setBorder(new Border(new BorderStroke(Color.AQUAMARINE, BorderStrokeStyle.SOLID, new CornerRadii(2.5), new BorderWidths(5.0))));
		outputDisplay.setPrefSize(consoleSize.getWidth(), consoleSize.getHeight() * OUTPUT_DISP_HEIGHT_RATIO);
		outputDisplay.setMaxSize(consoleSize.getWidth(), consoleSize.getHeight() * OUTPUT_DISP_HEIGHT_RATIO);
		printedLines.addListener(new ListChangeListener<String>(){
			@Override
			public void onChanged(Change<? extends String> c) {
				while(c.next()){
					List<? extends String> added=  c.getAddedSubList();
					for( String str: added){
						outputDisplay.getChildren().add(new Text(str));
					}
				}
			}
		});
	}

	private void setupInputContainer() {
		inputContainer.setPrefSize(consoleSize.getWidth(), consoleSize.getHeight() * (1 - OUTPUT_DISP_HEIGHT_RATIO));
		inputField = new TextArea();
		Button button = new Button(BUTTON_TEXT);
		button.setOnAction(action -> {
			String text=inputField.getText();
			text=text.trim();
			if (text.length() != 0) {
				bufferedCommandStr=text;
				//TODO this line will be removed
				printedLines.add("debug: "+text);
			}
			inputField.setText("");
		});
		inputContainer.getChildren().addAll(inputField, button);
	}

	@Override
	public void updateDisplayedData(FrontEndData data) {
		if(data.getCommandName()==null){
			return;
		}
		printedLines.add(data.getCommandName());
	}

	@Override
	public RawCommand getUserInteractionResult() {
		String rawCmdStr=""+bufferedCommandStr;
		bufferedCommandStr=null;
		return new RawCommand(rawCmdStr);
	}

	@Override
	public Node getVisualizedContent() {
		// TODO Auto-generated method stub
		return container;
	}

	@Override
	public boolean hasBufferedUserInteraction() {
		return bufferedCommandStr==null;
	}

}
