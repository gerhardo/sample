import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.catalina.WebResource;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.junit.Before;
import org.junit.Test;

import sample.rvs.domain.FileSender;

public class FileReceiverUploadTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void uploadSingleFile() {

		String fileName = "test.dat";
		String svcUri ="http://localhost:8080/upload";
		try {
			FileSender fsw = new FileSender();
			fsw.doWork(fileName, svcUri);
		}
		catch (Exception e) {
			fail(e.toString());
		}
	}
}

