package View.varDisplay;

import java.util.Map;
import java.util.TreeMap;
import View.FrontEndData;

/**
 * @author Yuxiang He
 *
 */
public class VarDispData extends FrontEndData {
	private Map<String, String> map;
	
	public VarDispData() {
		this(new TreeMap<String, String>());
	}
	
	public VarDispData(Map<String, String> _map) {
		super(new VarDisplay().getClass());
		map=_map;
	}

	@Override
	public Object getData() {
		return map;
	}
}
