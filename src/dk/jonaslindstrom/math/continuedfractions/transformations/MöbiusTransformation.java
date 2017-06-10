package dk.jonaslindstrom.math.continuedfractions.transformations;

import java.math.BigInteger;

/**
 * Instances of this class represents a Möbius transformation of the form, that
 * is a map of the form <i>(a + bx) / (c + dx)</i> for integers <i>a, b, c, d ∈
 * ℤ</i> with <i>ad - bc ≠ 0</i>.
 * 
 * @author Jonas Lindstrøm (jonas.lindstrom@alexandra.dk)
 *
 */
public class MöbiusTransformation {

	public BigInteger a, b, c, d;
	
	public MöbiusTransformation(int a, int b, int c, int d) {
		this(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c), BigInteger.valueOf(d));
	}

	public MöbiusTransformation(BigInteger a, BigInteger b, BigInteger c, BigInteger d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}
		
}
