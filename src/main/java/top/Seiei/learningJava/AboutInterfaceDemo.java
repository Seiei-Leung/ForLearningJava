package top.Seiei.learningJava;

/*
 * 	接口的字段不能是实例字段，只能声明常量，默认自带 final
 */
public interface AboutInterfaceDemo {
	public int field = 1;
	public String field2 = "123";
	public static String field3 = "123";
}

class AboutInterfaceClass implements AboutInterfaceDemo {
	public String field2 = "456";
	public static void main(String[] args) {
		AboutInterfaceClass aboutInterfaceClass = new AboutInterfaceClass();
		System.out.println(aboutInterfaceClass.field2);
		AboutInterfaceDemo aboutInterfaceDemo = aboutInterfaceClass;
		System.out.println(aboutInterfaceDemo.field2);
	}
}