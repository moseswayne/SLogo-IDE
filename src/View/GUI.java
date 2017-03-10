package View;

//TODO multiple turtles
//TODO show/hide turtles
//TODO drag-able turtles
//TODO see state of multiple turtles (probably a window)
//TODO display error messages (probably pop up a notification window)
//TODO animation
//TODO Display active turtles
//TODO show user defined commands
//TODO Help page
//TODO save preferences to file

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import View.ControlPanel.ControlPanel;
import View.cmdHistory.CmdHistoryDisplay;
import View.console.Console;
import View.turtleDisplay.TurtleDisplay;
import View.varDisplay.VarDisplay;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import utils.ColorManager;
import utils.ErrorMessage;
import utils.Language;
import utils.RawCommand;

public class GUI implements I_GUI{
	
	private final Dimension DEFAULT_SIZE = new Dimension(1000, 750);
	private final Dimension DEAFAULT_TURTLE_DISP_SIZE=new Dimension(600, 510);
	private final Dimension DEAFAULT_CONSOLE_SIZE=new Dimension(1000, 200);
	private final Dimension DEAFAULT_SIDE_DISP_SIZE=new Dimension(200, 400);
	private Scene myScene;
	private BorderPane root;
	private CmdHistoryDisplay cmdHistoryDisplay;
	private Console console;
	private TurtleDisplay turtleDisplay;
	private VarDisplay varDisplay;
	private ControlPanel ctrlPanel;
	private Language language;
	private Collection<I_FrontEndModule> myModules;
	private Properties prop;
	private ColorManager colorManager;
	
	/**
	 * 
	 * @param sceneWidth
	 * @param sceneHeight
	 */
	public GUI () {
		prop=loadPropetiesFromFile("GeneraGUISettings.properties");
		colorManager=new ColorManager();
		language=Language.valueOf(prop.getProperty("DEAFAULT_LANGUAGE"));
		initiateModules();
		addModulesToCollection();
		root=makeRoot();
		
		myScene= new Scene(root, DEFAULT_SIZE.getWidth(), DEFAULT_SIZE.getHeight());
	}

	public Color getColor(String name){
		return colorManager.getColor(name);
	}
	
	public ObservableList<String> getObservedColorNames(){
		return colorManager.getObservedColorNames();
	}

	/**
	 * adds a color using r, g, b values from 0-255
	 */
	@Override
	public void addColor(double r, double g, double b, String name) {
		colorManager.addColor(r, g, b, name);
	}
	
	/**
	 * builds the front end modules under GUI
	 */
	private void initiateModules() {
		cmdHistoryDisplay=new CmdHistoryDisplay(DEAFAULT_SIDE_DISP_SIZE.width, DEAFAULT_SIDE_DISP_SIZE.height);
		console=new Console((int)DEAFAULT_CONSOLE_SIZE.getWidth(), (int)DEAFAULT_CONSOLE_SIZE.getHeight(), language);
		turtleDisplay=new TurtleDisplay((int)DEAFAULT_TURTLE_DISP_SIZE.getWidth(), (int)DEAFAULT_TURTLE_DISP_SIZE.getHeight(), this);
		varDisplay=new VarDisplay(DEAFAULT_SIDE_DISP_SIZE.width, DEAFAULT_SIDE_DISP_SIZE.height);
		ctrlPanel=new ControlPanel(this, language);
	}
	
	public void setTurtleBackgroundColor(String color){
		turtleDisplay.setBackgroudColor(color);
	}
	
	public void setTurtleImage(File file){
		turtleDisplay.setTurtleImg(file);
	}
	
	public void setTurtlePenColor(String color){
		turtleDisplay.setPenColor(color);
	}
	
	public void setLanguage(Language lang){
		language=lang;
		for(I_FrontEndModule module: myModules){
			module.setLanguage(lang);
		}
	}
	
	public void setNewWindowButton(Runnable newWindowMethod){
		ctrlPanel.setNewWindowMethod(newWindowMethod);
	}

	private void addModulesToCollection() {
		myModules=new ArrayList<>();
		myModules.add(cmdHistoryDisplay);
		myModules.add(console);
		myModules.add(turtleDisplay);
		myModules.add(varDisplay);
		myModules.add(ctrlPanel);
	}
    
	/**
	 * create a BorderPane with the modules
	 */
	private BorderPane makeRoot() {
		BorderPane bp = new BorderPane();
		bp.setRight(varDisplay.getVisualizedContent());
		bp.setBottom(console.getVisualizedContent());
		bp.setLeft(cmdHistoryDisplay.getVisualizedContent());
		bp.setCenter(turtleDisplay.getVisualizedContent());
		bp.setTop(ctrlPanel.getContainer());
		return bp;
	}

	@Override
	public void show(Collection<FrontEndData> dataCollection) {
		for(FrontEndData data: dataCollection){
			if(data.getError()!=null){
				showError(data.getError());
			} else {
				cmdHistoryDisplay.updateDisplayedData(data);
				console.updateDisplayedData(data);
				varDisplay.updateDisplayedData(data);
				turtleDisplay.updateDisplayedData(data);
			}
			
		}
	}

	private void showError(ErrorMessage error) {
		Properties errProp=loadPropetiesFromFile(language+"Text.properties");
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(errProp.getProperty("alertTitle"));
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.show();
	}

	@Override
	public RawCommand getUserInput() {
		for(I_FrontEndModule module: myModules){
			if(module.hasBufferedUserInteraction()){
				RawCommand rcmd=module.getUserInteractionResult();
				return rcmd;
			}
		}
		return null;
	}

	@Override
	public Scene getScene() {
		return myScene;
	}
	
	private Properties loadPropetiesFromFile(String fileName){
		Properties properties=new Properties();
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream(fileName));
			return properties;
		} catch (IOException e1) {
			throw new Error("properties file not found or something else created an IO error");
		}
	}


}
