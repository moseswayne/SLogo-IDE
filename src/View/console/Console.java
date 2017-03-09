package View.console;

import java.awt.Dimension;
import java.io.IOException;
import java.util.Properties;
import View.FrontEndData;
import View.I_FrontEndModule;
import View.ObservedDisplay;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
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
	
	private ScrollPane outputContainer;
	private ObservedDisplay<Text> outputDisp;
	
	private VBox inputContainer;
	private TextArea inputField;
	
	private Dimension consoleSize;
	private String bufferedCommandStr;
	private Properties prop;
	private final double OUTPUT_DISP_HEIGHT_RATIO = 0.6;

	public Console(int width, int height) {
		consoleSize = new Dimension(width, height);
		inputContainer = new VBox();
		outputContainer = new ScrollPane();
		prop = new Properties();
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream("buttonEnglishText.properties"));
		} catch (IOException e1) {
			throw new Error("properties file not found or something else created an IO error");
		}
		setupOutDisp();
		setupInputContainer();
		container = new VBox(outputContainer, inputContainer);
	}

	private void setupOutDisp() {		
		outputDisp=new ObservedDisplay<Text>();
		outputContainer.setBorder(new Border(new BorderStroke(Color.AQUAMARINE, BorderStrokeStyle.SOLID,
				new CornerRadii(2.5), new BorderWidths(5.0))));
		outputContainer.setPrefSize(consoleSize.getWidth(), consoleSize.getHeight() * OUTPUT_DISP_HEIGHT_RATIO);
		
		outputContainer.setContent(outputDisp.getDisplay());
		outputContainer.vvalueProperty().bind(outputDisp.heightProperty());
	}

	private void setupInputContainer() {
		inputContainer.setPrefSize(consoleSize.getWidth(), consoleSize.getHeight() * (1 - OUTPUT_DISP_HEIGHT_RATIO));
		inputField = new TextArea();
		Button button = new Button(prop.getProperty("TextInputButton"));
		button.setOnMouseClicked(action -> {
			String text = inputField.getText().trim();
			if (text.length() != 0) {
				bufferedCommandStr = text;
				// TODO this line will be removed
				printToOutput("debug: " + text);
			}
			clearInputField();
		});
		inputContainer.getChildren().addAll(inputField, button);
	}
	
	private void printToOutput(String data){
		outputDisp.add(new Text(data));
	}

	private void clearInputField() {
		inputField.setText("");
	}

	@Override
	public void updateDisplayedData(FrontEndData data) {
		if (data.getPrintConsole() == null) {
			return;
		}
		double result=data.getPrintConsole();
		printToOutput(""+result);
	}

	@Override
	public RawCommand getUserInteractionResult() {
		String rawCmdStr = "" + bufferedCommandStr;
		bufferedCommandStr = null;
		return new RawCommand(rawCmdStr, "English");
	}

	@Override
	public Node getVisualizedContent() {
		return container;
	}

	@Override
	public boolean hasBufferedUserInteraction() {
		return bufferedCommandStr != null;
	}

}
