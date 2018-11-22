package top.Seiei.learningJava;

import org.apache.commons.lang3.StringUtils;

public class AboutStringUtilDemo {

	static String emptyString = "";
	static String nullString = null;
	static String blankString = "  ";
	static String hasBlankString = " seiei  ";
	
	public static void main(String[] args) {
		// 使用 StringUtils.isEmpty，String.isNotEmpty 同理
		System.out.println("使用 isEmpty 判断 emptyString:" + StringUtils.isEmpty(emptyString)); // true
		System.out.println("使用 isEmpty 判断 nullString:" + StringUtils.isEmpty(nullString)); // true
		System.out.println("使用 isEmpty 判断 blankString:" + StringUtils.isEmpty(blankString)); // false
		
		// 使用 StringUtils.isBlank，StringUtils.isNotBlank 同理
		System.out.println("使用 isBlank 判断 emptyString:" + StringUtils.isBlank(emptyString)); // true
		System.out.println("使用 isBlank 判断 nullString:" + StringUtils.isBlank(nullString)); // true
		System.out.println("使用 isBlank 判断 blankString:" + StringUtils.isBlank(blankString)); // true

		// 使用 字符串的静态 isEmpty 
		System.out.println("使用字符串的静态 isEmpty 判断 emptyString:" + emptyString.isEmpty()); // true
		// System.out.println("使用字符串的静态 isEmpty 判断 nullString:" + nullString.isEmpty()); // 报错
		System.out.println("使用字符串的静态 isEmpty 判断 blankString:" + blankString.isEmpty()); // false
		
		// 使用 StringUtils.trim()，使用时可以查看编译器给的信息，获取 trim, trimToEmpty, trimToNull 有什么不同
		System.out.println("使用 StringUtils.trim():" + StringUtils.trim(hasBlankString));
		
	}

}
