package top.Seiei.learningJava;

/*
 * 	接口的字段默认自带 final 修饰符，必须初始化
 */
interface AboutInterface {
	public int field = 1;
	public String field2 = "123";
	public static String field3 = "123";
}

public class AboutInterfaceDemo implements AboutInterface {
	public String field2 = "456";
	public static void main(String[] args) {
		AboutInterfaceDemo aboutInterfaceClass = new AboutInterfaceDemo();
		System.out.println(aboutInterfaceClass.field2);
		AboutInterface aboutInterfaceDemo = aboutInterfaceClass;
		System.out.println(aboutInterfaceDemo.field2);
	}
}