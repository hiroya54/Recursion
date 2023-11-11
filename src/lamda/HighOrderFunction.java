package lamda;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
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
	//オーバーロード
	public static int summation(Predicate<Integer> predicate,int n) {
		int sum = 0;
		for(int i=1;i<=n;i++) {
			if(predicate.test(i)) sum+=i;
		}
		return sum;
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
		
		//バリデーション(emailの構文チェック)	
		BiFunction<Function<String, Boolean>, String, String> emailValidation = (f, str) -> {
			if(f.apply(str)) return "Email is correct.";
			else return "Email is not correct.";
		};
		
		Function<String, Boolean> doesNotStartWithAt = email ->{
			if(email.charAt(0)!='@') return true;
			else return false;
		};
		System.out.println(emailValidation.apply(doesNotStartWithAt,"@gmail.com"));
		System.out.println(emailValidation.apply(doesNotStartWithAt,"kkk@gmail.com"));
		
		Function<String, Boolean> doesNotHaveSpace = email -> {
			String deleteSpaceString = email.replaceAll(" ", "");
			if(email.length()==deleteSpaceString.length()) return true;
			else return false;
		};
		System.out.println(emailValidation.apply(doesNotHaveSpace,"Hello world"));
		System.out.println(emailValidation.apply(doesNotHaveSpace,"Helloworld"));
		
		Function<String, Boolean> hasUppercaseAndLowercase = email -> {
			if(email.toUpperCase().equals(email) || email.toLowerCase().equals(email)) return false;
			else return true;
		};
		System.out.println(emailValidation.apply(hasUppercaseAndLowercase,"hello world"));
		System.out.println(emailValidation.apply(hasUppercaseAndLowercase,"HELLO WORLD"));
		System.out.println(emailValidation.apply(hasUppercaseAndLowercase,"Hello world"));
		
		//動物と人間の年齢
		BiFunction<Function<Integer, Integer>, Integer, Boolean> qualifiedForInsurance = (f,age) ->{
			if(f.apply(age)<=60) return true;
			return false;
		};
		
		Function<Integer, Integer> dogToHuman  = age -> {
			return 20 +(age-2)*7;
		};
		System.out.println(qualifiedForInsurance.apply(dogToHuman, 7));
		System.out.println(qualifiedForInsurance.apply(dogToHuman, 8));
		
		Function<Integer, Integer> catToHuman = age -> {
			return 24 + (age-2)*4;
		};
		System.out.println(qualifiedForInsurance.apply(catToHuman, 11));
		System.out.println(qualifiedForInsurance.apply(catToHuman, 12));
		
		//ラムダ総和
		BiFunction<Function<Integer, Boolean>, Integer, Integer> summation = (f,n) -> {
			int sum = 0;
			for(int i=1;i<=n;i++) {
				if(f.apply(i)) sum+=i;
			}
			return sum;
		};
		
		Function<Integer, Boolean> isOdd = n ->{
			if(n%2!=0) return true;
			else return false;
		};	
		System.out.println(summation.apply(isOdd, 3));
		System.out.println(summation.apply(isOdd, 10));
		System.out.println(summation.apply(isOdd, 25));
		
		Function<Integer, Boolean> isMultipleOf3Or5 = n ->{
			if(n%3==0 || n%5==0) return true;
			else return false;
		};
		System.out.println(summation.apply(isMultipleOf3Or5, 3));
		System.out.println(summation.apply(isMultipleOf3Or5, 10));
		System.out.println(summation.apply(isMultipleOf3Or5, 100));
		
		Function<Integer, Boolean> isPrime = n ->{
			if(n==2) return true;
			else if(n<2 || n%2==0) return false;
			else {
				for(int i=3;i<=Math.sqrt(n);i+=2) {
					if(n%i==0) return false;
				}
				return true;
			}
		};
		System.out.println(summation.apply(isPrime, 2));
		System.out.println(summation.apply(isPrime, 10));
		System.out.println(summation.apply(isPrime, 100));
		
		//ラムダ総和(Predicateを使用)
		Predicate<Integer> odd = n -> n%2!=0;
		Predicate<Integer> multipleOf3Or5 = n -> n%3==0 || n%5==0;
		Predicate<Integer> prime = n->{
			for(int i=2;i<=Math.sqrt(n);i++) {
				if(n%i==0) return false;
			}
			return n>1;
		};
		System.out.println(summation(odd, 3));
		System.out.println(summation(odd, 10));
		System.out.println(summation(odd, 25));
		System.out.println(summation(multipleOf3Or5, 3));
		System.out.println(summation(multipleOf3Or5, 10));
		System.out.println(summation(multipleOf3Or5, 100));
		System.out.println(summation(prime, 2));
		System.out.println(summation(prime, 10));
		System.out.println(summation(prime, 100));
		
		//惑星での体重
		Function<String,  Consumer<Integer>> weightFormulaByPlanet = planet-> weight->{
			double g = 0.0;
			if(planet.equals("Earth")) g=9.8;
			else if(planet.equals("Mars")) g=3.7;
			else if(planet.equals("Jupiter")) g=24.8;
			System.out.println("The weight on "+planet+ " is " + (int)(g*weight));
		};
		Consumer<Integer> getWeightOnEarth = weightFormulaByPlanet.apply("Earth");
		Consumer<Integer> getWeightOnMars = weightFormulaByPlanet.apply("Mars");
		Consumer<Integer> getWeightOnJupiter = weightFormulaByPlanet.apply("Jupiter");
		getWeightOnEarth.accept(50);
		getWeightOnMars.accept(70);
		getWeightOnJupiter.accept(90);
		
		//連邦税と州税
		Function<String, Consumer<Integer>> printTaxByState = state -> income -> {
			double stateTax = 0;
			if(state.equals("Arizona")) stateTax = 0.049;
			else if(state.equals("California")) stateTax = 0.088;
			else if(state.equals("Northcarolina")) stateTax = 0.025;
			System.out.println("Federal Tax: " + (int)(income*0.21));
			System.out.println(state +" State Tax: " + (int)(income*stateTax));
			System.out.println("Tax Amount: " + (int)(income*(0.21+stateTax)));
		};
		Consumer<Integer>  getTaxInAZ = printTaxByState.apply("Arizona");
		Consumer<Integer>  getTaxInCA = printTaxByState.apply("California");
		Consumer<Integer>  getTaxInNC = printTaxByState.apply("Northcarolina");
		getTaxInAZ.accept(400000);
		getTaxInCA.accept(100000);
		getTaxInNC.accept(500000);
		
	}

}
