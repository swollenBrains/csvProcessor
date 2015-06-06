package com.sb.csv.reader.exception;

import java.util.ArrayList;
import java.util.List;

import com.sb.csv.processor.exception.CsvProcessorException;

public class CsvReaderException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private final List<CsvProcessorException> exceptions = new ArrayList<CsvProcessorException>();
	
	public List<CsvProcessorException> getExceptions() {
		return exceptions;
	}

	public void addException(CsvProcessorException exception) {
		exceptions.add(exception);
	}
	
	public void addAllExceptions(List<CsvProcessorException> exceptions) {
		exceptions.addAll(exceptions);
	}
	
}
