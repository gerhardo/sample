package sample.rvs.api;

import java.io.InputStream;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import sample.rvs.domain.FileReceiver;

@RestController
@RequestMapping("/")
public class FileReceiverController {

	private static final Logger LOG = Logger.getLogger(FileReceiverController.class.getName());

	@PostMapping(path ="upload/{filename}", consumes="application/octet-stream")
	public ResponseEntity<String> uploadFile(@PathVariable("filename") String filename,
            InputStream fileInputStream) {

		try {
			LOG.fine("writing of " + filename + " started");
			FileReceiver fr = new FileReceiver();
			fr.saveInputToDirectory(filename, fileInputStream);
			LOG.info("upload of " + filename + " done");
			return new ResponseEntity<String>("uploaded " + filename,HttpStatus.OK);
		}
		catch (Exception e) {
			LOG.severe("exception receiving file: " + e.toString());
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}


	@GetMapping("health-check")
	public ResponseEntity<String> areYouThere() {
		return new ResponseEntity<String>("file receiver running", HttpStatus.OK);
	}


}
