package com.sb.csv.reader;

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

import com.sb.csv.CsvFieldDetails;
import com.sb.csv.annotations.CsvField;
import com.sb.csv.annotations.CsvFieldSet;
import com.sb.csv.processor.TypeProcessorFactory;
import com.sb.csv.processor.exception.CsvProcessorException;
import com.sb.csv.reader.exception.CsvReaderException;

public class CsvFileReader<T> {

	private final Class<T> clazz;
	private final Map<String, CsvFieldDetails> nameToFieldMap = new HashMap<String, CsvFieldDetails>();
	private final Map<String, CsvFieldSet> nameToFieldSetMap = new HashMap<String, CsvFieldSet>();
	
	public CsvFileReader(Class<T> clazz) {
		super();
		this.clazz = clazz;
		initFieldNameMap();
		initFieldSetNameMap();
	}

	/**
	 * Reads the CSV InputStream and returns a list of objects
	 * @param inputStream
	 * @return List of objects parsed
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws CsvReaderException 
	 */
	public List<T> readCsvIntoList(InputStream inputStream) throws IOException, IllegalAccessException, InvocationTargetException, CsvReaderException{
		List<T> models = new ArrayList<T>();
		List<CsvProcessorException> exceptionsInStream = new ArrayList<CsvProcessorException>();
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
				T model = newClassIntance();
				List<CsvProcessorException> exceptionsInRecord = new ArrayList<CsvProcessorException>();

				exceptionsInRecord.addAll(setCsvFields(model, record));
				setCsvFieldSets(indexToHeaderNameMap, record, model);
				if(exceptionsInRecord.size()>0){
					exceptionsInStream.addAll(exceptionsInRecord);
					continue;
				} 
				models.add(model);
			}	
		}
		if(exceptionsInStream.size()>0){
			CsvReaderException csvReaderException = new CsvReaderException();
			csvReaderException.addAllExceptions(exceptionsInStream);
			throw csvReaderException;
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
				CsvFieldDetails csvFieldDetails = new CsvFieldDetails(csvField, field.getType());
				nameToFieldMap.put(field.getName(), csvFieldDetails);
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
	
//	private T createModel(Map<Integer, String> indexToHeaderNameMap, CSVRecord record) throws IllegalAccessException,
//			InvocationTargetException {
//		T model = newClassIntance();
//		setCsvFields(model, record);
//		setCsvFieldSets(indexToHeaderNameMap, record, model);
//		return model;
//	}

	private void setCsvFieldSets(Map<Integer, String> indexToHeaderNameMap,
			CSVRecord record, T model) throws IllegalAccessException,
			InvocationTargetException {
		for (String modelAttribute : nameToFieldSetMap.keySet()) {
			CsvFieldSet csvFieldSet = nameToFieldSetMap.get(modelAttribute);
			Integer startColumnIndex = csvFieldSet.startColumnIndex();
			Integer endColumnIndex = csvFieldSet.endColumnIndex();
			Class type = csvFieldSet.type();
			Map<String, String> fieldSetMap = new HashMap<String, String>();
			for (int index = startColumnIndex; index <= endColumnIndex; index++) {
				fieldSetMap.put(indexToHeaderNameMap.get(index),
						record.get(index));
			}
			BeanUtils.setProperty(model, modelAttribute, fieldSetMap);
		}
	}
	
	/**
	 * Sets the attributes annotated with {@link CsvField} into the model
	 * @param model
	 * @param record
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private List<CsvProcessorException> setCsvFields(T model, CSVRecord record) throws IllegalAccessException, InvocationTargetException{
		List<CsvProcessorException> exceptions = new ArrayList<CsvProcessorException>();
		for (String modelAttribute : nameToFieldMap.keySet()) {
			try{
				CsvFieldDetails csvFieldDetails = nameToFieldMap.get(modelAttribute);
				Integer index = csvFieldDetails.getCsvField().columnIndex();
				TypeProcessorFactory.getTypeProcessor(csvFieldDetails.getTypeInModel());
				BeanUtils.setProperty(model, modelAttribute, record.get(index));
			} catch(CsvProcessorException e) {
				exceptions.add(e);
			}
		}
		return exceptions;
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
