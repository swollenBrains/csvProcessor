package com.sb.csv.processor.exception;

public class UnsupportedTypeException extends CsvProcessorException {
	
	private static final long serialVersionUID = 1L;
	private Class<?> clazz;
	
	public UnsupportedTypeException(Class<?> clazz){
		super("Unsupported type : "+clazz.getCanonicalName());
		this.clazz = clazz;
	}

	public Class<?> getClazz() {
		return clazz;
	}

}
