package top.Seiei.learningJava;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 *	正则表达式
 */

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

		// 搜索字符串，输出语句中某关键字的出场次数，并输出它的位置

		String str = "the quick brown fox jump over the lazy dog";
		Pattern pattern2 = Pattern.compile("the", Pattern.CASE_INSENSITIVE); // 忽略大小写
		Matcher matcher2 = pattern2.matcher(str);
		// 搜索字符串
		while (matcher2.find()) {
			String sub = str.substring(matcher2.start(), matcher2.end());
			System.out.println("the的起始位置：" + matcher2.start() + ",结束位置：" + matcher2.end());
		}

		// 关于正则表达式的替代
		System.out.println(str.replaceAll("(\\w+)", "<b>$1</b>")); // 这里的 `$1` 表示的是正则表达式中的第一个分组
	}

}
