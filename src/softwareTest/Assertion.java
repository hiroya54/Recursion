package softwareTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.BiPredicate;

class Assertion {
	
	public static void run(boolean b) throws Exception{
		if(!b) throw new Exception("Assertion Error");
	}
	
	public static boolean equalAssertion(String[] arr1, String[] arr2,BiPredicate<String[], String[]> callback) throws Exception{
		boolean equality = callback.test(arr1, arr2);
		System.out.println("Comparing " + Arrays.toString(arr1) +" and " + Arrays.toString(arr2) + "..." + (equality ? "They are equal." : "Error, they are NOT equal."));
		Assertion.run(equality);
		return true;
	}
	public static boolean equalAssertion(String a, String b) throws Exception{
		boolean equality = a.equals(b);
		System.out.println("Comparing " + a +" and " + b + "..." + (equality ? "They are equal." : "Error, they are NOT equal."));
		Assertion.run(equality);
		return true;
		
	}
	
	public static void reverseArr(String[] arr) {
		for(int i=0;i<arr.length/2;i++) {
			String tmp = arr[i];
			arr[i]=arr[arr.length-1-i];
			arr[arr.length-1-i]=tmp; 
		}
	}
	
	public static String[] createSetList(String[] arr) {
		HashMap<String, String> map = new HashMap<>();
		for(String str:arr) {
			map.put(str, str);
		}
		
		ArrayList<String> list = new ArrayList<>();;
		for(String key: map.keySet()) {
			list.add(map.get(key));
		}
		String[] result = new String[list.size()];
		for(int i=0;i<list.size();i++) {
			result[i]=list.get(i);
		}
		return result;
		
	}
	
	public static void main(String[] args) throws Exception{
		
		String[] arr1 = new String[] {"a","b","c","d","e","f"};
		String[] copyArr1 = arr1.clone();
		
		reverseArr(copyArr1);
		
		BiPredicate<String[], String[]> reverseArrayEquality = (a,b) ->{
			if(a.length!=b.length) return false;
			for(int i=0;i<a.length;i++) {
				if(!a[i].equals(b[b.length-1-i])) return false;
			}
			return true;
		};
		
		//テストケース
		//pass
		equalAssertion(arr1, copyArr1, reverseArrayEquality);
		
		String[] arr2 = new String[] {"test1@test.com","test2@test.com","test3@test.com","test4@test.com","test1@test.com","test2@test.com"};
		String[] unipueArr2 = createSetList(arr2);
		
		BiPredicate<String[], String[]> unorderedArrayEquality = (a,b) ->{
			HashMap<String, Integer> hashA = new HashMap<>();
			HashMap<String, Integer> hashB = new HashMap<>();
			
			for(int i=0;i<a.length;i++) {
				hashA.put(a[i], hashA.getOrDefault(a[i],0)+1);
			}
			for(int i=0;i<b.length;i++) {
				hashB.put(b[i], hashB.getOrDefault(b[i], 0)+1);
			}
			for(String key:hashA.keySet()) {
				if(hashB.get(key)==null) return false;
			}
			return true;
			
		};
		//テストケース
		//pass
		equalAssertion(arr2,unipueArr2,unorderedArrayEquality);
		
 	}

}
