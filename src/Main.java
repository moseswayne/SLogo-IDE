import java.util.ArrayList;
import View.FrontEndData;
import View.GUI;
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
//        stage.setResizable(false);
		Scene sc=display.getScene();
		stage.setScene(sc);
		stage.show();
		
		ArrayList<FrontEndData> dataCollection=new ArrayList<>();
		FrontEndData data=new FrontEndData(" ");
		data.addTurtleParameters(15, 125, 0, true, true);
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
