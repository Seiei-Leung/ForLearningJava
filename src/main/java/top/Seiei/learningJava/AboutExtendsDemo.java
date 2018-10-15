package top.Seiei.learningJava;

import java.util.ArrayList;
import java.util.List;

/**
 * 	测试向下转型的条件
 * 	需要由子类向上转型为父类后，该父类才能向下转型，此时需要强制转型
 *	最好在向下转型前，先使用 instanceof 判断
 *	即引用变量的声明类型与其实际类型可能不同，实际对象的方法调用总是实际类型，所以 Java 的实例方法调用是基于运行时的动态调用，这种动态调用叫做 动态
 *	多态是指针对某个类型方法的调用，其真正执行的方法取决于运行时的实际类型的方法
 */
public class AboutExtendsDemo {
	public AboutArrayDemo aboutArrayDemo;
	public static void main(String[] args) {
		// 初始化子类，并赋值年龄字段为 50
		Son son = new Son(50);
		// 子类向上转型
		Father sonTofather = son;
		// 在向上转型的父类中修改字段为 40，查看初始定义的子类源变量的该字段有否变化
		// 值得注意的是假如在 子类中重新命名一个一模一样的字段，则重新命名的字段会覆盖掉父类的字段
		sonTofather.age = 40;
		// 检查
		System.out.println("在向上转型的父类中修改字段为 40,查看初始定义的子类源变量的该字段是多少:" + son.age);
		if (sonTofather instanceof Son) {
			Son son1 = (Son) sonTofather;
			System.out.println("子类先向上转型为父类，后该父类可以向下转型回子类");
			System.out.println(sonTofather.age);
			System.out.println(son1.age);
			System.out.println("查看 final 修饰的字段究竟有没被继承修改：" + sonTofather.name);
			System.out.println("查看 final 修饰的字段究竟有没被继承修改：" + son1.name);
			System.out.println("查看静态字段究竟有没被继承修改：" + sonTofather.tall);
			System.out.println("查看静态字段究竟有没被继承修改：" + son1.tall);
			son1.say();
		}
		Father father = new Father(80);
		if (father instanceof Son) {
			Son son1 = (Son) father;
			System.out.println("父类直接向上转型成功");
			son1.say();
		}
	}
}

class Father {
	public Father(int age) {
		this.age = age;
	}
	public int age;
	public final String name = "father";
	public static String tall = "1.6";
	void say() {
		System.out.println("i am father");
	}
}

class Son extends Father {
	public Son(int age) {
		super(age);
	}
	public final String name = "son";
	public static String tall = "1.8";
	@Override
	void say() {
		System.out.println("i am son");
	}

}
