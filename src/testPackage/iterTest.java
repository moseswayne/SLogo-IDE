package testPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Model.backEndUtils.BackEndData;
import Model.backEndUtils.ParameterObject;
import Model.operations.CommandOperation;
import Model.operations.commandOps.basicOps.And;

public class iterTest {

	public static void main(String args[]) {
		CommandOperation and = new And();
		ArrayList<String> p = new ArrayList<>();
		p.add("0");
		p.add("0");
		ParameterObject test = new ParameterObject(p, new HashMap<String,Double>());
		BackEndData data = new BackEndData();
		and.execute(test, data);
		System.out.println(data.getMyValue());
	}
}
