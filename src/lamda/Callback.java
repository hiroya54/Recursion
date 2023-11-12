package lamda;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

class Callback {
	
	public static void main(String[] args) {
		//文字列の最大値
		BiFunction<BiPredicate<String,String>, String[],String> maxByCriteria = (callback,strArray) ->{
			int n = strArray.length;
			
			String res = strArray[0];
			for(int i=1;i<n;i++) {
				if(callback.test(strArray[i], res)) res = strArray[i];
			}
			return res;
		};
		BiPredicate<String, String> compareLength = (str1,str2) ->str1.length()>=str2.length();
		BiPredicate<String, String> compareAsciiTotal = (str1,str2) ->str1.chars().sum()>=str2.chars().sum();
		
		System.out.println(maxByCriteria.apply(compareLength, new String[]{"apple", "yumberry", "grape", "banana","mandarin"}));
		System.out.println(maxByCriteria.apply(compareLength, new String[]{"zoom", "choochoo", "beepbeep", "ahhhahhh"}));
		System.out.println(maxByCriteria.apply(compareAsciiTotal, new String[]{"apple", "yumberry", "grape", "banana","mandarin"}));
		System.out.println(maxByCriteria.apply(compareAsciiTotal, new String[]{"zoom", "choochoo", "beepbeep", "ahhhahhh"}));
		
		//カスタム配列
		BiConsumer<Function<Integer,Integer>, int[]> customArray = (callback,intArray)->{
			int n = intArray.length;
			int[] newArray = new int[n];
			for(int i=0;i<n;i++) {
				newArray[i] = callback.apply(intArray[i]);
				System.out.println(newArray[i]);
			}
		};
		Function<Integer,Integer> cube = x -> x*x*x;
		customArray.accept(cube, new int[] {3,11,24,31});
		Function<Integer, Integer> splitAndAdd = x ->{
			int res = 0;
			while(x>0) {
				res+=x%10;
				x/=10;
			}
			return res;
		};
		customArray.accept(splitAndAdd, new int[] {3,11,24,31});
		
		//過半数
		BiFunction<Predicate<Integer>, int[], Boolean> majority = (p,intArr) ->{
			int count=0;
			int len = intArr.length;
			for(int i=0;i<len;i++) {
				if(p.test(intArr[i])) count++;
			}
			return count>len/2;
		};
		
		Predicate<Integer> isOdd = n -> n%2!=0; 
		Predicate<Integer> isEven = n -> n%2==0; 
		System.out.println(majority.apply(isOdd, new int[] {1,2,3,4,5}));
		System.out.println(majority.apply(isOdd, new int[] {2,4,6,7,8}));
		System.out.println(majority.apply(isEven, new int[] {3,6,8,12,15}));
		System.out.println(majority.apply(isEven, new int[] {4,5,7,11,14}));
	}

}
