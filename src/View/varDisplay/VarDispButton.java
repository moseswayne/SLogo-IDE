package View.varDisplay;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class VarDispButton {
	private Button button;
	private TextField textField;
	private final String SEPERATION_STR = " : ";
	private String varName;
	private String varVal;
	
	public VarDispButton(String _varName, String _varVal) {
		varName=_varName;
		varVal=_varVal;
		textField=new TextField();
		button=new Button(String.format("%s%s%s", varName, SEPERATION_STR, varVal));
		button.setOnMouseClicked(action->{
			textField.setText(varVal);
			button.setGraphic(textField);
		});
	}

	public void setTextActionAfterEnter(Runnable r){
		textField.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				String prevVarVal=""+varVal;
				button.setGraphic(null);
				varVal=textField.getText().trim();
				button.setText(String.format("%s%s%s", varName, SEPERATION_STR, varVal));
				if(!prevVarVal.equals(varVal)){
					r.run();
				}
			}
		});
	}
	
	public void setPrefWidth(double width){
		button.setPrefWidth(width);
		textField.setMaxWidth(width-10);
	}
	
	public String getVarVal(){
		return varVal;
	}
	
	public Button getButton(){
		return button;
	}
}
