package View;

import java.awt.Dimension;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
import java.util.Collection;

import View.cmdHistory.CmdHistoryDisplay;
import View.console.Console;
import View.turtleDisplay.TurtleDisplay;
import View.varDisplay.VarDisplay;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import utils.RawCommand;

public class GUI implements I_GUI{
	
	private final Dimension DEFAULT_SIZE = new Dimension(800, 800);
	private final Dimension DEAFAULT_TURTLE_DISP_SIZE=new Dimension(500, 500);
	private final Dimension DEAFAULT_CONSOLE_SIZE=new Dimension(500, 200);
	private Scene myScene;
	private BorderPane root;
	private CmdHistoryDisplay cmdHistoryDisplay;
	private Console console;
	private TurtleDisplay turtleDisplay;
	private VarDisplay varDisplay;
	
	
	/**
	 * 
	 * @param sceneWidth
	 * @param sceneHeight
	 */
	public GUI () {
		root = new BorderPane();
		cmdHistoryDisplay=new CmdHistoryDisplay();
		console=new Console((int)DEAFAULT_CONSOLE_SIZE.getWidth(), (int)DEAFAULT_CONSOLE_SIZE.getHeight());
		turtleDisplay=new TurtleDisplay((int)DEAFAULT_TURTLE_DISP_SIZE.getWidth(), (int)DEAFAULT_TURTLE_DISP_SIZE.getHeight());
		varDisplay=new VarDisplay();
		setupRoot();
		myScene= new Scene(root, DEFAULT_SIZE.getWidth(), DEFAULT_SIZE.getHeight());
	}
    
	/**
	 * populate root with the modules
	 */
	private void setupRoot() {
		root.setRight(varDisplay.getVisualizedContent());
		root.setBottom(console.getVisualizedContent());
		root.setLeft(cmdHistoryDisplay.getVisualizedContent());
		root.setCenter(turtleDisplay.getVisualizedContent());
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
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Scene getScene() {
		return myScene;
	}

}
