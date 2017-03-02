package View;

import java.awt.Dimension;
import java.util.ArrayList;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import View.cmdHistory.CmdHistoryDisplay;
import View.console.Console;
import View.turtleDisplay.TurtleDisplay;
import View.varDisplay.VarDisplay;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import utils.RawCommand;

public class GUI implements I_GUI{
	

	private final Dimension DEFAULT_SIZE = new Dimension(1000, 700);
	private final Dimension DEAFAULT_TURTLE_DISP_SIZE=new Dimension(500, 400);
	private final Dimension DEAFAULT_CONSOLE_SIZE=new Dimension(1000, 200);
	
	private Scene myScene;
	private BorderPane root;
	private CmdHistoryDisplay cmdHistoryDisplay;
	private Console console;
	private TurtleDisplay turtleDisplay;
	private VarDisplay varDisplay;
	private ControlPanel ctrlPanel;
	
	private Collection<I_FrontEndModule> myModules;
	private Map<String, String> translationMap;
	
	
	/**
	 * 
	 * @param sceneWidth
	 * @param sceneHeight
	 */
	public GUI () {
		root = new BorderPane();
		initiateModules();
		root=makeRoot();
		myScene= new Scene(root, DEFAULT_SIZE.getWidth(), DEFAULT_SIZE.getHeight());
		translationMap=new HashMap<>();
	}

	private void initiateModules() {
		cmdHistoryDisplay=new CmdHistoryDisplay();
		console=new Console((int)DEAFAULT_CONSOLE_SIZE.getWidth(), (int)DEAFAULT_CONSOLE_SIZE.getHeight());
		turtleDisplay=new TurtleDisplay((int)DEAFAULT_TURTLE_DISP_SIZE.getWidth(), (int)DEAFAULT_TURTLE_DISP_SIZE.getHeight());
		varDisplay=new VarDisplay();
		ctrlPanel=new ControlPanel();
		myModules=new ArrayList<>();
		myModules.add(cmdHistoryDisplay);
		myModules.add(console);
		myModules.add(turtleDisplay);
		myModules.add(varDisplay);
	}
    
	/**
	 * populate root with the modules
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
