package sample.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Application fpr receiving files via HTTP.
 *
 * @author geotte
 *
 */
@SpringBootApplication
@EnableSwagger2
public class FileReceiverApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileReceiverApplication.class, args);
	}
}
