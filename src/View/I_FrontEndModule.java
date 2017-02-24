package View;

import utils.RawCommand;

interface I_FrontEndModule {
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
