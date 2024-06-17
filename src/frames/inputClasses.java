package frames;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import classes.Calculos;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class inputClasses {

	private JFrame frInputDados;

	private JTable tableCalculos;
	DefaultTableModel modelCalculos;
	private JLabel lblSomaFi;
	private JLabel lblSomaXiFi;
	private JLabel lblXbarra;
	private JLabel lblSomaXiMediaQuadradoFi;
	private JLabel lblVariancia;
	private JLabel lblDesvioPadrao;
	private JLabel lblCoeficienteDeVariacao;
	public static JTextField txSomaFi;
	private JTextField txSomaXifi;
	public static JTextField txXbarra;
	private JTextField txSomaXiMediaQuadradoFi;
	private JTextField txVariancia;
	public static JTextField txDesvioPadrao;
	private JTextField txCoeficienteDeVariacao;
	private JButton btLimparTela;
	private JButton btnDistribuicaoNormal;

	private JMenuBar menuBar;
		private JMenu mnInsercaoDeDados;
			private final ButtonGroup buttonGroupInsercaoDados = new ButtonGroup();
			private JRadioButtonMenuItem rdbtnmntmXi;
			private JRadioButtonMenuItem rdbtnmntmClasses;
	
		private JMenu mnCasasDecimais;
			private final ButtonGroup buttonGroupCasaDecimal = new ButtonGroup();
			private JRadioButtonMenuItem rdbtnmntm0;
			private JRadioButtonMenuItem rdbtnmntm1;
			private JRadioButtonMenuItem rdbtnmntm2;
			private JRadioButtonMenuItem rdbtnmntm3;
			private JRadioButtonMenuItem rdbtnmntm4;
			private JRadioButtonMenuItem rdbtnmntm5;
			private JRadioButtonMenuItem rdbtnmntm6;

		private JMenu mnAjuda;

	Double N = 0.0;
	Double somaXi = 0.0;
	Double xBarra = 0.0;
	Double somaXiFi = 0.0;
	Double somaXiMediaQuadradoFi = 0.0;
	Double variancia = 0.0;
	Double desvioPadrao = 0.0;
	Double coeficienteDeVariacao = 0.0;
	int casaDecimal = 2;
	private JLabel lblClassesLi;
	private JTextField txLimiteInferior;
	private JLabel lblClassesLs;
	private JTextField txLimiteSuperior;
	private JLabel lblClassesPosicao;
	private JTextField txPosicao;
	private JLabel lblClassesPmi;
	private JTextField txPMI;
	private JLabel lblClassesH;
	private JTextField txAltura;

	private JTextArea textAreaFi;
	private JLabel lblNewLabel;
	private JLabel lblContaFi;

	
	
	/**
	 * Método para processar as informações de fi fornecidas pelo usuário
	 * O código recupera a string fornecida na JTextArea textAreaFi, 
	 * eliminando espaços possíveis que o usuário possa ter inserido e, 
	 * na sequência, grava os valores em um Array Dinamico.
	 * 
	 * adaptado de: https://www.baeldung.com/java-csv-file-array
	 */
	public void transformarFi() {
		String inputFi = textAreaFi.getText().replace(" ", "").replace("\n", "").replace("\r", "");
		List<List<String>> valoresFi = new ArrayList<>();
		try (Scanner scanner = new Scanner(inputFi)) {
		    while (scanner.hasNextLine()) {
		    	valoresFi.add(gravarFi(scanner.nextLine()));
		    }
		}		
	}
	/**
	 * Método para processar as informações de fi fornecidas pelo usuário
	 * Os valores obtidos são salvos no Array Dinamico separados por vírgulas,
	 * o que irá indicar a posição deles. 
	 * O Método também faz a inserção dos dados na tabela
	 * adaptado de: https://www.baeldung.com/java-csv-file-array
	 */
	private List<String> gravarFi(String line) {
	    List<String> valoresFi = new ArrayList<String>();
	    try (Scanner rowScanner = new Scanner(line)) {
	    	
	        rowScanner.useDelimiter(",");
	        while (rowScanner.hasNext()) {
	        	valoresFi.add(rowScanner.next());
	        }
	    }
	    if (valoresFi.size() != quantidadeClasses()) {
	    	JOptionPane.showMessageDialog(null,
					"Perae! \nA quantidade de informações está divergente!"
					+ "\nVerifique a quantidade de classes e a quantidade de fis!",
					"Atenção!", JOptionPane.WARNING_MESSAGE);
	    } else {
	    	int tamanho = valoresFi.size();
		    modelCalculos.setNumRows(tamanho);
			for (int i = 0; i < tamanho; i++) {
				 int valorFi = Integer.parseInt(valoresFi.get(i));
				 modelCalculos.setValueAt(valorFi, i, 3);
			}
	    }
	    
	    return valoresFi;
	}
	/**
	 * Método para retornar a quantidade de classes dentro do frInputClasses
	 * @return A quantidade de classes obtida, cujo valor está armazenado no lblContaFi
	 */
	private int quantidadeClasses() {
		int quantidadeClasses = Integer.parseInt(lblContaFi.getText());
		return quantidadeClasses;
	}
	/**
	 * Método para retornar o valor do PMI
	 * @return retorna o valor informado pelo usuário em txPMI
	 */
	private Double valorPMI() {
		Double valorPMI = Double.parseDouble(txPMI.getText().replace(",","."));
		return valorPMI;
	}
	/**
	 * Método para retornar a posição do PMI
	 * @return retorna o valor informado pelo usuário em txPosicao. O valor é retornado com -1, devido ao posicionamento na tabela.
	 */
	private int posicaoPMI() {
		int posicaoPMI = Integer.parseInt(txPosicao.getText());
		posicaoPMI -= 1; //já levando em consideração que a posição de um vetor é -1 em relação a contagem padrão
		return posicaoPMI;
	}
	/**
	 * Método para retornar o intervalo do PMI
	 * @return retorna o intervalo entre PMIs, informado pelo usuário em txAltura
	 */
	private Double alturaPMI() {
		Double alturaPMI = Double.parseDouble(txAltura.getText().replace(",","."));
		return alturaPMI;
	}
	/**
	 * Método para inserir o PMI na posição informada pelo usuário
	 * Este método faz uso de valorPMI() e posicaoPMI() para inserir o valor do PMI na posição correspondente
	 */
	private void inserirPMI() {
		modelCalculos.setValueAt(valorPMI(), posicaoPMI(), 2);
	}
	/**
	 * Método para cálculo dos valores do PMI
	 * Este método realiza a análise da posição do PMI e calcula, somando ou subtraindo, o valor da altura correspondente à posição do PMI
	 */
	private void calcularPMI() {
		Double[] PMIs = new Double[quantidadeClasses()];
		Double valor = valorPMI();
		for (int i = posicaoPMI()+1; i < quantidadeClasses(); i++) {
			valor += alturaPMI();
			PMIs[i] = valor;
			PMIs[i] = Calculos.arredondamento(PMIs[i], 2);
		}
		valor = valorPMI();
		for (int i = posicaoPMI()-1;i >= 0; i-- ) {

			valor -= alturaPMI();
			PMIs[i] = valor;
			PMIs[i] = Calculos.arredondamento(PMIs[i], 2);
		}
		for (int i = 0; i < quantidadeClasses(); i++) {
			 modelCalculos.setValueAt(PMIs[i], i, 2);
		}

		inserirPMI();
	}
	
	/**
	 * Método para cálculo de N
	 * @return Retorna a soma dos valores fi
	 */
	public Integer SomaFi() {
		Integer somaFi = 0;

		for (int i = 0; i < modelCalculos.getRowCount(); i++) {
			somaFi += (Integer) modelCalculos.getValueAt(i, 3);
		}

		return somaFi;
	}
	/**
	 * Método para cálculo da multiplicação de xi (PMI) por fi
	 * Este método busca os valores de xi e fi que foram inseridos na tabela e também realiza a soma dos valores obtidos pela multiplicação
	 * @return Após localizar e multiplicar os valores de xi e fi, este método retorna a soma de todas razões obtidas. 
	 */
	public Double XiFi() {
		Double xifi = 0.0;
		Double xi = 0.0;
		int fi = 0;
		Double somaXiFi = 0.0;
		
		for (int i = 0; i < quantidadeClasses(); i++) {
			xi = (Double) modelCalculos.getValueAt(i, 2);
			fi = (int) modelCalculos.getValueAt(i, 3);
			xifi =  xi * fi;
			xifi = Calculos.arredondamento(xifi, 2);
			modelCalculos.setValueAt(xifi, i, 4);
			somaXiFi += xifi;
		}
		somaXiFi = Calculos.arredondamento(somaXiFi, casaDecimal);
		txSomaXifi.setText(somaXiFi.toString());
		return somaXiFi;
	}
	/**
	 * Método para calcular a média
	 * @return retorna o produto da divisão da soma de xifi por N (soma das frequencias)
	 */
	public Double media() {
		Double media = 0.0;
		media = XiFi() / SomaFi();
		media = Calculos.arredondamento(media, casaDecimal);
		txXbarra.setText(media.toString());
		return media;
	}
	/**
	 * Método para calcular o produto da frequência pelo quadrado da diferença do dado pela média
	 * @return retorna a soma obtida pelo cálculo de todos produtos
	 */
	public Double SomaXiMediaQuadradoFi() {
		Double xi = 0.0;
		int fi = 0;
		Double xiMediaQuadradoFi = 0.0;
		Double somaxiMediaQuadradoFi = 0.0;
		
		for (int i = 0; i < quantidadeClasses(); i++) {
			xi = (Double) modelCalculos.getValueAt(i, 2);
			fi = (int) modelCalculos.getValueAt(i, 3);
			xiMediaQuadradoFi =  Math.pow(xi-media(),2) * fi;
			xiMediaQuadradoFi = Calculos.arredondamento(xiMediaQuadradoFi, casaDecimal);
			modelCalculos.setValueAt(xiMediaQuadradoFi, i, 5);
			somaxiMediaQuadradoFi += xiMediaQuadradoFi;
		}
		somaxiMediaQuadradoFi = Calculos.arredondamento(somaxiMediaQuadradoFi, casaDecimal);
		txSomaXiMediaQuadradoFi.setText(somaxiMediaQuadradoFi.toString());
		return somaxiMediaQuadradoFi;
	}
	/**
	 * Método para cálculo da Variância
	 * @return Retorna o resultado da divisão da soma obtida no método SomaXiMediaQuadradoFi() pela resultado da subtração da média menos um.
	 */
	public Double Variancia() {
		Double variancia = 0.0;
		variancia = SomaXiMediaQuadradoFi()/(SomaFi()-1);
		variancia = Calculos.arredondamento(variancia, casaDecimal);
		txVariancia.setText(variancia.toString());
		return variancia;
	}
	/**
	 * Método para cálculo do Desvio Padrão
	 * @return Retorna o valor obtido pelo cálculo da raiz quadrada da Variancia
	 */
	public Double DesvioPadrao() {
		Double desvioPadrao = 0.0;
		desvioPadrao = Math.sqrt(Variancia());
		desvioPadrao = Calculos.arredondamento(desvioPadrao, casaDecimal);
		txDesvioPadrao.setText(desvioPadrao.toString());
		return desvioPadrao;
	}
	/**
	 * Método para cálculo do Coeficiente de Variação
	 * @return Retorna a porcentagem da divisão do desvioPadrao() pela media()
	 */
	public Double coeficienteVariacao() {
		Double coeficienteVariacao = 0.0;
		coeficienteVariacao = 100 * DesvioPadrao()/media();
		coeficienteVariacao = Calculos.arredondamento(coeficienteVariacao, casaDecimal);
		txCoeficienteDeVariacao.setText(coeficienteVariacao.toString() + "%");
		return coeficienteVariacao;
	}
	/**
	 * Método para realizar a inserção de dados e cálculos por meio das informações fornecidas pelo usuário.
	 * Caso os dados sejam insuficientes para realizar cálculos, o programa irá retornar um aviso ao usuário, solicitando mais entrada de dados.
	 */
	public void calcular() {
		if (Integer.parseInt(lblContaFi.getText()) < 2) {
			JOptionPane.showMessageDialog(null,
					"Calma, usuário! \nVocê ainda não inseriu dados o suficiente! \nSem nada para calcular por enquanto! \nBora trabalhar!",
					"Atenção!", JOptionPane.WARNING_MESSAGE);
			}else {				
				transformarFi();
				calcularPMI();
				XiFi();
				media();
				SomaXiMediaQuadradoFi();
				Variancia();
				DesvioPadrao();
				coeficienteVariacao();
				System.out.println(casaDecimal);
			}
	}
	/**
	 * Método para verificação se uma string possui valor de Double
	 * @param linha é a String a ser analisada
	 * @return retorna True se a String possuir valor Double e False se não possuir.
	 */
	public static boolean ehDouble(String linha) {
		try {
			Double.parseDouble(linha.replace(',', '.'));
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	/**
	 * Método para verificação se uma string possui valor de Integer
	 * @param linha é a String a ser analisada
	 * @return retorna True se a String possuir valor Integer e False se não possuir.
	 */
	public static boolean ehInteger(String linha) {
		try {
			Integer.parseInt(linha.replace(',', '.'));
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
/**
 * Método que realiza a "limpeza" das informações, reiniciando os valores das JTextField para o valor padrão inicial.
 */
	public void Limpar() {
		modelCalculos.setRowCount(0);
		ativarDistribuicaoNormal();
		tableCalculos.clearSelection();
		textAreaFi.setText("");
		txPMI.setText("");
		txPosicao.setText("1");
		txAltura.setText("");
		txLimiteInferior.setText("");
		txLimiteSuperior.setText("");
		lblContaFi.setText("0");
		txSomaXifi.setText("0,00");
		txXbarra.setText("0,00");
		txSomaXiMediaQuadradoFi.setText("0,00");
		txVariancia.setText("0,00");
		txDesvioPadrao.setText("0,00");
		txCoeficienteDeVariacao.setText("0,00 %");
	}

	/**
	 * Método que mantém o botão btnDistribuicaoNormal desativado até que a tabela
	 * tableCalculostenha tenha ao menos 2 valores inseridos.
	 */
	public void ativarDistribuicaoNormal() {

		if (modelCalculos.getRowCount() <= 1) {
			btnDistribuicaoNormal.setEnabled(false);
		} else if ((modelCalculos.getRowCount() >= 2) && (btnDistribuicaoNormal.isEnabled() == false)) {
			btnDistribuicaoNormal.setEnabled(true);
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println(Locale.getDefault());
					System.out.println("Here we go again! - inputDados");
					inputClasses window = new inputClasses();

					window.frInputDados.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public inputClasses() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frInputDados = new JFrame();
		frInputDados.setName("frEstatistica");
		frInputDados.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		frInputDados.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frInputDados.setResizable(false);
		frInputDados.setTitle("Estatística JCS");
		frInputDados.getContentPane().setBackground(new Color(255, 255, 204));
		frInputDados.setBackground(new Color(255, 204, 0));
		frInputDados.setBounds(new Rectangle(0, 0, 585, 725));
		frInputDados.setLocationRelativeTo(null);
		frInputDados.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frInputDados.getContentPane().setLayout(null);

		URL iconURL = getClass().getResource("/img/iconJCS.png");
		ImageIcon icon = new ImageIcon(iconURL);
		frInputDados.setIconImage(icon.getImage());
		
		//JScrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(10, 10, 549, 221);
		frInputDados.getContentPane().add(scrollPane);
	
			tableCalculos = new JTable();
			tableCalculos.setEnabled(false);
			tableCalculos.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			tableCalculos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableCalculos.setGridColor(Color.DARK_GRAY);
			tableCalculos.setFillsViewportHeight(true);
			tableCalculos.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tableCalculos.setForeground(Color.DARK_GRAY);
			tableCalculos.setBackground(new Color(255, 204, 153));
			tableCalculos.setRowMargin(2);
			tableCalculos.setRowHeight(30);
			modelCalculos = new DefaultTableModel();
			Object[] colunaCalculos = { "li", "ls", "PMI", "fi", "xifi", "(xi-x\u0304)\u00B2fi" };
			Object[] linhaCalculos = { new Double[0], new Double[1], new Double[2], new Integer[3], new Double[4], new Double[5] };
			modelCalculos.setColumnIdentifiers(colunaCalculos);
			tableCalculos.setModel(modelCalculos);
			scrollPane.setViewportView(tableCalculos);

		lblSomaFi = new JLabel("N: ");
		lblSomaFi.setToolTipText("<html>É a soma de [ fi ]!<br>"
				+ "Ou seja, é o número total de informações do conjunto ou amostra!</html>");
		lblSomaFi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSomaFi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSomaFi.setBounds(10, 281, 75, 30);
		frInputDados.getContentPane().add(lblSomaFi);

		lblSomaXiFi = new JLabel("∑xifi: ");
		lblSomaXiFi.setToolTipText("Soma do produto de cada dado por sua frequência");
		lblSomaXiFi.setLabelFor(lblSomaXiFi);
		lblSomaXiFi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSomaXiFi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSomaXiFi.setBounds(10, 321, 75, 30);
		frInputDados.getContentPane().add(lblSomaXiFi);

		lblXbarra = new JLabel("x̄: ");
		lblXbarra.setToolTipText("x̄, ou x barra, é o resultado da média! É a razão de ∑xifi por N");
		lblXbarra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblXbarra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblXbarra.setBounds(10, 361, 75, 30);
		frInputDados.getContentPane().add(lblXbarra);

		lblSomaXiMediaQuadradoFi = new JLabel("∑(xi-x̄)²fi: ");
		lblSomaXiMediaQuadradoFi.setToolTipText(
				"É a somatória do resultado do produto do quadrado da diferença entre o dado e a média pela sua frequência");
		lblSomaXiMediaQuadradoFi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSomaXiMediaQuadradoFi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSomaXiMediaQuadradoFi.setBounds(324, 241, 75, 30);
		frInputDados.getContentPane().add(lblSomaXiMediaQuadradoFi);

		lblVariancia = new JLabel("s²: ");
		lblVariancia.setToolTipText("É a variância! Isso significa que é o quadrado da diferença dos desvios!");
		lblVariancia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVariancia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVariancia.setBounds(324, 281, 75, 30);
		frInputDados.getContentPane().add(lblVariancia);

		lblDesvioPadrao = new JLabel("s: ");
		lblDesvioPadrao.setToolTipText("É o desvio padrão! √(s²)");
		lblDesvioPadrao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDesvioPadrao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDesvioPadrao.setBounds(324, 321, 75, 30);
		frInputDados.getContentPane().add(lblDesvioPadrao);

		lblCoeficienteDeVariacao = new JLabel("C.V.: ");
		lblCoeficienteDeVariacao.setToolTipText("É o coeficiente de variação!");
		lblCoeficienteDeVariacao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCoeficienteDeVariacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCoeficienteDeVariacao.setBounds(324, 361, 75, 30);
		frInputDados.getContentPane().add(lblCoeficienteDeVariacao);

		txSomaFi = new JTextField();
		txSomaFi.setToolTipText(
				"<html>É a soma de [ fi ]!<br>Ou seja, é o número <b>total de informações</b> do conjunto ou amostra!</html>");
		txSomaFi.setBackground(new Color(255, 204, 153));
		txSomaFi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txSomaFi.setText("0,00");
		txSomaFi.setHorizontalAlignment(SwingConstants.RIGHT);
		txSomaFi.setEditable(false);
		lblSomaFi.setLabelFor(txSomaFi);
		txSomaFi.setColumns(10);
		txSomaFi.setBounds(95, 281, 150, 30);
		frInputDados.getContentPane().add(txSomaFi);

		txSomaXifi = new JTextField();
		txSomaXifi.setToolTipText(lblSomaXiFi.getToolTipText());
		txSomaXifi.setBackground(new Color(255, 204, 153));
		txSomaXifi.setText("0,00");
		txSomaXifi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txSomaXifi.setHorizontalAlignment(SwingConstants.RIGHT);
		txSomaXifi.setEditable(false);
		txSomaXifi.setColumns(10);
		txSomaXifi.setBounds(95, 321, 150, 30);
		frInputDados.getContentPane().add(txSomaXifi);

		txXbarra = new JTextField();
		lblXbarra.setLabelFor(txXbarra);
		txXbarra.setToolTipText(lblXbarra.getToolTipText());
		txXbarra.setBackground(new Color(255, 204, 153));
		txXbarra.setText("0,00");
		txXbarra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txXbarra.setHorizontalAlignment(SwingConstants.RIGHT);
		txXbarra.setEditable(false);
		txXbarra.setColumns(10);
		txXbarra.setBounds(95, 361, 150, 30);
		frInputDados.getContentPane().add(txXbarra);

		txSomaXiMediaQuadradoFi = new JTextField();
		txSomaXiMediaQuadradoFi.setToolTipText(lblSomaXiMediaQuadradoFi.getToolTipText());
		txSomaXiMediaQuadradoFi.setBackground(new Color(255, 204, 153));
		lblSomaXiMediaQuadradoFi.setLabelFor(txSomaXiMediaQuadradoFi);
		txSomaXiMediaQuadradoFi.setText("0,00");
		txSomaXiMediaQuadradoFi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txSomaXiMediaQuadradoFi.setHorizontalAlignment(SwingConstants.RIGHT);
		txSomaXiMediaQuadradoFi.setEditable(false);
		txSomaXiMediaQuadradoFi.setColumns(10);
		txSomaXiMediaQuadradoFi.setBounds(409, 241, 150, 30);
		frInputDados.getContentPane().add(txSomaXiMediaQuadradoFi);

		txVariancia = new JTextField();
		lblVariancia.setLabelFor(txVariancia);
		txVariancia.setToolTipText(lblVariancia.getToolTipText());
		txVariancia.setBackground(new Color(255, 204, 153));
		txVariancia.setText("0,00");
		txVariancia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txVariancia.setHorizontalAlignment(SwingConstants.RIGHT);
		txVariancia.setEditable(false);
		txVariancia.setColumns(10);
		txVariancia.setBounds(409, 281, 150, 30);
		frInputDados.getContentPane().add(txVariancia);

		txDesvioPadrao = new JTextField();
		lblDesvioPadrao.setLabelFor(txDesvioPadrao);
		txDesvioPadrao.setToolTipText(lblDesvioPadrao.getToolTipText());
		txDesvioPadrao.setBackground(new Color(255, 204, 153));
		txDesvioPadrao.setText("0,00");
		txDesvioPadrao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txDesvioPadrao.setHorizontalAlignment(SwingConstants.RIGHT);
		txDesvioPadrao.setEditable(false);
		txDesvioPadrao.setColumns(10);
		txDesvioPadrao.setBounds(409, 321, 150, 30);
		frInputDados.getContentPane().add(txDesvioPadrao);

		txCoeficienteDeVariacao = new JTextField();
		lblCoeficienteDeVariacao.setLabelFor(txCoeficienteDeVariacao);
		txCoeficienteDeVariacao.setToolTipText(lblCoeficienteDeVariacao.getToolTipText());
		txCoeficienteDeVariacao.setBackground(new Color(255, 204, 153));
		txCoeficienteDeVariacao.setText("0,00");
		txCoeficienteDeVariacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txCoeficienteDeVariacao.setHorizontalAlignment(SwingConstants.RIGHT);
		txCoeficienteDeVariacao.setEditable(false);
		txCoeficienteDeVariacao.setColumns(10);
		txCoeficienteDeVariacao.setBounds(409, 361, 150, 30);
		frInputDados.getContentPane().add(txCoeficienteDeVariacao);

		btLimparTela = new JButton("Limpar Tela!");
		btLimparTela.setBounds(383, 521, 176, 80);
		frInputDados.getContentPane().add(btLimparTela);
		btLimparTela.setBackground(new Color(255, 204, 153));
		btLimparTela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableCalculos.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null,
							"Calma, usuário! \nVocê ainda não inseriu nenhum dado! \nSem nada para excluir por enquanto! \nBora trabalhar!",
							"Atenção!", JOptionPane.WARNING_MESSAGE);
				} else {
					Object[] opcoes = { "OK", "Cancelar" };
					int confirmar = JOptionPane.showOptionDialog(null,
							"Atenção: \nEssa ação irá excluir TODOS os seus dados atuais! \nClique em [OK] para prosseguir!",
							"ATENÇÃO!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opcoes,
							opcoes[0]);
					if (confirmar == 0) {
						int confirmarDeNovo = JOptionPane.showOptionDialog(null,
								"Só para ter certeza... MAS: \nVocê esta prestes a EXCLUIR todos os seus cálculos! \n[OK] por sua conta e risco!",
								"TEM CERTEZA?", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opcoes,
								opcoes[0]);
						if (confirmarDeNovo == 0) {
							Limpar();
						}
					}
				}
			}
		});
		btLimparTela.setFont(new Font("Tahoma", Font.PLAIN, 14));

		btnDistribuicaoNormal = new JButton("Distribuição Normal");
		btnDistribuicaoNormal.setBounds(10, 611, 549, 40);
		frInputDados.getContentPane().add(btnDistribuicaoNormal);
		btnDistribuicaoNormal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDistribuicaoNormal.setBackground(new Color(255, 204, 153));
		btnDistribuicaoNormal.setEnabled(false);
		btnDistribuicaoNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frames.distribuicaoNormal.main(null); // Abre a tela "distribuicaoNormal"
			}
		});
					
					lblClassesLi = new JLabel("li:");
					lblClassesLi.setBounds(263, 571, 30, 30);
					frInputDados.getContentPane().add(lblClassesLi);
					lblClassesLi.setToolTipText("Limite Inferior");
					lblClassesLi.setHorizontalAlignment(SwingConstants.RIGHT);
					lblClassesLi.setFont(new Font("Tahoma", Font.PLAIN, 14));
					
					txLimiteInferior = new JTextField();
					txLimiteInferior.setBounds(303, 571, 70, 30);
					frInputDados.getContentPane().add(txLimiteInferior);
					txLimiteInferior.setToolTipText("");
					txLimiteInferior.setText("0,00");
					txLimiteInferior.setHorizontalAlignment(SwingConstants.CENTER);
					txLimiteInferior.setFont(new Font("Tahoma", Font.PLAIN, 14));
					txLimiteInferior.setColumns(10);
					txLimiteInferior.setBackground(new Color(255, 204, 153));
					
					lblClassesLs = new JLabel("ls:");
					lblClassesLs.setBounds(263, 511, 30, 30);
					frInputDados.getContentPane().add(lblClassesLs);
					lblClassesLs.setToolTipText("Limite Superior");
					lblClassesLs.setHorizontalAlignment(SwingConstants.RIGHT);
					lblClassesLs.setFont(new Font("Tahoma", Font.PLAIN, 14));
					
					txLimiteSuperior = new JTextField();
					txLimiteSuperior.setBounds(303, 511, 70, 30);
					frInputDados.getContentPane().add(txLimiteSuperior);
					txLimiteSuperior.setToolTipText("");
					txLimiteSuperior.setText("1,00");
					txLimiteSuperior.setHorizontalAlignment(SwingConstants.CENTER);
					txLimiteSuperior.setFont(new Font("Tahoma", Font.PLAIN, 14));
					txLimiteSuperior.setColumns(10);
					txLimiteSuperior.setBackground(new Color(255, 204, 153));
					
					lblClassesPosicao = new JLabel("Posição:");
					lblClassesPosicao.setBounds(10, 561, 110, 40);
					frInputDados.getContentPane().add(lblClassesPosicao);
					lblClassesPosicao.setToolTipText("Informe a posição da classe");
					lblClassesPosicao.setHorizontalAlignment(SwingConstants.RIGHT);
					lblClassesPosicao.setFont(new Font("Tahoma", Font.PLAIN, 14));
					
					txPosicao = new JTextField();
					txPosicao.setBounds(130, 561, 70, 40);
					frInputDados.getContentPane().add(txPosicao);
					txPosicao.setText("1");
					txPosicao.setToolTipText("<html>Informe a posição do PMI.<br>\r\nLembrete: <br>\r\ndeve ser maior do que 0 e <br>\r\nmenor do que a quantidade total de Classes!</html>");
					txPosicao.setHorizontalAlignment(SwingConstants.CENTER);
					txPosicao.setFont(new Font("Tahoma", Font.PLAIN, 14));
					txPosicao.setColumns(10);
					txPosicao.setBackground(new Color(255, 204, 153));
					
					lblClassesPmi = new JLabel("PMI:");
					lblClassesPmi.setBounds(10, 511, 110, 40);
					frInputDados.getContentPane().add(lblClassesPmi);
					lblClassesPmi.setToolTipText("Informe a quantida de classes");
					lblClassesPmi.setHorizontalAlignment(SwingConstants.RIGHT);
					lblClassesPmi.setFont(new Font("Tahoma", Font.PLAIN, 14));
					
					txPMI = new JTextField();
					txPMI.setBounds(130, 512, 70, 40);
					frInputDados.getContentPane().add(txPMI);
					txPMI.setToolTipText("x̄, ou x barra, é o resultado da média! É a razão de ∑xifi por N");
					txPMI.setHorizontalAlignment(SwingConstants.CENTER);
					txPMI.setFont(new Font("Tahoma", Font.PLAIN, 14));
					txPMI.setColumns(10);
					txPMI.setBackground(new Color(255, 204, 153));
					
					lblClassesH = new JLabel("h:");
					lblClassesH.setBounds(189, 540, 30, 30);
					frInputDados.getContentPane().add(lblClassesH);
					lblClassesH.setToolTipText("<html>Altura da classe: <br>\r\né o intervalo correspondente <br>\r\nentre o limite inferior (li) e o <br>\r\nlimite superior (ls) de uma classe</html>");
					lblClassesH.setHorizontalAlignment(SwingConstants.RIGHT);
					lblClassesH.setFont(new Font("Tahoma", Font.PLAIN, 14));
					
					txAltura = new JTextField();
					txAltura.setBounds(223, 540, 70, 30);
					frInputDados.getContentPane().add(txAltura);
					txAltura.setToolTipText("<html>Altura da classe: <br>\r\né o intervalo correspondente <br>\r\nentre o limite inferior (li) e o <br>\r\nlimite superior (ls) de uma classe</html>");
					txAltura.setHorizontalAlignment(SwingConstants.CENTER);
					txAltura.setFont(new Font("Tahoma", Font.PLAIN, 14));
					txAltura.setColumns(10);
					txAltura.setBackground(new Color(255, 204, 153));
					
					JLabel lblInformeFi = new JLabel("Informe aqui os fi:");
					lblInformeFi.setBounds(10, 421, 153, 40);
					frInputDados.getContentPane().add(lblInformeFi);
					lblInformeFi.setToolTipText("Informe a posição da classe");
					lblInformeFi.setHorizontalAlignment(SwingConstants.RIGHT);
					lblInformeFi.setFont(new Font("Tahoma", Font.PLAIN, 14));
					
					JScrollPane scrollPane_1 = new JScrollPane();
					scrollPane_1.setBounds(173, 421, 200, 80);
					frInputDados.getContentPane().add(scrollPane_1);
					
					textAreaFi = new JTextArea();
					textAreaFi.setLineWrap(true);
					textAreaFi.addKeyListener(new KeyAdapter() {
						@Override
						public void keyReleased(KeyEvent e) {
							String input = textAreaFi.getText().replace("\n", "").replace("\r", "");
							int numCommas = input.length() - input.replace(",", "").length();
							lblContaFi.setText("" + (numCommas + 1)); 
						}
					});
					textAreaFi.setFont(new Font("Tahoma", Font.PLAIN, 16));
					textAreaFi.setToolTipText("<html>Insira aqui os [ fi ], separados por espaços.<br>\r\nExemplos:<br>\r\n1, 4, 5, 10, 21, 0, 4, 24<br>\r\n3,41,0,26,2,7,29,4,5<br>\r\n<br>\r\nLembrete!:<br>\r\nA quantidade de [ Classes ] ou [ PMIs ] <br>\r\ndevem ser a mesma quantidade de [ fis ]!\r\n</html>");
					textAreaFi.setBackground(new Color(255, 204, 153));
					scrollPane_1.setViewportView(textAreaFi);
					
					JButton btnInserirClasses = new JButton("Inserir Classes");
					btnInserirClasses.setBounds(383, 421, 176, 80);
					frInputDados.getContentPane().add(btnInserirClasses);
					btnInserirClasses.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							calcular();
						}
					});
					btnInserirClasses.setBackground(new Color(255, 204, 153));
					btnInserirClasses.setFont(new Font("Tahoma", Font.PLAIN, 14));
					
					lblNewLabel = new JLabel("Quantidade de fi:");
					lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
					lblNewLabel.setBounds(10, 461, 110, 40);
					frInputDados.getContentPane().add(lblNewLabel);
					
					lblContaFi = new JLabel("0");
					lblContaFi.setHorizontalAlignment(SwingConstants.CENTER);
					lblContaFi.setFont(new Font("Tahoma", Font.PLAIN, 14));
					lblContaFi.setBounds(123, 461, 40, 40);
					frInputDados.getContentPane().add(lblContaFi);
		
		menuBar = new JMenuBar();
		frInputDados.setJMenuBar(menuBar);

		mnInsercaoDeDados = new JMenu("Modo de inserção de dados");
		mnInsercaoDeDados.setFont(new Font("Tahoma", Font.PLAIN, 14));
		menuBar.add(mnInsercaoDeDados);

		rdbtnmntmXi = new JRadioButtonMenuItem("xi");
		buttonGroupInsercaoDados.add(rdbtnmntmXi);

		rdbtnmntmXi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnmntmXi.setSelected(true);
		mnInsercaoDeDados.add(rdbtnmntmXi);

		rdbtnmntmClasses = new JRadioButtonMenuItem("Classes");
		buttonGroupInsercaoDados.add(rdbtnmntmClasses);

		rdbtnmntmClasses.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mnInsercaoDeDados.add(rdbtnmntmClasses);

		mnCasasDecimais = new JMenu("Casas Decimais");
		mnCasasDecimais.setFont(new Font("Tahoma", Font.PLAIN, 14));
		menuBar.add(mnCasasDecimais);

		rdbtnmntm0 = new JRadioButtonMenuItem("0 - 0");
		rdbtnmntm0.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				casaDecimal = 0;
			}
		});
		rdbtnmntm0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				casaDecimal = 0;
			}
		});
		buttonGroupCasaDecimal.add(rdbtnmntm0);
		rdbtnmntm0.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mnCasasDecimais.add(rdbtnmntm0);

		rdbtnmntm1 = new JRadioButtonMenuItem("1 - 0,0");
		rdbtnmntm1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				casaDecimal = 1;
			}
		});
		rdbtnmntm1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				casaDecimal = 1;
			}
		});
		buttonGroupCasaDecimal.add(rdbtnmntm1);
		rdbtnmntm1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mnCasasDecimais.add(rdbtnmntm1);

		rdbtnmntm2 = new JRadioButtonMenuItem("2 - 0,00");
		rdbtnmntm2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				casaDecimal = 2;
			}
		});
		rdbtnmntm1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				casaDecimal = 2;
			}
		});
		rdbtnmntm2.setSelected(true);
		buttonGroupCasaDecimal.add(rdbtnmntm2);
		rdbtnmntm2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mnCasasDecimais.add(rdbtnmntm2);

		rdbtnmntm3 = new JRadioButtonMenuItem("3 - 0,000");
		rdbtnmntm3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				casaDecimal = 3;
			}
		});
		rdbtnmntm1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				casaDecimal = 3;
			}
		});
		buttonGroupCasaDecimal.add(rdbtnmntm3);
		rdbtnmntm3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mnCasasDecimais.add(rdbtnmntm3);

		rdbtnmntm4 = new JRadioButtonMenuItem("4 - 0,0000");
		rdbtnmntm4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				casaDecimal = 4;
			}
		});
		rdbtnmntm1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				casaDecimal = 4;
			}
		});
		buttonGroupCasaDecimal.add(rdbtnmntm4);
		rdbtnmntm4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mnCasasDecimais.add(rdbtnmntm4);

		rdbtnmntm5 = new JRadioButtonMenuItem("5 - 0,00000");
		rdbtnmntm5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				casaDecimal = 5;
			}
		});
		rdbtnmntm1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				casaDecimal = 5;
			}
		});
		buttonGroupCasaDecimal.add(rdbtnmntm5);
		rdbtnmntm5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mnCasasDecimais.add(rdbtnmntm5);

		rdbtnmntm6 = new JRadioButtonMenuItem("6 - 0,000000");
		rdbtnmntm6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				casaDecimal = 6;
			}
		});
		rdbtnmntm1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				casaDecimal = 6;
			}
		});
		buttonGroupCasaDecimal.add(rdbtnmntm6);
		rdbtnmntm6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mnCasasDecimais.add(rdbtnmntm6);

		mnAjuda = new JMenu("Ajuda???");
		mnAjuda.setFont(new Font("Tahoma", Font.PLAIN, 14));
		menuBar.add(mnAjuda);
		frInputDados.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblClassesLi, txLimiteInferior, lblClassesLs, txLimiteSuperior, lblClassesPosicao, txPosicao, lblClassesPmi, txPMI, lblClassesH, txAltura, textAreaFi, lblInformeFi, scrollPane_1, btnInserirClasses, lblNewLabel, lblContaFi}));
		mnAjuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frames.Ajuda.main(null); // invoca a tela "Ajuda"
			}
		});

	}
}
