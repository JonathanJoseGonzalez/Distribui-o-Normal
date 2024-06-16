package panels;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import classes.Calculos;
import frames.zTable;

import javax.swing.JSlider;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PanelProbabilidadeDeZ extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel lblCalcularPdeZ;

	private JLabel lblPValorZ;
	private JSlider sliderPValorZ;
	private JTextField txPValorZ;
	
		private JLabel lblPZmenorX;
		private JLabel lblPZMaiorX;
		private JLabel lblPZentre0eX;
		private JLabel lblPZentreMenosXeX;
		private JLabel lblPZmenorMenosXouZMaiorX;
		private JLabel lblResultado;


		private JTextField txPZmenorX;
		private JTextField txPZMaiorX;
		private JTextField txPZentre0eX;
		private JTextField txPZentreMenosXeX;
		private JTextField txPZmenorMenosXouZMaiorX;
		
		private String evidenciaResultado;

		private Double valorZ() {
			Double Z = Double.parseDouble(txPValorZ.getText().replace(",","."));
			return Z;
		}
		
		private Double probabilidadeZ() {
			Double ztable = Calculos.arredondamento(zTable.valorZ(valorZ()),4);
			return ztable;
		}
		
		private void atualizarSlider() {
			int sliderZ= (int) (valorZ()*100);
			sliderPValorZ.setValue(sliderZ);
		}
		
		private void sliderAtualizaZ() {
			int slider = sliderPValorZ.getValue();
			double sliderDouble = slider/100.0;
			String valorSlider = String.valueOf(sliderDouble);
			
			txPValorZ.setText(valorSlider);
		}
		
		private void atualizarValorZ() {
			
			lblPZmenorX.setText("<html>\r\nP(Z<sub>" + valorZ() + "</sub> &lt; x)</html>");
			lblPZMaiorX.setText("<html>\r\nP(Z<sub>" + valorZ() + "</sub> &gt; x)</html>");
			if (valorZ() > 0.0) {
				lblPZentre0eX.setText("<html>\r\nP(0 &lt; Z<sub>" + valorZ() + "</sub> &lt; x)</html>");
			} else {
				lblPZentre0eX.setText("<html>\r\nP(x &lt; Z<sub>" + valorZ() + "</sub> &lt; 0)</html>");
			}
			lblPZentreMenosXeX.setText("<html>\r\nP(-x &lt; Z<sub>" + valorZ() + "</sub> &lt; x)</html>");
			lblPZmenorMenosXouZMaiorX.setText("<html>\r\nP(Z<sub>" + valorZ() + "</sub> &lt; -x <br>"
													+ "ou Z<sub>" + valorZ() + "</sub> &gt; x)</html>");

			Double menorX = Calculos.arredondamento(probabilidadeZ(),4);
			Double menorXPercent = Calculos.arredondamento(menorX*100,2);
			txPZmenorX.setText(menorX.toString() + " = " + menorXPercent+ "%");
			
			Double ZmaiorX = Calculos.arredondamento(1.0 - probabilidadeZ(),4);
			Double ZmaiorXPercent = Calculos.arredondamento(ZmaiorX*100,2);
			txPZMaiorX.setText(ZmaiorX.toString() + " = " + ZmaiorXPercent+ "%");
			
			Double entre0eX = 0.0;
			if (valorZ() > 0.0) {
				entre0eX = Calculos.arredondamento((probabilidadeZ()+0.5-1),4);
			} else {
				entre0eX = Calculos.arredondamento((ZmaiorX - 0.5),4);
			}
			Double entre0eXPercent = Calculos.arredondamento(entre0eX*100,2);
			txPZentre0eX.setText(entre0eX.toString() + " = " + entre0eXPercent+ "%");
			
			Double entreMenosXeX = entre0eX * 2;
			Double entreMenosXeXPercent = Calculos.arredondamento(entreMenosXeX*100,2);
			txPZentreMenosXeX.setText(entreMenosXeX.toString() + " = " + entreMenosXeXPercent+ "%");
			
			Double ZmenorMenosXouZMaiorX = Calculos.arredondamento((1 - entreMenosXeX), 4);
			Double ZmenorMenosXouZMaiorXPercent = Calculos.arredondamento(ZmenorMenosXouZMaiorX*100,2);
			txPZmenorMenosXouZMaiorX.setText(ZmenorMenosXouZMaiorX.toString() + " = " + ZmenorMenosXouZMaiorXPercent+ "%");

		}
		
	/**
	 * Create the panel.
	 */
	public PanelProbabilidadeDeZ() {
		
		this.setLayout(null);
		this.setBackground(new Color(255, 204, 51));
		this.setBounds(0, 0, 350, 520);
		
			//PanelProbabilidadeDeZ JLabel
			lblCalcularPdeZ = new JLabel("Calcular um valor P da tabela Z");
			lblCalcularPdeZ.setHorizontalAlignment(SwingConstants.CENTER);
			lblCalcularPdeZ.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblCalcularPdeZ.setBounds(10, 10, 330, 40);
			this.add(lblCalcularPdeZ);

			lblPValorZ = new JLabel("Valor de Z");
			lblPValorZ.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPValorZ.setBounds(10, 60, 80, 40);
			add(lblPValorZ);
			
			lblPZmenorX = new JLabel("P(Z < x)");
			lblPZmenorX.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPZmenorX.setBounds(10, 110, 160, 40);
			this.add(lblPZmenorX);
			
			lblPZMaiorX = new JLabel("P(Z > x)");
			lblPZMaiorX.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPZMaiorX.setBounds(10, 160, 160, 40);
			this.add(lblPZMaiorX);
			
			lblPZentre0eX = new JLabel("P(0 < Z < x)");
			lblPZentre0eX.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPZentre0eX.setBounds(10, 210, 160, 40);
			this.add(lblPZentre0eX);
			
			lblPZentreMenosXeX = new JLabel("P(-x < Z < x)");
			lblPZentreMenosXeX.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPZentreMenosXeX.setBounds(10, 260, 160, 40);
			this.add(lblPZentreMenosXeX);
			
			lblPZmenorMenosXouZMaiorX = new JLabel("P(Z < -x ou Z > x)");
			lblPZmenorMenosXouZMaiorX.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPZmenorMenosXouZMaiorX.setBounds(10, 310, 160, 40);
			this.add(lblPZmenorMenosXouZMaiorX);
			
			lblResultado = new JLabel("<html>Clique em um resultado<br>para evidencia-lo</html>");
			lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
			lblResultado.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblResultado.setBounds(10, 360, 330, 150);
			add(lblResultado);
			
			//PanelProbabilidadeDeZ JSlider
			sliderPValorZ = new JSlider();
			sliderPValorZ.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					sliderAtualizaZ();
					atualizarValorZ();
				}
			});
			sliderPValorZ.setValue(0);
			sliderPValorZ.setMinimum(-400);
			sliderPValorZ.setMaximum(400);
			sliderPValorZ.setBackground(new Color(255, 204, 51));
			sliderPValorZ.setBounds(100, 60, 150, 40);
			add(sliderPValorZ);

			//PanelProbabilidadeDeZ JTextField
			txPValorZ = new JTextField();
			txPValorZ.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					txPValorZ.selectAll();
				}
			});
			txPValorZ.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					txPValorZ.selectAll();
				}
			});
			txPValorZ.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					atualizarSlider();
					atualizarValorZ();
				}
			});
			txPValorZ.setText("0");
			txPValorZ.setHorizontalAlignment(SwingConstants.CENTER);
			txPValorZ.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txPValorZ.setColumns(10);
			txPValorZ.setBounds(260, 60, 80, 40);
			add(txPValorZ);
			
			txPZmenorX = new JTextField();
			txPZmenorX.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					evidenciaResultado = lblPZmenorX.getText().replace("</html>","") + " = " + txPZmenorX.getText() + "</html>";
					lblResultado.setText(evidenciaResultado);
				}
			});
			txPZmenorX.setEditable(false);
			txPZmenorX.setHorizontalAlignment(SwingConstants.CENTER);
			txPZmenorX.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txPZmenorX.setColumns(10);
			txPZmenorX.setBounds(180, 110, 160, 40);
			this.add(txPZmenorX);
			
			txPZMaiorX = new JTextField();
			txPZMaiorX.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					evidenciaResultado = lblPZMaiorX.getText().replace("</html>","") + " = " + txPZMaiorX.getText() + "</html>";
					lblResultado.setText(evidenciaResultado);
				}
			});
			txPZMaiorX.setEditable(false);
			txPZMaiorX.setHorizontalAlignment(SwingConstants.CENTER);
			txPZMaiorX.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txPZMaiorX.setColumns(10);
			txPZMaiorX.setBounds(180, 160, 160, 40);
			this.add(txPZMaiorX);
			
			txPZentre0eX = new JTextField();
			txPZentre0eX.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					evidenciaResultado = lblPZentre0eX.getText().replace("</html>","") + " = " + txPZentre0eX.getText() + "</html>";
					lblResultado.setText(evidenciaResultado);
				}
			});
			txPZentre0eX.setEditable(false);
			txPZentre0eX.setHorizontalAlignment(SwingConstants.CENTER);
			txPZentre0eX.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txPZentre0eX.setColumns(10);
			txPZentre0eX.setBounds(180, 210, 160, 40);
			this.add(txPZentre0eX);
			
			txPZentreMenosXeX = new JTextField();
			txPZentreMenosXeX.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					evidenciaResultado = lblPZentreMenosXeX.getText().replace("</html>","") + " = " + txPZentreMenosXeX.getText() + "</html>";
					lblResultado.setText(evidenciaResultado);
				}
			});
			txPZentreMenosXeX.setEditable(false);
			txPZentreMenosXeX.setHorizontalAlignment(SwingConstants.CENTER);
			txPZentreMenosXeX.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txPZentreMenosXeX.setColumns(10);
			txPZentreMenosXeX.setBounds(180, 260, 160, 40);
			this.add(txPZentreMenosXeX);
			
			txPZmenorMenosXouZMaiorX = new JTextField();
			txPZmenorMenosXouZMaiorX.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					evidenciaResultado = lblPZmenorMenosXouZMaiorX.getText().replace("</html>","") + " = " + txPZmenorMenosXouZMaiorX.getText() + "</html>";
					lblResultado.setText(evidenciaResultado);
				}
			});
			txPZmenorMenosXouZMaiorX.setEditable(false);
			txPZmenorMenosXouZMaiorX.setHorizontalAlignment(SwingConstants.CENTER);
			txPZmenorMenosXouZMaiorX.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txPZmenorMenosXouZMaiorX.setColumns(10);
			txPZmenorMenosXouZMaiorX.setBounds(180, 310, 160, 40);
			this.add(txPZmenorMenosXouZMaiorX);
			
	}
}
