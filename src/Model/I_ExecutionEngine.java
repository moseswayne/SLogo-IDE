package Model;

import java.util.Collection;

import View.FrontEndData;
import utils.RawCommand;

public interface I_ExecutionEngine {

	public Collection<FrontEndData> runOp(RawCommand runCommand);
	
}
