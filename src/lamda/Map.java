package lamda;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class Map{
    
   public static void main(String[] args){
	   
   }
   
   public static char[] swapCase(char[] charList){
       // 関数を完成させてください
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
}