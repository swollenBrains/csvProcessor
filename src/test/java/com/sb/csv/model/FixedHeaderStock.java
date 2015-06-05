package com.sb.csv.model;

import com.sb.csv.annotations.CsvField;

public class FixedHeaderStock {
	
	@CsvField(columnIndex=0)
	private String id;
	
	@CsvField(columnIndex=1)
	private String sku;
	
	@CsvField(columnIndex=2)
	private String date;
	
	@CsvField(columnIndex=3)
	private String quantity;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getQuantity() {
		return quantity;
	}

	@Override
	public String toString() {
		return "Stock [id=" + id + ", sku=" + sku + ", date=" + date
				+ ", quantity=" + quantity + "]";
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
}
