package sample.rvs.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileReceiver {

	public static final String PROP_FILERECEIVER_SAVEDIR = "FILERECEIVER_SAVEDIR" ;

	public void saveInputToDirectory(String name, InputStream is) throws FileNotFoundException, IOException {
		byte[] buffer = new byte[1024];
		String saveDir = System.getenv(PROP_FILERECEIVER_SAVEDIR);
		if (saveDir == null || saveDir.length() == 0)
			saveDir = System.getenv("temp");
		String fout = saveDir + File.separator + name;
		FileOutputStream fos = new FileOutputStream(fout);
		int cnt = 0;
		while((cnt = is.read(buffer)) > 0) {
			fos.write(buffer, 0, cnt);
		}
		fos.close();
	}
}
