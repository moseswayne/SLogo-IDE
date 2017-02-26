/**
 * 
 */
package View.cmdHistory;

import View.FrontEndData;

/**
 * @author Yuxiang He
 *
 */
public class cmdData extends FrontEndData {
	private final Class<?> RELATED_FE_CLASS=new CmdHistoryDisplay().getClass();
	
	/**
	 * 
	 */
	public cmdData() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * relates to CmdHistoryDisplay 
	 */
	@Override
	public Class<?> getRelatedModuleClass() {
		return RELATED_FE_CLASS;
	}

}
