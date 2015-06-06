package com.sb.csv.processor;

import com.sb.csv.processor.exception.TypeProcessingException;

public class IntegerProcessor implements TypeProcessor {

	@Override
	public Object makeObject(String value) throws TypeProcessingException {
		try {
			return Integer.parseInt(value);
		} catch (Throwable throwable) {
			throw new TypeProcessingException(value, Integer.class, throwable);
		}
	}

}
