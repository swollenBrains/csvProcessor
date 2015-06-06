package com.sb.csv.processor.exception;

public class UnsupportedTypeException extends CsvProcessorException {
	
	private Class clazz;
	
	public UnsupportedTypeException(Class clazz){
		super("Unsupported type : "+clazz.getCanonicalName());
		this.clazz = clazz;
	}

}
