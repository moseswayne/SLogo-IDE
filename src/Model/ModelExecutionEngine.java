package Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import Model.backEndUtils.BackEndData;
import Model.backEndUtils.SLogoSettings;
import Model.expressionTree.ExpressionNode;
import Model.parser.CommandParser;
import View.viewUtils.FrontEndData;
import utils.RawCommand;

public class ModelExecutionEngine implements I_ExecutionEngine {

	private Map<String, Double> variableMap;
	private CommandParser myParser;
	private TurtleManager myTurtleManager;
	private SLogoSettings mySettings;
	
	public ModelExecutionEngine(SLogoSettings settings) {
		variableMap = new HashMap<String, Double>();
		mySettings = settings;
		myParser = new CommandParser();
		myTurtleManager = new TurtleManager();
	}

	@Override
	public FrontEndData runOp(RawCommand runCommand) {
		myTurtleManager.makeTurtle();
		Queue<ExpressionNode> runNodes = myParser.parse(runCommand.getCommandString(), runCommand.getLanguage(), myTurtleManager);
		BackEndData initData = new BackEndData();
		while(!runNodes.isEmpty()) {
			runNodes.poll().getValue(initData, variableMap, mySettings);
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
