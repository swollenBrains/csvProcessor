package org.gradle;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CsvReader {

	public void readCsv() throws IOException{
		try(Reader in = new InputStreamReader(this.getClass().getResourceAsStream("MOCK_DATA.csv"));
				CSVParser records = CSVFormat.EXCEL.withHeader("id","sku","date","quantity").parse(in)){
			for (CSVRecord record : records) {
			    String sku = record.get("sku");
			    String date = record.get("date");
			    String quantity = record.get("quantity");
				System.out.println(sku + " : " + date + " : " + quantity);
			}	
		}
	}
	
	public static void main(String[] args) {
		try {
			new CsvReader().readCsv();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
