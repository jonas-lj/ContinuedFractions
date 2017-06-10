package dk.jonaslindstrom.math.continuedfractions;

import java.math.BigInteger;
import java.util.Arrays;

public class ContinuedFractionEvaluator {

	private int maxTerms;

	/**
	 * Create a new evaluator which will allow up to <i>maxTerms</i> terms of
	 * the continued fractions for its evaluations.
	 * 
	 * @param maxTerms
	 */
	public ContinuedFractionEvaluator(int maxTerms) {
		this.maxTerms = maxTerms;
	}
	
	private BigInteger[] getConvergent(ContinuedFraction c, int maxTerms) {
		BigInteger h2 = BigInteger.ZERO, h1 = BigInteger.ONE;
		BigInteger k2 = BigInteger.ONE, k1 = BigInteger.ZERO;

		for (int i = 0; i < maxTerms; i++) {
			try {
				BigInteger a = c.getNextTerm();
				BigInteger h = a.multiply(h1).add(h2);
				h2 = h1; h1 = h;
				
				BigInteger k = a.multiply(k1).add(k2);
				k2 = k1; k1 = k;
			} catch (NoMoreTermsException e) {
				break;
			}
		}		
		c.reset();
		return new BigInteger[] {h1, k1};
	}
	
	public double evaluate(ContinuedFraction c) {
		BigInteger[] convergent = getRationalApproximation(c);
		return convergent[0].doubleValue() / convergent[1].doubleValue();
	}
	
	/**
	 * Get the best possible rational approximation of the continued fraction
	 * <i>c</i> within the limit of the number of terms of this evaluator.
	 * 
	 * @param c
	 * @return
	 */
	public BigInteger[] getRationalApproximation(ContinuedFraction c) {
		return getConvergent(c, maxTerms);
	}
	
	/**
	 * Get the first <i>m</i> terms (or until no more terms exist) of the
	 * continued fraction <i>c</i> as a list.
	 * 
	 * @param c
	 * @return
	 */
	public BigInteger[] getTerms(ContinuedFraction c, int m) {
		BigInteger[] terms = new BigInteger[m];
		for (int i = 0; i < m; i++) {
			try {
				terms[i] = c.getNextTerm();
			} catch (NoMoreTermsException e) {
				terms = Arrays.copyOf(terms, i);
				break;
			}
		}
		c.reset();
		return terms;
	}
	
}
