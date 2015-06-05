package com.ag.csv.model;

import com.ag.csv.CsvField;

public class Stock {
	
	@CsvField(name="id")
	private String id;
	
	@CsvField(name="sku")
	private String sku;
	
	@CsvField(name="date")
	private String date;
	
	@CsvField(name="quantity")
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
