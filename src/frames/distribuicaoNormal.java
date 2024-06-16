package frames;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import classes.Visual;
import panels.PanelCalculadoraZ;
import panels.PanelPde2Z;
import panels.PanelProbabilidadeDeZ;

public class distribuicaoNormal {
	public JFrame frDistribuicaoNormal;
	private JLabel lblTitulo;
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
		private JRadioButton rdbtnCalculadoraZ;
		private JRadioButton rdbtnPde2Z;
		private static JRadioButton rdbtnPdeZ;
	
	public JLayeredPane layeredPane;

	PanelCalculadoraZ PanelCalculadoraZ = new PanelCalculadoraZ();
	PanelPde2Z PanelPde2Z = new PanelPde2Z();
	PanelProbabilidadeDeZ PanelProbabilidadeDeZ = new PanelProbabilidadeDeZ();
	
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
		frDistribuicaoNormal.setBounds(new Rectangle(0, 0, 383, 690));
		frDistribuicaoNormal.setLocationRelativeTo(null);
		frDistribuicaoNormal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		URL iconURL = getClass().getResource("/img/iconJCS.png");
		ImageIcon icon = new ImageIcon(iconURL);
		frDistribuicaoNormal.setIconImage(icon.getImage());
		
		//Tela inicial
		lblTitulo = new JLabel("Distribuição Normal \"Z\"");
		lblTitulo.setBounds(10, 10, 350, 40);
		frDistribuicaoNormal.getContentPane().add(lblTitulo);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 102, 350, 520);
		frDistribuicaoNormal.getContentPane().add(layeredPane);
		Visual.trocaTela(layeredPane, PanelCalculadoraZ);

		//JRadioButton
		rdbtnPdeZ = new JRadioButton("<html>\r\nCalcular P <br>\r\nda tabela Z\r\n</html>");
		rdbtnPdeZ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Visual.trocaTela(layeredPane, PanelProbabilidadeDeZ);
			}
		});
		
		buttonGroup.add(rdbtnPdeZ);
		rdbtnPdeZ.setBackground(new Color(255, 255, 204));
		rdbtnPdeZ.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnPdeZ.setBounds(10, 56, 115, 40);
		frDistribuicaoNormal.getContentPane().add(rdbtnPdeZ);
		
		rdbtnPde2Z = new JRadioButton("<html>\r\nP entre<br>\r\ndois valores Z\r\n</html>");
		rdbtnPde2Z.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Visual.trocaTela(layeredPane, PanelPde2Z);
			}
		});
		buttonGroup.add(rdbtnPde2Z);
		rdbtnPde2Z.setBackground(new Color(255, 255, 204));
		rdbtnPde2Z.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnPde2Z.setBounds(127, 56, 115, 40);
		frDistribuicaoNormal.getContentPane().add(rdbtnPde2Z);
		
		rdbtnCalculadoraZ = new JRadioButton("<html>\r\nCalculadora Z\r\n</html>");
		rdbtnCalculadoraZ.setSelected(true);
		rdbtnCalculadoraZ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Visual.trocaTela(layeredPane, PanelCalculadoraZ);
			}
		});
		buttonGroup.add(rdbtnCalculadoraZ);
		rdbtnCalculadoraZ.setBackground(new Color(255, 255, 204));
		rdbtnCalculadoraZ.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnCalculadoraZ.setBounds(245, 56, 115, 40);
		frDistribuicaoNormal.getContentPane().add(rdbtnCalculadoraZ);
	}
}
