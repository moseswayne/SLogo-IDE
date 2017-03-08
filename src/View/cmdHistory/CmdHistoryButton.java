package View.cmdHistory;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import utils.RawCommand;
import javafx.event.EventHandler;

public class CmdHistoryButton{
	private Button myButton;
	private RawCommand myCommand;
	
	public CmdHistoryButton(RawCommand _myCommand) {
		myCommand=_myCommand;
		myButton=new Button(myCommand.getCommandString());
	}
	
	public void setOnMouseClicked(EventHandler<? super MouseEvent> value){
		myButton.setOnMouseClicked(value);
	}
	
	public void setPrefWidth(double width){
		myButton.setPrefWidth(width);
	}
	
	public Button getButton(){
		return myButton;
	}
	
	public RawCommand getRawCommand(){
		return myCommand;
	}

}
