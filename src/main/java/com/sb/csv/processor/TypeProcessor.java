package com.sb.csv.processor;

import com.sb.csv.processor.exception.TypeProcessingException;

public interface TypeProcessor {
	
	Object makeObject(String value) throws TypeProcessingException;
	
//	public static Object makeObject(String value, Class clazz) {
//		String clazzName = clazz.getCanonicalName();
//		Object objectToReturn = null;
//		System.out.println(clazzName);
//		switch(clazzName) {
//			case "java.lang.String":
//				objectToReturn = value;
//			case "java.lang.Integer":
//				objectToReturn= Integer.parseInt(value);
//		}
//		return objectToReturn;
//	}
//
//	@Test
//	public void test(){
//		Integer.parseInt("10");
//		Object object = TypeProcessor.makeObject("10", Integer.class);
//		Assert.assertTrue(object instanceof Integer);
//	}
	
}
