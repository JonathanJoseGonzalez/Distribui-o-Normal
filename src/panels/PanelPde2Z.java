package panels;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import classes.Calculos;
import frames.zTable;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelPde2Z extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel lblPde2Z;
	
	private JLabel lblPValorZ1;	private JSlider sliderPValorZ1;	private JTextField txPValorZ1;
	private JLabel lblPValorZ2; private JSlider sliderPValorZ2;	private JTextField txPValorZ2;
	
	private JLabel lblPZentre2x;	private JTextField txPZentre2x;
	private JLabel lblPZmenorXminOuZmaiorXmax; private JTextField txPZmenorXminOuZmaiorXmax;
	private JLabel lblPZmenorXmin;	private JTextField txPZmenorXmin;
	private JLabel lblPZmaiorXmax;	private JTextField txPZmaiorXmax;
	
	private JLabel lblResultado;
	
	private String evidenciaResultado;
	
	private Double valorZ1() {
		Double Z1 = Double.parseDouble(txPValorZ1.getText().replace(",","."));
		return Z1;
	}
	
	private Double valorZ2() {
		Double Z2 = Double.parseDouble(txPValorZ2.getText().replace(",","."));
		return Z2;
	}
	
	private Double probabilidadeZ1() {
		Double pZ1 = Calculos.arredondamento(zTable.valorZ(valorZ1()),4);
		return pZ1;
	}
	
	private Double probabilidadeZ2() {
		Double pZ2 = Calculos.arredondamento(zTable.valorZ(valorZ2()),4);
		return pZ2;
	}
	
	private void atualizarSlider1() {
		int sliderZ1= (int) (valorZ1()*100);
		sliderPValorZ1.setValue(sliderZ1);
	}
	
	private void atualizarSlider2() {
		int sliderZ2= (int) (valorZ2()*100);
		sliderPValorZ2.setValue(sliderZ2);
	}
	
	private void sliderAtualizaZ1() {
		int slider1 = sliderPValorZ1.getValue();
		double sliderDouble1 = slider1/100.0;
		String valorSlider1 = String.valueOf(sliderDouble1);
		
		txPValorZ1.setText(valorSlider1);
	}
	
	private void sliderAtualizaZ2() {
		int slider2 = sliderPValorZ2.getValue();
		double sliderDouble2 = slider2/100.0;
		String valorSlider2 = String.valueOf(sliderDouble2);
		
		txPValorZ2.setText(valorSlider2);
	}
	
	private void atualizarPde2Z(){
		Double Zentre2x = 0.0;
		Double Zentre2xpercent = 0.0;
		Double ZmenorXminOuZmaiorXmax = 0.0;
		Double ZmenorXminOuZmaiorXmaxPercent = 0.0;
		Double ZmenorXmin = 0.0;
		Double ZmenorXminPercent = 0.0;
		Double ZmaiorXmax = 0.0;
		Double ZmaiorXmaxPercent = 0.0;
				
				
		if (probabilidadeZ1() > probabilidadeZ2()) {
			Zentre2x = Calculos.arredondamento(probabilidadeZ1() - probabilidadeZ2(), 4);
			ZmenorXmin = Calculos.arredondamento(probabilidadeZ2(),4);
			ZmaiorXmax = Calculos.arredondamento(1 - probabilidadeZ1(),4);

		} else {
			Zentre2x = Calculos.arredondamento(probabilidadeZ2() - probabilidadeZ1(), 4);
			ZmenorXmin = Calculos.arredondamento(probabilidadeZ1(),4);
			ZmaiorXmax = Calculos.arredondamento(1 - probabilidadeZ2(),4);
		}
		ZmenorXminOuZmaiorXmax = Calculos.arredondamento(1 - Zentre2x, 4);

		Zentre2xpercent = Calculos.arredondamento(Zentre2x * 100, 2);
		ZmenorXminOuZmaiorXmaxPercent = Calculos.arredondamento(ZmenorXminOuZmaiorXmax * 100, 2);
		ZmenorXminPercent = Calculos.arredondamento(ZmenorXmin * 100, 2);
		ZmaiorXmaxPercent = Calculos.arredondamento(ZmaiorXmax * 100, 2);

		txPZentre2x.setText(Zentre2x + " = " + Zentre2xpercent + "%");
		txPZmenorXminOuZmaiorXmax.setText(ZmenorXminOuZmaiorXmax + " = " + ZmenorXminOuZmaiorXmaxPercent + "%");
		txPZmenorXmin.setText(ZmenorXmin + " = " + ZmenorXminPercent + "%");
		txPZmaiorXmax.setText(ZmaiorXmax + " = " + ZmaiorXmaxPercent + "%");
	}
	
	/**
	 * Create the panel.
	 */
	public PanelPde2Z() {
System.out.println("Haro from Pde2Z!");
		this.setLayout(null);
		this.setBackground(new Color(255, 204, 51));
		this.setBounds(0, 0, 350, 520);
		
			//PanelPde2Z JLabel
			lblPde2Z = new JLabel("Probabilidade entre dois valores Z");
			lblPde2Z.setHorizontalAlignment(SwingConstants.CENTER);
			lblPde2Z.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPde2Z.setBounds(10, 10, 330, 40);
			this.add(lblPde2Z);

			lblPValorZ1 = new JLabel("Valor de Z1");
			lblPValorZ1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPValorZ1.setBounds(10, 60, 80, 40);
			this.add(lblPValorZ1);

			lblPValorZ2 = new JLabel("Valor de Z2");
			lblPValorZ2.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPValorZ2.setBounds(10, 110, 80, 40);
			this.add(lblPValorZ2);
			
			lblPZentre2x = new JLabel("<html>\r\n<p>P(x<sub>min</sub> &lt; Z &lt; x<sub>max</sub>)</p>\r\n</html>");
			lblPZentre2x.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPZentre2x.setBounds(10, 160, 160, 40);
			this.add(lblPZentre2x);
			
			lblPZmenorXminOuZmaiorXmax = new JLabel("<html>\r\n<p>P(Z &le; x<sub>min</sub> ou Z &ge; x<sub>max</sub>)</p>\r\n</html>\r\n");
			lblPZmenorXminOuZmaiorXmax.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPZmenorXminOuZmaiorXmax.setBounds(10, 210, 160, 40);
			this.add(lblPZmenorXminOuZmaiorXmax);
			
			lblPZmenorXmin = new JLabel("<html>\r\n<p>P(Z &lt; x<sub>min</sub>)</p>\r\n</html>");
			lblPZmenorXmin.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPZmenorXmin.setBounds(10, 260, 160, 40);
			this.add(lblPZmenorXmin);
			
			lblPZmaiorXmax = new JLabel("<html>\r\n<p>P(Z &gt; x<sub>max</sub>)</p>\r\n</html>");
			lblPZmaiorXmax.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPZmaiorXmax.setBounds(10, 310, 160, 40);
			this.add(lblPZmaiorXmax);
			
			lblResultado = new JLabel("<html>Clique em um resultado<br>para evidencia-lo</html>");
			lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
			lblResultado.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblResultado.setBounds(10, 360, 330, 150);
			this.add(lblResultado);

			//PanelPde2Z JSlider
			sliderPValorZ1 = new JSlider();
			sliderPValorZ1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					sliderAtualizaZ1();
					atualizarPde2Z();
				}
			});
			sliderPValorZ1.setValue(0);
			sliderPValorZ1.setMinimum(-400);
			sliderPValorZ1.setMaximum(400);
			sliderPValorZ1.setBackground(new Color(255, 204, 51));
			sliderPValorZ1.setBounds(100, 60, 150, 40);
			this.add(sliderPValorZ1);
			
			sliderPValorZ2 = new JSlider();
			sliderPValorZ2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					sliderAtualizaZ2();
					atualizarPde2Z();
				}
			});
			sliderPValorZ2.setValue(0);
			sliderPValorZ2.setMinimum(-400);
			sliderPValorZ2.setMaximum(400);
			sliderPValorZ2.setBackground(new Color(255, 204, 51));
			sliderPValorZ2.setBounds(100, 110, 150, 40);
			this.add(sliderPValorZ2);			

			//PanelPde2Z JTextField
			txPValorZ1 = new JTextField();
			txPValorZ1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					txPValorZ1.selectAll();
				}
			});
			txPValorZ1.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					txPValorZ1.selectAll();
				}
			});
			txPValorZ1.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					atualizarSlider1();
					atualizarPde2Z();
				}
			});

			txPValorZ1.setText("0");
			txPValorZ1.setHorizontalAlignment(SwingConstants.CENTER);
			txPValorZ1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txPValorZ1.setColumns(10);
			txPValorZ1.setBounds(260, 60, 80, 40);
			this.add(txPValorZ1);
			
			txPValorZ2 = new JTextField();
			txPValorZ2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					txPValorZ2.selectAll();
				}
			});
			txPValorZ2.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					txPValorZ2.selectAll();
				}
			});
			txPValorZ2.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					atualizarSlider2();
					atualizarPde2Z();
				}
			});
			txPValorZ2.setText("0");
			txPValorZ2.setHorizontalAlignment(SwingConstants.CENTER);
			txPValorZ2.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txPValorZ2.setColumns(10);
			txPValorZ2.setBounds(260, 110, 80, 40);
			this.add(txPValorZ2);
			
			txPZentre2x = new JTextField();
			txPZentre2x.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					evidenciaResultado = lblPZentre2x.getText().replace("</html>","") + " = " + txPZentre2x.getText() + "</html>";
					lblResultado.setText(evidenciaResultado);
				}
			});

			txPZentre2x.setEditable(false);
			txPZentre2x.setHorizontalAlignment(SwingConstants.RIGHT);
			txPZentre2x.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txPZentre2x.setColumns(10);
			txPZentre2x.setBounds(180, 160, 160, 40);
			this.add(txPZentre2x);

			txPZmenorXminOuZmaiorXmax = new JTextField();
			txPZmenorXminOuZmaiorXmax.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					evidenciaResultado = lblPZmenorXminOuZmaiorXmax.getText().replace("</html>","") + " = " + txPZmenorXminOuZmaiorXmax.getText() + "</html>";
					lblResultado.setText(evidenciaResultado);
				}
			});
			txPZmenorXminOuZmaiorXmax.setEditable(false);
			txPZmenorXminOuZmaiorXmax.setHorizontalAlignment(SwingConstants.RIGHT);
			txPZmenorXminOuZmaiorXmax.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txPZmenorXminOuZmaiorXmax.setColumns(10);
			txPZmenorXminOuZmaiorXmax.setBounds(180, 210, 160, 40);
			this.add(txPZmenorXminOuZmaiorXmax);
			
			txPZmenorXmin = new JTextField();
			txPZmenorXmin.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					evidenciaResultado = lblPZmenorXmin.getText().replace("</html>","") + " = " + txPZmenorXmin.getText() + "</html>";
					lblResultado.setText(evidenciaResultado);
				}
			});
			txPZmenorXmin.setEditable(false);
			txPZmenorXmin.setHorizontalAlignment(SwingConstants.RIGHT);
			txPZmenorXmin.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txPZmenorXmin.setColumns(10);
			txPZmenorXmin.setBounds(180, 260, 160, 40);
			this.add(txPZmenorXmin);
			
			txPZmaiorXmax = new JTextField();
			txPZmaiorXmax.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					evidenciaResultado = lblPZmaiorXmax.getText().replace("</html>","") + " = " + txPZmaiorXmax.getText() + "</html>";
					lblResultado.setText(evidenciaResultado);
				}
			});
			txPZmaiorXmax.setEditable(false);
			txPZmaiorXmax.setHorizontalAlignment(SwingConstants.RIGHT);
			txPZmaiorXmax.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txPZmaiorXmax.setColumns(10);
			txPZmaiorXmax.setBounds(180, 310, 160, 40);
			this.add(txPZmaiorXmax);
			
	}
}
