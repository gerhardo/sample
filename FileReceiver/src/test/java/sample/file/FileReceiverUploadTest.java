package sample.file;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import sample.file.domain.FileReceiver;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = sample.file.FileReceiverApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class FileReceiverUploadTest {

	@LocalServerPort
	private int port;

	@Before
	public void setUp() {
		System.setProperty(FileReceiver.PROP_FILERECEIVER_SAVEDIR, "/tmp");
	}

	@Test
	public void uploadSingleFile() throws IOException {

		String fileName = "background.bmp";
		String filePath = Thread.currentThread().getContextClassLoader().getResource(fileName).getPath();
		String.valueOf(port);
		try {
			// FileSender fsw = new FileSender();
			// fsw.doWork(filePath, svcUri);

		} catch (Exception e) {
			fail(e.toString());
		}

		// test received file
		File f = new File(System.getProperty(FileReceiver.PROP_FILERECEIVER_SAVEDIR) + File.separatorChar + fileName);
		assertNotNull(f);
		assertTrue(f.canRead());

		File source = new File(filePath);
		assertTrue(f.length() == source.length());

		byte[] srcbuf = new byte[4096];
		byte[] targetbuf = new byte[4096];

		FileInputStream fis1 = new FileInputStream(source);
		FileInputStream fis2 = new FileInputStream(f);

		int cnt = 0;
		while ((cnt = fis1.read(srcbuf)) > 0) {
			fis2.read(targetbuf, 0, cnt);
			assertTrue(Arrays.equals(srcbuf, targetbuf));
		}

		fis1.close();
		fis2.close();
	}
}
