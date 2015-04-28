/**
 * 
 */
package com.jinbal.aimia;

import java.util.List;

import com.jinbal.aimia.number.NumberSegment;
import com.jinbal.aimia.number.NumberSegmentException;
import com.jinbal.aimia.number.OrderOfMagnitudeException;
import com.jinbal.aimia.number.reader.NumberReadStrategy;
import com.jinbal.aimia.numbertextmapper.NumberTextLanguageMapper;

/**
 * @author jin.bal
 *
 */
public class NumberToWordsConverter {
	
	private NumberTextLanguageMapper languageMapper;
	private NumberReadStrategy numberReadStrategy;
	
	public NumberToWordsConverter(NumberTextLanguageMapper languageMapper,NumberReadStrategy numberReadStrategy) {
		this.languageMapper = languageMapper;
		this.numberReadStrategy = numberReadStrategy;
	}
	private NumberToWordsConverter(){}

	
	public String convertNumberToWords(long number)throws NumberToWordsConverterException {
		if(number == 0) {
			return languageMapper.zero();
		}
		try {
			List<NumberSegment> numberSegments = numberReadStrategy.readNumber(number);
			return languageMapper.convertToTextLanguage(numberSegments);
		} catch (NumberSegmentException e) {
			throw new NumberToWordsConverterException(e);
		} catch (OrderOfMagnitudeException e) {
			throw new NumberToWordsConverterException(e);
		}
	}
}
