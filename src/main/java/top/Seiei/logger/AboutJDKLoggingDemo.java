package top.Seiei.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
 *	JDK的Logging
 *	日志的输出信息还会带有 日期时间、类名和方法名称
 */
public class AboutJDKLoggingDemo {

	public static void main(String[] args) {

		Logger logger = Logger.getGlobal(); // 创建 logger 实例
		// logger.setLevel(Level.WARNING); 设置之后只有级别在 Warning 之上的日志才会输出
		logger.info("开始啰");
		System.out.println("我是syso");
		try {
			Integer.parseInt(null);
		} catch (Exception e) {
			logger.log(Level.WARNING, "报错啰", e); // 使用这个重载方法，可以把异常信息也输出
			logger.warning("单参数报错啰");
		}
	}

}
