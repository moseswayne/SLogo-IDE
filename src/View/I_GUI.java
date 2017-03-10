package View;

import java.util.Collection;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
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
	 * For front end package classes
	 * @return
	 */
	public Color getColor(String name);
	
	
	public void addColor(double r, double g, double b, String name);
	
	/**
	 * 
	 * @return RawCommand for control to translate to Command
	 */
	public RawCommand getUserInput();
	
}