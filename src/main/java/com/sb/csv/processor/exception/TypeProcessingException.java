package com.sb.csv.processor.exception;

public class TypeProcessingException extends CsvProcessorException {

	private String value;
	private Class clazz;
	private Throwable rootCause;

	public TypeProcessingException(String value, Class clazz, Throwable rootCause) {
		super(value + " is not a valid " + clazz, rootCause);
		this.value = value;
		this.clazz = clazz;
		this.rootCause = rootCause;
	}

}
