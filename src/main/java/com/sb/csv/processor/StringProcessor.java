package com.sb.csv.processor;


public class StringProcessor implements TypeProcessor {

	@Override
	public Object makeObject(String value) {
		return value;
	}

}
