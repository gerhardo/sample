package sample.rvs.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import sample.rvs.domain.FileReceiverService;

@RestController
@RequestMapping("/")
public class FileReceiveController {

	@Autowired
	private FileReceiverService svc;

	@PostMapping()
	public ResponseEntity<String> uploadData(@PathVariable("filename") final String filename) {
		svc.writeFile(filename);
		return new ResponseEntity<String>("uploaded " + filename,HttpStatus.OK);
	}

}
