/**
 * 
 */
package com.jinbal.aimia.number;




/**
 * @author jin.bal
 *
 */
public class NumberSegment implements Comparable<NumberSegment>{
	public static final long MAX_SEGMENT_VALUE=999;
	private long totalNumber;
	private long digits;
	private OrderOfMagnitude magnitude;
	
	public NumberSegment(long digits,OrderOfMagnitude magnitude,long totalNumber)throws NumberSegmentException {
		if(digits> MAX_SEGMENT_VALUE) {
			throw new NumberSegmentException("Number segment has too many digits:"+digits+" max value:"+MAX_SEGMENT_VALUE);
		}
		if(digits <0 ) {
			throw new NumberSegmentException("digits must be 0 or greater not:"+digits);
		}
		if(totalNumber <0 ) {
			throw new NumberSegmentException("totalNumber must be 0 or greater not:"+totalNumber);
		}
		if(magnitude == null ) {
			throw new NumberSegmentException("no OrderOfMagnitude specified");
		}
		this.digits=digits;
		this.magnitude=magnitude;
		this.totalNumber = totalNumber;
	}
	public NumberSegment(long digits,OrderOfMagnitude magnitude)throws NumberSegmentException {
		this(digits,magnitude,digits);
	}
	private NumberSegment() {}
	

	public boolean isLessThanHundred() {
		return getMagnitude().getMagnitude() <=  OrderOfMagnitude.HUNDRED.getMagnitude();
	}
	
	public boolean isSegmentPartOfLargerNumber() throws OrderOfMagnitudeException {
		if(totalNumber ==0 ) {
			return false;
		}
		OrderOfMagnitude totalMagnitude = OrderOfMagnitude.findOrderOfMagnitude(getTotalNumber());
		return getMagnitude().getMagnitude() <= totalMagnitude.getMagnitude();
	}
	
	public long getTotalNumber() {
		return totalNumber;
	}
	public long getDigitsHundreds() {
		return OrderOfMagnitude.HUNDRED.getWholeUnits(digits);
	}
	
	public long getDigitsTens() {
		long tens = OrderOfMagnitude.HUNDRED.getRemainder(digits);
		return OrderOfMagnitude.TEN.getWholeUnits(tens);
	}
	
	public long getDigitsOnes() {
		long tens = OrderOfMagnitude.HUNDRED.getRemainder(digits);
		return OrderOfMagnitude.TEN.getRemainder(tens);
	}
	public long getDigits() {
		return digits;
	}
	
	public long getRemainder() {
		return OrderOfMagnitude.HUNDRED.getRemainder(digits);
	}
	public boolean hasRemainder() {
		long remainder = OrderOfMagnitude.HUNDRED.getRemainder(digits);
		return remainder >0;
	}
	
	public OrderOfMagnitude getMagnitude() {
		return magnitude;
	}
	/**
	 * sorts by OrderOfMagnitude ascending
	 */
	public int compareTo(NumberSegment o) {
		OrderOfMagnitude compareMagnitude = o.getMagnitude();
		OrderOfMagnitude thisMagnitude = getMagnitude();
		if(thisMagnitude.getMagnitude() < compareMagnitude.getMagnitude()) {
			return -1;
		} else if(thisMagnitude.getMagnitude() >  compareMagnitude.getMagnitude()) {
			return 1;
		} else {
			return 0;
		}
	}
}
