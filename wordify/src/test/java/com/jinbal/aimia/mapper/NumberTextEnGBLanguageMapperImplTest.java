/**
 * 
 */
package com.jinbal.aimia.mapper;

import java.util.List;

import org.junit.Test;
import static junit.framework.Assert.*;

import com.jinbal.aimia.NumberToWordsConverterException;
import com.jinbal.aimia.number.NumberSegment;
import com.jinbal.aimia.number.OrderOfMagnitude;
import com.jinbal.aimia.number.OrderOfMagnitudeException;
import com.jinbal.aimia.numbertextmapper.NumberTextEnGBLanguageMapperImpl;

/**
 * @author jin.bal
 *
 */
public class NumberTextEnGBLanguageMapperImplTest {
	private NumberTextEnGBLanguageMapperImpl underTest = new NumberTextEnGBLanguageMapperImpl();

	@Test (expected=OrderOfMagnitudeException.class)
	public void shouldThrowExceptionWhenOrderOfMagnitudeIsSmallerThanRequired() throws Exception {
		NumberSegment numberSegment = new NumberSegment(10, OrderOfMagnitude.TEN);
		String numberString = underTest.convertToTextLanguage(numberSegment);
	}
	
	@Test
	public void shouldConvertNumberSegmentStringForThousands() throws Exception {
		NumberSegment numberSegment = new NumberSegment(457, OrderOfMagnitude.THOUSAND);
		String numberString = underTest.convertToTextLanguage(numberSegment);
		assertEquals("Incorrect number string", "four hundred and fifty seven thousand", numberString);
		
		numberSegment = new NumberSegment(57, OrderOfMagnitude.THOUSAND);
		numberString = underTest.convertToTextLanguage(numberSegment);
		assertEquals("Incorrect number string", "fifty seven thousand", numberString);
		
		numberSegment = new NumberSegment(7, OrderOfMagnitude.THOUSAND);
		numberString = underTest.convertToTextLanguage(numberSegment);
		assertEquals("Incorrect number string", "seven thousand", numberString);
		
		numberSegment = new NumberSegment(0, OrderOfMagnitude.THOUSAND);
		numberString = underTest.convertToTextLanguage(numberSegment);
		assertEquals("Incorrect number string", "", numberString);
		
		numberSegment = new NumberSegment(101, OrderOfMagnitude.THOUSAND);
		numberString = underTest.convertToTextLanguage(numberSegment);
		assertEquals("Incorrect number string", "one hundred and one thousand", numberString);
	}
	
	@Test
	public void shouldContructNumberSegmentStringForHundreds() throws Exception {
		NumberSegment numberSegment = new NumberSegment(457, OrderOfMagnitude.HUNDRED);
		String numberString = underTest.convertToTextLanguage(numberSegment);
		assertEquals("Incorrect number string", "four hundred and fifty seven", numberString);
		
		numberSegment = new NumberSegment(700, OrderOfMagnitude.HUNDRED);
		numberString = underTest.convertToTextLanguage(numberSegment);
		assertEquals("Incorrect number string", "seven hundred", numberString);
	
		numberSegment = new NumberSegment(7, OrderOfMagnitude.HUNDRED);
		numberString = underTest.convertToTextLanguage(numberSegment);
		assertEquals("Incorrect number string", "seven", numberString);
	
		numberSegment = new NumberSegment(307, OrderOfMagnitude.HUNDRED);
		numberString = underTest.convertToTextLanguage(numberSegment);
		assertEquals("Incorrect number string", "three hundred and seven", numberString);
	
		numberSegment = new NumberSegment(0, OrderOfMagnitude.HUNDRED);
		numberString = underTest.convertToTextLanguage(numberSegment);
		assertEquals("Incorrect number string", "", numberString);
	}
	
	@Test
	public void shouldContructNumberSegmentStringForMillions() throws Exception {
		NumberSegment numberSegment = new NumberSegment(457, OrderOfMagnitude.MILLION);
		String numberString = underTest.convertToTextLanguage(numberSegment);
		assertEquals("Incorrect number string", "four hundred and fifty seven million", numberString);
		
		numberSegment = new NumberSegment(700, OrderOfMagnitude.MILLION);
		numberString = underTest.convertToTextLanguage(numberSegment);
		assertEquals("Incorrect number string", "seven hundred million", numberString);
	
		numberSegment = new NumberSegment(7, OrderOfMagnitude.MILLION);
		numberString = underTest.convertToTextLanguage(numberSegment);
		assertEquals("Incorrect number string", "seven million", numberString);
	
		numberSegment = new NumberSegment(307, OrderOfMagnitude.MILLION);
		numberString = underTest.convertToTextLanguage(numberSegment);
		assertEquals("Incorrect number string", "three hundred and seven million", numberString);
	
		numberSegment = new NumberSegment(0, OrderOfMagnitude.MILLION);
		numberString = underTest.convertToTextLanguage(numberSegment);
		assertEquals("Incorrect number string", "", numberString);
	}

}
