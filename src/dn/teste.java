package dn;


import java.util.Scanner;
import java.math.*;
import java.text.DecimalFormat;

/**
 * @author Arthur
 * @version 1.2
 *
 */
public class teste {

    public static void quebraDeLinha() {
        System.out.println("");
        System.out.println("-------------------------------------");
        System.out.println("");
    }

    /** Método para calcular o PMI de uma classe, utilizando os dois limites, inferior e superior
     * 
     * @param li - é o parâmetro para o limite inferior 
     *         
     * @param ls - é o parâmetro para o limite superior
     *         
     * @return
     *         retorna o PMI
     */
    public static double calculoPMI(int li, int ls) {
        double pmi = 0.5 * (li + ls);
        return pmi;
    }

    /**
     * Método para formatar números com uma quantidade específica de casas decimais
     * 
     * @param numero - o número a ser formatado
     * @param casasDecimais - a quantidade de casas decimais desejada
     * @return o número formatado como string
     */
    public static String formatarNumero(double numero, int casasDecimais) { //cria uma StringBuilder (para manipular strings de modo dinâmico) para construir o formato de número necessário
        StringBuilder formato = new StringBuilder("0.");
        for (int i = 0; i < casasDecimais; i++) { //adiciona o número de zeros ao formato com base no número de casas decimais
            formato.append("0");
        }
        DecimalFormat df = new DecimalFormat(formato.toString()); // cria um DecimalFormat com o formato especificado acima
        return df.format(numero); // formata o número usando o DecimalFormat e retorna como String
    }
    
    /** Método para fazer os cálculos estatísticos necessários
     * É calculado o N, a Média, a variância, o desvio padrão e o CV
     * Depois de fazer todos os cálculos, é feita uma impressão dos resultados
     * 
     * Este método é usado tanto para (xi) quanto para classes
     * 
     * @param matriz - uma matriz double que contém os valores de dados e FI
     * @param casasDecimais - número de casas decimais para formatação
     */
    public static void calculoEstatistico(double[][] matriz, int casasDecimais) {
        double xifi = 0, somaS2 = 0, media = 0, s2 = 0, s = 0, cv = 0;
        int n = 0, i;
        
        for (i = 0; i < matriz.length; i++) {
            n += matriz[i][1]; // calculando o valor de N
            xifi += matriz[i][0] * matriz[i][1]; // calculando o valor de xi*fi
        }
        media = xifi / n; //cálculo da média

        for (i = 0; i < matriz.length; i++) {
            somaS2 += Math.pow((matriz[i][0] - media), 2) * matriz[i][1]; // cálculo da parte E(xi - media)^2 * fi
        }
        
        s2 = somaS2 / (n-1); //cálculo da variância
        s = Math.sqrt(s2); //cálculo do desvio padrão
        cv = (100 * s) / media; //cálculo do coeficiente de variação

        System.out.println("N = " + n +";\nMédia = " + formatarNumero(media, casasDecimais) + ";\nVariância(s2) = " + formatarNumero(s2, casasDecimais) +
                ";\nDesvio padrão = " + formatarNumero(s, casasDecimais) + ";\nCoeficiente de Variação (CV) = " + formatarNumero(cv, casasDecimais) + "%"); //impressão de todos os resultados
    }

    /** Método para imprimir qualquer matriz do programa
     * 
     * @param matriz - é uma matriz double que contém os valores dos dados e FI
     * @param qual - é um valor inteiro para decidir qual é o tipo da matriz
     * @param casasDecimais - número de casas decimais para formatação
     */
    public static void imprimeMatriz(double[][] matriz, int qual, int casasDecimais) {
        /*
         * qual = 1 -> matriz de dados (xi)
         * qual = 2 -> matriz de classes
         */
        System.out.println("\n Os dados apresentados estão corretos? ");
        switch (qual) { //switch para verificar o tipo da matriz
        case 1:
            //cabeçalho de impressão da matriz por dados(xi)
            System.out.println("---DADOS(xi)---");
            System.out.println("XI\tFI"); 
            break;
        case 2:
            //cabeçalho de impressão da matriz por dados(xi)
            System.out.println("---CLASSES---");
            System.out.println("li---ls\tPMI\tFI");
            break;
        }
        for (int i = 0; i < matriz.length; i++) {
            if (qual == 1) {
                //se for matriz por dados (xi), imprime os valores de xi e fi
                System.out.println("[" + matriz[i][0] + "]\t[" + matriz[i][1] + "]");
            }
            if (qual == 2) {
                //se for matriz por classes, imprime os dois limites de cada classe, o PMI e o FI
                System.out.println("[" + matriz[i][2] + "]---[" + matriz[i][3] + "]\t[" + matriz[i][0]+ "]\t[" + matriz[i][1] + "]");
            }
        }
        calculoEstatistico(matriz, casasDecimais); //chama o método para fazer os cálculos e imprimí-los aqui
    }

    // inserção de dados do usuário: dados e frequências individuais ou pelo menos
    // uma classe (e calcula-se então a amplitude ((h) = (b - a)/2, sendo a = limite
    // inferior e b = limite superior)), as outras classes e os PMIs;
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        quebraDeLinha();
        System.out.println(
                "Bem-vindo ao programa JCS. Este programa serve unica e exclusivamente para calcular dados estatísticos \n"
                + "do tipo 'Distribuição Normal'. Para mais versões com outras funcionalidades, consulte a \n"
                + "Faculdade de Tecnologia de Jundiaí - Deputado Ary Fossen | Centro Paula Souza.");

        quebraDeLinha();

        System.out.println("Com quantas casas decimais deseja ver os resultados?");
        int casasDecimais = teclado.nextInt();

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
                imprimeMatriz(dadosAmostrais, 1, casasDecimais);

                System.out.print("\n Responda 1 para corrigir ou qualquer tecla para prosseguir: ");
                resp = teclado.next().charAt(0);

            } while (resp == '1');
            break;

        case 2:

            System.out.println("\n Para inserir classes, digite [1];");
            System.out.println("\n Para inserir PMIs (pontos médios) digite [2];");
            quebraDeLinha();
            System.out.print("Opção: ");
            int dadosOuClasses = teclado.nextInt();
            switch (dadosOuClasses) {
            case 1:
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
                    imprimeMatriz(classes, 2, casasDecimais);

                    System.out.print("\nResponda 1 para corrigir ou qualquer tecla para prosseguir: ");
                    resp = teclado.next().charAt(0);
                } while (resp == '1');

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
