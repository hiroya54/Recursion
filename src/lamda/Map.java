package lamda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Map{
    
   public static void main(String[] args){
	   
	   int[] arr= {1,2,3};
	   IntStream stream = Arrays.stream(arr);
	   stream.forEach(x -> System.out.println(x));
	   stream.forEach(System.out::println);
	   
	   List<String> strList = Arrays.asList("a","b","c");
	   strList.stream().forEach(System.out::println);
	   
	   BiFunction<Function<Integer,Integer>,int[], ArrayList<Integer>> myMap = (f,list) ->{
		   ArrayList<Integer> arrayList = new ArrayList<>();
		   for(int i=0;i<list.length;i++) {
			   arrayList.add(f.apply(list[i]));
		   }  
		   return arrayList;
	   };
	   
	   List<Integer> numList = Arrays.asList(1,2,3,4,5,6,7);
	   numList.stream().map(x->x*x).forEach(System.out::println);
	   Function<Integer,Integer> sq = x ->x*x;
	   numList.stream().map(sq).forEach(System.out::println);
	   
   }
   
   public static char[] swapCase(char[] charList){
       Function<Character,Character> conversion = ch ->{
       	if(Character.isUpperCase(ch)) return Character.toLowerCase(ch);
       	else return Character.toUpperCase(ch);
       };
       
       List<Character> list = new String(charList).chars()
    		   							.mapToObj(c -> (char)c)
    		   							.map(conversion)
    		   							.collect(Collectors.toList());
       
       char[] res = new char[charList.length];
       for(int i=0;i<res.length;i++) {
       	res[i] = list.get(i);
       }
       
       return res;
   }
   
   public static double[] over100m(String[] l){
       Function<String, Double> transrateM = str ->{
    	   int n = str.length();
    	   if(str.contains("km")) return Double.parseDouble(str.substring(0,n-2))*1000;
    	   else if(str.contains("mm")) return Double.parseDouble(str.substring(0,n-2))/1000;
    	   else return Double.parseDouble(str.substring(0,n-1));
       };
//       return Arrays.stream(l).map(transrateM).filter(x -> x>=100).toArray(Double[]:: new);
       return Arrays.stream(l).map(transrateM).filter(x -> x>=100).mapToDouble(x -> x.doubleValue()).toArray();
       
   }
}