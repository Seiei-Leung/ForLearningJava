package top.Seiei.forIO;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 *	字符流 Reader 的使用
 */


public class AboutReadDemo {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// 
		try (Reader reader = new InputStreamReader(new FileInputStream("src/forIO.txt"), "UTF-8")) {
			char[] chars = new char[20];
			int n;
			while ((n = reader.read()) != -1) {
				System.out.println((char) n);
			}
		}
		try (Reader reader = new InputStreamReader(new FileInputStream("src/forIO.txt"), "UTF-8")) {
			char[] chars = new char[20];
			int n;
			while ((n = reader.read(chars)) != -1) {
				for (char item:chars) {
					System.out.println(item);
				}
			}
		}

	}

}
