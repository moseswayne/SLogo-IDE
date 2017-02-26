package View;

import javafx.scene.Node;
import utils.RawCommand;

public interface I_FrontEndModule {
	/**
	 * updates the information displayed in the module to data
	 * @param data new data to be displayed
	 */
	public void updateDisplayedData(FrontEndData data);
	
	
	/**
	 * gets the result of a user's interaction
	 * @return
	 */
	public RawCommand getUserInteractionResult();
	
	
	/**
	 * @return Node to be positioned in GUI
	 */
	public Node getVisualizedContent();
}
