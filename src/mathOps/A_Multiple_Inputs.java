package mathOps;

import java.util.function.BiFunction;

import Operations.A_MathOperation;

public abstract class A_Multiple_Inputs extends A_MathOperation {
	protected static double solve(Iterable<Double> source, Double changingValue,
			BiFunction<Double, Double, Double> operation) {
		for (Double instance : source) {
			changingValue = operation.apply(changingValue, instance);
		}
		return changingValue;
	}
}
