package Model.parser;

import java.lang.reflect.InvocationTargetException;

import Model.operations.CommandOperation;

import java.lang.reflect.Constructor;

/**
 * Given a command name, 
 * churns out the corresponding command object
 * @author Justin Yang
 * @author Yuxiang He
 * @author Moses Wayne
 *
 */
public class OperationFactory {

	public OperationFactory() {
	}
	
	public CommandOperation getOp(String cmdName, String packageDir) {
		CommandOperation cmdOp;
		try {
			Class<?> cmdClass = Class.forName(packageDir+cmdName);
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
	
	/*
	public boolean isValidOp(String cmd) {
		return commandMap.containsKey(cmd) && commandMap.get(cmd).length() != 0;
	}*/
}
