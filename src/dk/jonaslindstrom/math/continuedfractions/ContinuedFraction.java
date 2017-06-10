package dk.jonaslindstrom.math.continuedfractions;

import java.math.BigInteger;

/**
 * Instances of this class represents a continued fraction. The API allows an
 * iterative access to the continued fraction which allows the caller to request
 * the next term.
 * 
 * @author Jonas Lindstrøm (jonas.lindstrom@alexandra.dk)
 *
 */
public interface ContinuedFraction {

	/**
	 * Get the next term of this continued fraction. If no such term exists, a
	 * {@link NoMoreTermsException} is thrown.
	 * 
	 * @return
	 * @throws NoMoreTermsException
	 */
	public BigInteger getNextTerm() throws NoMoreTermsException;

	/**
	 * Reset the iteration s.t. {@link #getNextTerm()} returns the very first
	 * term on next call.
	 */
	public void reset();

}
