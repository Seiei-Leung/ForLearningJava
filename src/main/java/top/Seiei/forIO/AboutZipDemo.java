package top.Seiei.forIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 
 * 	解压一个压缩包
 *
 */

public class AboutZipDemo {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream("src/forIO.zip"))) {
			ZipEntry entry = null;
			// 循环调用 getNextEntry 获取 ZipEntry，直至返回 null
			while ((entry = zipInputStream.getNextEntry()) != null) {
				// 解压
				// 每个 entry 表示着一个压缩文件或目录
				if (!entry.isDirectory()) {
					try (OutputStream outputStream = new FileOutputStream("src/zipTotxt.txt")) {
						int n;
						byte[] bt = new byte[1024];
						// 此时这个 zipInputStream 会随着循环获取 entry 而变化
						while ((n = zipInputStream.read(bt)) != -1) {
							outputStream.write(bt, 0, n);
						}
					}
				}
			}
		}

	}

}
