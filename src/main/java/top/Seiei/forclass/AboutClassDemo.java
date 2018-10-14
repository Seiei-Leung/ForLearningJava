package top.Seiei.forclass;

import java.lang.reflect.Field;

/*
 *	测试 Class
 *	通过 Class 实例可以获取 class 中的所有信息
 *	判断一个类的是否存在，只需使用 Class.forName 方法，并捕获异常
 *	
 */

public class AboutClassDemo {

	public static void main(String[] args) {
		Class studentClass = Student.class;
		System.out.println(studentClass.getName());
		System.out.println(studentClass.getSimpleName());
		System.out.println(studentClass.getTypeName());
		System.out.println(studentClass.getPackage().getName());
		try {
			System.out.println("==========================");
			Field field = studentClass.getField("name");
			System.out.println(field.getName());
			Field field1 = studentClass.getDeclaredField("sex");
			field1.setAccessible(true); // 强制访问 private
			System.out.println(field1.get(null)); // 访问静态字段的时候只需传入 null 值
			Student student1 = new Student();
			System.out.println(field.get(student1));
			field.set(student1, "Taka");
			System.out.println(student1.name);
		} catch (NoSuchFieldException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		// 生成 class 实例
		try {
			System.out.println("==========================");
			Student student2 = (Student) studentClass.newInstance();
			System.out.println(student2.name);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		if (hasTeacher()) {
			System.out.println("==========================");
			System.out.println("有Teacher类");
		} else {
			System.out.println("没有Teacher类");
		}
	}

	public static boolean hasTeacher() {
		try {
			Class.forName("top.Seiei.forclass.Teacher"); // 注意使用的全类名
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}

class Student {
	public String name = "Seiei";
	private static String sex = "man";
}

class Teacher {
	public String name = "Seiei";
}
