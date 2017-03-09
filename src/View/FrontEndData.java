package View;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;

import utils.Language;
import utils.TurtleParameters;

public class FrontEndData {
	private String myCommand;
	private Double myPrintValue;
	private Queue<TurtleParameters> myTurtleParameters;
	private Map<String, String> variables;
	private Language myLanguage;

	public FrontEndData(String command, Language lang) {
		myLanguage=lang;
		myCommand = command;
		myTurtleParameters = new ArrayDeque<TurtleParameters>();
	}

	public void setPrintData(double data) {
		myPrintValue = data;
	}
	
	public void addTurtleParameters(int id, double x, double y, double heading, boolean isPendown, boolean turtleShow, boolean turtleActive) {
		myTurtleParameters.add(new TurtleParameters(id, x, y, heading, isPendown, turtleShow, turtleActive));
	}

	public Double getPrintConsole() {
		return myPrintValue;
	}

	public Queue<TurtleParameters> getTurtleParameters() {
		return myTurtleParameters;
	}
	
	public Map<String, String> getVars() {
		return variables;
	}

	public String getCommandName() {
		return myCommand;
	}

	public Language getMyLanguage(){
		return myLanguage;
	}
}
