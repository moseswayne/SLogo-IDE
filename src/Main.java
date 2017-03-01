import java.util.ArrayList;
import View.FrontEndData;
import View.GUI;
import View.turtleDisplay.TurtleDispData;
import View.varDisplay.VarDispData;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.TurtlePosition;

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
		TurtlePosition pos=new TurtlePosition(115, 125, 90);
		TurtleDispData td=new TurtleDispData(pos, true);

		dataCollection.add(td);
		display.show(dataCollection);
	}

    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
