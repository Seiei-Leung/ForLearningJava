package top.Seiei.learningJava;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AboutRegexDemo {

	public static void main(String[] args) {
		
		// Java 字符串的 \ 表示转义
		
		String regexString = "\t";
		System.out.println(regexString);
		System.out.println("上面是一个 Tab");
		
		// 判断一个格式 yyyy-MM 的日期

		// 创建正则表达式
		Pattern pattern = Pattern.compile("([^0]\\d{0,3})\\-((0[1-9])|(1[12]))");

		// 获取 Matcher 对象
		Matcher matcher = pattern.matcher("2018-01");

		// 是否匹配成功
		System.out.println(matcher.matches()); // true

		// 提取分组
		if (matcher.matches()) {
			System.out.println("提取字符串是：" + matcher.group(0) + "，年份是：" + matcher.group(1) + "，月份是：" + matcher.group(2));
		}
	}

}
