import java.util.ArrayList;
import Model.ModelExecutionEngine;
import View.GUI;
import View.viewUtils.FrontEndData;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import utils.ErrorMessage;
import utils.Language;
import utils.RawCommand;
import utils.TurtleParameters;

public class Main extends Application {
	private final String TITLE = "SLogo Program";
	GUI display;
	ModelExecutionEngine engine;

	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

	@Override
	public void start(Stage stage) {
		display = new GUI();
		display.setNewWindowButton(() -> {
			(new Main()).start(new Stage());
		});
		engine = new ModelExecutionEngine();
		stage.setTitle(TITLE);
//		stage.setResizable(false);
		Scene sc = display.getScene();
		stage.setScene(sc);
		stage.show();
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();

		
		//TODO hard coded testing code, to be removed
//		ArrayList<FrontEndData> dataCollection = new ArrayList<>();
//		FrontEndData data1 = new FrontEndData("test main 43", Language.Chinese);
//		TurtleParameters params=new TurtleParameters(1, 125, 125, 45, true, true, true);
//		data1.addTurtleParameters(params);
//		dataCollection.add(data1);
//		
//		FrontEndData data2 = new FrontEndData("test main 51", Language.Chinese);
//		TurtleParameters params2=new TurtleParameters(2, 125, 125, 45, true, true, true);
//		data2.addTurtleParameters(params2);
//		dataCollection.add(data2);
//		display.show(dataCollection);
		
//		dataCollection = new ArrayList<>();
//		data = new FrontEndData("test main 60", Language.Chinese);
//		data.addError(new ErrorMessage("test error"));
//		dataCollection.add(data);
//		display.show(dataCollection);
	}

	private void step(double elapsedTime) {
		RawCommand rcmd = display.getUserInput();
		FrontEndData data = null;
		if (rcmd != null) {
			try {
				data = engine.runOp(rcmd);
			} catch (Exception e) {

				data = new FrontEndData(rcmd.getCommandString(), Language.English);
				data.addError(new ErrorMessage(e));
				
				//TODO REMOVE THIS LINE!
				e.printStackTrace();
			}
			ArrayList<FrontEndData> collec = new ArrayList<>();
			collec.add(data);
			display.show(collec);
		}
	}
	/**
	 * Start of the program.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
