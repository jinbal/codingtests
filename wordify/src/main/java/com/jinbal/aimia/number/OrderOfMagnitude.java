/**
 * 
 */
package com.jinbal.aimia.number;

/**
 * @author jin.bal
 *
 */
public enum OrderOfMagnitude {
	ONE(1,9),
	TEN(10,99),
	HUNDRED(100,999),
	THOUSAND(1000,999999),
	MILLION(1000000,999999999);
	//add numerical support for higher orders of mag here.
	
	private final long magnitude;
	private final long maxValue;
	public static final OrderOfMagnitude MIN_ORDER_OF_MAGNITUDE = HUNDRED;
	OrderOfMagnitude(long modValue,long maxValue) {
		this.magnitude=modValue;
		this.maxValue = maxValue;
	}
	public long getMagnitude() {
		return magnitude;
	}
	
	public long getMaxValue() {
		return maxValue;
	}
	
	public boolean hasMagnitude(long value) {
		return (value >= getMagnitude() && value <=getMaxValue()) ;
	}
	
	public long getWholeUnits(long value) {
		return  (value - (value % getMagnitude())) / getMagnitude();
	}
	
	public long getRemainder(long value) {
		return value % getMagnitude();
	}
	
	public boolean isSmallerThan(OrderOfMagnitude magnitude) {
		return getMagnitude() < magnitude.getMagnitude();
	}

	
	public static OrderOfMagnitude findOrderOfMagnitude(long number) throws OrderOfMagnitudeException {

		OrderOfMagnitude[] magnitudes = values();
		for (OrderOfMagnitude orderOfMagnitude : magnitudes) {
			if(orderOfMagnitude.hasMagnitude(number)) {
				return orderOfMagnitude;
			}
		}
		throw new OrderOfMagnitudeException("unsupported OrderOfMagnitude for "+number);
	}
	
}
