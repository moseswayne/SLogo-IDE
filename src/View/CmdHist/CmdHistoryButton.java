package View.CmdHist;

import javafx.scene.control.Button;
import utils.RawCommand;

public class CmdHistoryButton extends Button{
	private RawCommand myCommand;
	
	public CmdHistoryButton(RawCommand _myCommand) {
		myCommand=_myCommand;
		this.setText(String.format("%s\n(%s)", myCommand.getCommandString(), myCommand.getLanguage().toString().substring(0, 2)));
	}

	public RawCommand getRawCommand(){
		return myCommand;
	}

}
