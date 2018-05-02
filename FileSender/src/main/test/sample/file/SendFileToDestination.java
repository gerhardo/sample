package sample.file;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sample.file.domain.FileSender;

public class SendFileToDestination {

	@Before
	public void setUp() throws Exception {
	}

	//@Test
	public void test() {
		try {
			FileSender fs = new FileSender();
			fs.doWork(Thread.currentThread().getContextClassLoader().getResource("test.txt").getPath(), "http://dummy.domain/upload");
		}
		catch(Exception e) {
			fail(e.toString());
		}
	}

}
