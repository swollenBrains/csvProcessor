package com.sb.csv.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

/**
 * Applicable to a {@link Map} &lt; keyType, valueType &gt; where key=ColumnHeader and value=CellValue
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CsvFieldSet {
	
	public int startColumnIndex();
	public int endColumnIndex();
	public Class<?> keyType();
	public Class<?> valueType();

}
