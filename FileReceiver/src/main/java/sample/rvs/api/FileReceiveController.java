package sample.rvs.api;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import sample.rvs.domain.FileReceiverService;

@RestController
@RequestMapping("/")
public class FileReceiveController {

	@Autowired
	private FileReceiverService svc;

	@PostMapping(path ="upload/{filename}", consumes="application/octet-stream")
	public ResponseEntity<String> uploadFile(@PathVariable("filename") String filename,
            InputStream fileInputStream) {
		svc.writeFile(filename, fileInputStream);

		return new ResponseEntity<String>("uploaded " + filename,HttpStatus.OK);
	}


	@GetMapping("hi/{name}")
	public ResponseEntity<String> areYouThere(@PathVariable("name") final String name) {
		return new ResponseEntity<String>("Here is " + name + ", how are you ?", HttpStatus.OK);
	}

}
