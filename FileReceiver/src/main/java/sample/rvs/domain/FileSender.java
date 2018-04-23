package sample.rvs.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

public class FileSender {

	/**
	 * Send a file to the target address.
	 * @param filePath
	 * @param targetAdress
	 * @return HTTP Status code
	 * @throws FileNotFoundException
	 */
	public int doWork(String filePath, String targetAdress) throws FileNotFoundException {

		URL url= Thread.currentThread().getContextClassLoader().getResource(filePath);
		File f = new File(url.getFile());

		Configuration clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		client.property(ClientProperties.REQUEST_ENTITY_PROCESSING, "CHUNKED");

		WebTarget target = client.target(targetAdress);
		InputStream fileInStream = new FileInputStream(url.getFile());
		String contentDisposition = "attachment; filename=\"" + f.getName() + "\"";
		Response response = target.path(f.getName())
		            .request(MediaType.APPLICATION_OCTET_STREAM_TYPE)
		            .header("Content-Disposition", contentDisposition)
		            .header("Content-Length", (int) f.length())
		            .post(Entity.entity(fileInStream, MediaType.APPLICATION_OCTET_STREAM_TYPE));

		return response.getStatus();
	}
}
