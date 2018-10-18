package top.Seiei.forGenerics;

/**
 *	测试 泛型 <? extends Number> 作为方法参数时，对它的操作限制
 */

public class AboutExtendsDemo {

	public static void main(String[] args) {
		
	}

}

//泛型类
class One<T> {
	private T first;
	public T getFirst() {
		return first;
	}
	public void setFirst(T first) {
		this.first = first;
	}
}

//使用 <? extends Number> 通配符，此时只能传入泛型参数类型为Number或子类的One类型了
class OneHelper {
	static void test(One<? extends Number> one) {
		// 此时编译器不可预测实际类型究竟是Integer类型还是其他，但可以安全地将数值赋予给Number类型的变量
		Number fl = new Float(1.2f);
		Number number = one.getFirst();
		// one.setFirst(fl);
	}
}