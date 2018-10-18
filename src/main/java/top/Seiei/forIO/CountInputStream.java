package top.Seiei.forIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/*
 *	构建一个扩展 InputStream 类功能的 扩展类，使用的是组合方式而非继承
 *
 */


public class CountInputStream extends FilterInputStream {

	public CountInputStream(InputStream in) {
		super(in);
	}

	public int count = 0;
	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		int n = super.read(b, off, len);
		count += n;
		return n;
	}
	public static void main(String[] args) throws FileNotFoundException, IOException {
		try (CountInputStream countInputStream = new CountInputStream(new FileInputStream("src/forIO.txt"))) {
			byte[] bs = new byte[30];
			countInputStream.read(bs);
			System.out.println(countInputStream.count);
			InputStream inputStream = CountInputStream.class.getResourceAsStream("/log4j2.xml");
			if (inputStream != null) {
				System.out.println("classpath");
			}
		}
	}

}
