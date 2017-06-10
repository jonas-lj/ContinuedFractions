package dk.jonaslindstrom.math.continuedfractions.implementations;

import java.math.BigInteger;

import dk.jonaslindstrom.math.continuedfractions.ContinuedFraction;
import dk.jonaslindstrom.math.continuedfractions.NoMoreTermsException;

public class ContinuedFractionFromRational implements ContinuedFraction {

	private BigInteger n, d;
	private BigInteger nOriginal, dOriginal;
	private boolean noMoreTerms = false;

	/**
	 * Create a continued fraction representing the rational number <i>n / d</i>
	 * .
	 * 
	 * @param n
	 * @param d
	 */
	public ContinuedFractionFromRational(int n, int d) {
		this.n = this.nOriginal = BigInteger.valueOf(n);
		this.d = this.dOriginal = BigInteger.valueOf(d);
	}

	public ContinuedFractionFromRational(BigInteger n, BigInteger d) {
		this.n = this.nOriginal = n;
		this.d = this.dOriginal = d;
	}

	@Override
	public BigInteger getNextTerm() throws NoMoreTermsException {
		if (noMoreTerms) {
			throw new NoMoreTermsException();
		}

		BigInteger[] q = n.divideAndRemainder(d);

		if (q[1].equals(BigInteger.ZERO)) {
			this.noMoreTerms = true;
		}

		this.n = d;
		this.d = q[1];

		return q[0];
	}

	@Override
	public void reset() {
		n = nOriginal;
		d = dOriginal;
		noMoreTerms = false;
	}

}
