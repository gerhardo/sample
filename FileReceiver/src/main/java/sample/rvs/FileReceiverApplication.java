package sample.rvs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application fpr receiving files via HTTP.
 *
 * @author geotte
 *
 */
@SpringBootApplication
public class FileReceiverApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileReceiverApplication.class, args);
	}
}


