package View;

import java.util.Map;
import utils.TurtleParameters;

public class FrontEndData {
	private String myCommand;
	private Double myPrintValue;
	private TurtleParameters myTurtleParameters;
	private Map<String, String> variables;

	public FrontEndData(String command) {
		myCommand = command;
	}

	public void setPrintData(double data) {
		myPrintValue = data;
	}

	public void setTurtleParameters(TurtleParameters position) {
		myTurtleParameters = position;
	}


	public Double getPrintConsole() {
		return myPrintValue;
	}

	public TurtleParameters getTurtleParameters() {
		return myTurtleParameters;
	}
	
	public Map<String, String> getVars() {
		return variables;
	}

	public String getCommandName() {
		return myCommand;
	}
}
/*
 * public abstract class FrontEndData { private Class<?> relatedClass;
 * 
 * public FrontEndData(Class<?> _relatedClass) { relatedClass=_relatedClass; }
 * 
 * /**
 * 
 * @return String specifying which front end module's data does this object
 * contain
 *//*
	 * public Class<?> getRelatedModuleClass(){ return relatedClass; }
	 * 
	 * /**
	 * 
	 * @param module
	 * 
	 * @return true if module is able to display this data
	 *//*
	 * public boolean correspondsToModule(I_FrontEndModule module){ return
	 * relatedClass.toString().equals(module.getClass().toString()); }
	 * 
	 * /** This assumes that the data object is passed to the correct
	 * FrontEndModule class, so whoever calling this method knows how to cast
	 * the returned Object.
	 * 
	 * @return data for corresponding front end module to display.
	 *//*
		 * public abstract Object getData(); }
		 */
