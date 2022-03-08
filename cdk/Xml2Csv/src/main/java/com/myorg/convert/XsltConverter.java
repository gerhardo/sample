package com.myorg.convert;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XsltConverter  {
	
	public String doWork(String xmlData, String xsltFunc) {
		
		Logger log = Logger.getLogger("converter");
		log.log(Level.INFO, "convert data: " + xmlData);
		log.log(Level.INFO, "using func: " + xsltFunc);
		TransformerFactory factory = TransformerFactory.newInstance();
		Source xslt = new StreamSource(new StringReader(xsltFunc));
		Transformer transformer = null;
		try {
			transformer = factory.newTransformer(xslt);
		} catch (TransformerConfigurationException e) {
			log.log(Level.SEVERE, e.getMessageAndLocation());
			return null;
		}

		Source text = new StreamSource(new StringReader(xmlData));
		StringWriter result = new StringWriter();
		try {
			// No quotes around text
			transformer.setParameter("quote", "");
			transformer.setParameter("delim", ",");
			// Transform work
			transformer.transform(text, new StreamResult(result));
			return result.toString();
		} catch (TransformerException e) {
			log.log(Level.SEVERE, e.getMessageAndLocation());
			return null;
		}
	}
	

}
