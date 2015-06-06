package com.sb.csv.processor;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sb.csv.processor.exception.TypeProcessingException;

public class DateProcessor implements TypeProcessor {

	private static DateProcessor instance = new DateProcessor();
	
	public static DateProcessor getInstance(){
		return instance;
	}
	
	private DateProcessor() {}
	
	@Override
	public Object makeObject(String value) throws TypeProcessingException {
		try {
			return(new SimpleDateFormat("dd/MM/yyyy").parse(value));
		} catch (Throwable throwable) {
			throw new TypeProcessingException(value, Date.class, throwable);
		}
	}

}
