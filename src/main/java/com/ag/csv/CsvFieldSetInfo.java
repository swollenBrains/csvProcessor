package com.ag.csv;

import java.util.HashMap;
import java.util.Map;

public class CsvFieldSetInfo {
	
	private final int startIndex;
	private final int endIndex;
	private final Map<Integer, String> indexToHeaderMap = new HashMap<Integer, String>();
	
	public CsvFieldSetInfo(int startIndex, int endIndex) {
		super();
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}
	
	public void setIndexToHeaderMapping(Integer index, String headerName) {
		this.indexToHeaderMap.put(index, headerName);
	}
	
	
}
