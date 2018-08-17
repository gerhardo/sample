package sample.file.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

public class FileSender {

	private static final String AUTHORIZATION = "Authorization";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String BASIC_AUTH_TOKEN = "BASIC_AUTH_TOKEN";
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
		Builder builder = target.path(f.getName()).request(MediaType.APPLICATION_OCTET_STREAM_TYPE)
				.header("Content-Disposition", contentDisposition).header("Content-Length", (int) f.length());
		appendAuthorizationHeader(builder);
		Response response = builder.post(Entity.entity(fileInStream, MediaType.APPLICATION_OCTET_STREAM_TYPE));

		LOG.info("done, response was " + String.valueOf(response.getStatus()));
		return response.getStatus();
	}

	/**
	 * Appends an authorization header if properties with content are available.
	 *
	 * @param builder
	 */
	private void appendAuthorizationHeader(Builder builder) {
		Properties props = System.getProperties();
		String tmp = props.getProperty(BASIC_AUTH_TOKEN);
		if (tmp == null || tmp.isEmpty()) {
			tmp = props.getProperty(USERNAME);
			if (tmp != null) {
				if (props.containsKey(PASSWORD)) {
					tmp += ":" + props.getProperty(PASSWORD);
					tmp = java.util.Base64.getEncoder().encodeToString(tmp.getBytes());
				}
			}
		}
		if (tmp != null) {
			builder.header(AUTHORIZATION, "Basic " + tmp);
		} else {
			LOG.fine("no authorization info found");
		}
	}
}
