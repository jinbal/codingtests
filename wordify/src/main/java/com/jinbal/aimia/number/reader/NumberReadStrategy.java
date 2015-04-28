package com.jinbal.aimia.number.reader;

import java.util.List;

import com.jinbal.aimia.number.NumberSegment;
import com.jinbal.aimia.number.NumberSegmentException;
import com.jinbal.aimia.number.OrderOfMagnitudeException;

/**
 * 
 * @author Jin2
 *
 */
public interface NumberReadStrategy {
	public List<NumberSegment> readNumber(long totalNumber) throws NumberSegmentException, OrderOfMagnitudeException;
}
