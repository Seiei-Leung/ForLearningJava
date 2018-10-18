package top.Seiei.forGenerics;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 *	测试泛类继承，由子类获取父类的泛型类型
 *
 */
public class Main {

	public static void main(String[] args) {
		// 获取子类的 class
		Class<IntOne> cls = IntOne.class;
		
		// 这个 Type 的实际类型是 ParameterizedType (参数类型)
		Type t = cls.getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			ParameterizedType pType = (ParameterizedType) t;
			// 通过使用它的 getActualTypeArguments，便可以返回 Type 类型的数组，其中包含的就是父类中的泛型数组
			Type[] types = pType.getActualTypeArguments();
			// 这里实例只定义一个 Integer 参数类型，所以数组长度只为 1
			Type firstType = types[0];
			Class<?> typeClass = (Class<?>) firstType;
			System.out.println(typeClass);
			
		}
	}

}

class IntOne extends One<Integer> {
}