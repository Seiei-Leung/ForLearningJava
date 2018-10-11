package learningJava;

import java.util.Scanner;

/**
 * 	测试 scanner 类
 *
 */
public class AboutSannerDemo {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("输入你的名字：");
		
		// 读取字符串
		String name = scanner.nextLine();
		System.out.println("你的名字是：" + name);
		System.out.println("输入你的年龄：");
		
		// 读取整数
		int age = scanner.nextInt();
		System.out.println("你的年龄是：" + age);

	}

}
