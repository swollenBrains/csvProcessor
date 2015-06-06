package com.sb.csv.processor;

import com.sb.csv.processor.exception.TypeProcessingException;

public interface TypeProcessor {
	
	Object makeObject(String value) throws TypeProcessingException;
	
}
