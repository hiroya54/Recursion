package lamda;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Async {
	
	public static void asyncFnProcessing() {
		//スレッドプールの作成　複数スレッドを予め待機させる
		ExecutorService  ex = Executors.newFixedThreadPool(3);
		
		Random random = new Random();
		
		for(int i=0;i<10;i++) {
			//submitメソッドでRunnableインターフェースを実装したタスクを非同期で実行する
			ex.submit(new PrintTaskStatus(i, random.nextInt(1000)));
		}
		
		ex.shutdown();
	}
	
	public static void main(String[] args) {
		asyncFnProcessing();
	}

}


class PrintTaskStatus implements Runnable{
	public int number;
	public int wait;
	
	public PrintTaskStatus(int number, int wait) {
		this.number=number;
		this.wait=wait;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(this.wait);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(this.number+". processing comlpeted : " + Thread.currentThread().getId());
	}
}