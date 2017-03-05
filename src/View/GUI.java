package View;

import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import Model.ModelExecutionEngine;
import View.cmdHistory.CmdHistoryDisplay;
import View.console.Console;
import View.turtleDisplay.TurtleDisplay;
import View.varDisplay.VarDisplay;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import utils.RawCommand;

public class GUI implements I_GUI{
	

	private final Dimension DEFAULT_SIZE = new Dimension(1000, 700);
	private final Dimension DEAFAULT_TURTLE_DISP_SIZE=new Dimension(500, 400);
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
	private final String CHINESE="Chinese";
	private final String FRENCH="French";
	private final String GERMAN="German";
	private final String ITALIAN="Italian";
	private final String PORTUGUESE="Portuguese";
	private final String RUSSIAN="Russian";
	private final String SPANISH="Spanish";
	private Collection<I_FrontEndModule> myModules;
	private Map<String, String> translationMap;
	private Properties languageProp;
	
	ModelExecutionEngine engine;
	
	/**
	 * 
	 * @param sceneWidth
	 * @param sceneHeight
	 */
	public GUI () {
//		engine=new ModelExecutionEngine();
		language=DEAFAULT_LANGUAGE;
		root = new BorderPane();
		initiateModules();
		root=makeRoot();
		myScene= new Scene(root, DEFAULT_SIZE.getWidth(), DEFAULT_SIZE.getHeight());
		languageProp=new Properties();
		try {
			languageProp.load(getClass().getClassLoader().getResourceAsStream(language+".properties"));
		} catch (IOException e1) {
			throw new Error("properties file not found or something else created an IO error");
		}
		translationMap=buildTranslationMap();
//		try {
//			engine.runOp(getUserInput());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		int x=0;
	}
	
	/**
	 * loop through the property file to get reverse of the mapping, 
	 * helps back end implementation
	 * @return
	 */
	private Map<String, String> buildTranslationMap(){
		HashMap<String, String> out=new HashMap<>();
		for(Object command: languageProp.keySet()){
			String cmdString=(String) command;
			String match=languageProp.getProperty(cmdString);
			String[] matches=match.split("\\|");
			for(String m: matches){
				out.put(m, cmdString);
			}
		}
		return out;
	}

	/**
	 * builds the front end modules under GUI
	 */
	private void initiateModules() {
		cmdHistoryDisplay=new CmdHistoryDisplay(DEAFAULT_SIDE_DISP_SIZE.width, DEAFAULT_SIDE_DISP_SIZE.height);
		console=new Console((int)DEAFAULT_CONSOLE_SIZE.getWidth(), (int)DEAFAULT_CONSOLE_SIZE.getHeight());
		turtleDisplay=new TurtleDisplay((int)DEAFAULT_TURTLE_DISP_SIZE.getWidth(), (int)DEAFAULT_TURTLE_DISP_SIZE.getHeight());
		varDisplay=new VarDisplay(DEAFAULT_SIDE_DISP_SIZE.width, DEAFAULT_SIDE_DISP_SIZE.height);
		ctrlPanel=new ControlPanel();
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
				rcmd.setTranslationMap(translationMap);
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
