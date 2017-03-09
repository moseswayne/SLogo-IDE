package controller;

import Operations.CommandOperation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Constructor;
import java.util.Map;

/**
 * Given a command name, 
 * churns out the corresponding command object
 * @author Justin Yang
 * @author Yuxiang He
 *
 */
public class OperationFactory {
	
	private Map<String, String> commandMap;

	public OperationFactory(Map<String, String> cmdMap) {
		commandMap = cmdMap;
	}
	
	public CommandOperation getOp(String cmdName) {
		if (!isValidOp(cmdName)) {
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
	
	public boolean isValidOp(String cmd) {
		return commandMap.containsKey(cmd) && commandMap.get(cmd).length() != 0;
	}
}
