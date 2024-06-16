package panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import classes.Calculos;
import frames.inputDados;
import frames.zTable;

public class PanelCalculadoraZ extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel lblCalculadoraDeZ;

		private JLabel lblPXi;
		private JLabel lblPMedia;
		private JLabel lblPRaizN;
		private JLabel lblPdesvioPadrao;
		private JLabel lblValorZ;
		private JLabel lblValorZxi;
		
		private JTextField txPXi;
		public JTextField txPMedia;
		public JTextField txPN;
		private JTextField txPdesvioPadrao;
		private JTextField txValorZ;
		private JTextField txValorZxi;
		
		private JButton btnCalcular;
		private JButton btnImportarResultados;
		private JButton btnLimpar;
		
/**
 * Método para limpar os componentes de interação do usuário
 * @author Patrick Hiroshi Katsuta
 * @since 1.0
 */

		private void Limpar() {
			lblValorZ.setText("<html>\r\nZ<sub>xi</sub></html>");
			lblValorZxi.setText("<html>\r\nZ<sub>xi</sub> = ? = ?</html>");
			
			txPXi.setText("0,0");
			txPMedia.setText("0,0");
			txPN.setText("0");
			txPdesvioPadrao.setText("0,0");
			txValorZ.setText("0,0");
			txValorZxi.setText("0,0 %");
		}
		
		public void importarDados() {
			try {
				String media = inputDados.txXbarra.getText();
				String N = inputDados.txSomaFi.getText().replace(".0","");
				String desvioPadrao = inputDados.txDesvioPadrao.getText();
				
				txPMedia.setText(media);
				txPN.setText(N);
				txPdesvioPadrao.setText(desvioPadrao);
			} catch (NullPointerException err) {
				JOptionPane.showMessageDialog(null,
					"<html>Atenção! <br>"
					+ "Não existem dados a serem importados! <br>"
					+ "Realize a inserção de dados manualmente!"
					+ "</hmtl>", "Perae!",
					JOptionPane.WARNING_MESSAGE);
			}

		}

	/**
	 * Create the panel.
	 */
	public PanelCalculadoraZ() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				importarDados();
			}
		});
	
		this.setLayout(null);
		this.setBackground(new Color(255, 204, 51));
		this.setBounds(0, 0, 350, 520);
	
		lblCalculadoraDeZ = new JLabel("Calculadora de Z");
		lblCalculadoraDeZ.setHorizontalAlignment(SwingConstants.CENTER);
		lblCalculadoraDeZ.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCalculadoraDeZ.setBounds(10, 10, 330, 40);
		this.add(lblCalculadoraDeZ);
		
			//Calculadora Z JLabel
			lblPXi = new JLabel("xi");
			lblPXi.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPXi.setBounds(10, 60, 160, 40);
			this.add(lblPXi);
			
			lblPMedia = new JLabel("x̄");
			lblPMedia.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPMedia.setBounds(10, 110, 160, 40);
			this.add(lblPMedia);
			
			lblPRaizN = new JLabel("N");
			lblPRaizN.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPRaizN.setBounds(10, 160, 160, 40);
			this.add(lblPRaizN);
			
			lblPdesvioPadrao = new JLabel("s");
			lblPdesvioPadrao.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPdesvioPadrao.setBounds(10, 210, 160, 40);
			this.add(lblPdesvioPadrao);
	
			lblValorZ = new JLabel("<html>\r\nZ<sub>xi</sub></html>");
			lblValorZ.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblValorZ.setBounds(10, 260, 160, 40);
			this.add(lblValorZ);
		
			lblValorZxi = new JLabel("<html>\r\nZ<sub>xi</sub> = ? = ?</html>");
			lblValorZxi.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblValorZxi.setBounds(10, 310, 160, 40);
			add(lblValorZxi);
	
			//Calculadora Z JTextField
			txPXi = new JTextField();
			txPXi.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					txPXi.selectAll();
				}
			});
			txPXi.setHorizontalAlignment(SwingConstants.RIGHT);
			txPXi.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txPXi.setColumns(10);
			txPXi.setBounds(180, 60, 160, 40);
			this.add(txPXi);
			
			txPMedia = new JTextField();
			txPMedia.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					txPMedia.selectAll();
				}
			});
			txPMedia.setHorizontalAlignment(SwingConstants.RIGHT);
			txPMedia.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txPMedia.setColumns(10);
			txPMedia.setBounds(180, 110, 160, 40);
			this.add(txPMedia);
			
			txPN = new JTextField();
			txPN.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					txPN.selectAll();
				}
			});
			txPN.setHorizontalAlignment(SwingConstants.RIGHT);
			txPN.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txPN.setColumns(10);
			txPN.setBounds(180, 160, 160, 40);
			this.add(txPN);
			
			txPdesvioPadrao = new JTextField();
			txPdesvioPadrao.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					txPdesvioPadrao.selectAll();
				}
			});
			txPdesvioPadrao.setHorizontalAlignment(SwingConstants.RIGHT);
			txPdesvioPadrao.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txPdesvioPadrao.setColumns(10);
			txPdesvioPadrao.setBounds(180, 210, 160, 40);
			this.add(txPdesvioPadrao);
	
			txValorZ = new JTextField();
			txValorZ.setEditable(false);
			txValorZ.setHorizontalAlignment(SwingConstants.RIGHT);
			txValorZ.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txValorZ.setColumns(10);
			txValorZ.setBounds(180, 261, 160, 40);
			this.add(txValorZ);
			
			txValorZxi = new JTextField();
			txValorZxi.setEditable(false);
			txValorZxi.setHorizontalAlignment(SwingConstants.RIGHT);
			txValorZxi.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txValorZxi.setColumns(10);
			txValorZxi.setBounds(180, 310, 160, 40);
			this.add(txValorZxi);
			
			//Calculadora Z JButton
			btnCalcular = new JButton("Calcular!");
			btnCalcular.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Double xi = Double.parseDouble(txPXi.getText().replace(",", "."));
					Double media = Double.parseDouble(txPMedia.getText().replace(",", "."));
					int N = Integer.parseInt(txPN.getText());
					
					if (N <= 0) {
						JOptionPane.showMessageDialog(null,
								"<html>Mas existe campo amostral nulo ou negativo? <br>"
								+ "Você tentou informar um valor para [N]: "
								+ "<ul>"
								+ "<li>ou igual a zero;</li>"
								+ "<li>ou menor do que zero.</li>"
								+ "</ul> </hmtl>", "Eita!",
								JOptionPane.WARNING_MESSAGE);
						}
					
					Double desvioPadrao = Double.parseDouble(txPdesvioPadrao.getText().replace(",", "."));
					if (desvioPadrao < 0.0) {
						JOptionPane.showMessageDialog(null,
								"<html>Mas existe variância negativa? <br>"
								+ "O menor valor possível para o Desvio Padrão (s) é zero (0)!" 
								+ "</hmtl>", "Eita!",
								JOptionPane.WARNING_MESSAGE);
						}
								Double calculoZ = Calculos.arredondamento(Calculos.calculoZ(xi, media, N, desvioPadrao), 2);
								lblValorZ.setText("<html>\r\nZ<sub>" + txPXi.getText() + "</sub></html>");
								txValorZ.setText(calculoZ.toString());
								lblValorZxi.setText("<html>\r\nZ<sub>" + txPXi.getText() + "</sub> = " + calculoZ + " = " + Calculos.arredondamento(zTable.valorZ(calculoZ),4) + "</html>");
								
								txValorZxi.setText(Calculos.arredondamento(Calculos.arredondamento(zTable.valorZ(calculoZ),4) * 100, 2) + "%");
							
	
				}
			});
			btnCalcular.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnCalcular.setBounds(10, 360, 330, 40);
			this.add(btnCalcular);
			
			//Calculadora Z JButton
			btnImportarResultados = new JButton("Importar valores");
			btnImportarResultados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					importarDados();
				}
			});
			btnImportarResultados.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnImportarResultados.setBounds(10, 410, 330, 40);
			this.add(btnImportarResultados);
			
			btnLimpar = new JButton("Limpar!");
			btnLimpar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
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
			});
			btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnLimpar.setBounds(10, 460, 330, 40);
			add(btnLimpar);
			
			setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txPXi, txPMedia, txPN, txPdesvioPadrao, btnCalcular, btnImportarResultados, btnLimpar}));
			
			Limpar();
			
			
	}
}
