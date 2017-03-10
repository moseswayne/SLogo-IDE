package View.ControlPanel;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import View.FrontEndData;
import View.GUI;
import View.I_FrontEndModule;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utils.Language;
import utils.RawCommand;

public class ControlPanel implements I_FrontEndModule {
	ComboBox<Language> languageButton;
	private ComboBox<String> turtleBackGroundSelector, penColorSelector;
	private Button turtleImgButton, newWorkspaceButton;
	private ToolBar bar;
	private Properties prop;
	private GUI myGUI;
	private Language language;
	private final ObservableList<Language> LANG_BUTTON_OPTIONS = FXCollections.observableArrayList(Arrays.asList(Language.values()));
	
	
	/**
	 * 
	 */
	public ControlPanel(GUI _myGUI, Language _language) {
		myGUI=_myGUI;
		myGUI.addColor(120, 13, 0, "1 (added in controlPanel constructor)");
		language=_language;
		bar = new ToolBar();
		prop = new Properties();
		loadPropetiesFromFile(language.toString()+"Text.properties");
		initiateButtons();
		initiateListeners();
		bar.getItems().addAll(languageButton, turtleBackGroundSelector, penColorSelector, turtleImgButton, newWorkspaceButton);
	}

	

	
	/**
	 * 
	 * 
	 * 
	 */
	private void initiateListeners() {
		languageButton.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Language>() {
			@Override
			public void changed(ObservableValue<? extends Language> observable, Language oldValue, Language newValue) {
				myGUI.setLanguage(newValue);
			}
		});
		
		turtleBackGroundSelector.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				myGUI.setTurtleBackgroundColor(newValue);
			}				
		});
		
		penColorSelector.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				myGUI.setTurtlePenColor(newValue);
			}
		});
		
		turtleImgButton.setOnMouseClicked(action->{
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			fileChooser.getExtensionFilters().addAll(
	                new FileChooser.ExtensionFilter("All Images", "*.*"),
	                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
	                new FileChooser.ExtensionFilter("PNG", "*.png")
	            );
			File file=fileChooser.showOpenDialog(null);
			if(file!=null){
				myGUI.setTurtleImage(file);
			}
		});
	}
	
	public void setNewWindowMethod(Runnable newSimMethod){
		newWorkspaceButton.setOnMouseClicked(action->{
			newSimMethod.run();
		});
	}
	/**
	 * 
	 * 
	 * 
	 */
	private void initiateButtons() {
		languageButton = new ComboBox<Language>(LANG_BUTTON_OPTIONS);
		turtleBackGroundSelector = new ComboBox<String>();
		turtleBackGroundSelector.setItems(myGUI.getObservedColorNames());
		
		penColorSelector = new ComboBox<String>();
		penColorSelector.setItems(myGUI.getObservedColorNames());
		
		turtleImgButton = new Button(prop.getProperty("turtleImgButton"));
		newWorkspaceButton=new Button("New workspace");
		setButtonText();
	}

	private void setButtonText() {
		languageButton.setPromptText(prop.getProperty("languageButton"));
		turtleBackGroundSelector.setPromptText(prop.getProperty("turtleBackGroundButton"));
		penColorSelector.setPromptText(prop.getProperty("penColorButton"));
		turtleImgButton.setText(prop.getProperty("turtleImgButton"));
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

	@Override
	public void updateDisplayedData(FrontEndData data) {
		return;
	}

	@Override
	public RawCommand getUserInteractionResult() {
		return null;
	}

	/**
	 * Has #NO# user interaction buffered
	 * Always return false
	 */
	@Override
	public boolean hasBufferedUserInteraction() {
		return false;
	}

	@Override
	public Node getVisualizedContent() {
		return null;
	}
	
	@Override
	public void setLanguage(Language lang){
		language=lang;
		loadPropetiesFromFile(language.toString()+"Text.properties");
		setButtonText();
	}
	
	private void loadPropetiesFromFile(String fileName){
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream(fileName));
		} catch (IOException e1) {
			throw new Error("properties file not found or something else created an IO error");
		}
	}
}
