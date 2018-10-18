package top.Seiei.forIO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/*
 *	FileOutputStream 的使用
 */

public class AboutOutputStreamDemo {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		try (OutputStream output = new FileOutputStream("src/forIO.txt")) {
			byte[] outputString = "中文".getBytes("UTF-8");
			output.write(outputString, 0, outputString.length);
		}

	}

}
