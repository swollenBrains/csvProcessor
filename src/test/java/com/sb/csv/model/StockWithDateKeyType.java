package com.sb.csv.model;

import java.util.Date;
import java.util.Map;

import com.sb.csv.annotations.CsvField;
import com.sb.csv.annotations.CsvFieldSet;

public class StockWithDateKeyType {
	
	@CsvField(columnIndex=0)
	private String product;
	
	@CsvField(columnIndex=1)
	private String family;
	
	@CsvField(columnIndex=2)
	private String sku;
	
	@CsvFieldSet(startColumnIndex=3, endColumnIndex=4, valueType=Integer.class, keyType= Date.class)
	private Map<Date, Integer> dateQuantityMap;

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

	public Map<Date, Integer> getDateQuantityMap() {
		return dateQuantityMap;
	}

	public void setDateQuantityMap(Map<Date, Integer> dateQuantityMap) {
		this.dateQuantityMap = dateQuantityMap;
	}

}
