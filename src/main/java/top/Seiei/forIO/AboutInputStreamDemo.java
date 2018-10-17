package top.Seiei.forIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class AboutInputStreamDemo {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// jdk1.7 支持的 try(resource) 自动调用 close() 方法
		// 使用缓冲区读取字节
		try (InputStream fileInputStream = new FileInputStream("F:\\javacode\\ForLearningJava\\src\\forIO.txt")) {
			int n;
			byte[] bytes = new byte[20]; // new byte[1024]，一个中文占3个字节
			while ((n = fileInputStream.read(bytes)) != -1) {
				System.out.println("read " + n + " bytes");
				for (byte item:bytes) {
					System.out.println(item);
				}
			}
		}
		System.out.println("==========================");
		// 使用 try finally 关闭资源
		// 使用read直接读取字节
		InputStream input = null;
		try {
			input = new FileInputStream("src/forIO.txt");
			int n;
			while ((n = input.read()) != -1) {
				System.out.println(n);
			}
		} finally {
			if (input != null) {
				input.close();
			}
		}
	}
}
