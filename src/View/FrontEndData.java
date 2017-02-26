package View;

public abstract class FrontEndData {

	public FrontEndData() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @return String specifying which front end module's data does this object contain
	 */
	public abstract Class<?> getRelatedModuleClass();
}
