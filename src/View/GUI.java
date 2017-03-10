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
//TODO palette display

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;
import View.viewUtils.FrontEndData;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import utils.ColorManager;
import utils.ErrorMessage;
import utils.Language;
import utils.PropertyUtility;
import utils.RawCommand;

public class GUI implements I_GUI{
	private final Dimension DEFAULT_SIZE = new Dimension(1010, 770);
	private final Dimension DEAFAULT_TURTLE_DISP_SIZE=new Dimension(600, 510);
	private final Dimension DEAFAULT_CONSOLE_SIZE=new Dimension(1010, 200);
	private final Dimension DEAFAULT_SIDE_DISP_SIZE=new Dimension(200, 510);
	private Scene myScene;
	private Parent root;
	private CmdHistoryDisplay cmdHistoryDisplay;
	private Console console;
	private TurtleDisplay turtleDisplay;
	private VarDisplay varDisplay;
	private ControlPanel ctrlPanel;
	private Language language;
	private Collection<I_FrontEndModule> myModules;
	private ColorManager colorManager;
	
	/**
	 * 
	 * @param sceneWidth
	 * @param sceneHeight
	 */
	public GUI () {
		Properties prop=new PropertyUtility("GeneraGUISettings.properties").getProperties();
		colorManager=new ColorManager();
		language=Language.valueOf(prop.getProperty("DEAFAULT_LANGUAGE"));
		initiateModules();
		addModulesToCollection();
		root=new ScrollPane(makeRoot());
		root.prefHeight(DEFAULT_SIZE.getHeight());
		
		myScene= new Scene(root, DEFAULT_SIZE.getWidth(), DEFAULT_SIZE.getHeight());
	}
	
	
	/**
	 * Helper method, 
	 * 
	 */
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

	/**
	 * 
	 * builds the front end modules under GUI
	 */
	private void initiateModules() {
		cmdHistoryDisplay=new CmdHistoryDisplay(DEAFAULT_SIDE_DISP_SIZE.width, DEAFAULT_SIDE_DISP_SIZE.height);
		console=new Console((int)DEAFAULT_CONSOLE_SIZE.getWidth(), (int)DEAFAULT_CONSOLE_SIZE.getHeight(), language);
		turtleDisplay=new TurtleDisplay((int)DEAFAULT_TURTLE_DISP_SIZE.getWidth(), (int)DEAFAULT_TURTLE_DISP_SIZE.getHeight(), this);
		varDisplay=new VarDisplay(DEAFAULT_SIDE_DISP_SIZE.width, DEAFAULT_SIDE_DISP_SIZE.height);
		ctrlPanel=new ControlPanel(this, language);
	}
	
	/*
	 * 
	 * Front end APIs for FrontEndModules
	 * 
	 */
	
	/**
	 * used for front end classes, gets the color within the ColorManager, 
	 * This is to store all available palettes to avoid duplication
	 */
	Color getColor(String name){
		return colorManager.getColor(name);
	}
	
	/**
	 * 
	 * @return ObservableList<String> that shows all the available colors' names
	 */
	ObservableList<String> getObservedColorNames(){
		return colorManager.getObservedColorNames();
	}
	
	/**
	 * adds a color using r, g, b values from 0-255
	 * once added, available to all front end classes
	 */
	void addColor(double r, double g, double b, String name) {
		colorManager.addColor(r, g, b, name);
	}
	
	/**
	 * Sets the color of the turtle background
	 * @param color String specifying the color, must be from the observable list of names in ColorManager
	 */
	void setTurtleBackgroundColor(String color){
		turtleDisplay.setBackgroudColor(color);
	}
	
	/**
	 * Sets the image of the turtle to be the image specified by the file
	 * @param file
	 */
	void setTurtleImage(File file){
		turtleDisplay.setTurtleImg(file);
	}
	
	/**
	 * Sets the color of the turtle pen
	 * @param color String specifying the color, must be from the observable list of names in ColorManager
	 */
	void setTurtlePenColor(String color){
		turtleDisplay.setPenColor(color);
	}
	
	/**
	 * sets the language for all FrontEndModules to lang
	 * @param lang
	 */
	void setLanguage(Language lang){
		language=lang;
		for(I_FrontEndModule module: myModules){
				module.setLanguage(lang);
		}
	}
	
	
	/*
	 * 
	 * Public APIs (defined in I_GUI)
	 * 
	 */
	public void setNewWindowButton(Runnable newWindowMethod){
		ctrlPanel.setNewWindowMethod(newWindowMethod);
	}

	/**
	 * Display the information in the FrontEndData objects
	 */
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

	/**
	 * private helper method, shows the error in a certain way
	 * @param error
	 */
	private void showError(ErrorMessage error) {
		Properties errProp=new PropertyUtility(language+"Text.properties").getProperties();
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(errProp.getProperty("alertTitle"));
		alert.setHeaderText(errProp.getProperty("alertHeader"));
		alert.setContentText(error.getMessage());
		alert.show();
	}

	/**
	 * Gets the user input result in the form of a RawCommand object see @RawCommand
	 * @return RawCommand
	 */
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

	/**
	 * gets the Scene generated by GUI
	 */
	@Override
	public Scene getScene() {
		return myScene;
	}


}
