package sample.rvs.domain;

import java.io.FileOutputStream;
import java.io.InputStream;

import org.springframework.stereotype.Service;

@Service
public class FileReceiverService {

	public void writeFile(String name, InputStream is) {

		try {

			byte[] buffer = new byte[1024];
			String fout = System.getenv("temp") + "\\" + name;
			FileOutputStream fos = new FileOutputStream(fout);
			int cnt = 0;
			while((cnt = is.read(buffer)) > 0) {
				fos.write(buffer, 0, cnt);
			}
			fos.close();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}

	}

}
