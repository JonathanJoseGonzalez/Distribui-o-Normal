package frames;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.ImageIcon;



public class distribuicaoNormal {
	public JFrame frDistribuicaoNormal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					distribuicaoNormal window = new distribuicaoNormal();
					window.frDistribuicaoNormal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public distribuicaoNormal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frDistribuicaoNormal = new JFrame();
		frDistribuicaoNormal.getContentPane().setBackground(new Color(255, 204, 0));
		frDistribuicaoNormal.getContentPane().setLayout(null);
		frDistribuicaoNormal.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		frDistribuicaoNormal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frDistribuicaoNormal.setResizable(false);
		frDistribuicaoNormal.setTitle("Estatística JCS - Distribuição Normal");
		frDistribuicaoNormal.getContentPane().setBackground(new Color(255, 255, 204));
		frDistribuicaoNormal.setBounds(new Rectangle(0, 0, 585, 690));
		frDistribuicaoNormal.setLocationRelativeTo(frDistribuicaoNormal);
		frDistribuicaoNormal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 204));
		panel.setBounds(10, 10, 416, 243);
		frDistribuicaoNormal.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblTeste = new JLabel("TESTE");
		lblTeste.setIcon(new ImageIcon("C:\\Users\\Katsuta\\Desktop\\171056.png"));
		lblTeste.setFont(new Font("Tahoma", Font.PLAIN, 18));
//		lblTeste.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeste.setBounds(10, 75, 396, 102);
		panel.add(lblTeste);
		
		Canvas canvasCor = new Canvas();
		canvasCor.setBackground(new Color(51, 204, 0));
		canvasCor.setBounds(10, 81, 396, 310);
		panel.add(canvasCor);
		
		JSlider slider = new JSlider();
		slider.setMaximum(396);
		slider.setValue(396/2);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
			lblTeste.setText(String.valueOf(slider.getValue()-198));
			//canvasCor.setBounds(10, 81, ((slider.getValue())), 162);
			lblTeste.setBounds(10, 75, (slider.getValue()), 102);
			
			}
		});
		slider.setBounds(10, 49, 396, 26);
		panel.add(slider);
		
		JLabel lblNewLabel = new JLabel("Valor P para tabela Z");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 396, 40);
		panel.add(lblNewLabel);
		
		

	}
}
