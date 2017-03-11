package View;

import View.viewUtils.FrontEndData;
import javafx.scene.Node;
import utils.Language;
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
	 * 
	 * @return true if there is user interaction result to be passed out
	 */
	public boolean hasBufferedUserInteraction();
	
	/**
	 * @return Node to be positioned in GUI
	 */
	public Node getVisualizedContent();
	
	
	/**
	 * Sets the language of the module
	 * @param language
	 */
	public void setLanguage(Language language);
	
	

}
