package sample.file.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Logger;

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

	private static final Logger LOG = Logger.getLogger(FileSender.class.getName());

	/**
	 * Send a file to the target address.
	 *
	 * @param filePath
	 * @param targetAdress
	 * @return HTTP Status code
	 * @throws FileNotFoundException
	 */
	public int doWork(String filePath, String targetAdress) throws FileNotFoundException {

		File f = new File(filePath);
		if (f == null || !f.canRead()) {
			throw new IllegalArgumentException("file '" + filePath + "' not found or can not be read");
		}

		Configuration clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		client.property(ClientProperties.REQUEST_ENTITY_PROCESSING, "CHUNKED");

		LOG.info("sending " + filePath + " to " + targetAdress);

		WebTarget target = client.target(targetAdress);
		InputStream fileInStream = new FileInputStream(filePath);
		String contentDisposition = "attachment; filename=\"" + f.getName() + "\"";
		Response response = target.path(f.getName()).request(MediaType.APPLICATION_OCTET_STREAM_TYPE)
				.header("Content-Disposition", contentDisposition).header("Content-Length", (int) f.length())
				.post(Entity.entity(fileInStream, MediaType.APPLICATION_OCTET_STREAM_TYPE));

		LOG.info("done, respone was " + String.valueOf(response.getStatus()));
		return response.getStatus();
	}
}
