package com.sb.csv.model;

import java.util.Map;

import com.sb.csv.annotations.CsvField;
import com.sb.csv.annotations.CsvFieldSet;

public class StockWithIntegerValueType {
	
	@CsvField(columnIndex=0)
	private String product;
	
	@CsvField(columnIndex=1)
	private String family;
	
	@CsvField(columnIndex=2)
	private String sku;
	
	@CsvFieldSet(startColumnIndex=3, endColumnIndex=4, valueType=Integer.class, keyType= String.class)
	private Map<String, Integer> dateQuantityMap;

	public String getProduct() {
		return product;
	}

	@Override
	public String toString() {
		return "Stock [product=" + product + ", family=" + family + ", sku="
				+ sku + ", dateQuantityMap=" + dateQuantityMap + "]";
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Map<String, Integer> getDateQuantityMap() {
		return dateQuantityMap;
	}

	public void setDateQuantityMap(Map<String, Integer> dateQuantityMap) {
		this.dateQuantityMap = dateQuantityMap;
	}

}
