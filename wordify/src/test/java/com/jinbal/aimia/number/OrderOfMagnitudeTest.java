package com.jinbal.aimia.number;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import org.junit.Test;
public class OrderOfMagnitudeTest {
	
	@Test
	public void hundredShouldHaveCorrectMagnitude() {
		assertEquals("HUNDRED contains the wrong Magnitude", 100, OrderOfMagnitude.HUNDRED.getMagnitude());
	}
	
	@Test
	public void thousandShouldHaveCorrectMagnitude() {
		assertEquals("THOUSAND contains the wrong Magnitude", 1000, OrderOfMagnitude.THOUSAND.getMagnitude());
	}
	
	@Test
	public void millionShouldHaveCorrectMagnitude() {
		assertEquals("MILLION contains the wrong Magnitude", 1000000, OrderOfMagnitude.MILLION.getMagnitude());
	}
	
	@Test
	public void hundredShouldCorrectlyDetectMagnitude() {
		assertFalse("number supplied should Not have this magnitude: "+OrderOfMagnitude.HUNDRED, OrderOfMagnitude.HUNDRED.hasMagnitude(99));
		assertTrue("number supplied should  have this magnitude: "+OrderOfMagnitude.HUNDRED, OrderOfMagnitude.HUNDRED.hasMagnitude(100));
		assertTrue("number supplied should  have this magnitude: "+OrderOfMagnitude.HUNDRED, OrderOfMagnitude.HUNDRED.hasMagnitude(101));
	}
	
	@Test
	public void thousandShouldCorrectlyDetectMagnitude() {
		assertFalse("number supplied should Not have this magnitude: "+OrderOfMagnitude.THOUSAND, OrderOfMagnitude.THOUSAND.hasMagnitude(999));
		assertTrue("number supplied should  have this magnitude: "+OrderOfMagnitude.THOUSAND, OrderOfMagnitude.THOUSAND.hasMagnitude(1000));
		assertTrue("number supplied should  have this magnitude: "+OrderOfMagnitude.THOUSAND, OrderOfMagnitude.THOUSAND.hasMagnitude(1001));
	}
	
	@Test
	public void millionShouldCorrectlyDetectMagnitude() {
		assertFalse("number supplied should Not have this magnitude: "+OrderOfMagnitude.MILLION, OrderOfMagnitude.MILLION.hasMagnitude(999999));
		assertTrue("number supplied should  have this magnitude: "+OrderOfMagnitude.MILLION, OrderOfMagnitude.MILLION.hasMagnitude(1000000));
		assertTrue("number supplied should  have this magnitude: "+OrderOfMagnitude.MILLION, OrderOfMagnitude.MILLION.hasMagnitude(1000001));
	}

	@Test
	public void shouldCalculateCorrectWholeUnits() {
		assertEquals("incorrect whole units ",2, OrderOfMagnitude.HUNDRED.getWholeUnits(200));
		assertEquals("incorrect whole units ",3, OrderOfMagnitude.THOUSAND.getWholeUnits(3663));
		assertEquals("incorrect whole units ",366, OrderOfMagnitude.THOUSAND.getWholeUnits(366345));
		assertEquals("incorrect whole units ",0, OrderOfMagnitude.THOUSAND.getWholeUnits(999));
		assertEquals("incorrect whole units ",3, OrderOfMagnitude.TEN.getWholeUnits(35));
		assertEquals("incorrect whole units ",32, OrderOfMagnitude.TEN.getWholeUnits(325));
	}
	
	@Test 
	public void shouldCalculateCorrectRemainder() {
		assertEquals("incorrect remainder ",23, OrderOfMagnitude.HUNDRED.getRemainder(223));
		assertEquals("incorrect remainder ",663, OrderOfMagnitude.THOUSAND.getRemainder(3663));
		assertEquals("incorrect remainder ",345, OrderOfMagnitude.THOUSAND.getRemainder(366345));
		assertEquals("incorrect remainder ",366345, OrderOfMagnitude.MILLION.getRemainder(366345));
		assertEquals("incorrect remainder ",999, OrderOfMagnitude.THOUSAND.getRemainder(999));
		assertEquals("incorrect remainder ",9, OrderOfMagnitude.TEN.getRemainder(999));
		assertEquals("incorrect remainder ",99, OrderOfMagnitude.HUNDRED.getRemainder(999));
	}
	
	@Test
	public void shouldFindCorrectMagnitude() throws Exception {
		assertEquals("wrong OrderOfMagnitude", OrderOfMagnitude.TEN, OrderOfMagnitude.findOrderOfMagnitude(99));
		assertEquals("wrong OrderOfMagnitude", OrderOfMagnitude.HUNDRED, OrderOfMagnitude.findOrderOfMagnitude(100));
		assertEquals("wrong OrderOfMagnitude", OrderOfMagnitude.THOUSAND, OrderOfMagnitude.findOrderOfMagnitude(1000));
		assertEquals("wrong OrderOfMagnitude", OrderOfMagnitude.MILLION, OrderOfMagnitude.findOrderOfMagnitude(1000000));
	}
	
	@Test(expected=OrderOfMagnitudeException.class)
	public void shouldThrowExceptionWhenNumberoutOfRange() throws OrderOfMagnitudeException {
		OrderOfMagnitude.findOrderOfMagnitude(1000000000);
		OrderOfMagnitude.findOrderOfMagnitude(9);
	}
	
	@Test
	public void shouldReturnTrueWhenOrderOfMagnitudeIsSmaller() {
		assertTrue("Should return true as is smaller", OrderOfMagnitude.HUNDRED.isSmallerThan(OrderOfMagnitude.THOUSAND));
	}
	
}
