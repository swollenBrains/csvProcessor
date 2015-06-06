package com.sb.csv.processor.exception;

public class CsvProcessorException extends Exception{

	private static final long serialVersionUID = 1L;

	public CsvProcessorException(final String message){
		super(message);
	}
	
	public CsvProcessorException(final String message, final Throwable cause){
		super(message);
		super.initCause(cause);
	}
	
}
