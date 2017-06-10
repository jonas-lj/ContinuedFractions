package dk.jonaslindstrom.math.continuedfractions.transformations;

import java.math.BigInteger;

import dk.jonaslindstrom.math.continuedfractions.ContinuedFraction;
import dk.jonaslindstrom.math.continuedfractions.NoMoreTermsException;

/**
 * Instances of this class represents the transformation of two continued
 * fractions <i>x, y</i> using a {@link TwoVariableTransformation}. This is done
 * using <a href="http://perl.plover.com/yak/cftalk/INFO/gosper.txt">Gosper's
 * algorithm</a>.
 * 
 * @author Jonas Lindstrøm (jonas.lindstrom@alexandra.dk)
 *
 */
public class TransformedContinuedFraction implements ContinuedFraction {

	private ContinuedFraction x, y;
	private TwoVariableTransformation s, original;

	public TransformedContinuedFraction(ContinuedFraction x, ContinuedFraction y,
			TwoVariableTransformation transformation) {
		this.x = x;
		this.y = y;
		this.s = this.original = transformation;
	}

	/**
	 * Return true iff all the inputs are equal.
	 * 
	 * @param values
	 * @return
	 */
	private boolean isEqual(BigInteger... values) {
		for (int i = 1; i < values.length; i++) {
			if (!values[i].equals(values[0])) {
				return false;
			}
		}
		return true;
	}

	private boolean isZero(BigInteger x) {
		return x.equals(BigInteger.ZERO);
	}

	@Override
	public BigInteger getNextTerm() throws NoMoreTermsException {

		if (isEqual(BigInteger.ZERO, s.e, s.f, s.g, s.h)) {
			throw new NoMoreTermsException();
		}

		while (isZero(s.e) || isZero(s.f) || isZero(s.g) || isZero(s.h)
				|| !isEqual(s.a.divide(s.e), s.b.divide(s.f), s.c.divide(s.g), s.d.divide(s.h))) {

			if (isEqual(BigInteger.ZERO, s.g, s.h) || isEqual(BigInteger.ZERO, s.e, s.f)) {
				inputFromY();
			} else if (isZero(s.f) || isZero(s.e)) {
				inputFromX();
			} else if (isZero(s.g) || isZero(s.h)) {
				inputFromY();
			} else {
				BigInteger lhs = s.b.divide(s.f).subtract(s.a.divide(s.e)).abs();
				BigInteger rhs = s.a.divide(s.e).subtract(s.c.divide(s.g)).abs();
				
				if (lhs.compareTo(rhs) > 0) {
					inputFromX();
				} else {
					inputFromY();
				}
			}
		}

		return output();
	}

	private void inputFromX() {
		try {
			BigInteger p = x.getNextTerm();
			s = new TwoVariableTransformation(s.b, s.a.add(s.b.multiply(p)), s.d,
					s.c.add(s.d.multiply(p)), s.f, s.e.add(s.f.multiply(p)), s.h,
					s.g.add(s.h.multiply(p)));
		} catch (NoMoreTermsException e) {
			s = new TwoVariableTransformation(s.b, s.b, s.d, s.d, s.f, s.f, s.h, s.h);
		}
	}
		
	private void inputFromY() {
		try {
			BigInteger q = y.getNextTerm();
			s = new TwoVariableTransformation(s.c, s.d, s.a.add(s.c.multiply(q)),
					s.b.add(s.d.multiply(q)), s.g, s.h, s.e.add(s.g.multiply(q)),
					s.f.add(s.h.multiply(q)));
		} catch (NoMoreTermsException e) {
			s = new TwoVariableTransformation(s.c, s.d, s.c, s.d, s.g, s.h, s.g, s.h);
		}
	}
	
	private BigInteger output() {
		BigInteger r = s.a.divide(s.e);
		s = new TwoVariableTransformation(s.e, s.f, s.g, s.h, s.a.subtract(s.e.multiply(r)),
				s.b.subtract(s.f.multiply(r)), s.c.subtract(s.g.multiply(r)),
				s.d.subtract(s.h.multiply(r)));
		return r;
	}
	
	@Override
	public void reset() {
		x.reset();
		y.reset();
		s = original;
	}

}
