package lamda;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class Decorator {

	public static void main(String[] args) {
		//デコレータ
		Function<Supplier<String>,Supplier<String>> simpleDecorator = f -> ()->{
			System.out.println("Running f......");
			return f.get();
		};
		
		Supplier<String> helloworld = () ->{
			return "Hello World";
		};
		
		Supplier<String> newFunc1 = simpleDecorator.apply(helloworld);
		Supplier<String> newFunc2 = simpleDecorator.apply(()->"Hello Jupter");
		
		System.out.println(newFunc1.get());
		System.out.println(newFunc2.get());
		
		//単項関数fを受け取り、新しい機能が追加された関数fを返す
		Function<Function<Integer,Integer>,Function<Integer, Integer>> timerDecorator = f -> arg->{
			int start = (int)System.currentTimeMillis();
			int result = f.apply(arg);
			int end = (int)System.currentTimeMillis();
			System.out.println("This function tool: " + (end-start) + "ms");
			return result;
		};
		System.out.println(timerDecorator.apply(x->x*2).apply(2424));
		
		System.out.println(timerDecorator.apply(x->{
			int finalResult = 1;
			for(int i=1;i<x;i++) {
				int result = i;
				for(int j=1;j<i;j++) {
					result+=j;
				}
				finalResult+=result;
			}
			return finalResult;
		}).apply(1000));
		
		
		//[コーディング問題]デコレータ
		Function<Integer[], Integer> sumOfArray = intArr ->{
			return Arrays.stream(intArr).reduce(0, Integer::sum);
		};
		
		Function<Function<Integer[], Integer>, Function<Integer[], String>> validateDecorator = f -> intArr ->{
			int sum = f.apply(intArr);
			int count = Arrays.stream(intArr).filter(x -> x<10).toArray().length;
			if(count==0) {
				return "Sum of array is "+sum;
			}else {
				return count + " error found";
			}
		};
		
		//sumOfArrayに対して、エラーメッセージの出力という新たな機能を追加する。
		Function<Integer[], String> result = validateDecorator.apply(sumOfArray);
		
		System.out.println(result.apply(new Integer[] {10,20,30,40}));
		System.out.println(result.apply(new Integer[] {9,10,20,30}));
		System.out.println(result.apply(new Integer[] {3,5,40,50}));
		
		
		//[コーディング問題]複数のデコレータ
		Function<char[], String> toString = charArr ->{
			String res = "";
			for(int i=0;i<charArr.length;i++) {
				res+=charArr[i];
			}
			return res;
		};
		
		Function<Function<char[],String> ,Function<char[],String> > capitalizeDecorator = f -> charArr->{
			charArr[0] = Character.toUpperCase(charArr[0]);
			return f.apply(charArr);
		};
		
		Function<Function<char[], String>, Function<char[], String>> lowercaseResultDecorator = f -> charArr ->{
			return f.apply(charArr).toLowerCase();
		};
		
		Function<char[], String> f1 = capitalizeDecorator.apply(toString);
		Function<char[], String> f2 = lowercaseResultDecorator.apply(toString);
		
		char[] arr = {'h','E','L','L','O'};
		System.out.println(f1.apply(arr));
		System.out.println(f2.apply(arr));
		
		BiFunction<String, String, String> concat = (str1,str2)->{
			return str1+str2;
		};
		
		Function<BiFunction<String, String, String>, BiFunction<String, String, String>> printInfoDecorator = f -> (str1,str2)->{
			return str1 + " + " + str2 + " = " +f.apply(str1, str2);
		};
		
		BiFunction<String, String, String> f3 = printInfoDecorator.apply(concat);
		System.out.println(f3.apply(f1.apply(arr), f2.apply(arr)));
 	}

}
