package lamda;

import java.util.function.Function;

class AnonymousFunction{
	
	@FunctionalInterface
	interface FizzBuzz{
		String fizzbuzz(Integer x);
	}
	
    public static void main(String[] args){
    	//引数(Integer)を受け取って、Doubleを返却する
        Function<Integer, Double> circleArea = x -> x*x*3.14;
        Integer radius = 1;
        System.out.println(circleArea.apply(radius));
        radius=5;
        System.out.println(circleArea.apply(radius));
        
//        FizzBuzz fz = new FizzBuzz() {
//        	@Override
//        	public String fizzbuzz(Integer x) {
//        		String str = "";
//        		for(int i=1;i<=x;i++) {
//        			if(i%15==0) str+="FizzBuzz";
//        			else if(i%3==0) str+="Fizz";
//        			else if(i%5==0) str+="Buzz";
//        			else str+=i;
//        			
//        			if(i!=x) str+="-";
//        		}
//        		return str;
//        	}
//        };
//        System.out.println(fz.fizzbuzz(9));
//        System.out.println(fz.fizzbuzz(20));
//        
        //FizzBuzz
        Function<Integer, String> fizzbuzz = x->{
        	String str = "";
    		for(int i=1;i<=x;i++) {
    			if(i%15==0) str+="FizzBuzz";
    			else if(i%3==0) str+="Fizz";
    			else if(i%5==0) str+="Buzz";
    			else str+=i;
    			
    			if(i!=x) str+="-";
    		}
    		return str;
        };
        
        System.out.println(fizzbuzz.apply(9));
        System.out.println(fizzbuzz.apply(20));
        
        //文字列0の複製
        Function<Integer,String> duplicateZero = x->{
        	String str = "";
        	for(int i=0;i<x;i++) {
        		str+="0";
        	}
        	return str;
        };
        System.out.println(duplicateZero.apply(5));
        System.out.println(duplicateZero.apply(10));
    }
        	
}
