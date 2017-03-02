package mathOps;

import java.util.Random;

import Operations.A_MathOperation;
import utils.ParameterObject;
/**
 * Returns a random number that is less than the input maximum
 * @author Elbert
 *
 */
public class GenerateRandom extends A_MathOperation{
//TODO see if random generates only btn 0 and 1 or if it can include 1
	@Override
	protected double evaluate(ParameterObject params) {
		Random rn = new Random();
		//return rn.nextInt(((Double) params.getDoubleAt(0)).intValue());
		//next double is between 0.0 and 1.0
		//multiply by the maximum allowed value and will get a positive number below it
		double checkBelow = rn.nextDouble();
		while(checkBelow >= 1){
			checkBelow = rn.nextDouble();
		}
		return checkBelow * params.getDoubleAt(0);
	}

}
