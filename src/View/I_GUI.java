package View;

import java.util.Collection;

import javafx.scene.Scene;
import utils.RawCommand;

public interface I_GUI {
	
	
	/**
	 * 
	 * @return the scene created by GUI
	 */
	public Scene getScene();
	
	
	/**
	 * displays the something on the front end
	 * @param data
	 */
	public void show(Collection<FrontEndData> dataCollection);
	
	
	
	/**
	 * 
	 * @return RawCommand for control to translate to Command
	 */
	public RawCommand getUserInput();
	
}