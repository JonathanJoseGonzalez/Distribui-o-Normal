import java.util.Scanner;
import java.lang.Math;

public class distribuiçãoNormal {

    public static void quebraDeLinha() {
        System.out.println("");
        System.out.println("-------------------------------------");
        System.out.println("");
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
    System.out.println("/n Digite [1] para inserir dados.");
    System.out.println("/n Digite [2] para inserir classes.");

    quebraDeLinha();

    String resposta = teclado.nextInt();

    // inserção de dados;

    switch(resposta)
    {
        case 1: 

        do {
            System.out.println("/n Quantos dados deseja inserir: ");
            int quantidadeDeDados = teclado.nextInt();
            // inserção dos dados e suas respectivas frequências;
            float [][] dadosAmostrais = new float[quantidadeDeDados][2];
            for (int i = 0; i < quantidadeDeDados; i++) {
                for (j = 0; j < quantidadeDeDados; j++) {
                   System.out.println("Digite o dado desejado: ");
                   float dado  = teclado.nextFloat();
                   dadosAmostrais = dado [i];
                   System.out.println("Digite a respectiva frequência: ");
                   int frequência = teclado.nextInt();
                   dadosAmostrais = dado [j];
                    
                }
             }

            System.out.println("/n Os dados apresentados estão corretos? ");
            for (int i = 0; i <quantidadeDeDados; i++) {
                for (int j = 0; j <quantidadeDeDados; j++) {
                    System.out.println("[" + dadosAmostrais[i][j] + "]");
                }
            }
            System.out.println("/n Responda [Y/N]: ");
            char respostaYN = teclado.next().charAt(0);
            dadosCorretos = (respostaYN == 'Y' || respostaYN == 'y'); 
            
        } while(!dadosCorretos);
        break;



        case 2:
    
        System.out.println("/n Para inserir classes, digite [1];");
        System.out.println("/n Para inserir PMIs (pontos médios) digite [2];");
        int dadosOuClasses = teclado.nextInt();

        // calcular amplitude e ponto médio da classe
        do {
        if (dadosOuClasses == 1) {
            System.out.println("Quantas classes deseja inserir?:  ");
            int quantidadeDeClasses = teclado.nextInt();
            float [][] classes = new float[quantidadeDeClasses][2];
            for (int i = 0; i < quantidadeDeClasses; i++) {
                for (int j=0; j < quantidadeDeClasses, j++) {
                 System.out.println("Digite o limite inferior desta classe: ");
                 float limiteInferior = teclado.nextFloat();
                 limiteInferior = classes [i];
                 System.out.println(" Digite o limite superior desta classe: ");
                 float limiteSuperior = teclado.nextFloat();
                 limiteSuperior = classes [j];
                }
            }
            System.out.println("/n Os dados apresentados estão corretos? ");
            for (int i = 0; i <quantidadeDeClasses; i++) {
                for (int j = 0; j <quantidadeDeClasses; j++) {
                    System.out.println("[" + classes[i][j] + "]");
                }
            }

            System.out.println("/n Responda [Y/N]: ");
            char respostaYN = teclado.next().charAt(0);
            dadosCorretos = (respostaYN == 'Y' || respostaYN == 'y');

            } 
        }while (!dadosCorretos);

        if (dadosOuClasses == 2) {
            System.out.println("")
        }
        

            break;

        default:
            System.out.println("Valor não encontrado no menu.");
        
    }
    teclado.close();

    }

    // cálculo da média (x̅) = (∑xifi)/ N, sendo xi = dados ou pontos médios e fi =
    // frequências individuais e N = espaço amostral ou número total de termos;

    // cálculo da variância (s²) = (∑(xifi - x̅)²)/ N, sendo xi = dados ou pontos
    // médios e fi = frequências individuais e N = espaço amostral ou número total
    // de termos;

    // cálculo do desvio padrão (s) = √s²

    // cálculo do coeficiente de variação = (100*s)/x̅;

}
