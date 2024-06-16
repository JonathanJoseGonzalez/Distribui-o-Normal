package frames;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import classes.Calculos;

public class inputDados {

	private JFrame frInputDados;

	private JTable tableCalculos;
	
	private JLabel lblXi;
	private JLabel lblFi;
	private JLabel lblSomaXi;
	private JLabel lblSomaFi;
	private JLabel lblSomaXiFi;
	private JLabel lblXbarra;
	private JLabel lblSomaXiMediaQuadradoFi;
	private JLabel lblVariancia;
	private JLabel lblDesvioPadrao;
	private JLabel lblCoeficienteDeVariacao;

	private JTextField txXi;
	private JTextField txFi;
	private JTextField txSomaXi;
	public static JTextField txSomaFi;
	private JTextField txSomaXifi;
	public static JTextField txXbarra;
	private JTextField txSomaXiMediaQuadradoFi;
	private JTextField txVariancia;
	public static JTextField txDesvioPadrao;
	private JTextField txCoeficienteDeVariacao;

	private JButton btCalcular;
	private JButton btCorrigir;
	private JButton btApagar;
	private JButton btLimparTela;
	private JButton btInserir;
	
	DefaultTableModel modelCalculos;

	Double xi = 0.0;
	Double fi = 0.0;
	Double N = 0.0;
	Double somaXi = 0.0;
	Double xBarra = 0.0;
	Double somaXiFi = 0.0;
	Double somaXiMediaQuadradoFi = 0.0;
	Double variancia = 0.0;
	Double desvioPadrao = 0.0;
	Double coeficienteDeVariacao = 0.0;

	private JMenuBar menuBar;
	private JButton btnDistribuicaoNormal;
	private JPanel panel;
	private JLabel lblNewLabel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButtonMenuItem rdbtnmntm1;
	private JRadioButtonMenuItem rdbtnmntm2;
	private JRadioButtonMenuItem rdbtnmntm3;
	private JRadioButtonMenuItem rdbtnmntm4;
	private JRadioButtonMenuItem rdbtnmntm5;
	private JRadioButtonMenuItem rdbtnmntm6;
	private JRadioButtonMenuItem rdbtnmntm0;

	int casaDecimal = 2;	

	
	public static boolean ehNumero(String linha) {
		try {
			Double.parseDouble(linha.replace(',', '.'));
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public Double inserirXi(String valorXi) {
		String alterarXi = valorXi.replace(',', '.'); // Substituir a vírgula pelo ponto
		xi = Double.parseDouble(alterarXi);
		return (xi);
	}

	public Double inserirFi(String valorFi) {
		String alterarFi = valorFi.replace(',', '.'); // Substituir a vírgula pelo ponto
		fi = Double.parseDouble(alterarFi);
		return (fi);
	}

	public Double somaXi() {
		Double somaXi = 0.0;

		for (int i = 0; i < modelCalculos.getRowCount(); i++) {
			somaXi += Double.parseDouble(modelCalculos.getValueAt(i, 0).toString());
		}

		return somaXi;
	}

	public Double somaFi() {
		Double somaFi = 0.0;

		for (int i = 0; i < modelCalculos.getRowCount(); i++) {
			somaFi += ((Double) modelCalculos.getValueAt(i, 1));
		}

		return somaFi;
	}

	public Double somaXiFi() {
		Double somaXiFi = 0.0;

		for (int i = 0; i < modelCalculos.getRowCount(); i++) {
			somaXiFi += ((Double) modelCalculos.getValueAt(i, 2));
		}

		return somaXiFi;
	}

	public Double media() {
		Double media = 0.0;
		Calculos.arredondamento(media = somaXiFi()/somaFi(), casaDecimal);
		return media;
	}

	public Double somaXiMediaQuadradoFi() {
		Double somaXiMediaQuadradoFi = 0.0;

		for (int i = 0; i < modelCalculos.getRowCount(); i++) {
			somaXiMediaQuadradoFi += Double.parseDouble(modelCalculos.getValueAt(i, 3).toString().replace(',', '.'));
		}

		return somaXiMediaQuadradoFi;
	}

	public Double xiMediaQuadradoFi(double xi, double fi) {
		Double xiMediaQuadradoFi = 0.0;
		Double media = Double.parseDouble(txXbarra.getText().replace(',', '.'));
		Calculos.arredondamento((xiMediaQuadradoFi = Math.pow((xi - media), 2) * fi), casaDecimal);

		return xiMediaQuadradoFi;
	}

	public void Limpar() {
		modelCalculos.setRowCount(0);
		atualizarCalculos();
		ativarDistribuicaoNormal();
		txXi.setText("");
		txFi.setText("");
		tableCalculos.clearSelection();
		btInserir.setEnabled(true);
		btCorrigir.setEnabled(true);
		btApagar.setEnabled(true);

		txXbarra.setText("0,00");
		txSomaXiMediaQuadradoFi.setText("0,00");
		txVariancia.setText("0,00");
		txDesvioPadrao.setText("0,00");
		txCoeficienteDeVariacao.setText("0,00 %");
	}
	
	public void atualizarCalculos() {
		somaXi();
		somaFi();
		somaXiFi();

		txSomaFi.setText(Calculos.arredondamento(somaFi(), casaDecimal).toString());
		txSomaXi.setText(Calculos.arredondamento(somaXi(), casaDecimal).toString());
		txSomaXifi.setText(Calculos.arredondamento(somaXiFi(), casaDecimal).toString());
		
	}
	/**
	 * Método que mantém o botão btnDistribuicaoNormal desativado até que a tabela tableCalculostenha tenha ao menos 2 valores inseridos.
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
					inputDados window = new inputDados();

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
	public inputDados() {
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
		frInputDados.setBounds(new Rectangle(0, 0, 585, 690));
		frInputDados.setLocationRelativeTo(null);
		frInputDados.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frInputDados.getContentPane().setLayout(null);

		URL iconURL = getClass().getResource("/img/iconJCS.png");
		ImageIcon icon = new ImageIcon(iconURL);
		frInputDados.setIconImage(icon.getImage());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(10, 10, 549, 221);
		frInputDados.getContentPane().add(scrollPane);

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
					txXi.setText(modelCalculos.getValueAt(i, 0).toString());
					txFi.setText(modelCalculos.getValueAt(i, 1).toString());
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
		Double[] linhaCalculos = new Double[4];
		modelCalculos.setColumnIdentifiers(colunaCalculos);
		tableCalculos.setModel(modelCalculos);

		/*
		 * calculos.setModel(new DefaultTableModel( new Object[][] { }, new String[] {
		 * "xi", "fi", "xifi", "(xi-x\u0304)\u00B2fi" } ));
		 */
		scrollPane.setViewportView(tableCalculos);

		// JLabel

		lblXi = new JLabel("xi: ");
		lblXi.setToolTipText("Dados ou informações numéricas");
		lblXi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblXi.setLabelFor(txXi);
		lblXi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblXi.setBounds(10, 434, 75, 40);
		frInputDados.getContentPane().add(lblXi);

		lblFi = new JLabel("fi: ");
		lblFi.setToolTipText("Frequência individual absoluta");
		lblFi.setLabelFor(txFi);
		lblFi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFi.setBounds(10, 484, 75, 40);
		frInputDados.getContentPane().add(lblFi);
		
		lblSomaXi = new JLabel("∑xi: ");
		lblSomaXi.setToolTipText("Soma dos dados ou informações numéricas");
		lblSomaXi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSomaXi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSomaXi.setBounds(10, 241, 75, 30);
		frInputDados.getContentPane().add(lblSomaXi);
		
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
		lblXbarra.setLabelFor(txXbarra);
		lblXbarra.setToolTipText("x̄, ou x barra, é o resultado da média! É a razão de ∑xifi por N");
		lblXbarra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblXbarra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblXbarra.setBounds(10, 361, 75, 30);
		frInputDados.getContentPane().add(lblXbarra);

		lblSomaXiMediaQuadradoFi = new JLabel("∑(xi-x̄)²fi: ");
		lblSomaXiMediaQuadradoFi.setToolTipText("É a somatória do resultado do produto do quadrado da diferença entre o dado e a média pela sua frequência");
		lblSomaXiMediaQuadradoFi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSomaXiMediaQuadradoFi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSomaXiMediaQuadradoFi.setBounds(324, 241, 75, 30);
		frInputDados.getContentPane().add(lblSomaXiMediaQuadradoFi);

		lblVariancia = new JLabel("s²: ");
		lblVariancia.setToolTipText("É a variância! Isso significa que é o quadrado da diferença dos desvios!");
		lblVariancia.setLabelFor(txVariancia);
		lblVariancia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVariancia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVariancia.setBounds(324, 281, 75, 30);
		frInputDados.getContentPane().add(lblVariancia);

		lblDesvioPadrao = new JLabel("s: ");
		lblDesvioPadrao.setToolTipText("É o desvio padrão! √(s²)");
		lblDesvioPadrao.setLabelFor(txDesvioPadrao);
		lblDesvioPadrao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDesvioPadrao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDesvioPadrao.setBounds(324, 321, 75, 30);
		frInputDados.getContentPane().add(lblDesvioPadrao);

		lblCoeficienteDeVariacao = new JLabel("C.V.: ");
		lblCoeficienteDeVariacao.setToolTipText("É o coeficiente de variação!");
		lblCoeficienteDeVariacao.setLabelFor(txCoeficienteDeVariacao);
		lblCoeficienteDeVariacao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCoeficienteDeVariacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCoeficienteDeVariacao.setBounds(324, 361, 75, 30);
		frInputDados.getContentPane().add(lblCoeficienteDeVariacao);

		// JTextField

		txXi = new JTextField();
		txXi.setToolTipText(lblXi.getToolTipText());
		txXi.setBackground(new Color(255, 255, 204));
		txXi.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				txXi.selectAll();
			}
		});
		txXi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txXi.setHorizontalAlignment(SwingConstants.CENTER);
		txXi.setText("0");
		txXi.setBounds(89, 437, 150, 40);
		frInputDados.getContentPane().add(txXi);
		txXi.setColumns(10);

		txFi = new JTextField();
		txFi.setToolTipText(lblFi.getToolTipText());
		txFi.setBackground(new Color(255, 255, 204));
		txFi.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txFi.selectAll();
			}
		});
		txFi.setHorizontalAlignment(SwingConstants.CENTER);
		txFi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txFi.setText("0");
		txFi.setBounds(89, 484, 150, 40);
		frInputDados.getContentPane().add(txFi);
		txFi.setColumns(10);

		txSomaXi = new JTextField();
		txSomaXi.setToolTipText(lblSomaXi.getToolTipText());
		txSomaXi.setBackground(new Color(255, 204, 153));
		txSomaXi.setText("0,00");
		txSomaXi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSomaXi.setLabelFor(txSomaXi);
		txSomaXi.setHorizontalAlignment(SwingConstants.RIGHT);
		txSomaXi.setEditable(false);
		txSomaXi.setColumns(10);
		txSomaXi.setBounds(95, 241, 150, 30);
		frInputDados.getContentPane().add(txSomaXi);

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
		txCoeficienteDeVariacao.setToolTipText(lblCoeficienteDeVariacao.getToolTipText());
		txCoeficienteDeVariacao.setBackground(new Color(255, 204, 153));
		txCoeficienteDeVariacao.setText("0,00");
		txCoeficienteDeVariacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txCoeficienteDeVariacao.setHorizontalAlignment(SwingConstants.RIGHT);
		txCoeficienteDeVariacao.setEditable(false);
		txCoeficienteDeVariacao.setColumns(10);
		txCoeficienteDeVariacao.setBounds(409, 361, 150, 30);
		frInputDados.getContentPane().add(txCoeficienteDeVariacao);

		//JButton
		
		btInserir = new JButton("Inserir dados!");
		btInserir.setBackground(new Color(255, 255, 204));
		btInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(txXi.getText());
				System.out.println(txFi.getText());
				
				if (txXi.getText().equals(null) || txFi.getText().equals(null)) {
					JOptionPane.showMessageDialog(null,
							"Não esqueceu de nada não? \nVerifique se você digitou o [xi] e o [fi]!", "OPA!",
							JOptionPane.WARNING_MESSAGE);
				} else if ((ehNumero(txXi.getText()) == false) || (ehNumero(txFi.getText()) == false)) {
					JOptionPane.showMessageDialog(null,
							"Perae, o que houve??? \nRespira aí e: \nConfirma se você informou [ VALORES NUMÉRICOS ], \ne não letras (ou espaços), para valores de [xi] ou [fi]!",
							"Tá testando o DEV?!", JOptionPane.WARNING_MESSAGE);
				} else if (inserirFi(txFi.getText()) <= 0.0) {
					JOptionPane.showMessageDialog(null,
							"<html>Mas existe frequência nula ou negativa? <br>"
							+ "Você tentou informar um valor para [fi]: "
							+ "<ul>"
							+ "<li>ou igual a zero;</li>"
							+ "<li>ou menor do que zero.</li>"
							+ "</ul> </hmtl>", "Eita!",
							JOptionPane.WARNING_MESSAGE);
				} else {
					
					inserirXi(txXi.getText());
					inserirFi(txFi.getText());

					linhaCalculos[0] = xi;

					linhaCalculos[1] = fi;

					linhaCalculos[2] = xi * fi;

					modelCalculos.addRow(linhaCalculos);
					atualizarCalculos();					

					txXi.setText("");
					txFi.setText("");
					txXi.grabFocus();
				}
			}
		});

		btInserir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btInserir.setBounds(249, 434, 150, 90);
		frInputDados.getContentPane().add(btInserir);

		btCalcular = new JButton("Calcular!");
		btCalcular.setBackground(new Color(255, 204, 153));
		btCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableCalculos.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "Calma, jovem: \nVocê já informou algum conjunto de dados?",
							"Tá testando o DEV?!", JOptionPane.WARNING_MESSAGE);
				} else {
					media();
					txXbarra.setText(String.format("%.2f", media()));

					for (int i = 0; i < modelCalculos.getRowCount(); i++) {
						xi = (Double) (modelCalculos.getValueAt(i, 0));
						fi = (Double) (modelCalculos.getValueAt(i, 1));
						double res = xiMediaQuadradoFi(xi, fi);
						modelCalculos.setValueAt(Calculos.arredondamento(res, casaDecimal), i, 3);
					}

					somaXiMediaQuadradoFi();
					txSomaXiMediaQuadradoFi.setText(Calculos.arredondamento(somaXiMediaQuadradoFi(), casaDecimal).toString());

					variancia = (Double.parseDouble(txSomaXiMediaQuadradoFi.getText().replace(',', '.')))
							/ (somaFi() - 1);
					txVariancia.setText(Calculos.arredondamento(variancia, casaDecimal).toString());

					desvioPadrao = Math.sqrt(Double.parseDouble(txVariancia.getText().replace(',', '.')));
					txDesvioPadrao.setText(Calculos.arredondamento(desvioPadrao, casaDecimal).toString());

					coeficienteDeVariacao = 100 * Double.parseDouble(txDesvioPadrao.getText().replace(',', '.'))
							/ media();
					txCoeficienteDeVariacao.setText(Calculos.arredondamento(coeficienteDeVariacao, casaDecimal).toString());
					ativarDistribuicaoNormal();
				}
			}
		});
		btCalcular.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btCalcular.setBounds(409, 434, 150, 90);
		frInputDados.getContentPane().add(btCalcular);

		btCorrigir = new JButton("Corrigir!");
		btCorrigir.setBackground(new Color(255, 204, 153));
		btCorrigir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = tableCalculos.getSelectedRow();
				if (i < 0) {
					JOptionPane.showMessageDialog(null,
							"Viu... \nPra poder corrigir uma entrada de dados: \nVocê precisa, PRIMEIRO, SELECIONAR a linha com os dados a serem corrigidos!",
							"ATENÇÃO, cidadão!", JOptionPane.WARNING_MESSAGE);
				} else if ((ehNumero(txXi.getText()) == false) || (ehNumero(txFi.getText()) == false)) {
					JOptionPane.showMessageDialog(null,
							"Perae, o que houve??? \nRespira aí e: \nConfirma se você informou [ VALORES NUMÉRICOS ], \ne não letras (ou espaços), para valores de [xi] ou [fi]!",
							"Tá testando o DEV?!", JOptionPane.WARNING_MESSAGE);
				} else if (txXi.getText().equals("") || txFi.getText().equals("")) {
					JOptionPane.showMessageDialog(null,
							"Não esqueceu de nada não? \nVerifique se você digitou o [xi] e o [fi]!", "OPA!",
							JOptionPane.WARNING_MESSAGE);
				} else if (Double.parseDouble(txFi.getText().replace(',', '.')) < 0.0) {
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
						xi = inserirXi(txXi.getText());
						fi = inserirFi(txFi.getText());

						modelCalculos.setValueAt(xi, i, 0);
						modelCalculos.setValueAt(fi, i, 1);
						modelCalculos.setValueAt(xi * fi, i, 2);

						atualizarCalculos();
						txXi.setText("");
						txFi.setText("");

						tableCalculos.clearSelection();

						JOptionPane.showMessageDialog(null, "Informações alteradas com sucesso!", "FEITO!",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btCorrigir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btCorrigir.setBounds(89, 534, 150, 40);
		frInputDados.getContentPane().add(btCorrigir);

		btApagar = new JButton("Apagar Linha!");
		btApagar.setBackground(new Color(255, 204, 153));
		btApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = tableCalculos.getSelectedRow();
				if (i < 0) {
					JOptionPane.showMessageDialog(null,
							"Se você quiser apagar uma linha...\nVocê precisa SELECIONAR uma LINHA antes!", "Hey!",
							JOptionPane.WARNING_MESSAGE);
					;
				} else {
					Object[] opcoes = { "SIM!", "NÃO!" };
					int confirmar = JOptionPane.showOptionDialog(null,
							"Você vai mesmo excluir uma linha? \n[SIM!] para confirmar!", "Perae!",
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opcoes, opcoes[0]);
					if (confirmar == 0) {
						modelCalculos.removeRow(i);
						JOptionPane.showMessageDialog(null, "Informações eliminadas com sucesso!", "FEITO!",
								JOptionPane.INFORMATION_MESSAGE);
						atualizarCalculos();
						txXi.setText("");
						txFi.setText("");
						tableCalculos.clearSelection();
						
						ativarDistribuicaoNormal();

					}
				}
			}
		});
		btApagar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btApagar.setBounds(249, 533, 150, 40);
		frInputDados.getContentPane().add(btApagar);

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
			}
		});
		btLimparTela.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btLimparTela.setBounds(409, 534, 150, 40);
		frInputDados.getContentPane().add(btLimparTela);
		
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
		frInputDados.getContentPane().add(btnDistribuicaoNormal);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 204, 153));
		panel.setBounds(10, 401, 395, 128);
		frInputDados.getContentPane().add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("Inserção de dados: informe aqui os pares ordenados para realizar os cálculos. ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 10, 375, 13);
		panel.add(lblNewLabel);
		frInputDados.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txXi, txFi, btInserir}));

		menuBar = new JMenuBar();
		frInputDados.setJMenuBar(menuBar);

		JMenu mnInsercaoDeDados = new JMenu("Modo de inserção de dados");
		mnInsercaoDeDados.setFont(new Font("Tahoma", Font.PLAIN, 14));
		menuBar.add(mnInsercaoDeDados);

		JRadioButtonMenuItem rdbtnmntmXi = new JRadioButtonMenuItem("xi");
		rdbtnmntmXi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnmntmXi.setSelected(true);
		mnInsercaoDeDados.add(rdbtnmntmXi);

		JRadioButtonMenuItem rdbtnmntmClasses = new JRadioButtonMenuItem("Classes");
		rdbtnmntmClasses.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mnInsercaoDeDados.add(rdbtnmntmClasses);
		
		JMenu mnCasasDecimais = new JMenu("Casas Decimais");
		mnCasasDecimais.setFont(new Font("Tahoma", Font.PLAIN, 14));
		menuBar.add(mnCasasDecimais);
		
		rdbtnmntm0 = new JRadioButtonMenuItem("0 - 0");
		rdbtnmntm0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				casaDecimal = 0;
			}
		});
		buttonGroup.add(rdbtnmntm0);
		rdbtnmntm0.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mnCasasDecimais.add(rdbtnmntm0);
		
		rdbtnmntm1 = new JRadioButtonMenuItem("1 - 0,0");
		rdbtnmntm1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				casaDecimal = 1;
			}
		});
		buttonGroup.add(rdbtnmntm1);
		rdbtnmntm1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mnCasasDecimais.add(rdbtnmntm1);
		
		rdbtnmntm2 = new JRadioButtonMenuItem("2 - 0,00");
		rdbtnmntm1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				casaDecimal = 2;
			}
		});
		rdbtnmntm2.setSelected(true);
		buttonGroup.add(rdbtnmntm2);
		rdbtnmntm2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mnCasasDecimais.add(rdbtnmntm2);
		
		rdbtnmntm3 = new JRadioButtonMenuItem("3 - 0,000");
		rdbtnmntm1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				casaDecimal = 3;
			}
		});
		buttonGroup.add(rdbtnmntm3);
		rdbtnmntm3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mnCasasDecimais.add(rdbtnmntm3);
		
		rdbtnmntm4 = new JRadioButtonMenuItem("4 - 0,0000");
		rdbtnmntm1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				casaDecimal = 4;
			}
		});
		buttonGroup.add(rdbtnmntm4);
		rdbtnmntm4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mnCasasDecimais.add(rdbtnmntm4);
		
		rdbtnmntm5 = new JRadioButtonMenuItem("5 - 0,00000");
		rdbtnmntm1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				casaDecimal = 5;
			}
		});
		buttonGroup.add(rdbtnmntm5);
		rdbtnmntm5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mnCasasDecimais.add(rdbtnmntm5);
		
		rdbtnmntm6 = new JRadioButtonMenuItem("6 - 0,000000");
		rdbtnmntm1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				casaDecimal = 6;
			}
		});
		buttonGroup.add(rdbtnmntm6);
		rdbtnmntm6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mnCasasDecimais.add(rdbtnmntm6);
		
		JMenu mnAjuda = new JMenu("Ajuda???");
		mnAjuda.setFont(new Font("Tahoma", Font.PLAIN, 14));
		menuBar.add(mnAjuda);
		
		JMenuItem mntmCalculos = new JMenuItem("O que é cada coisa?");
		mntmCalculos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frames.Ajuda.main(null); // invoca a tela "Ajuda"
			}
		});
		mntmCalculos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mnAjuda.add(mntmCalculos);
		frInputDados.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txXi, txFi, btInserir}));
		
	}

}
