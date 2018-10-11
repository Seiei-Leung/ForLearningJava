package top.Seiei.learningJava;

/**
 * 	测试向下转型的条件
 * 	需要由子类向上转型为父类后，该父类才能向下转型，此时需要强制转型
 *	最好在向下转型前，先使用 instanceof 判断
 *	即引用变量的声明类型与其实际类型可能不同，实际对象的方法调用总是实际类型，所以 Java 的实例方法调用是基于运行时的动态调用，这种动态调用叫做 动态
 *	多态是指针对某个类型方法的调用，其真正执行的方法取决于运行时的实际类型的方法
 */
public class AboutExtendsDemo {
	public static void main(String[] args) {
		Father sonTofather = new Son();
		if (sonTofather instanceof Son) {
			Son son = (Son) sonTofather;
			System.out.println("子类先向上转型为父类，后该父类可以向下转型回子类");
			son.say();
		}
		Father father = new Father();
		if (father instanceof Son) {
			Son son = (Son) father;
			System.out.println("父类直接向上转型成功");
			son.say();
		}
	}
}

class Father {
	void say() {
		System.out.println("i am father");
	}
}

class Son extends Father {
	@Override
	void say() {
		System.out.println("i am son");
	}

}
