package top.Seiei.forTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 *	Date 转换 String，String 转换 Date
 *
 */

public class AboutDateDemo {
	public static void main(String[] args) {
		Date date = new Date();
		System.out.println("获取当前时间戳：" + date.getTime());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("格式化输出日期：" + simpleDateFormat.format(date));
		try {
			// 字符串转 Date
			System.out.println(simpleDateFormat.parse("2018-11-11").getDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
