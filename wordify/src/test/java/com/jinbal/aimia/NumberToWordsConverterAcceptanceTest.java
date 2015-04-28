/**
 * 
 */
package com.jinbal.aimia;
import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import com.jinbal.aimia.number.reader.DefaultNumberReaderStrategyImpl;
import com.jinbal.aimia.numbertextmapper.NumberTextEnGBLanguageMapperImpl;

/**
 * @author jin.bal
 *
 */
public class NumberToWordsConverterAcceptanceTest {

	@Test
	public void shouldConvertNumberToBritishString() throws Exception {
		NumberToWordsConverter underTest = getEnGBNumberConverter();
		long number = 0; 
		assertEquals("incorrect number string generated", "zero", underTest.convertNumberToWords(number));
		number = 1;
		assertEquals("incorrect number string generated", "one", underTest.convertNumberToWords(number));
		number = 10;
		assertEquals("incorrect number string generated", "ten", underTest.convertNumberToWords(number));
		number = 15;
		assertEquals("incorrect number string generated", "fifteen", underTest.convertNumberToWords(number));
		number = 20;
		assertEquals("incorrect number string generated", "twenty", underTest.convertNumberToWords(number));
		number = 35;
		assertEquals("incorrect number string generated", "thirty five", underTest.convertNumberToWords(number));
		number = 100;
		assertEquals("incorrect number string generated", "one hundred", underTest.convertNumberToWords(number));
		number = 101;
		assertEquals("incorrect number string generated", "one hundred and one", underTest.convertNumberToWords(number));
		number = 175; 
		assertEquals("incorrect number string generated", "one hundred and seventy five", underTest.convertNumberToWords(number));
		number = 999; 
		assertEquals("incorrect number string generated", "nine hundred and ninety nine", underTest.convertNumberToWords(number));
		number = 1000; 
		assertEquals("incorrect number string generated", "one thousand", underTest.convertNumberToWords(number));
		number = 1001; 
		assertEquals("incorrect number string generated", "one thousand and one", underTest.convertNumberToWords(number));
		number = 1250;
		assertEquals("incorrect number string generated", "one thousand two hundred and fifty", underTest.convertNumberToWords(number));
		number = 9999;
		assertEquals("incorrect number string generated", "nine thousand nine hundred and ninety nine", underTest.convertNumberToWords(number));
		number = 10001;
		assertEquals("incorrect number string generated", "ten thousand and one", underTest.convertNumberToWords(number));
		number = 15351;
		assertEquals("incorrect number string generated", "fifteen thousand three hundred and fifty one", underTest.convertNumberToWords(number));
		number = 19999;
		assertEquals("incorrect number string generated", "nineteen thousand nine hundred and ninety nine", underTest.convertNumberToWords(number));
		number = 20000;
		assertEquals("incorrect number string generated", "twenty thousand", underTest.convertNumberToWords(number));
		number = 99999;
		assertEquals("incorrect number string generated", "ninety nine thousand nine hundred and ninety nine", underTest.convertNumberToWords(number));
		number = 100000;
		assertEquals("incorrect number string generated", "one hundred thousand", underTest.convertNumberToWords(number));
		number = 100001;
		assertEquals("incorrect number string generated", "one hundred thousand and one", underTest.convertNumberToWords(number));
		number = 100101;
		assertEquals("incorrect number string generated", "one hundred thousand one hundred and one", underTest.convertNumberToWords(number));
		number = 101101;
		assertEquals("incorrect number string generated", "one hundred and one thousand one hundred and one", underTest.convertNumberToWords(number));
		number = 115354;
		assertEquals("incorrect number string generated", "one hundred and fifteen thousand three hundred and fifty four", underTest.convertNumberToWords(number));
		number = 1000000;
		assertEquals("incorrect number string generated", "one million", underTest.convertNumberToWords(number));
		number = 1000001;
		assertEquals("incorrect number string generated", "one million and one" , underTest.convertNumberToWords(number));
		number = 15000001;
		assertEquals("incorrect number string generated", "fifteen million and one" , underTest.convertNumberToWords(number));
		number = 123456789;
		assertEquals("incorrect number string generated", "one hundred and twenty three million four hundred and fifty six thousand seven hundred and eighty nine" , underTest.convertNumberToWords(number));
		number = 999999999;
		assertEquals("incorrect number string generated", "nine hundred and ninety nine million nine hundred and ninety nine thousand nine hundred and ninety nine", underTest.convertNumberToWords(number));
	}
	
	private NumberToWordsConverter getEnGBNumberConverter() {
		return new NumberToWordsConverter(new NumberTextEnGBLanguageMapperImpl(),new DefaultNumberReaderStrategyImpl());
	}
}
