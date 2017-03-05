package View;

import java.io.IOException;
import java.util.Properties;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToolBar;

public class ControlPanel {
	private ComboBox<String> languageButton, turtleBackGroundSelector, penColorSelector;
	private Button turtleImgButton;
	private ToolBar bar;
	private Properties prop;
	private GUI myGUI;
	private final ObservableList<String> COLOR_BUTTON_OPTIONS = FXCollections.observableArrayList("White", "Bisque", "Red", "Green",
			"Blue", "Grey");

	/**
	 * 
	 */
	public ControlPanel(GUI _myGUI) {
		bar = new ToolBar();
		prop = new Properties();
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream("buttonEnglishText.properties"));
		} catch (IOException e1) {
			throw new Error("properties file not found or something else created an IO error");
		}
		initiateButtons();
		initiateListeners();
		bar.getItems().addAll(languageButton, turtleBackGroundSelector, penColorSelector, turtleImgButton);
		myGUI=_myGUI;
	}

	/**
	 * 
	 * 
	 * 
	 */
	private void initiateListeners() {
		
		languageButton.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				
			}
		});
		
		turtleBackGroundSelector.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				myGUI.setTurtleBgdColor(newValue);
			}
		});
		
		penColorSelector.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				myGUI.setTurtlePenColor(newValue);
			}
		});
	}
	
	/**
	 * 
	 * 
	 * 
	 */
	private void initiateButtons() {
		languageButton = new ComboBox<String>();
		languageButton.setPromptText(prop.getProperty("languageButton"));
		turtleBackGroundSelector = new ComboBox<String>(COLOR_BUTTON_OPTIONS);
		turtleBackGroundSelector.setPromptText(prop.getProperty("turtleBackGroundButton"));
		penColorSelector = new ComboBox<String>(COLOR_BUTTON_OPTIONS);
		penColorSelector.setPromptText(prop.getProperty("penColorButton"));
		turtleImgButton = new Button(prop.getProperty("turtleImgButton"));
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public Node getContainer() {
		return bar;
	}

	/**
	 * 
	 * 
	 */
	public void setTurtleBackGroundButton(Runnable r) {
		turtleBackGroundSelector.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				r.run();
			}
		});
	}
}
