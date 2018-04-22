package sample.rvs.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class FileReceiverController {

	private static final Logger LOG = Logger.getLogger(FileReceiverController.class.getName());

	public static final String PROP_FILERECEIVER_SAVEDIR = "FILERECEIVER_SAVEDIR" ;

	@PostMapping(path ="upload/{filename}", consumes="application/octet-stream")
	public ResponseEntity<String> uploadFile(@PathVariable("filename") String filename,
            InputStream fileInputStream) {

		LOG.fine("writing of " + filename + " started");
		saveInputToDirectory(filename, fileInputStream);
		LOG.info("upload of " + filename + "done");

		return new ResponseEntity<String>("uploaded " + filename,HttpStatus.OK);
	}


	@GetMapping("health-check")
	public ResponseEntity<String> areYouThere() {
		return new ResponseEntity<String>("file receiver running", HttpStatus.OK);
	}

	private void saveInputToDirectory(String name, InputStream is) {
		try {

			byte[] buffer = new byte[1024];
			String saveDir = System.getProperty(PROP_FILERECEIVER_SAVEDIR, System.getenv("temp"));
			String fout = saveDir + File.separator + name;
			FileOutputStream fos = new FileOutputStream(fout);
			int cnt = 0;
			while((cnt = is.read(buffer)) > 0) {
				fos.write(buffer, 0, cnt);
			}
			fos.close();
		}
		catch (Exception e) {
			LOG.severe("writing uploaded file '" + name + "' failed: " + e.toString());
		}
	}
}
