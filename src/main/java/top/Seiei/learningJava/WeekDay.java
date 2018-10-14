package top.Seiei.learningJava;

/*
 *	枚举Demo
 *	enum 可以定义常量类型，它被编译器编译为： final class Xxx extends Enum {...}
 *	使用 name() 可以获取常量定义的字符串
 *	可以为枚举类声明构造函数，字段和方法
 *	枚举中的构造函数是私有类，也就是无法再外面创建enum
 *	构造方法声明为 private
 *	因为枚举类实例只有一个，所以可以直接使用 等号 来确定二者是否相同
 *
 */
public enum WeekDay {

	SUN("星期日"), MON("星期一"), TUE("星期二"), WED("星期三"), THU("星期四"), FRI("星期五"), SAT("星期六");

	private String chinese;

	public String toChinese() {
		return chinese;
	}

	WeekDay(String chinese) {
		this.chinese = chinese;
	}
}

class TestForWeekDayEnum {

	public static void main(String[] args) {
		WeekDay day1 = WeekDay.SUN;
		WeekDay day2 = WeekDay.SUN;
		System.out.println(day1.name());
		System.out.println(day1.toChinese());
		System.out.println(day1 == day2);
		Integer int1 = 100;
		Integer int2 = 100;
		Integer int3 = new Integer(100);
		System.out.println(int1 == int2);
		System.out.println(int1 == int3);
	}
}