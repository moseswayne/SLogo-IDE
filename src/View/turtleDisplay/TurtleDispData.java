package View.turtleDisplay;

import View.FrontEndData;
import utils.TurtleParameters;

public class TurtleDispData extends FrontEndData {
	TurtleParameters position;
	private boolean penDown;
	
	public TurtleDispData(TurtleParameters _position, boolean _penDown) {
		super(new TurtleDisplay(5, 5).getClass());
		position=_position;
		penDown=_penDown;
	}

	@Override
	public Object getData() {
		return position;
	}
	
	public boolean isPenDown() {
		return penDown;
	}
}
