package View.turtleDisplay;

import View.FrontEndData;
import utils.TurtlePosition;

public class TurtleDispData extends FrontEndData {
	TurtlePosition position;
	private boolean penDown;
	
	public TurtleDispData(TurtlePosition _position, boolean _penDown) {
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
