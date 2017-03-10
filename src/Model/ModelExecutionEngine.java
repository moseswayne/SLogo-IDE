package Model;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

import Operations.CommandOperation;
import View.viewUtils.FrontEndData;
import controller.CommandParser;
import utils.ParameterObject;
import utils.RawCommand;

public class ModelExecutionEngine implements I_ExecutionEngine {

	private Map<String, Double> variableMap;
	private CommandParser myParser;
	
	public ModelExecutionEngine() {
		variableMap = new HashMap<String, Double>();
//		myParser = new CommandParser();
	}

	@Override
	public FrontEndData runOp(RawCommand runCommand) throws Exception {
		
		String run = runCommand.getCommandString();
		String[] runArr = run.split(" ");
		Class<?> clazz = Class.forName("booleanOps."+runArr[0]);
		ArrayList<String> myParams = new ArrayList<String>();
		myParams.add(runArr[1]);
		myParams.add(runArr[2]);
		CommandOperation o = (CommandOperation) clazz.newInstance();
		ParameterObject params = new ParameterObject(myParams, variableMap);
		FrontEndData myReturn = new FrontEndData(null);
		o.execute(params, myReturn);
		return myReturn;
	}

}
