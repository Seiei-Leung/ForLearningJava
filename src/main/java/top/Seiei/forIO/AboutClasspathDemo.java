package top.Seiei.forIO;

import java.io.IOException;
import java.io.InputStream;

public class AboutClasspathDemo {
	public static void main(String[] args) throws IOException {

		// classpath 其实就是 buildpath
		// 注意路径前要添加 /
		try (InputStream inputStream = AboutClasspathDemo.class.getResourceAsStream("/log4j2.xml")) {
			if (inputStream != null) {
				System.out.println("找到log4j2.xml");
			}
		}
		System.out.println(AboutClasspathDemo.class.getResource(""));
		System.out.println(AboutClasspathDemo.class.getResource("/log4j2.xml"));
	}
}
