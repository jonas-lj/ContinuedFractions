package dk.jonaslindstrom.math.continuedfractions.transformations;

import java.math.BigInteger;

import dk.jonaslindstrom.math.continuedfractions.ContinuedFraction;
import dk.jonaslindstrom.math.continuedfractions.NoMoreTermsException;

public class MöbiusTransformedContinuedFraction implements ContinuedFraction {

	private MöbiusTransformation current, original;
	private boolean noMoreTerms = false;
	private ContinuedFraction x;

	public MöbiusTransformedContinuedFraction(ContinuedFraction x, MöbiusTransformation transformation) {
		this.current = this.original = transformation;
		this.x = x;
	}

	@Override
	public BigInteger getNextTerm() throws NoMoreTermsException {
		if (noMoreTerms) {
			throw new NoMoreTermsException();
		}

		while (current.c.equals(BigInteger.ZERO) || current.d.equals(BigInteger.ZERO)
				|| !current.a.divide(current.c).equals(current.b.divide(current.d))) {
			try {
				BigInteger p = x.getNextTerm();
				transformInput(p);
			} catch (NoMoreTermsException e) {
				transformInputInfinity();
				noMoreTerms = true;
				break;
			}
		}

		BigInteger q = current.a.divide(current.c);
		transformOutput(q);
		return q;
	}

	@Override
	public void reset() {
		this.current = original;
		this.noMoreTerms = false;
		x.reset();
	}

	private void transformOutput(BigInteger q) {
		current = new MöbiusTransformation(current.c, current.d, current.a.subtract(current.c.multiply(q)),
				current.b.subtract(current.d.multiply(q)));
	}

	private void transformInput(BigInteger p) {
		current = new MöbiusTransformation(current.b, current.a.add(current.b.multiply(p)), current.d,
				current.c.add(current.d.multiply(p)));
	}

	private void transformInputInfinity() {
		current = new MöbiusTransformation(current.b, current.b, current.d, current.d);
	}

}
