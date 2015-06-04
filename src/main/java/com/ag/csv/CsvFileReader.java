package com.ag.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CsvFileReader<T> {

	private final Class<T> clazz;
	private String[] headers;
	private final Map<String, String> modelToCsvFieldNameMap = new HashMap<String, String>();
	
	public CsvFileReader(Class<T> clazz) {
		super();
		this.clazz = clazz;
		initFieldNameMap();
	}

	public List<T> readCsvFile(InputStream inputStream) throws IOException, IllegalAccessException, InvocationTargetException{
		List<T> models = new ArrayList<T>();
		try(Reader in = new InputStreamReader(inputStream);
			CSVParser records = CSVFormat.EXCEL.withHeader(headers).parse(in)){
			boolean isHeader = true;
			for (CSVRecord record : records) {
				if(isHeader){
					isHeader = false;
					continue;
				}
				T model = createModel(record);
				models.add(model);
			}	
		}
		return models;
	}

	private void initFieldNameMap() {
		List<String> headerList = new ArrayList<String>();
		for(Field field:clazz.getDeclaredFields()){
			CsvField csvField = field.getAnnotation(CsvField.class);
			if(csvField != null){
				modelToCsvFieldNameMap.put(field.getName(), csvField.name());
				headerList.add(csvField.name());
			}
		}
		headers = headerList.toArray(new String[headerList.size()]);
	}
	
	private T createModel(CSVRecord record) throws IllegalAccessException,
			InvocationTargetException {
		T model = newClassIntance();
		for(String modelAttribute : modelToCsvFieldNameMap.keySet()){
			String csvFieldName = modelToCsvFieldNameMap.get(modelAttribute);
			BeanUtils.setProperty(model, modelAttribute, record.get(csvFieldName));
		}
		return model;
	}
	
	private T newClassIntance() {
		T entry;
		try {
			entry = clazz.newInstance();
		} catch (InstantiationException ie) {
			throw new RuntimeException(String.format("can not instantiate class %s", clazz.getName()), ie);
		} catch (IllegalAccessException iae) {
			throw new RuntimeException(String.format("can not access class %s", clazz.getName()), iae);
		}
		return entry;
	}
	
}
