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
import javafx.scene.layout.BorderPane;
import utils.RawCommand;

public class GUI implements I_GUI{
	
//	private final String SHOW_DATA_METHOD_NAME="updateDisplayedData";
	private final Dimension DEFAULT_SIZE = new Dimension(800, 600);
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
		console=new Console();
		turtleDisplay=new TurtleDisplay();
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
//			Method correspondingShowMethod=findCorrespondingModuleShowMethod(data);
			I_FrontEndModule correspondingModule=findCorrespondingInstanceModule(data);
			correspondingModule.updateDisplayedData(data);
		}
		
	}
	
	/**
	 * 
	 * @param data
	 * @return the instance module of GUI that can show data
	 */
	//TODO Any better way to do this?
	private I_FrontEndModule findCorrespondingInstanceModule(FrontEndData data){
		if(data.correspondsToModule(cmdHistoryDisplay)){
			return cmdHistoryDisplay;
		} else if(data.correspondsToModule(console)){
			return console;
		} else if(data.correspondsToModule(varDisplay)){
			return varDisplay;
		} else if(data.correspondsToModule(turtleDisplay)){
			return turtleDisplay;
		} else {
			throw new IllegalArgumentException("FrontEndData data does not correspond to any module known by GUI");
		}
	}
	
//	/**
//	 * finds the show method that can properly display the data
//	 * @param data
//	 */
//	private Method findCorrespondingModuleShowMethod(FrontEndData data) {
//		try {
//			return data.getRelatedModuleClass().getMethod(SHOW_DATA_METHOD_NAME, data.getClass());
//		} catch (NoSuchMethodException | SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//	}

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
