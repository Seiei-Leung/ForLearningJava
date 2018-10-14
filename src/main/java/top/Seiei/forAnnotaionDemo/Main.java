package top.Seiei.forAnnotaionDemo;

import java.lang.reflect.Field;

/**
 * 运行期间通过反射读取注解，处理注解
 *
 */
public class Main {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		Person person1 = new Person("Seiei", 18);
		Person person2 = new Person(null, 18);
		Person person3 = new Person("Seiei", 51);
		checkPerson(person1);
		checkPerson(person2);
		checkPerson(person3);
	}

	static void checkPerson(Person person) throws IllegalArgumentException, IllegalAccessException {
		System.out.println("Check" + person + "...");

		Class cls = Person.class;

		// 获取 person 类的所有 public 字段，并循环检测
		for (Field feild : cls.getFields()) {
			checkFeild(feild, person);
		}
	}

	static void checkFeild(Field field, Person person) throws IllegalArgumentException, IllegalAccessException {
		// 当该字段被 @NotNull 修饰时，检测当前 person 实例的该字段的数值是否为空
		if (field.isAnnotationPresent(NotNull.class)) {
			// 获取实例的字段数值
			if ((field.get(person) == null)) {
				System.out.println("警告：name为空");
			}
		}

		// 当该字段被 @Range 修饰时，检测当前 person 实例的该字段的数值范围是否为在 @Range 注解的范围之内
		if (field.isAnnotationPresent(Range.class)) {
			// 获取当前 person实例 的 注解Annotion 实例
			Range range = field.getAnnotation(Range.class);
			// 获取当前 person实例 的字段数值
			int age = (int) field.get(person);
			// 获取 注解实例 的字段数值
			if (!(range.min() < age && age < range.max())) {
				System.out.println("警告：年龄超出范围了");
			}
		}
	}
}
