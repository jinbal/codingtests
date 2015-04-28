/**
 * 
 */
package com.jinbal.aimia.number.reader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.jinbal.aimia.number.NumberSegment;
import com.jinbal.aimia.number.NumberSegmentException;
import com.jinbal.aimia.number.OrderOfMagnitude;
import com.jinbal.aimia.number.OrderOfMagnitudeException;

/**
 * @author Jin2
 *
 */
public class DefaultNumberReaderStrategyImpl implements NumberReadStrategy {

	/* (non-Javadoc)
	 * @see com.jinbal.aimia.number.NumberReadStrategy#readNumber(long)
	 */
	public  List<NumberSegment> readNumber(long totalNumber) throws NumberSegmentException, OrderOfMagnitudeException {
		ArrayList<NumberSegment> numberSegments = new ArrayList<NumberSegment>();
		long currentNumber = totalNumber;
		while (currentNumber > 0) {
			if(currentNumber <= OrderOfMagnitude.MIN_ORDER_OF_MAGNITUDE.getMaxValue()) {
				NumberSegment numberSegment = new NumberSegment(currentNumber, OrderOfMagnitude.MIN_ORDER_OF_MAGNITUDE,totalNumber);
				numberSegments.add(numberSegment);
				break;
			}
			OrderOfMagnitude actualMagnitude = OrderOfMagnitude.findOrderOfMagnitude(currentNumber);
			NumberSegment numberSegment = new NumberSegment(actualMagnitude.getWholeUnits(currentNumber), actualMagnitude,totalNumber);
			numberSegments.add(numberSegment);
			currentNumber = actualMagnitude.getRemainder(currentNumber);
		}
		Collections.sort(numberSegments);
		Collections.reverse(numberSegments);
		return numberSegments;
	}
}
