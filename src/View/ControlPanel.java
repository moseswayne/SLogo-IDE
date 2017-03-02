package View;

import java.io.IOException;
import java.util.Properties;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;


public class ControlPanel{
	private Button languageButton, turtleBackGroundButton, penColorButton, turtleImgButton;
	private ToolBar bar;
	private Properties prop;
	
	public ControlPanel() {
		bar = new ToolBar();
		prop = new Properties();
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream("buttonEnglishText.properties"));
		} catch (IOException e1) {
			throw new Error("properties file not found or something else created an IO error");
		}
		initiateButtons();
		bar.getItems().addAll(languageButton, turtleBackGroundButton, penColorButton, turtleImgButton);
	}

	private void initiateButtons() {
		languageButton=new Button(prop.getProperty("languageButton"));
		turtleBackGroundButton=new Button(prop.getProperty("turtleBackGroundButton"));
		penColorButton=new Button(prop.getProperty("penColorButton"));
		turtleImgButton=new Button(prop.getProperty("turtleImgButton"));
	}

	public Node getContainer(){
		return bar;
	}
}
