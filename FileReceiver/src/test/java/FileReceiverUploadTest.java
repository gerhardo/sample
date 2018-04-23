import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import sample.rvs.domain.FileReceiver;
import sample.rvs.domain.FileSender;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = sample.rvs.FileReceiverApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class FileReceiverUploadTest {

	@LocalServerPort
	private int port;

	@Before
	public void setUp() {
		System.setProperty(FileReceiver.PROP_FILERECEIVER_SAVEDIR, "/tmp");
	}

	@Test
	public void uploadSingleFile() {

		String fileName = "test.dat";
		String svcUri ="http://localhost:" + String.valueOf(port) + "/upload";
		try {
			FileSender fsw = new FileSender();
			fsw.doWork(Thread.currentThread().getContextClassLoader().getResource(fileName).getPath(), svcUri);
		}
		catch (Exception e) {
			fail(e.toString());
		}
	}
}

