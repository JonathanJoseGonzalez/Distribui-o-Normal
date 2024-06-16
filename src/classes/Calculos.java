package classes;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculos {
	/**
	 * @author Arthur Renan
	 * @param num - número double que vai ser arredondado
	 * @return retorna o número arredondado
	 */
	public static double arredondamento(double num, int decimal) {
		Double numArredondado = BigDecimal.valueOf(num).setScale(decimal, RoundingMode.HALF_UP).doubleValue();
		return numArredondado;
	}
	public static Double arredondamento(Double num, int decimal) {
		Double numArredondado = BigDecimal.valueOf(num).setScale(decimal, RoundingMode.HALF_UP).doubleValue();
		return numArredondado;
	}
	
	/**
	 * @author Ruan Souza
	 * @param xi - o valor informado pelo usuário
	 * @param media - media calculada
	 * @param n - a soma dos FIs
	 * @param s - desvio padrão
	 * @return retorna o número z para ser procurado na zTable
	 */
	public static double calculoZ(double xi, double media, int n, double s) {
		double Z = ((xi - media) * Math.sqrt(n) / s);
		return Z;
	}
	public static Double calculoZ(Double xi, Double media, int n, Double s) {
		double Z = ((xi - media) * Math.sqrt(n) / s);
		return Z;
	}
	
}
