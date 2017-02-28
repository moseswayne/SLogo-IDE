import View.GUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	private final String TITLE = "SLogo Program";

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		GUI display=new GUI();
		stage.setTitle(TITLE);
		stage.setScene(display.getScene());
		stage.show();
	}

    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
