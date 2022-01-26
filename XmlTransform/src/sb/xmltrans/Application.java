package sb.xmltrans;

import java.io.File;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class Application {

	public static void main(String[] args) {
		TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File("resources/transform.xslt"));
        Transformer transformer= null;
		try {
			transformer = factory.newTransformer(xslt);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}

        Source text = new StreamSource(new File("resources/input.xml"));
        try {
			transformer.transform(text, new StreamResult(new File("output.xml")));
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

}
