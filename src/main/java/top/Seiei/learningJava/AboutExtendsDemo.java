package top.Seiei.learningJava;


/**
 * 
 *	测试对象初始化的顺序
 *	关于继承对象的重写，编译器只会检查 final 修饰的方法，静态字段和 final 修饰的字段尽管重写不了但不会报错
 *	假如在子类中重新声明一个一模一样的字段，则重新命名的字段与父类的字段再无关系，如果此时向上转型，访问得到的将是父类声明的字段
 *	即引用变量的声明类型与其实际类型可能不同，实际对象的方法调用总是实际类型，所以 Java 的实例方法调用是基于运行时的动态调用，这种动态调用叫做 动态
 *	多态是指针对某个类型方法的调用，其真正执行的方法取决于运行时的实际类型的方法
 *
 */
public class AboutExtendsDemo {
	public static void main(String[] args) {
		System.out.println("输出继承对象的初始化顺序：");
		// 输出：父类静态构造方法块->子类静态构造方法块->父类构造方法块->父类构造函数->子类构造方法块->子类构造函数
		
		Son son = new Son();
		Father sonToFather = son; // 向上转型

		System.out.println(" ");
		System.out.println("====================================================================================");
		System.out.println(" ");
		System.out.println("关于字段与方法的重写：");
		System.out.println(" ");
		System.out.println("子类的静态字段：" + Son.staticData); // 子类静态字段
		System.out.println("子类的实例字段（在子类重新声明过）：" + son.instanceData); // 子类实例字段
		System.out.println("子类的实例字段（没在子类重新声明过）：" + son.instanceData2); // 子类实例字段
		System.out.println("子类的 final 静态字段：" + Son.finalStaticData); // 子类静态 final 字段
		System.out.println("子类的静态方法：" + Son.staticMethod()); // 子类静态方法
		System.out.println("子类的实例方法：" + son.instanceMethod()); // 子类实例方法
		System.out.println("子类的 final 静态方法：" + Son.finalStaticMethod()); // 父类 final 静态方法
		System.out.println("子类的 final 实例方法：" + son.finalInstanceMethod()); // 父类 final 实例方法
		
		System.out.println(" ");
		System.out.println("====================================================================================");
		System.out.println(" ");
		System.out.println("向上转型后");
		System.out.println("sonToFather的静态字段：" + sonToFather.staticData); // 父类静态字段
		System.out.println("sonToFather的实例字段（在子类重新声明过）：" + sonToFather.instanceData); // 父类类实例字段
		System.out.println("sonToFather的实例字段（没在子类重新声明过）：" + sonToFather.instanceData2); // 子类类实例字段
		System.out.println("sonToFather的 final 静态字段：" + sonToFather.finalStaticData); // 父类静态 final 字段
		System.out.println("sonToFather的静态方法：" + sonToFather.staticMethod()); // 父类静态方法
		System.out.println("sonToFather的实例方法：" + son.instanceMethod()); // 子类实例方法
		System.out.println("sonToFather的 final 静态方法：" + sonToFather.finalStaticMethod()); // 父类 final 静态方法
		System.out.println("sonToFather的 final 实例方法：" + sonToFather.finalInstanceMethod()); // 父类 final 实例方法

		System.out.println(" ");
		System.out.println("====================================================================================");
		System.out.println(" ");
		sonToFather.instanceData = "修改过的实例字段";
		System.out.println("修改 sonToFather 的 instanceData 为 123，此时的 son 变量的 instanceData 是：" + son.instanceData);
		// 子类实例字段
		sonToFather.instanceData2 = "修改过的实例字段";
		System.out.println("修改 sonToFather 的 instanceData2 为 456，此时的 son 变量的 instanceData 是：" + son.instanceData2);
		// 修改过的实例字段

		System.out.println(" ");
		System.out.println("====================================================================================");
		System.out.println(" ");
		if (sonToFather instanceof Son) {
			son = (Son) sonToFather;
			System.out.println("向下转型后调用" + son.sonMethod());
		}
	}
}

class Father {
	{
		System.out.print("父类构造方法块->");
	}
	static {
		System.out.print("父类静态构造方法块->");
	}
	Father() {
		System.out.print("父类构造函数->");
	}
	
	// 父类静态字段,可以不赋值
	static String staticData = "父类静态字段";
	
	// 子类实例字段，可以不赋值，在子类中重新声明该字段
	String instanceData = "父类实例字段";
	
	// 子类实例字段，可以不赋值，不在在子类中重新声明该字段
	String instanceData2 = "父类实例字段";
	
	// 使用了final修饰的静态字段，假如不在声明中赋值或者在静态代码块中赋值就会报错
	final static String finalStaticData = "父类静态 final 字段"; 
	
	// 使用了final修饰的实例字段，假如不在声明中赋值或者在父类构造方法块或构造函数中赋值就会报错
	final String finalInstanceData = "父类实例 final 字段";
	
	// 父类实例方法
	static String staticMethod() {
		return "父类静态方法";
	}

	// 父类实例方法
	String instanceMethod() {
		return "父类实例方法";
	}
	
	// 父类 final 修饰的方法，final 修饰符比 返回类型 要靠前
	final String finalInstanceMethod() {
		return "父类 final 实例方法";
	}

	// 父类 final 修饰的静态方法，final 修饰符比 返回类型 要靠前
	static final String finalStaticMethod() {
		return "父类 final 静态方法";
	}
	
}

class Son extends Father {
	{
		System.out.print("子类构造方法块->");
	}
	static {
		System.out.print("子类静态构造方法块->");
	}
	Son() {
		instanceData2 = "子类实例字段"; // 在子类没有重新声明，而是在构造函数中修改
		System.out.print("子类构造函数");
	}
	static String staticData = "子类静态字段";
	String instanceData = "子类实例字段";
	final static String finalStaticData = "子类静态 final 字段"; 
	final String finalInstanceData = "子类实例 final 字段";
	
	static String staticMethod() {
		return "子类静态方法";
	}
	
	String instanceMethod() {
		return "子类实例方法";
	}
	
	String sonMethod() {
		return "子类独有的方法";
	}
	
//	final void finalInstanceMethod() {
//		System.out.println("子类 final 实例方法");
//	}

//	static final void finalStaticMethod() {
//		System.out.println("子类 final 静态方法");
//	}
	
}
