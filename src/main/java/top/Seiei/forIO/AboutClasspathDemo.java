package top.Seiei.forIO;

import java.io.IOException;
import java.io.InputStream;

/*
 *	使用  classpath资源 获取 log4j2.xml 文件
 *	使用 classpath资源 获取文件路径信息
 *
 */

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
		// file:/F:/javacode/ForLearningJava/target/classes/top/Seiei/forIO/
		System.out.println(AboutClasspathDemo.class.getResource("/log4j2.xml"));
		// file:/F:/javacode/ForLearningJava/target/classes/log4j2.xml
	}
}
