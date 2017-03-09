package View;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import View.cmdHistory.CmdHistoryDisplay;
import View.console.Console;
import View.turtleDisplay.TurtleDisplay;
import View.varDisplay.VarDisplay;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import utils.RawCommand;
import java.util.stream.Stream;

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
	private String language;
	private final String DEAFAULT_LANGUAGE="English";
	private Collection<I_FrontEndModule> myModules;
	
	/**
	 * 
	 * @param sceneWidth
	 * @param sceneHeight
	 */
	public GUI () {
		language=DEAFAULT_LANGUAGE;
		root = new BorderPane();
		initiateModules();
		addModulesToCollection();
		root=makeRoot();
		myScene= new Scene(root, DEFAULT_SIZE.getWidth(), DEFAULT_SIZE.getHeight());
	}


	/**
	 * builds the front end modules under GUI
	 */
	private void initiateModules() {
		cmdHistoryDisplay=new CmdHistoryDisplay(DEAFAULT_SIDE_DISP_SIZE.width, DEAFAULT_SIDE_DISP_SIZE.height);
		console=new Console((int)DEAFAULT_CONSOLE_SIZE.getWidth(), (int)DEAFAULT_CONSOLE_SIZE.getHeight());
		turtleDisplay=new TurtleDisplay((int)DEAFAULT_TURTLE_DISP_SIZE.getWidth(), (int)DEAFAULT_TURTLE_DISP_SIZE.getHeight());
		varDisplay=new VarDisplay(DEAFAULT_SIDE_DISP_SIZE.width, DEAFAULT_SIDE_DISP_SIZE.height);
		ctrlPanel=new ControlPanel(this);
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

	private void addModulesToCollection() {
		myModules=new ArrayList<>();
		myModules.add(cmdHistoryDisplay);
		myModules.add(console);
		myModules.add(turtleDisplay);
		myModules.add(varDisplay);
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
			cmdHistoryDisplay.updateDisplayedData(data);
			console.updateDisplayedData(data);
			varDisplay.updateDisplayedData(data);
			turtleDisplay.updateDisplayedData(data);
		}
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

}
