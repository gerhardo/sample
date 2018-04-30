package sample.file.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.util.StringUtils;

/**
 * Handle a received file.
 *
 * @author geotte
 *
 */
public class FileReceiver {

	public static final String PROP_FILERECEIVER_SAVEDIR = "FILERECEIVER_SAVEDIR" ;

	/**
	 * Read input stream and write it to a file.
	 * @param name
	 * @param is
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void saveInputToDirectory(String name, InputStream is) throws FileNotFoundException, IOException {
		byte[] buffer = new byte[1024];

		String saveDir = System.getProperty(PROP_FILERECEIVER_SAVEDIR);
		if (StringUtils.isEmpty(saveDir)) {
			saveDir = System.getenv(PROP_FILERECEIVER_SAVEDIR);
		}
		if (StringUtils.isEmpty(saveDir)) {
			throw new IllegalArgumentException("Environment variable or property  '" + PROP_FILERECEIVER_SAVEDIR + "' not set. Set it to a valid directory for saving.");
		}
		String fout = saveDir + File.separator + name;
		FileOutputStream fos = new FileOutputStream(fout);
		int cnt = 0;
		while((cnt = is.read(buffer)) > 0) {
			fos.write(buffer, 0, cnt);
		}
		fos.close();
	}
}
