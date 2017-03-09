package controller;

import Operations.CommandOperation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Constructor;
import java.util.Map;

public class OperationFactory {
	
	private Map<String, String> commandMap;

	public OperationFactory(Map<String, String> cmdMap) {
		commandMap = cmdMap;
	}
	
	public CommandOperation getOp(String cmdName) {
		if (!isValidCommand(cmdName)) {
			throw new ParserException(ParserException.INVALID_CMD, cmdName);
		}
		CommandOperation cmdOp;
		try {
			Class<?> cmdClass = Class.forName(cmdName);
			Constructor<?> cmdConstructor = cmdClass.getConstructor();
			cmdOp = (CommandOperation) cmdConstructor.newInstance();
		}
		catch (ClassNotFoundException | NoSuchMethodException |
			   InstantiationException | InvocationTargetException |
			   IllegalAccessException e) {
			throw new ParserException(e);
		}
		return cmdOp;
	}
	
	private boolean isValidCommand(String cmd) {
		return commandMap.containsKey(cmd) && commandMap.get(cmd).length() != 0;
	}
}
