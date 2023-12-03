package lamda;

import java.util.function.Function;

public class Lamda {
	
	public static double usdTojpy(double USDJYP, double paymentUSD, double handlingCharge) {
		return USDJYP*paymentUSD+handlingCharge;
	}
	
	public static void main(String[] args) {
		System.out.println(usdTojpy(139.85, 200, 3000));
		System.out.println(usdTojpy(139.85, 32, 3000));
		
		//部分適用:複数の引数を取る時に、一部引数をデフォルト値とすることでより少ない引数をとる新しい関数を作成すること
		Function<Double, Double> usdTojpyVisaNov17th = paymentUSD -> usdTojpy(139.85, paymentUSD, 3000);
		System.out.println(usdTojpyVisaNov17th.apply(200d));
		System.out.println(usdTojpyVisaNov17th.apply(32d));
		
		//カリー化:複数の引数を取る関数を、それぞれ単一の引数をとる一連の関数に変換る手法
		Function<Double, Function<Double, Function<Double, Double>>> usdTojpyCarry 
			= handlingCharge->USDJPY->paymentUSD->USDJPY*paymentUSD+handlingCharge;
		Function<Double, Double> usdTojpyVisaNov17thCarry = usdTojpyCarry.apply(3000d).apply(139.85);
		System.out.println(usdTojpyVisaNov17thCarry.apply(200d));
		System.out.println(usdTojpyVisaNov17thCarry.apply(32d));
	}

}
