package View.console;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import View.FrontEndData;
import View.I_FrontEndModule;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import utils.RawCommand;

public class Console implements I_FrontEndModule {
	private VBox container;
	private VBox outputDisplay;
	private VBox inputContainer;
	private TextArea inputField;
	private Dimension consoleSize;
	private String mostRecentCommand;
	private ObservableList<String> history;
	private final double OUTPUT_DISP_HEIGHT_RATIO = 0.8;
	private final String BUTTON_TEXT = "Execute";

	public Console(int width, int height) {
		consoleSize = new Dimension(width, height);
		outputDisplay = new VBox();
		history=  FXCollections.observableArrayList(new ArrayList<String>());
		setupOutDisp();
		inputContainer = new VBox();
		setupInputContainer();
		container = new VBox(outputDisplay, inputContainer);
	}

	private void setupOutDisp() {
		outputDisplay.setPrefSize(consoleSize.getWidth(), consoleSize.getHeight() * OUTPUT_DISP_HEIGHT_RATIO);
		outputDisplay.setMaxSize(consoleSize.getWidth(), consoleSize.getHeight() * OUTPUT_DISP_HEIGHT_RATIO);
		history.addListener(new ListChangeListener<String>(){
			@Override
			public void onChanged(Change<? extends String> c) {
				while(c.next()){
					List<? extends String> added=  c.getAddedSubList();
					for( String str: added){
						str=str.trim();
						outputDisplay.getChildren().add(new Text(str));
						mostRecentCommand=str;
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
			if (text.length() != 0) {
				history.add(inputField.getText());
			}
			inputField.setText("");
		});
		inputContainer.getChildren().addAll(inputField, button);
	}

	@Override
	public void updateDisplayedData(FrontEndData data) {
		// TODO Auto-generated method stub

	}

	@Override
	public RawCommand getUserInteractionResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getVisualizedContent() {
		// TODO Auto-generated method stub
		return container;
	}

}
