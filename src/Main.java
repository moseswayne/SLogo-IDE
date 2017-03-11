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
