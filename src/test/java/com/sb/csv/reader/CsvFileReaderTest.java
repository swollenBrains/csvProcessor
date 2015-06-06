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
import com.sb.csv.model.StockWithDateKeyType;
import com.sb.csv.model.StockWithIntegerValueType;
import com.sb.csv.model.StockWithUnsupportedType;
import com.sb.csv.reader.CsvReader;
import com.sb.csv.reader.exception.CsvReaderException;

public class CsvFileReaderTest {
	
	@Test
	public void shouldReadCsvForStock() throws IllegalAccessException, InvocationTargetException, IOException, CsvReaderException{
		CsvReader<FixedHeaderStock> stockReader = new CsvReader<FixedHeaderStock>(FixedHeaderStock.class);
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
		CsvReader<Stock> stockReader = new CsvReader<Stock>(Stock.class);
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
		CsvReader<StockWithUnsupportedType> stockReader = new CsvReader<StockWithUnsupportedType>(StockWithUnsupportedType.class);
		try(InputStream csvInput = getClass().getResourceAsStream("StockData.csv")) {
			stockReader.readCsvIntoList(csvInput);
		}
		Assert.fail();
	}
	
	@Test
	public void shouldParseCsvFieldsIntoInteger() throws IllegalAccessException, InvocationTargetException, IOException, CsvReaderException{
		CsvReader<ModelWithIntegerCsvField> stockReader = new CsvReader<ModelWithIntegerCsvField>(ModelWithIntegerCsvField.class);
		try(InputStream csvInput = getClass().getResourceAsStream("FixedHeaderStockData.csv")) {
			List<ModelWithIntegerCsvField> stocks = stockReader.readCsvIntoList(csvInput);
//			for(Stock stock: stocks){
//				System.out.println(stock.toString());
//			}
			Assert.assertEquals(10, stocks.size());
		} 
	}
	
	@Test
	public void shouldParseCsvFieldSetWithValueTypeInteger() throws IOException, IllegalAccessException, InvocationTargetException, CsvReaderException {
		CsvReader<StockWithIntegerValueType> stockReader = new CsvReader<StockWithIntegerValueType>(StockWithIntegerValueType.class);
		try(InputStream csvInput = getClass().getResourceAsStream("StockData.csv")) {
			List<StockWithIntegerValueType> stocks = stockReader.readCsvIntoList(csvInput);
//			for(StockWithIntegerCsvFieldSet stock: stocks){
//				System.out.println(stock.toString());
//			}
			Assert.assertEquals(200, stocks.size());
		} 
	}
	
	@Test
	public void shouldParseCsvFieldSetWithKeyTypeDate() throws IOException, IllegalAccessException, InvocationTargetException, CsvReaderException {
		CsvReader<StockWithDateKeyType> stockReader = new CsvReader<StockWithDateKeyType>(StockWithDateKeyType.class);
		try(InputStream csvInput = getClass().getResourceAsStream("StockData.csv")) {
			List<StockWithDateKeyType> stocks = stockReader.readCsvIntoList(csvInput);
//			for(StockWithDateKeyType stock: stocks){
//				System.out.println(stock.toString());
//			}
			Assert.assertEquals(200, stocks.size());
		} 
	}
}