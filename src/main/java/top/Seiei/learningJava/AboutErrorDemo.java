package top.Seiei.learningJava;

/*
 *	异常的转换
 *	当 catch 语句抛出异常的时候，finally 语句会比 catch 语句先执行
 *	当 catch 语句和 finally 语句同时都抛出异常的时候，由于异常只能抛出一个，所以只有 finally 语句抛出的异常会被抛出，而 catch 语句中的异常会被屏蔽掉
 */
public class AboutErrorDemo {
	public static void main(String[] args) {
		try {
			Integer.parseInt(null);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(e); // 传入原始异常
//			System.out.println("catch");
		} finally {
			System.out.println("finally");
		}
	}
}
