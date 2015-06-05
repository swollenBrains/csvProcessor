package com.ag.csv;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.ag.csv.model.FixedHeaderStock;
import com.ag.csv.model.Stock;

public class CsvFileReaderTest {
	
	@Test
	public void shouldReadCsvForStock() throws IllegalAccessException, InvocationTargetException, IOException{
		CsvFileReader<FixedHeaderStock> stockReader = new CsvFileReader<FixedHeaderStock>(FixedHeaderStock.class);
		try(InputStream csvInput = CsvFileReader.class.getResourceAsStream("dataWithAddedColumn.csv")) {
			List<FixedHeaderStock> stocks = stockReader.readCsvFile(csvInput);
//			for(Stock stock: stocks){
//				System.out.println(stock.toString());
//			}
			Assert.assertEquals(200, stocks.size());
		} 
	}

	
	@Test
	public void shouldReadCsvForStockData() throws IllegalAccessException, InvocationTargetException, IOException{
		CsvFileReader<Stock> stockReader = new CsvFileReader<Stock>(Stock.class);
		try(InputStream csvInput = CsvFileReader.class.getResourceAsStream("StockData.csv")) {
			List<Stock> stocks = stockReader.readCsvFile(csvInput);
			for(Stock stock: stocks){
				System.out.println(stock.toString());
			}
			Assert.assertEquals(200, stocks.size());
		} 
	}
	
}
