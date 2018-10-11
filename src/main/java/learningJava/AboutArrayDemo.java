package learningJava;

import java.util.Arrays;

/**
 *	关于数组的打印，使用 Arrays.toString() 这个静态方法
 *	关于数组的排序，使用 Arrays.Sort() 这个静态方法，此时直接改变原数组
 *  
 *
 */
public class AboutArrayDemo {

	public static void main(String[] args) {
		int[] intArray = {3,1,6};
		System.out.println(intArray);
		System.out.println(Arrays.toString(intArray));
		Arrays.sort(intArray);
		System.out.println(Arrays.toString(intArray));
	}

}
