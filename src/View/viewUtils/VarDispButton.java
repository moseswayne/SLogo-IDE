package View.viewUtils;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class VarDispButton extends Button {
	private TextField textField;
	private final String SEPERATION_STR = " = ";
	private final String DEAFAULT_VALUE = "0";
	private final double TEXT_FIELD_LENGTH_OFFSET=18;
	private String varName;
	private String varVal;
	
	public VarDispButton(String _varName, String _varVal) {
		varName=_varName;
		varVal=_varVal;
		textField=new TextField();
		this.setText(String.format("%s%s%s", varName, SEPERATION_STR, varVal));
		this.setOnMouseClicked(action->{
			textField.setText(varVal);
			this.setGraphic(textField);
		});
	}

	public void setTextActionAfterEnter(Runnable r){
		textField.setOnAction(e->{
				String prevVarVal=""+varVal;
				setGraphic(null);
				varVal=textField.getText().trim();
				setText(String.format("%s%s%s", varName, SEPERATION_STR, varVal));
				if(varVal.length()==0){
					varVal=DEAFAULT_VALUE;
				}
				if(!prevVarVal.equals(varVal)){
					r.run();
				}
				setText(String.format("%s%s%s", varName, SEPERATION_STR, varVal));
		});
	}
	
	public void setPrefWidthForAll(double width){
		this.setPrefWidth(width);
		textField.setMaxWidth(width-TEXT_FIELD_LENGTH_OFFSET);
	}
	
	public String getVarVal(){
		return varVal;
	}
	
	public String getVarName(){
		return varName;
	}
}
