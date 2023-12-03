package lamda;

import java.util.function.Supplier;

public class Closure {
	
	public static void main(String[] args) {
		Supplier<Supplier<Integer>> counterFn=()->{
			final int count = 0;
			
			int[] countArr = new int[] {0};
			
			//inner関数
			Supplier<Integer> increase = ()->{
				System.out.println("count: " + count);
				countArr[0]++;
				return countArr[0];
			};
			
			return increase;
		};
		Supplier<Integer> counter = counterFn.get();
		System.out.println(counter.get());
		System.out.println(counter.get());
		System.out.println(counter.get());
		System.out.println(counter.get());
	}

}
