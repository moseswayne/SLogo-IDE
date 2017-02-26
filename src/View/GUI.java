package View;

import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

import View.cmdHistory.CmdHistoryDisplay;
import View.console.Console;
import View.turtleDisplay.TurtleDisplay;
import View.varDisplay.VarDisplay;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import utils.RawCommand;

public class GUI implements I_GUI{
	
	private final String SHOW_DATA_METHOD_NAME="updateDisplayedData";
	private final Dimension DEFAULT_SIZE = new Dimension(800, 600);
	private BorderPane root;
	private int sceneWidth, sceneHeight;
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
		console=new Console();
		turtleDisplay=new TurtleDisplay();
		varDisplay=new VarDisplay();
		setUpRoot();
	}
	
	private void setUpRoot() {
		root.setRight(varDisplay.getVisualizedContent());
		root.setBottom(console.getVisualizedContent());
		root.setLeft(cmdHistoryDisplay.getVisualizedContent());
		root.setCenter(turtleDisplay.getVisualizedContent());
	}




	@Override
	//TODO Any better way to do this?
	public void show(Collection<FrontEndData> dataCollection) {
		for(FrontEndData data: dataCollection){
			Method correspondingShowMethod=findCorrespondingModuleShowMethod(data);
			try {
				correspondingShowMethod.invoke(data, data);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	/**
	 *
	 * @param data
	 */
	//TODO Any better way to do this?
	private Method findCorrespondingModuleShowMethod(FrontEndData data) {
		try {
			return data.getRelatedModuleClass().getMethod(SHOW_DATA_METHOD_NAME, data.getClass());
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public RawCommand getUserInput() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public Scene getScene() {
		return new Scene(root, DEFAULT_SIZE.getWidth(), DEFAULT_SIZE.getHeight());
	}

}
