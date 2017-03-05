package Model;

import View.FrontEndData;
import utils.RawCommand;

public interface I_ExecutionEngine {

	public FrontEndData runOp(RawCommand runCommand) throws Exception;
	
}
