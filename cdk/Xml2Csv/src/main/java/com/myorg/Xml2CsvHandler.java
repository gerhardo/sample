package com.myorg;

import java.util.Map;

import com.amazonaws.handlers.RequestHandler2;
import com.amazonaws.services.lambda.runtime.Context;
import com.myorg.convert.XsltConverter;

public class Xml2CsvHandler extends RequestHandler2 {
	
	private static final String XSLT_FUNC = "xsltFunc";
	private static final String XML_DATA = "xmlData";

	public String handle(Map<String,String> event, Context context) {
		
		XsltConverter converter = new XsltConverter();
		return converter.doWork(event.get(XML_DATA), event.get(XSLT_FUNC));
		
	}

}
