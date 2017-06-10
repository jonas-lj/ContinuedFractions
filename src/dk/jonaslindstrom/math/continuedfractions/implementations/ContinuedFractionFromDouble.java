package dk.jonaslindstrom.math.continuedfractions.implementations;

import java.math.BigInteger;

import dk.jonaslindstrom.math.continuedfractions.ContinuedFraction;
import dk.jonaslindstrom.math.continuedfractions.NoMoreTermsException;

public class ContinuedFractionFromDouble implements ContinuedFraction {

	private double original, current;
	private boolean moreTerms = true;

	/**
	 * Create a continued fraction for the double value <i>x</i>.
	 * 
	 * @param x
	 */
	public ContinuedFractionFromDouble(double x) {
		this.original = this.current = x;
	}

	@Override
	public BigInteger getNextTerm() throws NoMoreTermsException {
		if (!moreTerms) {
			throw new NoMoreTermsException();
		}

		double floor = Math.floor(current);
		double fractionalPart = current - floor;
		BigInteger term = BigInteger.valueOf((long) floor);

		if (fractionalPart == 0.0) {
			moreTerms = false;
			return term;
		}

		current = 1.0 / fractionalPart;
		return term;
	}

	@Override
	public void reset() {
		current = original;
		moreTerms = true;
	}

}
