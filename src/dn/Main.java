/**
 * 
 */
package dn;

import java.util.Scanner;

/**
 * @author Arthur
 * @version 1.1
 *
 */
public class Main {
	public static void quebraDeLinha() {
		System.out.println("");
		System.out.println("-------------------------------------");
		System.out.println("");
	}
	
	public static void imprimeMatriz(float [][] matriz, int qual) {
		/*o int qual decide se vai imprimir matriz de dados ou de classes
		 * qual = 1 -> dados (xi)
		 * qual = 2 -> classes
		 * */
		System.out.println("\n Os dados apresentados estão corretos? ");
		switch(qual) {
			case 1:
				System.out.println("XI\tFI");
				break;
			case 2:
	            System.out.println("------CLASSES------");
	            break;
		}
        for (int i = 0; i < matriz.length; i++) {
        		if(qual == 1) {
        			
                    System.out.println("[" + matriz[i][0] + "]\t[" + matriz[i][1] + "]");
        		}	
        		
        		if(qual == 2) {
                    System.out.println("[" + matriz[i][0] + "]---[" + matriz[i][1] + "]");		            
        		}
        }
	}
	
	// inserção de dados do usuário: dados e frequências individuais ou pelo menos
	// uma classe (e calcula-se então a amplitude ((h) = (b - a)/2, sendo a = limite
	// inferior e b = limite superior)), as outras classes e os PMIs);
	public static void main(String[] args){

	    Scanner teclado = new Scanner(System.in);
	
	    quebraDeLinha();
	    System.out.println("Bem-vindo ao programa JCS. Este programa serve unica e exclusivamente para calcular dados estatísticos do tipo 'Distribuição Normal Padronizada'. Para mais versões com outras funcionalidades, consulte a Faculdade de Tecnologia de Jundiaí - Deputado Ary Fossen | Centro Paula Souza.");
	
	    quebraDeLinha();
	
	    System.out.println("Para este cálculo, deseja inserir dados ou classes?");
	    System.out.println("\n Digite [1] para inserir dados.");
	    System.out.println("\n Digite [2] para inserir classes.");
	
	    quebraDeLinha();
	    System.out.print("Opção: ");
	    int resposta = teclado.nextInt();
	    char resp;

	    // inserção de dados;
	
	    switch(resposta)
	    {
	        case 1: 
	
		        do {
		        	
		            System.out.print("\n Quantos dados deseja inserir: ");
		            int quantidadeDeDados = teclado.nextInt();
		            // inserção dos dados e suas respectivas frequências;
		            float [][] dadosAmostrais = new float[quantidadeDeDados][2];
		            for (int i = 0; i < dadosAmostrais.length; i++) {
		                   System.out.print("\nDigite o valor do " + (i+1) +"° dado: ");
		                   float dado  = teclado.nextFloat();
		                   dadosAmostrais[i][0] = dado;
		                   System.out.print("\nDigite a frequência do "+ (i+1) +"° dado: ");
		                   int freq = teclado.nextInt();
		                   dadosAmostrais[i][1] = freq;
		                   
		             }
		            // mostrando os dados inseridos
		            imprimeMatriz(dadosAmostrais, 1);
		            
		            /*criar função para repetição do programa*/
		            System.out.print("\n Responda 1 para corrigir ou qualquer tecla para prosseguir: ");
		            resp = teclado.next().charAt(0); 
		            
		        } while(resp == '1');
		    break;
	
	        case 2:
	    
		        System.out.println("\n Para inserir classes, digite [1];");
		        System.out.println("\n Para inserir PMIs (pontos médios) digite [2];");
		        quebraDeLinha();
			    System.out.print("Opção: ");
		        int dadosOuClasses = teclado.nextInt();
		        switch(dadosOuClasses) {
		        	case 1:
		        		do {
		        			System.out.print("\nQuantas classes deseja inserir?:  ");
				            int quantidadeDeClasses = teclado.nextInt();
				            float [][] classes = new float[quantidadeDeClasses][2];
				            for (int i = 0; i < classes.length; i++) {
				                 System.out.print("\nDigite o limite inferior da "+ (i+1) +"° classe: ");
				                 float limI = teclado.nextFloat();
				                 classes[i][0] = limI;
				                 System.out.print("\nDigite o limite superior da "+ (i+1) +"° classe: ");
				                 float limS = teclado.nextFloat();
				                 classes[i][1] = limS;
				            }
				            // mostrando os dados inseridos
				            imprimeMatriz(classes, 2);
				       
				            System.out.print("\nResponda 1 para corrigir ou qualquer tecla para prosseguir: ");
				            resp = teclado.next().charAt(0);   
		        		}while (resp == '1' );
	
		        	break;
		        	
		        	case 2:
		        	
		        	break;
		        	
		        	default:
			            System.out.println("Valor não encontrado no menu.");
		        
		        }
		        
		        // calcular amplitude e ponto médio da classe
		     
		        
	        break;
	
	        default:
	            System.out.println("Valor não encontrado no menu.");
	         
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


