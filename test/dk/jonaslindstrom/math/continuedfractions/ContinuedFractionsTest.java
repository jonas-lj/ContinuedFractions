package dk.jonaslindstrom.math.continuedfractions;

import org.junit.Assert;
import org.junit.Test;

import dk.jonaslindstrom.math.continuedfractions.implementations.ContinuedFractionFromDouble;

public class ContinuedFractionsTest {

	@Test
	public void test() {
		
		double X = 1.8;
		double Y = 0.2;
		double Z = 0.5;
		
		double expected = (X+Y) / Z;
		
		System.out.println(expected);
		ContinuedFractionEvaluator evaluator = new ContinuedFractionEvaluator(32);
		
		ContinuedFraction x = new ContinuedFractionFromDouble(X);
		ContinuedFraction y = new ContinuedFractionFromDouble(Y);
		ContinuedFraction z = new ContinuedFractionFromDouble(Z);
		
		ContinuedFraction prod = Calculator.multiply(Calculator.add(x, y), Calculator.invert(z));
		double actual = evaluator.evaluate(prod);
		Assert.assertEquals(expected, actual, 0.0001);
	}
	
}
