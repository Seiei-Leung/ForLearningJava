package top.Seiei.forThread;

/*
 *	synchronized 共享资源同步
 */

class Resource {
	static int num = 0;
	synchronized static void minus() {
		int n = 0;
		while (n < 10000) {
//			synchronized (Resource.class) {
//				num -= 1;
//			}
			num -= 1;
			n += 1;
		}
	}
	synchronized static void add() {
		int n = 0;
		while (n < 10000) {
//			synchronized (Resource.class) {
//				num += 1;
//			}
			num += 1;
			n += 1;
		}
	}
}

class AddThread extends Thread {

	@Override
	public void run() {
		Resource.add();
	}
	
}

class MinusThread extends Thread {

	@Override
	public void run() {
		Resource.minus();
	}
	
}

public class AboutSynchronizedDemo {
	public static void main(String[] args) throws InterruptedException {
		AddThread addThread = new AddThread();
		MinusThread minusThread = new MinusThread();
		addThread.start();
		minusThread.start();
		addThread.join(); // 等待结束
		minusThread.join(); // 等待结束
		System.out.println(Resource.num);
	}
}
