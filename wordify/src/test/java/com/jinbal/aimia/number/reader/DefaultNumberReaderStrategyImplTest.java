package com.jinbal.aimia.number.reader;

import static junit.framework.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.jinbal.aimia.number.NumberSegment;
import com.jinbal.aimia.number.OrderOfMagnitude;

public class DefaultNumberReaderStrategyImplTest {
	
	private DefaultNumberReaderStrategyImpl underTest = new DefaultNumberReaderStrategyImpl();
	
	@Test
	public void shouldSegmentNumberAndReturnInDescendingMagnitudeOrder() throws Exception {
		long testNumber = 11150175;
		List<NumberSegment> segs = underTest.readNumber(testNumber);
		assertEquals("Incorrect number of segments found", 3, segs.size());
		NumberSegment[] segArr = new NumberSegment[segs.size()];
		segs.toArray(segArr);
		NumberSegment millions = segArr[0];
		assertEquals("Incorrect digits", 11, millions.getDigits());
		assertEquals("Incorrect magnitude", OrderOfMagnitude.MILLION,millions.getMagnitude());
		
		NumberSegment thousands = segArr[1];
		assertEquals("Incorrect digits", 150, thousands.getDigits());
		assertEquals("Incorrect magnitude", OrderOfMagnitude.THOUSAND,thousands.getMagnitude());
		
		NumberSegment hundreds = segArr[2];
		assertEquals("Incorrect digits", 175, hundreds.getDigits());
		assertEquals("Incorrect magnitude", OrderOfMagnitude.HUNDRED,hundreds.getMagnitude());
	}
	
	@Test
	public void shouldSegmentNumberLessThan1000IntoSingleHundredSegment() throws Exception {
		long testNumber = 536;
		List<NumberSegment> segs = underTest.readNumber(testNumber);
		assertEquals("Incorrect number of segments found", 1, segs.size());
		NumberSegment[] segArr = new NumberSegment[segs.size()];
		segs.toArray(segArr);
		NumberSegment hundreds = segArr[0];
		assertEquals("Incorrect digits", 536, hundreds.getDigits());
		assertEquals("Incorrect magnitude", OrderOfMagnitude.HUNDRED,hundreds.getMagnitude());
	}
}
