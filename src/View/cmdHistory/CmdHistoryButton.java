package View.cmdHistory;

import javafx.scene.control.Button;
import utils.RawCommand;

public class CmdHistoryButton extends Button{
	private RawCommand myCommand;
	
	public CmdHistoryButton(RawCommand _myCommand) {
		myCommand=_myCommand;
		this.setText(String.format("%s", myCommand.getCommandString()));
	}

	public RawCommand getRawCommand(){
		return myCommand;
	}

}
