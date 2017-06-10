package dk.jonaslindstrom.math.continuedfractions.implementations;

import dk.jonaslindstrom.math.continuedfractions.ContinuedFraction;
import dk.jonaslindstrom.math.continuedfractions.NoMoreTermsException;

/**
 * This class allows the creation of continued fractions for (approximations of)
 * common constants.
 * 
 * @author Jonas Lindstrøm (jonas.lindstrom@alexandra.dk)
 *
 */
public class ContinuedFractionFactory {

	public enum Constant { PI, GOLDEN_RATIO, E };
	
	public static ContinuedFraction createContinuedFraction(Constant c) {
		
		switch (c) {
			case PI:
				// https://oeis.org/A001203
				return new FiniteContinuedFraction(3, 7, 15, 1, 292, 1, 1, 1, 2, 1, 3, 1, 14, 2, 1, 1, 2,
						2, 2, 2, 1, 84, 2, 1, 1, 15, 3, 13, 1, 4, 2, 6, 6, 99, 1, 2, 2, 6, 3, 5, 1, 1, 6, 8,
						1, 7, 1, 2, 3, 7, 1, 2, 1, 1, 12, 1, 1, 1, 3, 1, 1, 8, 1, 1, 2, 1, 6, 1, 1, 5, 2, 2,
						3, 1, 2, 4, 4, 16, 1, 161, 45, 1, 22, 1, 2, 2, 1, 4, 1, 2, 24, 1, 2, 1, 3, 1, 2, 1);
				
			case GOLDEN_RATIO:
				return new CyclicContinuedFraction(1, 1);
				
			case E:
				return new ContinuedFractionIntWrapper() {
					int counter = 0;
					
					@Override
					public int getNextTermAsInt() throws NoMoreTermsException {
						int term;
						if (counter == 0) {
							term = 2;
						} else {
							int mod3 = Math.floorMod(counter, 3);
							if (mod3 == 2) {
								int cycle = Math.floorDiv(counter, 3);
								term = 2 * (cycle + 1);
							} else {
								term = 1;
							}							
						}
						counter++;
						return term;
					}

					@Override
					public void reset() {
						counter = 0;						
					}
				
				};
				
			default:
				return null;
		}
	}

}
