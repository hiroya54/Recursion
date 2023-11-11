package lamda;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

class HighOrderFunction {
	
	public static int summation(Function<Integer, Integer> g, int a, int b) {
		if(b<a) return 0;
		return g.apply(b) + summation(g, a, b-1);
	}
	
	//オーバーロード
	public static int summation(Supplier<Integer> g, int a, int b) {
		if(b<a) return 0;
		return g.get()+summation(g, a, b-1);
	}
	
	public static int pPi(Function<Integer, Integer> g, int a, int b) {
		if(b<a) return 1;
		return g.apply(b)*pPi(g, a, b-1);
	}
	
	//オーバーロード
	public static int pPi(Supplier<Integer> g, int a,int b) {
		if(b<a) return 1;
		return g.get()*pPi(g, a, b-1);
	}
	
	public static void main(String[] args) {
		
		BiFunction<Function<Integer, ?>, Integer, ?> fSquaredX = (f, x) -> f.apply(x*x);
		//f(x) = x + 30; fSquaredX = f(x^2) = x^2 + 30;
		System.out.println(fSquaredX.apply(x -> x+30, 5));
		
		Function<Integer, String> callabe = p -> "p is " + p;
		
		System.out.println(fSquaredX.apply(callabe, 10));
		
		//10までの総和
		Function<Integer, Integer> identity = i -> i;
		System.out.println(summation(identity, 1, 10));
		
		//10*100の計算
		Supplier<Integer> function = () -> 10;
		System.out.println(summation(function, 1, 100));
		
		//10の階乗
		System.out.println(pPi(identity, 1, 10));
		
		//5^10の計算
		Supplier<Integer> function5 = () -> 5;
		System.out.println(pPi(function5, 1, 10));
	}

}
