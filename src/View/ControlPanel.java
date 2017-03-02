package View;

import java.io.IOException;
import java.util.Properties;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class ControlPanel {
	private Button languageButton, turtleBackGroundButton, penColorButton, turtleImgButton;
	private ToolBar bar;
	private Properties prop;
	
	public ControlPanel() {
		bar = new ToolBar();
		prop = new Properties();
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream("buttonEnglishText.properties"));
		} catch (IOException e1) {
			throw new Error("gameText.properties not found or something else created an IO error");
		}
	}

}
