package com.sb.csv.reader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.sb.csv.model.FixedHeaderStock;
import com.sb.csv.model.ModelWithIntegerCsvField;
import com.sb.csv.model.Stock;
import com.sb.csv.model.StockWithIntegerCsvFieldSet;
import com.sb.csv.model.StockWithUnsupportedType;
import com.sb.csv.reader.CsvFileReader;
import com.sb.csv.reader.exception.CsvReaderException;

public class CsvFileReaderTest {
	
	@Test
	public void shouldReadCsvForStock() throws IllegalAccessException, InvocationTargetException, IOException, CsvReaderException{
		CsvFileReader<FixedHeaderStock> stockReader = new CsvFileReader<FixedHeaderStock>(FixedHeaderStock.class);
		try(InputStream csvInput = getClass().getResourceAsStream("FixedHeaderStockData.csv")) {
			List<FixedHeaderStock> stocks = stockReader.readCsvIntoList(csvInput);
//			for(Stock stock: stocks){
//				System.out.println(stock.toString());
//			}
			Assert.assertEquals(10, stocks.size());
		} 
	}

	
	@Test
	public void shouldReadCsvForStockData() throws IllegalAccessException, InvocationTargetException, IOException{
		CsvFileReader<Stock> stockReader = new CsvFileReader<Stock>(Stock.class);
		try(InputStream csvInput = getClass().getResourceAsStream("StockData.csv")) {
			List<Stock> stocks = stockReader.readCsvIntoList(csvInput);
//			for(Stock stock: stocks){
//				System.out.println(stock.toString());
//			}
			Assert.assertEquals(200, stocks.size());
		} catch (CsvReaderException e) {
			Assert.fail();
		} 
	}
	
	@Test(expected=CsvReaderException.class)
	public void shouldThrowExceptionWhenCsvFieldMentionsInvalidType() throws IllegalAccessException, InvocationTargetException, IOException, CsvReaderException{
		CsvFileReader<StockWithUnsupportedType> stockReader = new CsvFileReader<StockWithUnsupportedType>(StockWithUnsupportedType.class);
		try(InputStream csvInput = getClass().getResourceAsStream("StockData.csv")) {
			stockReader.readCsvIntoList(csvInput);
		}
		Assert.fail();
	}
	
	@Test
	public void shouldParseCsvFieldsIntoInteger() throws IllegalAccessException, InvocationTargetException, IOException, CsvReaderException{
		CsvFileReader<ModelWithIntegerCsvField> stockReader = new CsvFileReader<ModelWithIntegerCsvField>(ModelWithIntegerCsvField.class);
		try(InputStream csvInput = getClass().getResourceAsStream("FixedHeaderStockData.csv")) {
			List<ModelWithIntegerCsvField> stocks = stockReader.readCsvIntoList(csvInput);
//			for(Stock stock: stocks){
//				System.out.println(stock.toString());
//			}
			Assert.assertEquals(10, stocks.size());
		} 
	}
	
	@Test
	public void shouldParseCsvFieldSetWithValueTypeInteger() throws IOException, IllegalAccessException, InvocationTargetException {
		CsvFileReader<StockWithIntegerCsvFieldSet> stockReader = new CsvFileReader<StockWithIntegerCsvFieldSet>(StockWithIntegerCsvFieldSet.class);
		try(InputStream csvInput = getClass().getResourceAsStream("StockData.csv")) {
			List<StockWithIntegerCsvFieldSet> stocks = stockReader.readCsvIntoList(csvInput);
//			for(StockWithIntegerCsvFieldSet stock: stocks){
//				System.out.println(stock.toString());
//			}
			Assert.assertEquals(200, stocks.size());
		} catch (CsvReaderException e) {
			Assert.fail();
		} 
	}
	
}