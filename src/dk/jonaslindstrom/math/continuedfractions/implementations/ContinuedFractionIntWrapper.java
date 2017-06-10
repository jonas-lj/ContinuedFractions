package dk.jonaslindstrom.math.continuedfractions.implementations;

import java.math.BigInteger;

import dk.jonaslindstrom.math.continuedfractions.ContinuedFraction;
import dk.jonaslindstrom.math.continuedfractions.NoMoreTermsException;

public abstract class ContinuedFractionIntWrapper implements ContinuedFraction {

	public abstract int getNextTermAsInt() throws NoMoreTermsException;
	
	@Override
	public BigInteger getNextTerm() throws NoMoreTermsException {
		return BigInteger.valueOf(getNextTermAsInt());
	}

	@Override
	public abstract void reset();

}
