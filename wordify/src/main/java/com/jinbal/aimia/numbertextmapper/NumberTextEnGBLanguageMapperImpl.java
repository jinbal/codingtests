/**
 * 
 */
package com.jinbal.aimia.numbertextmapper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jinbal.aimia.number.NumberSegment;
import com.jinbal.aimia.number.OrderOfMagnitude;
import com.jinbal.aimia.number.OrderOfMagnitudeException;

/**
 * @author jin.bal
 *
 */
public class NumberTextEnGBLanguageMapperImpl implements NumberTextLanguageMapper {

	private static final int TWENTY = 20;

	private static final String ZERO = "zero";
	
	private static final String[] TENS_LESS_THAN_ONE_HUNDRED = {
		""," ten"," twenty", " thirty"," forty"," fifty"," sixty",
		" seventy"," eighty"," ninety"
	};

	private static final String[] NUMBERS_LESS_THAN_TWENTY = {
		""," one"," two", " three", " four", " five"," six"," seven"," eight"," nine"," ten",
		" eleven"," twelve"," thirteen"," fourteen"," fifteen", " sixteen"," seventeen"," eighteen"," nineteen"
	};
	private static Map<OrderOfMagnitude, String> ORDERS_OF_MAG_TEXT_MAPPING = new HashMap<OrderOfMagnitude, String>();
	static{
		ORDERS_OF_MAG_TEXT_MAPPING.put(OrderOfMagnitude.HUNDRED, " hundred");
		ORDERS_OF_MAG_TEXT_MAPPING.put(OrderOfMagnitude.THOUSAND, " thousand");
		ORDERS_OF_MAG_TEXT_MAPPING.put(OrderOfMagnitude.MILLION, " million");
		// add language support for higher orders of magnitude here
	}

	public NumberTextEnGBLanguageMapperImpl() {
	}
	
	public String convertToTextLanguage(List<NumberSegment> numberSegments)throws OrderOfMagnitudeException {
		StringBuilder numberWords = new StringBuilder();
		Iterator<NumberSegment> segmentsIterator = numberSegments.iterator();
		while (segmentsIterator.hasNext()) {
			NumberSegment numberSegment = (NumberSegment) segmentsIterator.next();
			numberWords.append(convertToTextLanguage(numberSegment));
			if(segmentsIterator.hasNext()) {
				numberWords.append(" ");
			}
		}
		return numberWords.toString().trim();
	}

	public String convertToTextLanguage(NumberSegment numberSegment) throws OrderOfMagnitudeException {
		if(numberSegment.getMagnitude().isSmallerThan(OrderOfMagnitude.MIN_ORDER_OF_MAGNITUDE)) {
			throw new OrderOfMagnitudeException("Magnitude is smaller than required by this mapper");
		}
		StringBuilder builder = new StringBuilder();
		convertSegmentDigits(numberSegment, builder);
		if(shouldExpressMagnitude(numberSegment) && builder.length() > 0 ){
			appendOrderOfMagnitudeText(numberSegment, builder);
		}
		return builder.toString().trim();
	}

	private void convertSegmentDigits(NumberSegment numberSegment,StringBuilder builder) throws OrderOfMagnitudeException {
		long hundredsDigits = numberSegment.getDigitsHundreds();
		if(hundredsDigits >0) {
			appendHundreds(numberSegment, builder, hundredsDigits);
		} else {
			if(lessThanTwenty(numberSegment) ) {
				appendLessThanTwenty(numberSegment, builder);
			} else {
				appendMoreThanThanNineteen(numberSegment, builder);
			}
		}
	}

	private void appendMoreThanThanNineteen(NumberSegment numberSegment,StringBuilder builder) {
		builder.append(TENS_LESS_THAN_ONE_HUNDRED[(int)numberSegment.getDigitsTens()]);
		builder.append(NUMBERS_LESS_THAN_TWENTY[(int) numberSegment.getDigitsOnes()]);
	}

	private void appendOrderOfMagnitudeText(NumberSegment numberSegment,StringBuilder builder) {
		builder.append(ORDERS_OF_MAG_TEXT_MAPPING.get(numberSegment.getMagnitude()));
	}

	private boolean lessThanTwenty(NumberSegment numberSegment) {
		return numberSegment.getDigits()<NUMBERS_LESS_THAN_TWENTY.length;
	}
	
	private void appendLessThanTwenty(NumberSegment numberSegment,StringBuilder builder) throws OrderOfMagnitudeException {
		appendAndIfNecessary(numberSegment, builder);
		builder.append(NUMBERS_LESS_THAN_TWENTY[(int) numberSegment.getDigits()]);
	}

	private void appendAndIfNecessary( NumberSegment numberSegment, StringBuilder builder) throws OrderOfMagnitudeException {
		if( numberSegment.isLessThanHundred() &&  numberSegment.isSegmentPartOfLargerNumber()) {
			builder.append(" and");
		}
	}

	private void appendHundreds(NumberSegment numberSegment, StringBuilder builder, long hundredsDigits) {
		builder.append(NUMBERS_LESS_THAN_TWENTY[(int)hundredsDigits]);
		builder.append(ORDERS_OF_MAG_TEXT_MAPPING.get(OrderOfMagnitude.HUNDRED));
		if(numberSegment.hasRemainder()) {
			builder.append(" and");
			if(numberSegment.getRemainder() < TWENTY) {
				builder.append(NUMBERS_LESS_THAN_TWENTY[(int) numberSegment.getRemainder()]);
			} else {
				appendMoreThanThanNineteen(numberSegment, builder);
			}
		}	
	}
	
	private boolean shouldExpressMagnitude(NumberSegment segment){ 
		OrderOfMagnitude magnitude = segment.getMagnitude();
		if(magnitude != OrderOfMagnitude.ONE &&
				magnitude != OrderOfMagnitude.HUNDRED &&
					magnitude != OrderOfMagnitude.TEN ) {
			return true;
		}
		return false;
	}

	public String zero() {
		return ZERO;
	}
	
}
