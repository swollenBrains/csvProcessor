package com.sb.csv.processor.exception;

public class CsvProcessorException extends Exception{

	public CsvProcessorException(final String message){
		super(message);
	}
	
	public CsvProcessorException(final String message, final Throwable cause){
		super(message);
		super.initCause(cause);
	}
	
}
