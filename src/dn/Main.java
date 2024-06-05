/** 
 * @author Ruan Souza
 * 
 * @version 1.3
 * 
 * Inserção do cálculo de Z e aguardando a integração  com á tabela Z.
 * 
 */
package dn;

import java.util.Scanner;
import java.math.*;
import java.text.DecimalFormat;
import dn.zTable;

public class Main {

	static Scanner teclado = new Scanner(System.in);
	static double xifi = 0, somaS2 = 0, media = 0, s2 = 0, s = 0, cv = 0, n = 0; //variáveis para os cálculos estatísticos
	static DecimalFormat formatador = new DecimalFormat("0.00");//formatação de valores


	public static void quebraDeLinha() {
		System.out.println("");
		System.out.println("-------------------------------------");
		System.out.println("");
	}
	
	/**
	 * @author Arthur Renan
	 * @param num - número double que vai ser arredondado
	 * @return retorna o número arredondado
	 */
	public static double arredondamento(double num) {
		Double numArredondado = BigDecimal.valueOf(num)
			    .setScale(2, RoundingMode.HALF_UP)
			    .doubleValue();
		return numArredondado;
	}
	
	/** Método para calcular o PMI de uma classe, utilizando os dois limites, inferior e superior
	 * @author Ruan Souza
	 * @param li - é o parâmetro para o limite inferior 
	 * 		
	 * @param ls - é o parâmetro para o limite superior
	 * 		
	 * @return retorna o PMI
	 */
	public static double calculoPMI(int li, int ls) {
		double pmi = arredondamento((0.5 * (li + ls)));
		return pmi;
	}
	
	/** Método para fazer os cálculos estatísticos necessários
	 * É calculado o N, a Média, a variância, o desvio padrão e o CV
	 * Depois de fazer todos os cálculos, é feita uma impressão dos resultados
	 * 
	 * Este método é usado tanto para (xi) quanto para classes
	 * 
	 * @author Arthur Renan
	 * @param matriz - uma matriz double que contém os valores de dados e FI
	 */
	
	public static void calculoEstatistico(double[][] matriz) {
		int i;
		
		for (i = 0; i < matriz.length; i++) {
			n += matriz[i][1]; // calculando o valor de N
			xifi += matriz[i][0] * matriz[i][1]; // calculando o valor de xi*fi
		}
		media = arredondamento((xifi / n)); //cálculo da média

		for (i = 0; i < matriz.length; i++) {
			somaS2 += arredondamento((Math.pow((matriz[i][0] - media), 2) * matriz[i][1])); // cálculo da parte E(xi - media)^2 * fi
		}
		
		s2 = arredondamento((somaS2 / (n-1))); //cálculo da variância
		s = arredondamento((Math.sqrt(s2))); //cálculo do desvio padrão
		cv = arredondamento(((100 * s) / media)); //cálculo do coeficiente de variação
		
		System.out.println("N = " + n +";\nMédia = " + media + ";\nVariância(s2) = " +s2 +
				";\nDesvio padrão = " + s + ";\nCoeficiente de Variação (CV) = " + cv + "%"); //impressão de todos os resultados	
	}
	
	/**
	 * @author Ruan Souza
	 * @param xi - o valor informado pelo usuário
	 * @param media - media calculada
	 * @param n - a soma dos FIs
	 * @param s - desvio padrão
	 * @return retorna o número z para ser procurado na zTable
	 */
	public static double calculoZ(double xi, double media, double n, double s) {
		double Z = arredondamento(((xi - media) * Math.sqrt(n) / s));
		return Z;
	}

	/** Método para imprimir qualquer matriz do programa
	 * @author Arthur Renan
	 * @param matriz - é uma matriz double que contém os valores dos dados e FI
	 * @param qual - é um valor inteiro para decidir qual é o tipo da matriz
	 */
	
	public static void imprimeMatriz(double[][] matriz, int qual) {
		
		/*
		 * qual = 1 -> matriz de dados (xi)
		 * qual = 2 -> matriz de classes
		 */
		
		System.out.println("\n Os dados apresentados estão corretos? ");
		switch (qual) { //switch para verificar o tipo da matriz
		case 1:
			System.out.println("---DADOS(xi)---"); //cabeçalho de impressão da matriz por dados(xi)
			System.out.println("XI\tFI"); 
			break;
		case 2:
			System.out.println("---CLASSES---"); //cabeçalho de impressão da matriz por dados(xi)
			System.out.println("li---ls\tPMI\tFI");
			break;
		}
		for (int i = 0; i < matriz.length; i++) {
			if (qual == 1) {
				System.out.println("[" + matriz[i][0] + "]\t[" + matriz[i][1] + "]"); //se for matriz por dados (xi), imprime os valores de xi e fi
			}
			if (qual == 2) {
				//se for matriz por classes, imprime os dois limites de cada classe, o PMI e o FI
				System.out.println("[" + matriz[i][2] + "]---[" + matriz[i][3] + "]\t[" + matriz[i][0]+ "]\t[" + matriz[i][1] + "]");
			}
		}
	}

	// inserção de dados do usuário: dados e frequências individuais ou pelo menos
	// uma classe (e calcula-se então a amplitude ((h) = (b - a)/2, sendo a = limite
	// inferior e b = limite superior)), as outras classes e os PMIs);
	public static void main(String[] args) {


		quebraDeLinha();
		System.out.println(
				"Bem-vindo ao programa JCS. Este programa serve unica e exclusivamente para calcular dados estatísticos \n"
				+ "do tipo 'Distribuição Normal'. Para mais versões com outras funcionalidades, consulte a \n"
				+ "Faculdade de Tecnologia de Jundiaí - Deputado Ary Fossen | Centro Paula Souza.");

		quebraDeLinha();

		System.out.println("Para este cálculo, deseja inserir dados ou classes?");
		System.out.println("\n Digite [1] para inserir dados.");
		System.out.println("\n Digite [2] para inserir classes.");

		quebraDeLinha();
		System.out.print("Opção: ");
		int resposta = teclado.nextInt();
		char resp;

		// inserção de dados;

		switch (resposta) {
		case 1:

			do {

				System.out.print("\n Quantos dados deseja inserir: ");
				int quantidadeDeDados = teclado.nextInt();
				
				// inserção dos dados e suas respectivas frequências;
				
				double[][] dadosAmostrais = new double[quantidadeDeDados][2];
				for (int i = 0; i < dadosAmostrais.length; i++) {
					System.out.print("\nDigite o valor do " + (i + 1) + "° dado: ");
					double dado = teclado.nextDouble();
					dadosAmostrais[i][0] = dado;
					System.out.print("\nDigite a frequência do " + (i + 1) + "° dado: ");
					int freq = teclado.nextInt();
					dadosAmostrais[i][1] = freq;
					quebraDeLinha();
				}
				
				// mostrando os dados inseridos
				
				imprimeMatriz(dadosAmostrais, 1);
				calculoEstatistico(dadosAmostrais); //chama o método para fazer os cálculos

				System.out.print("\n Responda 1 para corrigir ou qualquer tecla para prosseguir: ");
				resp = teclado.next().charAt(0);

			} while (resp == '1');
			break;

		case 2:

			quebraDeLinha();
				do {
					System.out.print("\nQuantas classes deseja inserir?:  ");
					int quantidadeDeClasses = teclado.nextInt();
					double[][] classes = new double[quantidadeDeClasses][4];
					for (int i = 0; i < classes.length; i++) {
						System.out.print("\nDigite o limite inferior da " + (i + 1) + "° classe: ");
						int limI = teclado.nextInt();
						classes[i][2] = limI;
						System.out.print("\nDigite o limite superior da " + (i + 1) + "° classe: ");
						int limS = teclado.nextInt();
						classes[i][3] = limS;
						classes[i][0] = calculoPMI(limI, limS);
						System.out.print("\nDigite a frequência da " + (i + 1) + "° classe: ");
						int freq = teclado.nextInt();
						classes[i][1] = freq;
						quebraDeLinha();
					}
					
					// mostrando os dados inseridos
					
					imprimeMatriz(classes, 2);
					calculoEstatistico(classes); //chama o método para fazer os cálculos
					System.out.print("\nResponda 1 para corrigir ou qualquer tecla para prosseguir: ");
					resp = teclado.next().charAt(0);
				} while (resp == '1');

			break;

		default:
			System.out.println("Valor não encontrado no menu.");
		}
		
		/** 
		 * Área direcionada para o cálculo do valor de Z, antes do mesmo ser puxado da tabela Z.
		 * 
		 */
		
		quebraDeLinha();

		System.out.println("Cálculo da distribuição Normal: ");
		
		quebraDeLinha();
		
		System.out.print("Digite o valor de A: ");
		double a = teclado.nextDouble();
		double zScore = 0;
		double zA = calculoZ(a, media, n , s);
		zScore = zTable.valorZ(zA);
		System.out.print("\nValor de Za: " + zA);
		System.out.print("\nZa na tabela Z é = " + zScore);
		System.out.println("\nDigite [1] para inserir o valor de B  se desejar um intervalo (A até B), ou qualquer número para continuar: ");
		int x = teclado.nextInt();
		
		if(x == 1) {
			System.out.print("\nDigite o valor de B: ");
			double b = teclado.nextDouble();
			
			double zB = calculoZ(b, media, n , s);
			
			System.out.print("\nValor de Zb: " + zB);
			zScore = zTable.valorZ(zB);
			System.out.print("\nZb na tabela Z é = " + zScore);
		}
		teclado.close();
	}

}

// cálculo da média (x̅) = (∑xifi)/ N, sendo xi = dados ou pontos médios e fi =
// frequências individuais e N = espaço amostral ou número total de termos;

// cálculo da variância (s²) = (∑(xifi - x̅)²)/ N, sendo xi = dados ou pontos
// médios e fi = frequências individuais e N = espaço amostral ou número total
// de termos;

// cálculo do desvio padrão (s) = √s²

// cálculo do coeficiente de variação = (100*s)/x̅;

// Criar função e resolver a repetição do programa

// Cálculo de Z -> Z = ((xi - media) * raiz(n)) / s.
