package View;

import java.util.Collection;

import utils.RawCommand;

public interface I_GUI {
	
	/**
	 * displays the result of an execution on the front end
	 * @param data
	 */
	public void show(Collection<FrontEndData> data);
	
	
	/**
	 * 
	 * @return RawCommand for control to translate to Command
	 */
	public RawCommand getUserInput();
	
}