import java.util.ArrayList;

import Model.ModelExecutionEngine;
import View.FrontEndData;
import View.GUI;
import controller.TranslationMapGenerator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import utils.Language;
import utils.RawCommand;

public class Main extends Application {
	private final String TITLE = "SLogo Program";
	GUI display;
	ModelExecutionEngine engine;
	private TranslationMapGenerator tmGenerator;
	
	public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		tmGenerator=new TranslationMapGenerator();
		 display = new GUI();
		 engine=new ModelExecutionEngine();
		stage.setTitle(TITLE);
		 stage.setResizable(false);
		Scene sc = display.getScene();
		stage.setScene(sc);
		stage.show();
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
		
		ArrayList<FrontEndData> dataCollection = new ArrayList<>();
		FrontEndData data = new FrontEndData("测试 main 43行", Language.Chinese);
		data.addTurtleParameters(0, 125, 125, 45, true, true, true);
		dataCollection.add(data);
		display.show(dataCollection);
	}

    private void step (double elapsedTime) {
        RawCommand rcmd=display.getUserInput();
        FrontEndData data=null;
        if(rcmd!=null){
        	  try {
        		  rcmd.setTranslationMap(tmGenerator.getTranslationMap(rcmd.getLanguage()));
        		  data=engine.runOp(rcmd);
      		} catch (Exception e) {
      			// TODO Auto-generated catch block
      			e.printStackTrace();
      		}
        	  ArrayList<FrontEndData> collec=new ArrayList<>();
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
