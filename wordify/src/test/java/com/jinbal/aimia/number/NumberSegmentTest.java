package com.jinbal.aimia.number;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import org.junit.Test;

public class NumberSegmentTest {
	
	@Test(expected=NumberSegmentException.class)
	public void shouldThrowExceptionWhenSegmentContainsTooManyDigits() throws Exception{
		NumberSegment numberSegment = new NumberSegment(1000, OrderOfMagnitude.THOUSAND);
	}
	
	@Test(expected=NumberSegmentException.class)
	public void shouldThrowExceptionWhenSegmentContructedWithNullOrderOfMagnitude() throws Exception{
		NumberSegment numberSegment = new NumberSegment(1000, null);
	}
	
	@Test(expected=NumberSegmentException.class)
	public void shouldThrowExceptionWhenSegmentContructedWithNegativeDigits() throws Exception{
		NumberSegment numberSegment = new NumberSegment(-1, OrderOfMagnitude.MIN_ORDER_OF_MAGNITUDE);
	}
	
	@Test(expected=NumberSegmentException.class)
	public void shouldThrowExceptionWhenSegmentContructedWithNegativeTotalNumber() throws Exception{
		NumberSegment numberSegment = new NumberSegment(1, OrderOfMagnitude.MIN_ORDER_OF_MAGNITUDE,-1);
	}
	
	
	
	@Test
	public void shouldConstructNumberSegmentCorrectly()throws Exception {
		NumberSegment numberSegment = new NumberSegment(900, OrderOfMagnitude.THOUSAND);
		assertEquals("Incorrect value found in constructed NumberSegment", 900, numberSegment.getDigits());
		assertEquals("OrderOfMagnitude is not the same as the one used to construct NumberSegment", numberSegment.getMagnitude(),OrderOfMagnitude.THOUSAND);
	}
	
	@Test
	public void shouldExtractCorrectHundredsDigits() throws Exception {
		NumberSegment numberSegment = new NumberSegment(654, OrderOfMagnitude.THOUSAND);
		assertEquals("incorrect hundreds digits", 6, numberSegment.getDigitsHundreds());
	}
	@Test
	public void shouldExtractCorrectTensDigits() throws Exception {
		NumberSegment numberSegment = new NumberSegment(165, OrderOfMagnitude.THOUSAND);
		assertEquals("incorrect hundreds digits", 6, numberSegment.getDigitsTens());
	}
	@Test
	public void shouldExtractCorrectOnesDigits() throws Exception {
		NumberSegment numberSegment = new NumberSegment(439, OrderOfMagnitude.THOUSAND);
		assertEquals("incorrect hundreds digits", 9, numberSegment.getDigitsOnes());
	}
	
	@Test
	public void shouldDetectRemainder()throws Exception {
		NumberSegment numberSegment = new NumberSegment(439, OrderOfMagnitude.HUNDRED);
		assertTrue("Should have remainder", numberSegment.hasRemainder());
	}
	
	@Test
	public void shouldExtractCorrectRemainder() throws Exception {
		NumberSegment numberSegment = new NumberSegment(439, OrderOfMagnitude.HUNDRED);
		assertEquals("returned incorrect remainder",39, numberSegment.getRemainder());
		
	}
	
	@Test
	public void compareToShouldCorrectlyOrderSegmentsLargestToSmallest() throws Exception {
		NumberSegment thousand = new NumberSegment(439, OrderOfMagnitude.THOUSAND);
		NumberSegment hundred = new NumberSegment(439, OrderOfMagnitude.HUNDRED);
		NumberSegment sameHundred = new NumberSegment(439, OrderOfMagnitude.HUNDRED);
		assertEquals("compareTo returning wrong value", -1, hundred.compareTo(thousand));
		assertEquals("compareTo returning wrong value", 1, thousand.compareTo(hundred));
		assertEquals("compareTo returning wrong value", 0, hundred.compareTo(sameHundred));
	}
	

}
