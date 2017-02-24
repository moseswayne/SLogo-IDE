package View;

import utils.RawCommand;

interface FrontEndModule {
	/**
	 * updates the information displayed in the module to data
	 * @param data new data to be displayed
	 */
	void updateDisplayedData(FrontEndData data);
	
	
	/**
	 * gets the result of a user's interaction
	 * @return
	 */
	RawCommand getUserInteractionResult();
}
