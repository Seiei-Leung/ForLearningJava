package top.Seiei.forThread;

/*
 *	关于 Thread.join
 */


class ThreadTestForJoin extends Thread {

	String name;
	public ThreadTestForJoin(String name) {
		this.name = name;
	}
	@Override
	public void run() {
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("我是线程：" + this.name);
	}
	
}

class ThreadTestForInterrupt extends Thread{

	
	volatile boolean isrun = true;
	String name;
	public ThreadTestForInterrupt(String name) {
		this.name = name;
	}
	@Override
	public void run() {
		while (this.isrun && !isInterrupted()) {
			try {
				Thread.sleep(1000);
				System.out.println("我是线程：" + this.name);
			} catch (InterruptedException e) {
				System.out.println("中断");
				// 要添加 终止 break
				break;
			}
		}
		System.out.println("线程" + this.name + "结束");
	}
}

public class AboutThreadTestDemo {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("劳资是主线程");
		Thread thread1 = new ThreadTestForJoin("join 线程");
		thread1.start();
		thread1.join(); // 主线程等待该线程结束后再执行，同时可以传入一个时间，表示该时间过后，线程如果还没有执行完，就不再等待
		System.out.println("回到主线程");
		System.out.println("======================");
		ThreadTestForInterrupt thread2 = new ThreadTestForInterrupt("中断线程");
		thread2.start();
		Thread.sleep(10000);
//		thread2.interrupt(); // 使用 interrput 方法
		thread2.isrun = false; // 由于标志位是子类的字段，所以声明变量的时候要使用子类类型
		System.out.println("回到主线程");
	}
}
