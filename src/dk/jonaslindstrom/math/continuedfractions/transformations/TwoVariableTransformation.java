package dk.jonaslindstrom.math.continuedfractions.transformations;

import java.math.BigInteger;

/**
 * Instances of this class represents maps of the form <i>(a + bx + cy + dxy) /
 * (e + fx + gy + hxy)</i> for <i>a, b, c, d, e, f, g, h ∈ ℤ</i>.
 * 
 * @author Jonas Lindstrøm (jonas.lindstrom@alexandra.dk)
 *
 */
public class TwoVariableTransformation {

	public BigInteger a, b, c, d, e, f, g, h;

	public TwoVariableTransformation(BigInteger a, BigInteger b, BigInteger c, BigInteger d,
			BigInteger e, BigInteger f, BigInteger g, BigInteger h) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
		this.g = g;
		this.h = h;
	}

	public TwoVariableTransformation(int a, int b, int c, int d, int e, int f, int g, int h) {
		this(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c),
				BigInteger.valueOf(d), BigInteger.valueOf(e), BigInteger.valueOf(f),
				BigInteger.valueOf(g), BigInteger.valueOf(h));
	}
	
	@Override
	public String toString() {
		return a + ", " + b + ", " + c + ", " + d + ", " + e + ", " + f + ", " + g + ", " + h;
	}

}
