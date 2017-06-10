package dk.jonaslindstrom.math.continuedfractions.implementations;

import java.util.Arrays;

import dk.jonaslindstrom.math.continuedfractions.NoMoreTermsException;

public class FiniteContinuedFraction extends ContinuedFractionIntWrapper {

	private int counter = 0;
	private int[] terms;
	
	public FiniteContinuedFraction(int ... terms) {
		this.terms = terms;
	}
	
	@Override
	public int getNextTermAsInt() throws NoMoreTermsException {
		if (counter < terms.length) {
			return terms[counter++];
		}
		throw new NoMoreTermsException();
	}
	
	@Override
	public String toString() {
		return Arrays.toString(terms);
	}

	@Override
	public void reset() {
		counter = 0;
	}
	
}
