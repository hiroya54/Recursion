package lamda;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Reduce {
	
	public static int myReduce(BiFunction<Integer, Integer, Integer> reduceCallback,int[] list,int initial) {
		int lastResult = initial;
		for(int i=0;i<list.length;i++) {
			int result = reduceCallback.apply(list[i],lastResult);
			lastResult = result;
		}
		return lastResult;
	}
	
	public static int calculateFinalMoney(int[] interests, int capital){
		//アキュムレーター関数の第一引数がアキュムレーターの累積値であり、第二引数がストリームからの次の要素です。
		return (int)Arrays.stream(interests).asDoubleStream()
        .reduce(capital, (amount,rate)->(1+rate/100)*amount);
    }
	
	public static int calcProfitsOrLosses(String[] stockList){
        //["取得したときの株価-所有株数-現在の株価"]
		//各要素に対してしたい計算は、(現在の株価-取得した時の株価)*所有株数
		//自作のラムダ式(convert)で配列要素を記録の文字列から損益に変換
		Function<String,Integer> convert = str ->{
			int[] list =Arrays.stream(str.split("-"))
					.mapToInt(Integer::parseInt).toArray();
			return (list[2]-list[0])*list[1];
		};
		
		return Arrays.stream(stockList).map(convert).reduce(0, Integer::sum);
		
    }
	
	
	public static void main(String[] args) {
		
		//リスト反復処理
		int[] list1 = {1,2,3};
		int[] list2 = {1,2,3,4,5,6,7,8,9,10};
		
		System.out.println(myReduce((x,total) -> x*total, list1, 1));
		System.out.println(myReduce((x,total) -> x*total, list2, 1));
		
		System.out.println(Arrays.stream(list2).reduce(1, (x,total)->x*total));
		
		//連想配列
		Order apple = new Order("Apple", 100, 10);
		Order orange = new Order("Orange", 120, 8);
		Order banana = new Order("Banana", 80,14);
		
		Order[] shoppingList = new Order[] {apple,orange,banana};
		
		int totalcost = Arrays.stream(shoppingList)
				.mapToInt(order -> order.price*order.quantity)
				.reduce(0, Integer::sum);
		System.out.println(totalcost);
		
		//平坦化
		int[][] array2d = {{2,3,4,5},{5,22,34,4,5},{12,13,45,67,84}};
		
		int[] flatten = Arrays.stream(array2d).flatMapToInt(Arrays::stream).toArray();
		
		System.out.println(Arrays.toString(flatten));
		System.out.println(flatten[1]);
	}

}

class Order{
	public String name;
	public int price;
	public int quantity;
	
	public Order(String name, int price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
}
