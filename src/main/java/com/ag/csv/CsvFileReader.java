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
	private final Map<String, CsvField> nameToFieldMap = new HashMap<String, CsvField>();
	private final Map<String, CsvFieldSet> nameToFieldSetMap = new HashMap<String, CsvFieldSet>();
	
	public CsvFileReader(Class<T> clazz) {
		super();
		this.clazz = clazz;
		initFieldNameMap();
		initFieldSetNameMap();
	}

	public List<T> readCsvFile(InputStream inputStream) throws IOException, IllegalAccessException, InvocationTargetException{
		List<T> models = new ArrayList<T>();
		
		try(Reader in = new InputStreamReader(inputStream);
			CSVParser records = CSVFormat.EXCEL.withAllowMissingColumnNames().parse(in)){
			Map<Integer, String> indexToHeaderNameMap = new HashMap<Integer, String>();
			boolean isHeader = true;
			for (CSVRecord record : records) {
				if(isHeader){
					initIndexToHeaderNameMap(indexToHeaderNameMap, record, 5);
					isHeader = false;
					continue;
				}
				T model = createModel(indexToHeaderNameMap, record);
				models.add(model);
			}	
		}
		return models;
	}

	private void initIndexToHeaderNameMap(Map<Integer, String> indexToHeaderNameMap, CSVRecord record, int headerLength) {
		for(int i=0; i< headerLength; i++) {
			indexToHeaderNameMap.put(i, record.get(i));
		}
	}
	
	private void initFieldNameMap() {
		for(Field field:clazz.getDeclaredFields()){
			CsvField csvField = field.getAnnotation(CsvField.class);
			if(csvField != null){
				nameToFieldMap.put(field.getName(), csvField);
			}
		}
	}

	private void initFieldSetNameMap() {
		for (Field field : clazz.getDeclaredFields()) {
			CsvFieldSet csvFieldSet = field.getAnnotation(CsvFieldSet.class);
			if (csvFieldSet != null) {
				nameToFieldSetMap.put(field.getName(), csvFieldSet);
			}
		}
	}
	
	private T createModel(Map<Integer, String> indexToHeaderNameMap, CSVRecord record) throws IllegalAccessException,
			InvocationTargetException {
		T model = newClassIntance();
		for (String modelAttribute : nameToFieldMap.keySet()) {
			Integer index = nameToFieldMap.get(modelAttribute).columnIndex();
			BeanUtils.setProperty(model, modelAttribute, record.get(index));
		}
		for (String modelAttribute : nameToFieldSetMap.keySet()) {
			CsvFieldSet csvFieldSet = nameToFieldSetMap.get(modelAttribute);
			Integer startColumnIndex = csvFieldSet.startColumnIndex();
			Integer endColumnIndex = csvFieldSet.endColumnIndex();
			Class type = csvFieldSet.type();
//			type.getClass()
			Map<String, String> fieldSetMap = new HashMap<String, String>();
			for (int index = startColumnIndex; index <= endColumnIndex; index++) {
				fieldSetMap.put(indexToHeaderNameMap.get(index),
						record.get(index));
			}
			BeanUtils.setProperty(model, modelAttribute, fieldSetMap);
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
