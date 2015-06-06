package com.sb.csv.processor;

import java.util.Date;

import com.sb.csv.processor.exception.UnsupportedTypeException;

public class TypeProcessorFactory {
	
	public static TypeProcessor getTypeProcessor(Class<?> clazz) throws UnsupportedTypeException{
		if(Integer.class.getCanonicalName().equalsIgnoreCase(clazz.getCanonicalName())){
			return IntegerProcessor.getInstance();
		}
		if(String.class.getCanonicalName().equals(clazz.getCanonicalName())) {
			return StringProcessor.getInstance();
		}
		if(Date.class.getCanonicalName().equals(clazz.getCanonicalName())) {
			return DateProcessor.getInstance();
		}
		throw new UnsupportedTypeException(clazz);
	}
	
}
