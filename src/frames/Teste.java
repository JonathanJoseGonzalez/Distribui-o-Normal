package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Teste {

	private JFrame frame;
	private JTextField txtLi;
	private JTextField txtLs;
	private JTextField txtPosicao;
	private JTextField txtQnt;
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Teste window = new Teste();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Teste() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		txtLi = new JTextField();
		txtLi.setText("li");
		txtLi.setBounds(21, 145, 96, 19);
		panel.add(txtLi);
		txtLi.setColumns(10);
		
		txtLs = new JTextField();
		txtLs.setText("ls");
		txtLs.setBounds(135, 145, 96, 19);
		panel.add(txtLs);
		txtLs.setColumns(10);
		
		txtPosicao = new JTextField();
		txtPosicao.setText("posicao");
		txtPosicao.setBounds(21, 127, 96, 19);
		panel.add(txtPosicao);
		txtPosicao.setColumns(10);
		
		txtQnt = new JTextField();
		txtQnt.setText("qnt");
		txtQnt.setBounds(21, 109, 96, 19);
		panel.add(txtQnt);
		txtQnt.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("---LI _________________________ LS--");
		lblNewLabel.setBounds(21, 174, 210, 13);
		panel.add(lblNewLabel);
		
		
	}
}
