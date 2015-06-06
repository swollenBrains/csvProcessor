package com.sb.csv.model;

import java.util.Map;

import com.sb.csv.annotations.CsvField;
import com.sb.csv.annotations.CsvFieldSet;

/**
 * StockWithUnsupportedType has sku declared as Stock which is a unsupported type
 * @author ankitgupta7
 *
 */
public class StockWithUnsupportedType {
	
	@CsvField(columnIndex=0)
	private String product;
	
	@CsvField(columnIndex=1)
	private String family;
	
	@CsvField(columnIndex=2)
	private Stock sku;
	
	@CsvFieldSet(startColumnIndex=3, endColumnIndex=4, type=String.class)
	private Map<String, String> dateQuantityMap;

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

	public Stock getSku() {
		return sku;
	}

	public void setSku(Stock sku) {
		this.sku = sku;
	}

	public Map<String, String> getDateQuantityMap() {
		return dateQuantityMap;
	}

	public void setDateQuantityMap(Map<String, String> dateQuantityMap) {
		this.dateQuantityMap = dateQuantityMap;
	}

}
