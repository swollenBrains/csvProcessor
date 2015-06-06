package com.sb.csv.processor;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sb.csv.processor.exception.TypeProcessingException;

public class DateProcessor implements TypeProcessor {

	@Override
	public Object makeObject(String value) throws TypeProcessingException {
		try {
			return(new SimpleDateFormat("dd/MM/yyyy").parse(value));
		} catch (Throwable throwable) {
			throw new TypeProcessingException(value, Date.class, throwable);
		}
	}

}
