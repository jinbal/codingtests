package com.jinbal.aimia.numbertextmapper;

import java.util.List;

import com.jinbal.aimia.number.NumberSegment;
import com.jinbal.aimia.number.OrderOfMagnitudeException;

public interface NumberTextLanguageMapper {

	public String convertToTextLanguage(List<NumberSegment> numberSegments)throws OrderOfMagnitudeException ;
	public String convertToTextLanguage(NumberSegment numberSegment)throws OrderOfMagnitudeException ;
	public String zero();
}
