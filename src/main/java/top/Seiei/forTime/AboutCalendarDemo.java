package top.Seiei.forTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AboutCalendarDemo {

	public static void main(String[] args) throws ParseException {
		
		// 初始化 Calendar，默认当前日期
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		int weekday = calendar.get(Calendar.DAY_OF_WEEK) - 1; // 英语国家的星期从星期天开始，即数字1表示星期天
		System.out.println(year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second + " " + weekday);

		// Date 转化 Calendar
		calendar.clear(); // 清除此前变量定义过的日期信息
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		calendar.setTime(simpleDateFormat.parse("2018-11-11"));
		System.out.println(calendar.get(Calendar.MONTH) + 1);
		
		// 自定义日期 Calendar
		calendar.clear(); // 清除此前变量定义过的日期信息
		calendar.set(Calendar.YEAR, 2018);
		calendar.set(Calendar.MONTH, 7); // 这里输入 0 表示 1月
		calendar.set(Calendar.DATE, 21);
		System.out.println(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH)+1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
	
		// Calendar 转化为 Date
		Date date = calendar.getTime();
		System.out.println(simpleDateFormat.format(date)); // 2018-08-21
		System.out.println(date.getTime()); // 时间戳
		System.out.println(calendar.getTimeInMillis()); // 时间戳
		
		// 获取年份，月份的天数信息
		System.out.println("2018的天数:" + calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
		System.out.println("2018-8月的天数:" + calendar.getActualMaximum(Calendar.DATE));
		
		
		// add 方法为某日期增加时间段
		System.out.println("2018-8-21 增加十天后");
		calendar.add(Calendar.DAY_OF_MONTH, 20);
		System.out.println(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH)+1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
	
		
	}

}
