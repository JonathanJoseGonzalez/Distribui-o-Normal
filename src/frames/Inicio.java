package frames;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Inicio {
	
	private JFrame frInicio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("Here we go! Início!");
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
		frInicio.setBounds(new Rectangle(0, 0, 585, 450));
		frInicio.setLocationRelativeTo(null);
		frInicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frInicio.getContentPane().setLayout(null);

		URL iconURL = getClass().getResource("/img/iconJCS.png");
		ImageIcon icon = new ImageIcon(iconURL);
		frInicio.setIconImage(icon.getImage());

		
		JLabel lblInicio = new JLabel("<html>\r\n<body>\r\n<h2 style=\"text-align: right;\">Estatística JCS</h2>\r\n<p style=\"text-align: right;\">Desenvolvido por:</p>\r\n<h4 style=\"text-align: right;\">ARTHUR RENAN GUTIERREZ DIAS PEREIRA</h4>\r\n<h4 style=\"text-align: right;\">JONATHAN JOSE GONZALEZ</h4>\r\n<h4 style=\"text-align: right;\">PATRICK HIROSHI KATSUTA</h4>\r\n<h4 style=\"text-align: right;\">RUAN VITOR SANTOS DE SOUZA</h4>\r\n<br>\r\n<p style=\"text-align: right;\">Sob supervisão de:</p>\r\n<h4 style=\"text-align: right;\">JOÃO CARLOS DOS SANTOS</h4>\r\n</body>\r\n</html>");
		lblInicio.setHorizontalAlignment(SwingConstants.LEFT);
		lblInicio.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblInicio.setBounds(299, 10, 262, 270);
		frInicio.getContentPane().add(lblInicio);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 204, 0));
		panel.setBounds(10, 290, 551, 113);
		frInicio.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnIniciar = new JButton("INICIAR");
		btnIniciar.setBounds(10, 10, 531, 40);
		panel.add(btnIniciar);
		btnIniciar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIniciar.setBackground(new Color(255, 255, 204));
		
		JButton btnCalculadoraZ = new JButton("Calculadora Z");
		btnCalculadoraZ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frames.distribuicaoNormal.main(null);
				frInicio.dispose();
				
			}
		});
		btnCalculadoraZ.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCalculadoraZ.setBackground(new Color(255, 255, 204));
		btnCalculadoraZ.setBounds(10, 60, 531, 40);
		panel.add(btnCalculadoraZ);
		
		JLabel lblJC = new JLabel("");
		lblJC.setBounds(10, 10, 279, 270);
		frInicio.getContentPane().add(lblJC);
		lblJC.setIcon(new ImageIcon(Inicio.class.getResource("/img/icon3.png")));
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frames.mainEstatistica.main(null);
				frInicio.dispose();
			}
		});
	}
}
