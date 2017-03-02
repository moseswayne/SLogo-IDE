import java.util.ArrayList;
import View.FrontEndData;
import View.GUI;
import View.turtleDisplay.TurtleDispData;
import View.varDisplay.VarDispData;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.TurtleParameters;

public class Main extends Application {
	private final String TITLE = "SLogo Program";

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		GUI display=new GUI();

		stage.setTitle(TITLE);
		Scene sc=display.getScene();
		stage.setScene(sc);
		stage.show();
		
		ArrayList<FrontEndData> dataCollection=new ArrayList<>();
		TurtleParameters pos=new TurtleParameters(15, 125, 0, true);
		FrontEndData data=new FrontEndData(" ");
		data.setTurtleParameters(pos);
		dataCollection.add(data);
		display.show(dataCollection);
	}

    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
