package com.ag.csv;

public class CsvFieldDetails {
	
	CsvField csvField;
	Class typeInModel;
	
	public CsvFieldDetails(CsvField csvField, Class typeInModel) {
		super();
		this.csvField = csvField;
		this.typeInModel = typeInModel;
	}
	
	public CsvField getCsvField() {
		return csvField;
	}
	public void setCsvField(CsvField csvField) {
		this.csvField = csvField;
	}
	public Class getTypeInModel() {
		return typeInModel;
	}
	public void setTypeInModel(Class typeInModel) {
		this.typeInModel = typeInModel;
	}
	
	
}
