package lamda;

import java.util.Arrays;

public class Filter {

	public static void main(String[] args) {
		

	}
	
	public static int[] ageCheck(int[] ages) {
		int[] res = Arrays.stream(ages).filter(x->x>=18).toArray();
		return res;
	}

}
