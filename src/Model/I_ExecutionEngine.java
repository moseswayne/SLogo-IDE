package Model;

import java.util.Collection;

import View.FrontEndData;

public interface I_ExecutionEngine {

	public Collection<FrontEndData> runOp(Command runCommand);
	
}
