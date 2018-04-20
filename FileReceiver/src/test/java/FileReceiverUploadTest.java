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

public class FileReceiverUploadTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void uploadSingleFile() {

		String fileName = "test.dat";
		String svcUri ="http://localhost:8080/upload";

		try {
			URL url= Thread.currentThread().getContextClassLoader().getResource(fileName);
			File f = new File(url.getFile());

			Configuration clientConfig = new ClientConfig();
			Client client = ClientBuilder.newClient(clientConfig);
			client.property(ClientProperties.REQUEST_ENTITY_PROCESSING, "CHUNKED");

			WebTarget target = client.target(svcUri);
			InputStream fileInStream = new FileInputStream(url.getFile());
			String contentDisposition = "attachment; filename=\"" + f.getName() + "\"";
			Response response = target.path(f.getName())
			            .request(MediaType.APPLICATION_OCTET_STREAM_TYPE)
			            .header("Content-Disposition", contentDisposition)
			            .header("Content-Length", (int) f.length())
			            .post(Entity.entity(fileInStream, MediaType.APPLICATION_OCTET_STREAM_TYPE));
			System.out.println(response.getStatus());
			System.out.println(response.getHeaders());

		}
		catch (Exception e) {
			fail(e.toString());
		}
	}
}

