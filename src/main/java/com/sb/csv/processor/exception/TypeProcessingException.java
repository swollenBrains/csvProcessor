package com.sb.csv.processor.exception;

public class TypeProcessingException extends CsvProcessorException {

	private static final long serialVersionUID = 1L;
	private final String value;
	private final Class<?> clazz;
	private final Throwable rootCause;

	public TypeProcessingException(String value, Class<?> clazz, Throwable rootCause) {
		super(value + " is not a valid " + clazz, rootCause);
		this.value = value;
		this.clazz = clazz;
		this.rootCause = rootCause;
	}

	public String getValue() {
		return value;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public Throwable getRootCause() {
		return rootCause;
	}

}
