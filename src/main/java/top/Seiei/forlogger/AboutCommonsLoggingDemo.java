package top.Seiei.forlogger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/*
 *	commons Logging
 *	如果有 Log4j ，则优先使用 Log4j
 *	根据 classpath 寻找 log4j2.xml
 *
 */
public class AboutCommonsLoggingDemo {

	static Log log = LogFactory.getLog(AboutCommonsLoggingDemo.class); 
	// 相当于获取了 AboutCommonsLoggingDemo 类的 logger 实例，输出日志中的类名就是这个类

	public static void main(String[] args) {
		AboutCommonsLogging.log.info("Start");
		try {
			Integer.parseInt(null);
		} catch (Exception e) {
			log.error("报错", e);
		}
	}
}

class AboutCommonsLogging {
	static Log log = LogFactory.getLog(AboutCommonsLogging.class);
}
