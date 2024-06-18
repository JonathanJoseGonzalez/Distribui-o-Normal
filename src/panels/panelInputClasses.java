package panels;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import classes.Calculos;
import frames.mainEstatistica;

public class panelInputClasses extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tableCalculos;
	private JScrollPane scrollPane;
	private DefaultTableModel modelCalculos;

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

	private JLabel lblClassesPosicao;
	private JTextField txPosicao;

	private JLabel lblClassesPmi;
	private JTextField txPMI;

	private JLabel lblAmplitudeH;
	private JTextField txAmplitudeH;

	private JLabel lblClassesLi;
	private JTextField txLimiteInferior;

	private JLabel lblClassesLs;
	private JTextField txLimiteSuperior;

	private JLabel lblInformeFi;
	private JScrollPane scrollPaneTextArea;
	private JTextArea textAreaFi;

	private JLabel lblqntFi;
	private JLabel lblContaFi;

	private JButton btnInserirClasses;
	private JButton btLimparTela;
	private JButton btnDistribuicaoNormal;

	/**
	 * Método para processar as informações de fi fornecidas pelo usuário O código
	 * recupera a string fornecida na JTextArea textAreaFi, eliminando espaços
	 * possíveis que o usuário possa ter inserido e, na sequência, grava os valores
	 * em um Array Dinamico.
	 * 
	 * adaptado de: https://www.baeldung.com/java-csv-file-array
	 */
	public void transformarFi() {
		String inputFi = getTextAreaFi().getText().replace(" ", "").replace("\n", "").replace("\r", "");
		List<List<String>> valoresFi = new ArrayList<>();
		try (Scanner scanner = new Scanner(inputFi)) {
			while (scanner.hasNextLine()) {
				valoresFi.add(gravarFi(scanner.nextLine()));
			}
		}
	}

	/**
	 * Método para processar as informações de fi fornecidas pelo usuário Os valores
	 * obtidos são salvos no Array Dinamico separados por vírgulas, o que irá
	 * indicar a posição deles. O Método também faz a inserção dos dados na tabela
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
		int tamanho = valoresFi.size();
		modelCalculos.setNumRows(tamanho);
		for (int i = 0; i < tamanho; i++) {
			int valorFi = Integer.parseInt(valoresFi.get(i));
			modelCalculos.setValueAt(valorFi, i, 3);
		}
		return valoresFi;
	}

	/**
	 * Método para retornar a quantidade de classes dentro do frInputClasses
	 * 
	 * @return A quantidade de classes obtida, cujo valor está armazenado no
	 *         lblContaFi
	 */
	private int quantidadeClasses() {
		int quantidadeClasses = Integer.parseInt(lblContaFi.getText());
		return quantidadeClasses;
	}

	private Double lInferior() {
		Double li = 0.0;
		String verificarLi = txLimiteInferior.getText().replace(" ", "");
		if (verificarLi.equals(null) || verificarLi.equals("")) {
			li = PMIparaLInferior();
		} else {
			li = Double.parseDouble(verificarLi.replace(",", "."));
		}
		return li;
	}

	private Double lSuperior() {
		Double ls = 0.0;
		String verificarLs = txLimiteSuperior.getText().replace(" ", "");
		if (verificarLs.equals(null) || verificarLs.equals("")) {
			ls = PMIparaLSuperior();
		} else {
			ls = Double.parseDouble(verificarLs.replace(",", "."));
		}
		return ls;
	}

	private Double PMIparaLInferior() {
		Double li = valorPMI() - alturaPMI() / 2;
		li = Calculos.arredondamento(li, mainEstatistica.casaDecimal);
		return li;
	}

	private Double PMIparaLSuperior() {
		Double ls = valorPMI() + alturaPMI() / 2;
		ls = Calculos.arredondamento(ls, mainEstatistica.casaDecimal);
		return ls;
	}

	private Double amplitudeLSmLI() {
		Double h = lSuperior() - lInferior();
		h = Calculos.arredondamento(h, mainEstatistica.casaDecimal);
		txAmplitudeH.setText(h.toString());
		return h;
	}

	private Double classesParaPMI() {
		Double PMI = (lInferior() + lSuperior()) / 2;
		PMI = Calculos.arredondamento(PMI, mainEstatistica.casaDecimal);
		txPMI.setText(PMI.toString());
		return PMI;
	}

	/**
	 * Método para retornar o valor do PMI
	 * 
	 * @return retorna o valor informado pelo usuário em txPMI
	 */
	private Double valorPMI() {
		Double valorPMI = 0.0;
		String verificarPMI = txPMI.getText().replace(" ", "");
		if (verificarPMI.equals(null) || verificarPMI.equals("")) {
			valorPMI = classesParaPMI();
		} else {
			valorPMI = Double.parseDouble(verificarPMI.replace(",", "."));
		}
		return valorPMI;
	}

	/**
	 * Método para retornar a posição do PMI
	 * 
	 * @return retorna o valor informado pelo usuário em txPosicao. O valor é
	 *         retornado com -1, devido ao posicionamento na tabela.
	 */
	private int posicaoPMI() {
		int posicaoPMI = Integer.parseInt(getTxPosicao().getText());
		posicaoPMI -= 1; // já levando em consideração que a posição de um vetor é -1 em relação a
							// contagem padrão
		return posicaoPMI;
	}

	/**
	 * Método para retornar o intervalo do PMI
	 * 
	 * @return retorna o intervalo entre PMIs, informado pelo usuário em txAltura
	 */
	private Double alturaPMI() {
		Double alturaPMI = Double.parseDouble(getTxClassesH().getText().replace(",", "."));
		return alturaPMI;
	}

	/**
	 * Método para cálculo dos valores do PMI Este método realiza a análise da
	 * posição do PMI e calcula, somando ou subtraindo, o valor da altura
	 * correspondente à posição do PMI
	 */
	private void calcularPMI() {
		int qnt = quantidadeClasses();
		Double[] PMIs = new Double[qnt];
		Double[] ali = new Double[qnt];
		Double[] als = new Double[qnt];

		PMIs[posicaoPMI()] = valorPMI();
		ali[posicaoPMI()] = PMIparaLInferior();
		als[posicaoPMI()] = PMIparaLSuperior();

		Double li = PMIparaLInferior();
		Double ls = PMIparaLSuperior();
		Double valor = valorPMI();
		for (int i = posicaoPMI() + 1; i < qnt; i++) {
			valor += alturaPMI();
			li += alturaPMI();
			ls += alturaPMI();

			PMIs[i] = valor;
			ali[i] = li;
			als[i] = ls;

			PMIs[i] = Calculos.arredondamento(PMIs[i], mainEstatistica.casaDecimal);
			ali[i] = Calculos.arredondamento(ali[i], mainEstatistica.casaDecimal);
			als[i] = Calculos.arredondamento(als[i], mainEstatistica.casaDecimal);
		}
		valor = valorPMI();
		li = PMIparaLInferior();
		ls = PMIparaLSuperior();
		for (int i = posicaoPMI() - 1; i >= 0; i--) {
			valor -= alturaPMI();
			li -= alturaPMI();
			ls -= alturaPMI();

			PMIs[i] = valor;
			ali[i] = li;
			als[i] = ls;

			PMIs[i] = Calculos.arredondamento(PMIs[i], mainEstatistica.casaDecimal);
			ali[i] = Calculos.arredondamento(ali[i], mainEstatistica.casaDecimal);
			als[i] = Calculos.arredondamento(als[i], mainEstatistica.casaDecimal);
		}
		for (int i = 0; i < qnt; i++) {
			modelCalculos.setValueAt(ali[i], i, 0);
			modelCalculos.setValueAt(als[i], i, 1);
			modelCalculos.setValueAt(PMIs[i], i, 2);
		}
	}

	private void calcularClasses() {
		int qnt = quantidadeClasses();
		Double[] PMIs = new Double[qnt];
		Double[] ali = new Double[qnt];
		Double[] als = new Double[qnt];

		PMIs[posicaoPMI()] = classesParaPMI();
		ali[posicaoPMI()] = lInferior();
		als[posicaoPMI()] = lSuperior();

		Double li = lInferior();
		Double ls = lSuperior();
		Double valor = classesParaPMI();
		for (int i = posicaoPMI() + 1; i < qnt; i++) {
			valor += amplitudeLSmLI();
			li += amplitudeLSmLI();
			ls += amplitudeLSmLI();

			PMIs[i] = valor;
			ali[i] = li;
			als[i] = ls;

			PMIs[i] = Calculos.arredondamento(PMIs[i], mainEstatistica.casaDecimal);
			ali[i] = Calculos.arredondamento(ali[i], mainEstatistica.casaDecimal);
			als[i] = Calculos.arredondamento(als[i], mainEstatistica.casaDecimal);
		}
		valor = classesParaPMI();
		li = lInferior();
		ls = lSuperior();
		for (int i = posicaoPMI() - 1; i >= 0; i--) {
			valor -= amplitudeLSmLI();
			li -= amplitudeLSmLI();
			ls -= amplitudeLSmLI();

			PMIs[i] = valor;
			ali[i] = li;
			als[i] = ls;

			PMIs[i] = Calculos.arredondamento(PMIs[i], mainEstatistica.casaDecimal);
			ali[i] = Calculos.arredondamento(ali[i], mainEstatistica.casaDecimal);
			als[i] = Calculos.arredondamento(als[i], mainEstatistica.casaDecimal);
		}
		for (int i = 0; i < qnt; i++) {
			modelCalculos.setValueAt(ali[i], i, 0);
			modelCalculos.setValueAt(als[i], i, 1);
			modelCalculos.setValueAt(PMIs[i], i, 2);
		}
	}

	/**
	 * Método para cálculo de N
	 * 
	 * @return Retorna N, que contém a soma dos valores fi
	 */
	public Integer SomaFi() {
		Integer N = 0;

		for (int i = 0; i < modelCalculos.getRowCount(); i++) {
			N += (Integer) modelCalculos.getValueAt(i, 3);
		}
		txSomaFi.setText(N.toString());
		return N;
	}

	/**
	 * Método para cálculo da multiplicação de xi (PMI) por fi Este método busca os
	 * valores de xi e fi que foram inseridos na tabela e também realiza a soma dos
	 * valores obtidos pela multiplicação
	 * 
	 * @return Após localizar e multiplicar os valores de xi e fi, este método
	 *         retorna a soma de todas razões obtidas.
	 */
	public Double XiFi() {
		Double xifi = 0.0;
		Double xi = 0.0;
		int fi = 0;
		Double somaXiFi = 0.0;

		for (int i = 0; i < quantidadeClasses(); i++) {
			xi = (Double) modelCalculos.getValueAt(i, 2);
			fi = (int) modelCalculos.getValueAt(i, 3);
			xifi = xi * fi;
			xifi = Calculos.arredondamento(xifi, mainEstatistica.casaDecimal);
			modelCalculos.setValueAt(xifi, i, 4);
			somaXiFi += xifi;
		}
		somaXiFi = Calculos.arredondamento(somaXiFi, mainEstatistica.casaDecimal);
		txSomaXifi.setText(somaXiFi.toString());
		return somaXiFi;
	}

	/**
	 * Método para calcular a média
	 * 
	 * @return retorna o produto da divisão da soma de xifi por N (soma das
	 *         frequencias)
	 */
	public Double media() {
		Double media = 0.0;
		media = XiFi() / SomaFi();
		media = Calculos.arredondamento(media, mainEstatistica.casaDecimal);
		txXbarra.setText(media.toString());
		return media;
	}

	/**
	 * Método para calcular o produto da frequência pelo quadrado da diferença do
	 * dado pela média
	 * 
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
			xiMediaQuadradoFi = Math.pow(xi - media(), 2) * fi;
			xiMediaQuadradoFi = Calculos.arredondamento(xiMediaQuadradoFi, mainEstatistica.casaDecimal);
			modelCalculos.setValueAt(xiMediaQuadradoFi, i, 5);
			somaxiMediaQuadradoFi += xiMediaQuadradoFi;
		}
		somaxiMediaQuadradoFi = Calculos.arredondamento(somaxiMediaQuadradoFi, mainEstatistica.casaDecimal);
		txSomaXiMediaQuadradoFi.setText(somaxiMediaQuadradoFi.toString());
		return somaxiMediaQuadradoFi;
	}

	/**
	 * Método para cálculo da Variância
	 * 
	 * @return Retorna o resultado da divisão da soma obtida no método
	 *         SomaXiMediaQuadradoFi() pela resultado da subtração da média menos
	 *         um.
	 */
	public Double Variancia() {
		Double variancia = 0.0;
		variancia = SomaXiMediaQuadradoFi() / (SomaFi() - 1);
		variancia = Calculos.arredondamento(variancia, mainEstatistica.casaDecimal);
		txVariancia.setText(variancia.toString());
		return variancia;
	}

	/**
	 * Método para cálculo do Desvio Padrão
	 * 
	 * @return Retorna o valor obtido pelo cálculo da raiz quadrada da Variancia
	 */
	public Double DesvioPadrao() {
		Double desvioPadrao = 0.0;
		desvioPadrao = Math.sqrt(Variancia());
		desvioPadrao = Calculos.arredondamento(desvioPadrao, mainEstatistica.casaDecimal);
		txDesvioPadrao.setText(desvioPadrao.toString());
		return desvioPadrao;
	}

	/**
	 * Método para cálculo do Coeficiente de Variação
	 * 
	 * @return Retorna a porcentagem da divisão do desvioPadrao() pela media()
	 */
	public Double coeficienteVariacao() {
		Double coeficienteVariacao = 0.0;
		coeficienteVariacao = 100 * DesvioPadrao() / media();
		coeficienteVariacao = Calculos.arredondamento(coeficienteVariacao, mainEstatistica.casaDecimal);
		txCoeficienteDeVariacao.setText(coeficienteVariacao.toString() + "%");
		return coeficienteVariacao;
	}

	/**
	 * Método para realizar a inserção de dados e cálculos por meio das informações
	 * fornecidas pelo usuário. Caso os dados sejam insuficientes para realizar
	 * cálculos, o programa irá retornar um aviso ao usuário, solicitando mais
	 * entrada de dados.
	 */
	public void calcular() {
		transformarFi();
		if (quantidadeClasses() < 2) {
			JOptionPane.showMessageDialog(null,
					"Calma, usuário! \nVocê ainda não inseriu dados o suficiente! \nSem nada para calcular por enquanto! \nBora trabalhar!",
					"Atenção!", JOptionPane.WARNING_MESSAGE);
		} else if (((txLimiteInferior.getText().isEmpty() || txLimiteInferior.getText().isBlank()))
				&& ((txLimiteSuperior.getText().isEmpty()) || (txLimiteSuperior.getText().isBlank()))) {
			if (lInferior() > lSuperior()) {
				JOptionPane.showMessageDialog(null,
						"Calma, usuário!" + "\nPor favor, verifique se você inseriu o limites "
							+ "\nInferior (li) e Superior (ls) nos seus devidos lugares!",
							"Mas gente..?", JOptionPane.WARNING_MESSAGE);
			} else {
				calcularPMI();
			}
		} else if (txPMI.getText().isEmpty() || txPMI.getText().isBlank()) {
			calcularClasses();
		} else {
			JOptionPane.showMessageDialog(null,
					"Calma, usuário!" + "\nConfira as informações que você inseriu! "
							+ "\n Deve estar faltando alguma informação..."
							+ "\nOu é algum cálculo que eu ainda não consigo realizar..." + "\nBora trabalhar!",
					"Atenção!", JOptionPane.WARNING_MESSAGE);
		}

		XiFi();
		media();
		SomaXiMediaQuadradoFi();
		Variancia();
		DesvioPadrao();
		coeficienteVariacao();
		ativarDistribuicaoNormal();
	}

	/**
	 * Método para verificação se uma string possui valor de Double
	 * 
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
	 * 
	 * @param linha é a String a ser analisada
	 * @return retorna True se a String possuir valor Integer e False se não
	 *         possuir.
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
	 * Método que realiza a "limpeza" das informações, reiniciando os valores das
	 * JTextField para o valor padrão inicial.
	 */
	public void Limpar() {
		modelCalculos.setRowCount(0);
		ativarDistribuicaoNormal();
		tableCalculos.clearSelection();
		getTextAreaFi().setText("");
		getTxPMI().setText("");
		getTxPosicao().setText("1");
		getTxClassesH().setText("");
		getTxLimiteInferior().setText("");
		getTxLimiteSuperior().setText("");
		txSomaFi.setText("0");
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
	 * Create the panel.
	 */
	public panelInputClasses() {
		this.setLayout(null);
		this.setBackground(new Color(255, 204, 0));
		this.setBounds(new Rectangle(0, 0, 585, 725));

		// JScrollPane
		scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(10, 10, 549, 221);
		this.add(scrollPane);

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
		modelCalculos.setColumnIdentifiers(colunaCalculos);
		tableCalculos.setModel(modelCalculos);
		scrollPane.setViewportView(tableCalculos);

		lblSomaFi = new JLabel("N: ");
		lblSomaFi.setToolTipText("<html>É a soma de [ fi ]!<br>"
				+ "Ou seja, é o número total de informações do conjunto ou amostra!</html>");
		lblSomaFi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSomaFi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSomaFi.setBounds(10, 281, 75, 30);
		this.add(lblSomaFi);

		lblSomaXiFi = new JLabel("∑xifi: ");
		lblSomaXiFi.setToolTipText("Soma do produto de cada dado por sua frequência");
		lblSomaXiFi.setLabelFor(lblSomaXiFi);
		lblSomaXiFi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSomaXiFi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSomaXiFi.setBounds(10, 321, 75, 30);
		this.add(lblSomaXiFi);

		lblXbarra = new JLabel("x̄: ");
		lblXbarra.setToolTipText("x̄, ou x barra, é o resultado da média! É a razão de ∑xifi por N");
		lblXbarra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblXbarra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblXbarra.setBounds(10, 361, 75, 30);
		this.add(lblXbarra);

		lblSomaXiMediaQuadradoFi = new JLabel("∑(xi-x̄)²fi: ");
		lblSomaXiMediaQuadradoFi.setToolTipText(
				"É a somatória do resultado do produto do quadrado da diferença entre o dado e a média pela sua frequência");
		lblSomaXiMediaQuadradoFi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSomaXiMediaQuadradoFi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSomaXiMediaQuadradoFi.setBounds(324, 241, 75, 30);
		this.add(lblSomaXiMediaQuadradoFi);

		lblVariancia = new JLabel("s²: ");
		lblVariancia.setToolTipText("É a variância! Isso significa que é o quadrado da diferença dos desvios!");
		lblVariancia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVariancia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVariancia.setBounds(324, 281, 75, 30);
		this.add(lblVariancia);

		lblDesvioPadrao = new JLabel("s: ");
		lblDesvioPadrao.setToolTipText("É o desvio padrão! √(s²)");
		lblDesvioPadrao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDesvioPadrao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDesvioPadrao.setBounds(324, 321, 75, 30);
		this.add(lblDesvioPadrao);

		lblCoeficienteDeVariacao = new JLabel("C.V.: ");
		lblCoeficienteDeVariacao.setToolTipText("É o coeficiente de variação!");
		lblCoeficienteDeVariacao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCoeficienteDeVariacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCoeficienteDeVariacao.setBounds(324, 361, 75, 30);
		this.add(lblCoeficienteDeVariacao);

		txSomaFi = new JTextField();
		txSomaFi.setToolTipText(lblSomaFi.getToolTipText());
		txSomaFi.setBackground(new Color(255, 204, 153));
		txSomaFi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txSomaFi.setText("0,00");
		txSomaFi.setHorizontalAlignment(SwingConstants.RIGHT);
		txSomaFi.setEditable(false);
		lblSomaFi.setLabelFor(txSomaFi);
		txSomaFi.setColumns(10);
		txSomaFi.setBounds(95, 281, 150, 30);
		this.add(txSomaFi);

		txSomaXifi = new JTextField();
		txSomaXifi.setToolTipText(lblSomaXiFi.getToolTipText());
		txSomaXifi.setBackground(new Color(255, 204, 153));
		txSomaXifi.setText("0,00");
		txSomaXifi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txSomaXifi.setHorizontalAlignment(SwingConstants.RIGHT);
		txSomaXifi.setEditable(false);
		txSomaXifi.setColumns(10);
		txSomaXifi.setBounds(95, 321, 150, 30);
		this.add(txSomaXifi);

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
		this.add(txXbarra);

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
		this.add(txSomaXiMediaQuadradoFi);

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
		this.add(txVariancia);

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
		this.add(txDesvioPadrao);

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
		this.add(txCoeficienteDeVariacao);

		btLimparTela = new JButton("Limpar Tela!");
		btLimparTela.setBounds(383, 521, 176, 80);
		this.add(btLimparTela);
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
		this.add(btnDistribuicaoNormal);
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
		this.add(lblClassesLi);
		lblClassesLi.setToolTipText("É o Limite Inferior da classe!");
		lblClassesLi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblClassesLi.setFont(new Font("Tahoma", Font.PLAIN, 14));

		setTxLimiteInferior(new JTextField());
		getTxLimiteInferior().setBounds(303, 571, 70, 30);
		this.add(getTxLimiteInferior());
		getTxLimiteInferior().setToolTipText("É o Limite Inferior da classe!");
		getTxLimiteInferior().setHorizontalAlignment(SwingConstants.CENTER);
		getTxLimiteInferior().setFont(new Font("Tahoma", Font.PLAIN, 14));
		getTxLimiteInferior().setColumns(10);
		getTxLimiteInferior().setBackground(new Color(255, 255, 204));

		lblClassesLs = new JLabel("ls:");
		lblClassesLs.setBounds(263, 511, 30, 30);
		this.add(lblClassesLs);
		lblClassesLs.setToolTipText("É o Limite Superior da classe!");
		lblClassesLs.setHorizontalAlignment(SwingConstants.RIGHT);
		lblClassesLs.setFont(new Font("Tahoma", Font.PLAIN, 14));

		setTxLimiteSuperior(new JTextField());
		getTxLimiteSuperior().setBounds(303, 511, 70, 30);
		this.add(getTxLimiteSuperior());
		getTxLimiteSuperior().setToolTipText("É o Limite Superior da classe!");
		getTxLimiteSuperior().setHorizontalAlignment(SwingConstants.CENTER);
		getTxLimiteSuperior().setFont(new Font("Tahoma", Font.PLAIN, 14));
		getTxLimiteSuperior().setColumns(10);
		getTxLimiteSuperior().setBackground(new Color(255, 255, 204));

		lblClassesPosicao = new JLabel("Posição:");
		lblClassesPosicao.setBounds(10, 561, 110, 40);
		this.add(lblClassesPosicao);
		lblClassesPosicao.setToolTipText(
				"<html>Informe a posição do PMI ou classe!<br>\r\n*Obrigatório*<br>\r\nLembrete: <br>\r\ndeve ser maior do que 0 e <br>\r\nmenor do que a quantidade total de Classes!</html>");
		lblClassesPosicao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblClassesPosicao.setFont(new Font("Tahoma", Font.PLAIN, 14));

		setTxPosicao(new JTextField());
		getTxPosicao().setBounds(130, 561, 70, 40);
		this.add(getTxPosicao());
		getTxPosicao().setText("1");
		getTxPosicao().setToolTipText(
				"<html>Informe a posição do PMI ou classe!<br>\r\nLembrete: <br>\r\ndeve ser maior do que 0 e <br>\r\nmenor do que a quantidade total de Classes!</html>");
		getTxPosicao().setHorizontalAlignment(SwingConstants.CENTER);
		getTxPosicao().setFont(new Font("Tahoma", Font.PLAIN, 14));
		getTxPosicao().setColumns(10);
		getTxPosicao().setBackground(new Color(255, 255, 204));

		lblClassesPmi = new JLabel("PMI:");
		lblClassesPmi.setBounds(10, 511, 110, 40);
		this.add(lblClassesPmi);
		lblClassesPmi.setToolTipText(
				"<html>\r\nÉ o Ponto Médio da classe!<br>\r\n(li + ls) &divide; 2 ou <br>\r\n(li + ls) &times; 0,5 <br>\r\n</html>");
		lblClassesPmi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblClassesPmi.setFont(new Font("Tahoma", Font.PLAIN, 14));

		setTxPMI(new JTextField());
		getTxPMI().setBounds(130, 512, 70, 40);
		this.add(getTxPMI());
		getTxPMI().setToolTipText(
				"<html>\r\nÉ o Ponto Médio da classe!<br>\r\n(li + ls) &divide; 2 ou <br>\r\n(li + ls) &times; 0,5 <br>\r\n</html>");
		getTxPMI().setHorizontalAlignment(SwingConstants.CENTER);
		getTxPMI().setFont(new Font("Tahoma", Font.PLAIN, 14));
		getTxPMI().setColumns(10);
		getTxPMI().setBackground(new Color(255, 255, 204));

		lblAmplitudeH = new JLabel("h:");
		lblAmplitudeH.setBounds(189, 540, 30, 30);
		this.add(lblAmplitudeH);
		lblAmplitudeH.setToolTipText(
				"<html>Altura da classe: <br>\r\né o intervalo correspondente <br>\r\nentre o limite inferior (li) e o <br>\r\nlimite superior (ls) de uma classe</html>");
		lblAmplitudeH.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmplitudeH.setFont(new Font("Tahoma", Font.PLAIN, 14));

		setTxClassesH(new JTextField());
		getTxClassesH().setBounds(223, 540, 70, 30);
		this.add(getTxClassesH());
		getTxClassesH().setToolTipText(
				"<html>Altura da classe: <br>\r\né o intervalo correspondente <br>\r\nentre o limite inferior (li) e o <br>\r\nlimite superior (ls) de uma classe</html>");
		getTxClassesH().setHorizontalAlignment(SwingConstants.CENTER);
		getTxClassesH().setFont(new Font("Tahoma", Font.PLAIN, 14));
		getTxClassesH().setColumns(10);
		getTxClassesH().setBackground(new Color(255, 255, 204));

		lblInformeFi = new JLabel("Informe aqui os fi:");
		lblInformeFi.setBounds(10, 421, 153, 40);
		this.add(lblInformeFi);
		lblInformeFi.setToolTipText("");
		lblInformeFi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInformeFi.setFont(new Font("Tahoma", Font.PLAIN, 14));

		scrollPaneTextArea = new JScrollPane();
		scrollPaneTextArea.setBounds(173, 421, 200, 80);
		this.add(scrollPaneTextArea);

		setTextAreaFi(new JTextArea());
		getTextAreaFi().setLineWrap(true);
		getTextAreaFi().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String input = getTextAreaFi().getText().replace("\n", "").replace("\r", "");
				int numCommas = input.length() - input.replace(",", "").length();
				lblContaFi.setText("" + (numCommas + 1));
			}
		});
		getTextAreaFi().setFont(new Font("Tahoma", Font.PLAIN, 16));
		getTextAreaFi().setToolTipText(
				"<html>Insira aqui os [ fi ], separados por espaços.<br>\r\nExemplos:<br>\r\n1, 4, 5, 10, 21, 0, 4, 24<br>\r\n3,41,0,26,2,7,29,4,5<br>\r\n<br>\r\nLembrete!:<br>\r\nA quantidade de [ Classes ] ou [ PMIs ] <br>\r\ndevem ser a mesma quantidade de [ fis ]!\r\n</html>");
		getTextAreaFi().setBackground(new Color(255, 255, 204));
		scrollPaneTextArea.setViewportView(getTextAreaFi());

		setBtnInserirClasses(new JButton("Inserir Classes"));
		getBtnInserirClasses().setBounds(383, 421, 176, 80);
		this.add(getBtnInserirClasses());
		getBtnInserirClasses().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcular();
			}
		});
		getBtnInserirClasses().setBackground(new Color(255, 255, 204));
		getBtnInserirClasses().setFont(new Font("Tahoma", Font.PLAIN, 14));

		lblqntFi = new JLabel("Quantidade de fi:");
		lblqntFi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblqntFi.setBounds(10, 461, 110, 40);
		this.add(lblqntFi);

		lblContaFi = new JLabel("0");
		lblContaFi.setToolTipText("É a quantidade de [fi] que você está informando!");
		lblContaFi.setHorizontalAlignment(SwingConstants.CENTER);
		lblContaFi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContaFi.setBounds(123, 461, 40, 40);
		this.add(lblContaFi);

	}

	public JTextArea getTextAreaFi() {
		return textAreaFi;
	}

	public void setTextAreaFi(JTextArea textAreaFi) {
		this.textAreaFi = textAreaFi;
	}

	public JTextField getTxPMI() {
		return txPMI;
	}

	public void setTxPMI(JTextField txPMI) {
		this.txPMI = txPMI;
		txPMI.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				txLimiteInferior.setText("");
				txLimiteSuperior.setText("");
			}
		});
	}

	public JTextField getTxPosicao() {
		return txPosicao;
	}

	public void setTxPosicao(JTextField txPosicao) {
		this.txPosicao = txPosicao;
	}

	public JTextField getTxClassesH() {
		return txAmplitudeH;
	}

	public void setTxClassesH(JTextField txClassesH) {
		this.txAmplitudeH = txClassesH;
		txAmplitudeH.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				txLimiteInferior.setText("");
				txLimiteSuperior.setText("");
			}
		});
	}

	public JTextField getTxLimiteInferior() {
		return txLimiteInferior;
	}

	public void setTxLimiteInferior(JTextField txLimiteInferior) {
		this.txLimiteInferior = txLimiteInferior;
		txLimiteInferior.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				txAmplitudeH.setText("");
				txPMI.setText("");
			}
		});
	}

	public JTextField getTxLimiteSuperior() {
		return txLimiteSuperior;
	}

	public void setTxLimiteSuperior(JTextField txLimiteSuperior) {
		this.txLimiteSuperior = txLimiteSuperior;
		txLimiteSuperior.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				txAmplitudeH.setText("");
				txPMI.setText("");
			}
		});
	}

	public JButton getBtnInserirClasses() {
		return btnInserirClasses;
	}

	public void setBtnInserirClasses(JButton btnInserirClasses) {
		this.btnInserirClasses = btnInserirClasses;
	}

}
