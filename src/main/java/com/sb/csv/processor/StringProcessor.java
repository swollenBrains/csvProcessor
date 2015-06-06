package com.sb.csv.processor;


public class StringProcessor implements TypeProcessor {

	private static StringProcessor instance = new StringProcessor();
	
	public static StringProcessor getInstance(){
		return instance;
	}
	
	@Override
	public Object makeObject(String value) {
		return value;
	}

}
