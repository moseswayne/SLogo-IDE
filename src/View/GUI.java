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
	
//	private final String SHOW_DATA_METHOD_NAME="updateDisplayedData";
	private final Dimension DEFAULT_SIZE = new Dimension(800, 600);
	private final Dimension DEFAULT_TURTLE_DISP_SIZE = new Dimension(300, 300);
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
		cmdHistoryDisplay = new CmdHistoryDisplay();
		console = new Console();
		turtleDisplay = new TurtleDisplay((int)DEFAULT_TURTLE_DISP_SIZE.getWidth(), (int)DEFAULT_TURTLE_DISP_SIZE.getHeight());
		varDisplay = new VarDisplay();
//		 root = new BorderPane();
//
//        Canvas canvas = new Canvas(300, 300);
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        drawLines(gc);
//
//        root.getChildren().add(canvas);
		root = makeRoot();
		myScene = new Scene(root, DEFAULT_SIZE.getWidth(), DEFAULT_SIZE.getHeight());
	}


//    private void drawLines(GraphicsContext gc) {
//    	
//        gc.beginPath();
//        gc.moveTo(30.5, 30.5);
//        gc.lineTo(150.5, 30.5);
//        gc.lineTo(150.5, 150.5);
//        gc.lineTo(30.5, 30.5);
//        gc.stroke();
//    }
//    
	/**
	 * populate root with the modules
	 */
	private BorderPane makeRoot() {
		BorderPane bp = new BorderPane();
		bp.setRight(varDisplay.getVisualizedContent());
		bp.setBottom(console.getVisualizedContent());
		bp.setLeft(cmdHistoryDisplay.getVisualizedContent());
		bp.setCenter(turtleDisplay.getVisualizedContent());
		return bp;
	}

	@Override
	public void show(Collection<FrontEndData> dataCollection) {
		for(FrontEndData data: dataCollection){
//			Method correspondingShowMethod=findCorrespondingModuleShowMethod(data);
			cmdHistoryDisplay.updateDisplayedData(data);
			console.updateDisplayedData(data);
			varDisplay.updateDisplayedData(data);
			turtleDisplay.updateDisplayedData(data);
		}
	}
	
	/**
	 * 
	 * @param data
	 * @return the instance module of GUI that can show data
	 */
	//TODO Any better way to do this?
//	private I_FrontEndModule findCorrespondingInstanceModule(FrontEndData data){
//		if(data.correspondsToModule(cmdHistoryDisplay)){
//			return cmdHistoryDisplay;
//		} else if(data.correspondsToModule(console)){
//			return console;
//		} else if(data.correspondsToModule(varDisplay)){
//			return varDisplay;
//		} else if(data.correspondsToModule(turtleDisplay)){
//			return turtleDisplay;
//		} else {
//			throw new IllegalArgumentException("FrontEndData data does not correspond to any module known by GUI");
//		}
//	}
	
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
