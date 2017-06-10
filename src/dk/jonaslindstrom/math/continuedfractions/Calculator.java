package dk.jonaslindstrom.math.continuedfractions;

import dk.jonaslindstrom.math.continuedfractions.transformations.MöbiusTransformation;
import dk.jonaslindstrom.math.continuedfractions.transformations.MöbiusTransformedContinuedFraction;
import dk.jonaslindstrom.math.continuedfractions.transformations.TransformedContinuedFraction;
import dk.jonaslindstrom.math.continuedfractions.transformations.TwoVariableTransformation;

public class Calculator {

	public static ContinuedFraction add(ContinuedFraction a, ContinuedFraction b) {
		return new TransformedContinuedFraction(a, b,
				new TwoVariableTransformation(0, 1, 1, 0, 1, 0, 0, 0));
	}

	public static ContinuedFraction multiply(ContinuedFraction a, ContinuedFraction b) {
		return new TransformedContinuedFraction(a, b,
				new TwoVariableTransformation(0, 0, 0, 1, 1, 0, 0, 0));
	}

	public static ContinuedFraction invert(ContinuedFraction a) {
		return new MöbiusTransformedContinuedFraction(a, new MöbiusTransformation(1, 0, 0, 1));
	}

}
