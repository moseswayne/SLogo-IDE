package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import Model.backEndUtils.BackEndData;
import Model.backEndUtils.ParameterObject;
import Model.expressionTree.ExpressionNode;
import Model.operations.CommandOperation;
import Model.parser.CommandParser;
import View.viewUtils.FrontEndData;
import utils.RawCommand;

public class ModelExecutionEngine implements I_ExecutionEngine {

	private Map<String, Double> variableMap;
	private CommandParser myParser;
	private TurtleManager myTurtleManager;
	
	public ModelExecutionEngine() {
		variableMap = new HashMap<String, Double>();
		myParser = new CommandParser();
		myTurtleManager = new TurtleManager();
	}

	@Override
	public FrontEndData runOp(RawCommand runCommand) {
		Queue<ExpressionNode> runNodes = myParser.parse(runCommand.getCommandString(), runCommand.getLanguage(), myTurtleManager);
		BackEndData initData = new BackEndData();
		while(!runNodes.isEmpty()) {
			runNodes.poll().getValue(initData, variableMap);
		}
		FrontEndData returnData = new FrontEndData(runCommand.getCommandString(), runCommand.getLanguage());
		transferData(initData,returnData);
		generateReturnMap(returnData);
		return returnData;
	}
	
	private void transferData(BackEndData rawValues, FrontEndData finalValues) {
		finalValues.setPrintData(rawValues.getMyValue());
		while(rawValues.hasNextTurtleParameter()) {
			finalValues.addTurtleParameters(rawValues.transferParameter());
		}
	}
	
	private void generateReturnMap(FrontEndData finalValues) {
		HashMap<String,String> returnMap = new HashMap<String,String>();
		for(String key:variableMap.keySet()) {
			returnMap.put(key, variableMap.get(key).toString());
		}
		finalValues.setVars(returnMap);
	}

}
