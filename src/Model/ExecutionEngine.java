package Model;

import java.util.Collection;

import View.FrontEndData;

public interface ExecutionEngine {

	public Collection<FrontEndData> runOp(Command runCommand);
	
}
