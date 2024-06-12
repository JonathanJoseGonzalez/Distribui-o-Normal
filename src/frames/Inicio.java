package frames;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Inicio {

	private JFrame frInicio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio window = new Inicio();
					window.frInicio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Inicio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frInicio = new JFrame();
		frInicio.setTitle("Estatística JCS");
		frInicio.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		frInicio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frInicio.setResizable(false);
		frInicio.setTitle("Estatística JCS");
		frInicio.getContentPane().setBackground(new Color(255, 255, 204));
		frInicio.setBackground(new Color(255, 204, 0));
		frInicio.setBounds(new Rectangle(0, 0, 585, 400));
		frInicio.setLocationRelativeTo(null);
		frInicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frInicio.getContentPane().setLayout(null);
		
		JLabel lblInicio = new JLabel("<html>\r\n<body>\r\n<h2>Estatística JCS</h2>\r\n<p>Desenvolvido por:</p>\r\n<h4>ARTHUR RENAN GUTIERREZ DIAS PEREIRA</h4>\r\n<h4>JONATHAN JOSE GONZALEZ</h4>\r\n<h4>PATRICK HIROSHI KATSUTA</h4>\r\n<h4>RUAN VITOR SANTOS DE SOUZA</h4>\r\n<br>\r\n<p>Sob supervisão de:</p>\r\n<h4>JOÃO CARLOS DOS SANTOS</h4>\r\n</body>\r\n</html>");
		lblInicio.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblInicio.setBounds(10, 10, 551, 256);
		frInicio.getContentPane().add(lblInicio);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 204, 0));
		panel.setBounds(10, 290, 551, 63);
		frInicio.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnIniciar = new JButton("INICIAR");
		btnIniciar.setBounds(10, 10, 531, 40);
		panel.add(btnIniciar);
		btnIniciar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIniciar.setBackground(new Color(255, 255, 204));
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frames.inputDados.main(null);
				frInicio.dispose();
			}
		});
	}
}
