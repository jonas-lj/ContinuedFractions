package dk.jonaslindstrom.math.continuedfractions.implementations;

import dk.jonaslindstrom.math.continuedfractions.NoMoreTermsException;

public class CyclicContinuedFraction extends ContinuedFractionIntWrapper {

	private int a;
	private int[] cycle;
	private int counter = 0;
	
	public CyclicContinuedFraction(int a, int ... cycle) {
		this.a = a;
		this.cycle = cycle;
	}
	
	@Override
	public int getNextTermAsInt() throws NoMoreTermsException {
		int term;
		if (counter == 0) {
			term = a;
		} else {
			int local = Math.floorMod(counter - 1, cycle.length);
			term = cycle[local];
		}
		counter++;
		return term;
	}

	@Override
	public void reset() {
		counter = 0;
	}
	
}
