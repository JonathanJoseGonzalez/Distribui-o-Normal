package panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import classes.Calculos;
import frames.mainEstatistica;

public class panelInputDados extends JPanel {

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JTable tableCalculos;
	private DefaultTableModel modelCalculos;
	private JLabel lblXi;
	private JLabel lblFi;
	private JLabel lblSomaFi;
	private JLabel lblSomaXiFi;
	private JLabel lblXbarra;
	private JLabel lblSomaXiMediaQuadradoFi;
	private JLabel lblVariancia;
	private JLabel lblDesvioPadrao;
	private JLabel lblCoeficienteDeVariacao;
	private JTextField txXi;
	private JTextField txFi;
	public static JTextField txSomaFi;
	private JTextField txSomaXifi;
	public static JTextField txXbarra;
	private JTextField txSomaXiMediaQuadradoFi;
	private JTextField txVariancia;
	public static JTextField txDesvioPadrao;
	private JTextField txCoeficienteDeVariacao;
	private JButton btInserir;
	private JButton btCalcular;
	private JButton btCorrigir;
	private JButton btApagar;
	private JButton btLimparTela;
	private JButton btnDistribuicaoNormal;
	private JPanel panel;
	private JLabel lblNewLabel;

	private Double valorXi(String xi) {
		Double valorXi = Double.parseDouble(xi.replace(",","."));
		return valorXi;
	}
	
	private Integer valorFi(String fi) {
		Integer valorFi = Integer.parseInt(fi.replace(",","."));
		return valorFi;
	}
	/**
	 * Método para verificar a quantidade de linhas na tabela
	 * @return retorna a quantidade de linhas preenchidas na tabela
	 */
	private int quantidadeLinhas() {
		int linhas = modelCalculos.getRowCount();
		return linhas;
	}
	/**
	 * Método para cálculo de N
	 * @return Retorna N, que contém a soma dos valores fi
	 */
	public Integer SomaFi() {
		Integer N = 0;

		for (int i = 0; i < quantidadeLinhas(); i++) {
			N += (Integer) modelCalculos.getValueAt(i, 1);
		}
		txSomaFi.setText(N.toString());

		return N;
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
		
		for (int i = 0; i < quantidadeLinhas(); i++) {
			xi = (Double) modelCalculos.getValueAt(i, 0);
			fi = (int) modelCalculos.getValueAt(i, 1);
			xifi =  xi * fi;
			xifi = Calculos.arredondamento(xifi, mainEstatistica.casaDecimal);
			modelCalculos.setValueAt(xifi, i, 2);
			somaXiFi += xifi;
		}
		somaXiFi = Calculos.arredondamento(somaXiFi, mainEstatistica.casaDecimal);
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
		media = Calculos.arredondamento(media, mainEstatistica.casaDecimal);
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
		
		for (int i = 0; i < quantidadeLinhas(); i++) {
			xi = (Double) modelCalculos.getValueAt(i, 0);
			fi = (int) modelCalculos.getValueAt(i, 1);
			xiMediaQuadradoFi =  Math.pow(xi-media(),2) * fi;
			xiMediaQuadradoFi = Calculos.arredondamento(xiMediaQuadradoFi, mainEstatistica.casaDecimal);
			modelCalculos.setValueAt(xiMediaQuadradoFi, i, 3);
			somaxiMediaQuadradoFi += xiMediaQuadradoFi;
		}
		somaxiMediaQuadradoFi = Calculos.arredondamento(somaxiMediaQuadradoFi, mainEstatistica.casaDecimal);
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
		variancia = Calculos.arredondamento(variancia, mainEstatistica.casaDecimal);
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
		desvioPadrao = Calculos.arredondamento(desvioPadrao, mainEstatistica.casaDecimal);
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
		coeficienteVariacao = Calculos.arredondamento(coeficienteVariacao, mainEstatistica.casaDecimal);
		txCoeficienteDeVariacao.setText(coeficienteVariacao.toString() + "%");
		return coeficienteVariacao;
	}
	/**
	 * Método para realizar a inserção de dados e cálculos por meio das informações fornecidas pelo usuário.
	 * Caso os dados sejam insuficientes para realizar cálculos, o programa irá retornar um aviso ao usuário, solicitando mais entrada de dados.
	 */
	public void calcular() {
		if (quantidadeLinhas() < 2) {
			JOptionPane.showMessageDialog(null,
					"Calma, usuário! \nVocê ainda não inseriu dados o suficiente! \nSem nada para calcular por enquanto! \nBora trabalhar!",
					"Atenção!", JOptionPane.WARNING_MESSAGE);
			}else {
				SomaFi();
				XiFi();
				media();
				SomaXiMediaQuadradoFi();
				Variancia();
				DesvioPadrao();
				coeficienteVariacao();
			}
	}

/**
 * Método que realiza a "limpeza" das informações, reiniciando os valores das JTextField para o valor padrão inicial.
 */
	public void Limpar() {
		modelCalculos.setRowCount(0);
		ativarDistribuicaoNormal();
		tableCalculos.clearSelection();
		getTxXi().setText("");
		getTxFi().setText("");
		txSomaFi.setText("0,00");
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
	 * Create the panel.
	 */
	public panelInputDados() {
		this.setLayout(null);
		this.setBackground(new Color(255, 204, 0));
		this.setBounds(new Rectangle(0, 0, 585, 725));

		scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(10, 10, 549, 221);
		this.add(scrollPane);

		tableCalculos = new JTable();
		tableCalculos.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		tableCalculos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tableCalculos.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null,
							"Calma, estatístico! \nVocê ainda não inseriu nenhum dado! \nSem nada para selecionar por enquanto! \nBora trabalhar!",
							"Atenção!", JOptionPane.WARNING_MESSAGE);
				} else {
					int i = tableCalculos.getSelectedRow();
					getTxXi().setText(modelCalculos.getValueAt(i, 0).toString());
					getTxFi().setText(modelCalculos.getValueAt(i, 1).toString());
				}
			}
		});
		tableCalculos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCalculos.setGridColor(Color.DARK_GRAY);
		tableCalculos.setFillsViewportHeight(true);
		tableCalculos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableCalculos.setForeground(Color.DARK_GRAY);
		tableCalculos.setBackground(new Color(255, 204, 153));
		tableCalculos.setRowMargin(2);
		tableCalculos.setRowHeight(30);
		modelCalculos = new DefaultTableModel();
		Object[] colunaCalculos = { "xi", "fi", "xifi", "(xi-x\u0304)\u00B2fi" };
		@SuppressWarnings("removal")
		Object[] linhaCalculos = { new Double (0.0), new Integer (0), new Double (0.0), new Double (0.0) };
		modelCalculos.setColumnIdentifiers(colunaCalculos);
		tableCalculos.setModel(modelCalculos);
		scrollPane.setViewportView(tableCalculos);

		// JLabel

		lblXi = new JLabel("xi: ");
		lblXi.setToolTipText("Dados ou informações numéricas");
		lblXi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblXi.setLabelFor(getTxXi());
		lblXi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblXi.setBounds(10, 434, 75, 40);
		this.add(lblXi);

		lblFi = new JLabel("fi: ");
		lblFi.setToolTipText("Frequência individual absoluta");
		lblFi.setLabelFor(getTxFi());
		lblFi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFi.setBounds(10, 484, 75, 40);
		this.add(lblFi);
		
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
		lblXbarra.setLabelFor(txXbarra);
		lblXbarra.setToolTipText("x̄, ou x barra, é o resultado da média! É a razão de ∑xifi por N");
		lblXbarra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblXbarra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblXbarra.setBounds(10, 361, 75, 30);
		this.add(lblXbarra);

		lblSomaXiMediaQuadradoFi = new JLabel("∑(xi-x̄)²fi: ");
		lblSomaXiMediaQuadradoFi.setToolTipText("É a somatória do resultado do produto do quadrado da diferença entre o dado e a média pela sua frequência");
		lblSomaXiMediaQuadradoFi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSomaXiMediaQuadradoFi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSomaXiMediaQuadradoFi.setBounds(324, 241, 75, 30);
		this.add(lblSomaXiMediaQuadradoFi);

		lblVariancia = new JLabel("s²: ");
		lblVariancia.setToolTipText("É a variância! Isso significa que é o quadrado da diferença dos desvios!");
		lblVariancia.setLabelFor(txVariancia);
		lblVariancia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVariancia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVariancia.setBounds(324, 281, 75, 30);
		this.add(lblVariancia);

		lblDesvioPadrao = new JLabel("s: ");
		lblDesvioPadrao.setToolTipText("É o desvio padrão! √(s²)");
		lblDesvioPadrao.setLabelFor(txDesvioPadrao);
		lblDesvioPadrao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDesvioPadrao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDesvioPadrao.setBounds(324, 321, 75, 30);
		this.add(lblDesvioPadrao);

		lblCoeficienteDeVariacao = new JLabel("C.V.: ");
		lblCoeficienteDeVariacao.setToolTipText("É o coeficiente de variação!");
		lblCoeficienteDeVariacao.setLabelFor(txCoeficienteDeVariacao);
		lblCoeficienteDeVariacao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCoeficienteDeVariacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCoeficienteDeVariacao.setBounds(324, 361, 75, 30);
		this.add(lblCoeficienteDeVariacao);

		// JTextField

		setTxXi(new JTextField());
		getTxXi().setToolTipText(lblXi.getToolTipText());
		getTxXi().setBackground(new Color(255, 255, 204));
		getTxXi().addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				getTxXi().selectAll();
			}
		});
		getTxXi().setFont(new Font("Tahoma", Font.PLAIN, 14));
		getTxXi().setHorizontalAlignment(SwingConstants.CENTER);
		getTxXi().setText("0");
		getTxXi().setBounds(89, 437, 150, 40);
		this.add(getTxXi());
		getTxXi().setColumns(10);

		setTxFi(new JTextField());
		getTxFi().setToolTipText(lblFi.getToolTipText());
		getTxFi().setBackground(new Color(255, 255, 204));
		getTxFi().addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				getTxFi().selectAll();
			}
		});
		getTxFi().setHorizontalAlignment(SwingConstants.CENTER);
		getTxFi().setFont(new Font("Tahoma", Font.PLAIN, 14));
		getTxFi().setText("0");
		getTxFi().setBounds(89, 484, 150, 40);
		this.add(getTxFi());
		getTxFi().setColumns(10);

		txSomaFi = new JTextField();
		txSomaFi.setToolTipText("<html>É a soma de [ fi ]!<br>Ou seja, é o número <b>total de informações</b> do conjunto ou amostra!</html>");
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
		txCoeficienteDeVariacao.setToolTipText(lblCoeficienteDeVariacao.getToolTipText());
		txCoeficienteDeVariacao.setBackground(new Color(255, 204, 153));
		txCoeficienteDeVariacao.setText("0,00");
		txCoeficienteDeVariacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txCoeficienteDeVariacao.setHorizontalAlignment(SwingConstants.RIGHT);
		txCoeficienteDeVariacao.setEditable(false);
		txCoeficienteDeVariacao.setColumns(10);
		txCoeficienteDeVariacao.setBounds(409, 361, 150, 30);
		this.add(txCoeficienteDeVariacao);

		//JButton
		
		setBtInserir(new JButton("Inserir dados!"));
		getBtInserir().setBackground(new Color(255, 255, 204));
		getBtInserir().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String xi = getTxXi().getText().replaceAll(",", ".").replaceAll(" ","");
				String fi = getTxFi().getText().replaceAll(",", ".").replaceAll(" ","");
				
				if (xi.equals(null) || fi.equals(null)) {
					JOptionPane.showMessageDialog(null,
							"Não esqueceu de nada não? \nVerifique se você digitou o [xi] e o [fi]!", "OPA!",
							JOptionPane.WARNING_MESSAGE);
				} else if ((ehDouble(xi) == false) || (ehInteger(fi) == false)) {
					JOptionPane.showMessageDialog(null,
							"Perae, o que houve??? \nRespira aí e: \nConfirma se você informou [ VALORES NUMÉRICOS ], \ne não letras (ou espaços), para valores de [xi] ou [fi]!",
							"Tá testando o DEV?!", JOptionPane.WARNING_MESSAGE);
				} else if (Double.parseDouble(getTxFi().getText()) <= 0.0) {
					JOptionPane.showMessageDialog(null,
							"<html>Mas existe frequência nula ou negativa? <br>"
							+ "Você tentou informar um valor para [fi]: "
							+ "<ul>"
							+ "<li>ou igual a zero;</li>"
							+ "<li>ou menor do que zero.</li>"
							+ "</ul> </hmtl>", "Eita!",
							JOptionPane.WARNING_MESSAGE);
				} else {
					linhaCalculos[0] = valorXi(xi);
					linhaCalculos[1] = valorFi(fi);
					
					modelCalculos.addRow(linhaCalculos);

					getTxXi().setText("");
					getTxFi().setText("");
					getTxXi().grabFocus();
				}
			}
		});
		getBtInserir().setFont(new Font("Tahoma", Font.PLAIN, 14));
		getBtInserir().setBounds(249, 434, 150, 90);
		this.add(getBtInserir());

		btCalcular = new JButton("Calcular!");
		btCalcular.setBackground(new Color(255, 204, 153));
		btCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (quantidadeLinhas() == 0) {
					JOptionPane.showMessageDialog(null, "Calma, jovem: \nVocê já informou algum conjunto de dados?",
							"Tá testando o DEV?!", JOptionPane.WARNING_MESSAGE);
				} else {
					calcular();
					ativarDistribuicaoNormal();
				}
			}
		});
		btCalcular.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btCalcular.setBounds(409, 434, 150, 90);
		this.add(btCalcular);

		btCorrigir = new JButton("Corrigir!");
		btCorrigir.setBackground(new Color(255, 204, 153));
		btCorrigir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String xi = getTxXi().getText().replaceAll(",", ".").replaceAll(" ","");
				String fi = getTxFi().getText().replaceAll(",", ".").replaceAll(" ","");
				
				int i = tableCalculos.getSelectedRow();
				if (i < 0) {
					JOptionPane.showMessageDialog(null,
							"Viu... \nPra poder corrigir uma entrada de dados: \nVocê precisa, PRIMEIRO, SELECIONAR a linha com os dados a serem corrigidos!",
							"ATENÇÃO, cidadão!", JOptionPane.WARNING_MESSAGE);
				} else if ((ehDouble(getTxXi().getText()) == false) || (ehInteger(getTxFi().getText()) == false)) {
					JOptionPane.showMessageDialog(null,
							"Perae, o que houve??? \nRespira aí e: \nConfirma se você informou [ VALORES NUMÉRICOS ], \ne não letras (ou espaços), para valores de [xi] ou [fi]!",
							"Tá testando o DEV?!", JOptionPane.WARNING_MESSAGE);
				} else if (xi.equals("") || fi.equals("")) {
					JOptionPane.showMessageDialog(null,
							"Não esqueceu de nada não? \nVerifique se você digitou o [xi] e o [fi]!", "OPA!",
							JOptionPane.WARNING_MESSAGE);
				} else if (Double.parseDouble(fi) <= 0.0) {
					JOptionPane.showMessageDialog(null,
							"Mas existe frequência negativa, produção? \nVocê tentou alterar uma informação e informou uma [fi] negativa?",
							"Eita!", JOptionPane.WARNING_MESSAGE);
				} else {
					Object[] opcoes = { "OK", "Cancelar" };
					int confirmar = JOptionPane.showOptionDialog(null,
							"Atenção: \nEssa ação irá ALTERAR os dados da linha selecionada! \nVocê tem certeza? \n[OK] para prosseguir!",
							"ATENÇÃO!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opcoes,
							opcoes[0]);
					if (confirmar == 0) {
						modelCalculos.setValueAt(valorXi(xi), i, 0);
						modelCalculos.setValueAt(valorFi(fi), i, 1);

						calcular();
						getTxXi().setText("");
						getTxFi().setText("");

						tableCalculos.clearSelection();

						JOptionPane.showMessageDialog(null, "Informações alteradas com sucesso!", "FEITO!",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btCorrigir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btCorrigir.setBounds(89, 534, 150, 40);
		this.add(btCorrigir);

		btApagar = new JButton("Apagar Linha!");
		btApagar.setBackground(new Color(255, 204, 153));
		btApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = tableCalculos.getSelectedRow();
				if (i < 0) {
					JOptionPane.showMessageDialog(null,
							"Se você quiser apagar uma linha...\nVocê precisa SELECIONAR uma LINHA antes!", "Hey!",
							JOptionPane.WARNING_MESSAGE);
				} else {
					Object[] opcoes = { "SIM!", "NÃO!" };
					int confirmar = JOptionPane.showOptionDialog(null,
							"Você vai mesmo excluir uma linha? \n[SIM!] para confirmar!", "Perae!",
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opcoes, opcoes[0]);
					if (confirmar == 0) {
						modelCalculos.removeRow(i);
						JOptionPane.showMessageDialog(null, "Informações eliminadas com sucesso!", "FEITO!",
								JOptionPane.INFORMATION_MESSAGE);
						getTxXi().setText("");
						getTxFi().setText("");
						tableCalculos.clearSelection();
					}
				}
				ativarDistribuicaoNormal();
			}
		});
		btApagar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btApagar.setBounds(249, 533, 150, 40);
		this.add(btApagar);

		btLimparTela = new JButton("Limpar Tela!");
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
				ativarDistribuicaoNormal();
			}
		});
		btLimparTela.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btLimparTela.setBounds(409, 534, 150, 40);
		this.add(btLimparTela);
		
		btnDistribuicaoNormal = new JButton("Distribuição Normal");
		btnDistribuicaoNormal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDistribuicaoNormal.setBackground(new Color(255, 204, 153));
		btnDistribuicaoNormal.setEnabled(false);
		btnDistribuicaoNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frames.distribuicaoNormal.main(null); // Abre a tela "distribuicaoNormal"
			}
		});
		btnDistribuicaoNormal.setBounds(89, 580, 470, 40);
		this.add(btnDistribuicaoNormal);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 204, 153));
		panel.setBounds(10, 401, 395, 128);
		this.add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("Inserção de dados: informe aqui os pares ordenados para realizar os cálculos. ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(0, 0, 395, 23);
		panel.add(lblNewLabel);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{getTxXi(), getTxFi(), getBtInserir()}));
	
	}

	public JTextField getTxFi() {
		return txFi;
	}

	public void setTxFi(JTextField txFi) {
		this.txFi = txFi;
	}

	public JButton getBtInserir() {
		return btInserir;
	}

	public void setBtInserir(JButton btInserir) {
		this.btInserir = btInserir;
	}

	public JTextField getTxXi() {
		return txXi;
	}

	public void setTxXi(JTextField txXi) {
		this.txXi = txXi;
	}
}
