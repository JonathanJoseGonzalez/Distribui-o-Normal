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
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import classes.Visual;
import panels.panelInputClasses;
import panels.panelInputDados;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class mainEstatistica extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	
	private JMenu mnInsercaoDeDados;
		private final ButtonGroup buttonGroupInsercaoDados = new ButtonGroup();
		public static JRadioButtonMenuItem rdbtnmntmXi;
		public static JRadioButtonMenuItem rdbtnmntmClasses;
		panelInputDados panelInputDados = new panelInputDados();
		panelInputClasses panelInputClasses = new panelInputClasses();
		
	private JMenu mnCasasDecimais;
	public static int casaDecimal = 2;
	private final ButtonGroup buttonGroupCasaDecimal = new ButtonGroup();
		private JRadioButtonMenuItem rdbtnmntm0;
		private JRadioButtonMenuItem rdbtnmntm1;
		private JRadioButtonMenuItem rdbtnmntm2;
		private JRadioButtonMenuItem rdbtnmntm3;
		private JRadioButtonMenuItem rdbtnmntm4;
		private JRadioButtonMenuItem rdbtnmntm5;
		private JRadioButtonMenuItem rdbtnmntm6;
		
	private JMenu mnAjuda;
	private JLayeredPane layeredPane;
	private JMenuItem mntmAjuda;

	public void casaDecimal() {
		int casaDecimal = 0;

		System.out.println(casaDecimal);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainEstatistica frame = new mainEstatistica();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainEstatistica() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.setResizable(false);
		this.setTitle("Estatística JCS");
		this.getContentPane().setBackground(new Color(255, 255, 204));
		this.setBackground(new Color(255, 204, 0));
		this.setBounds(new Rectangle(0, 0, 585, 725));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);


		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 571, 665);
		getContentPane().add(layeredPane);
		Visual.trocaTela(layeredPane,panelInputDados);
		
		URL iconURL = getClass().getResource("/img/iconJCS.png");
		ImageIcon icon = new ImageIcon(iconURL);
		this.setIconImage(icon.getImage());
		
		
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		mnInsercaoDeDados = new JMenu("Modo de inserção de dados");
		mnInsercaoDeDados.setFont(new Font("Tahoma", Font.PLAIN, 14));
		menuBar.add(mnInsercaoDeDados);

		rdbtnmntmXi = new JRadioButtonMenuItem("xi");
		rdbtnmntmXi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Visual.trocaTela(layeredPane,panelInputDados);
			}
		});
		buttonGroupInsercaoDados.add(rdbtnmntmXi);

		rdbtnmntmXi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnmntmXi.setSelected(true);
		mnInsercaoDeDados.add(rdbtnmntmXi);

		rdbtnmntmClasses = new JRadioButtonMenuItem("Classes");
		rdbtnmntmClasses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Visual.trocaTela(layeredPane,panelInputClasses);
			}
		});
		buttonGroupInsercaoDados.add(rdbtnmntmClasses);

		rdbtnmntmClasses.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mnInsercaoDeDados.add(rdbtnmntmClasses);

		mnCasasDecimais = new JMenu("Casas Decimais");
		mnCasasDecimais.setFont(new Font("Tahoma", Font.PLAIN, 14));
		menuBar.add(mnCasasDecimais);

		rdbtnmntm0 = new JRadioButtonMenuItem("0 - 0");
		rdbtnmntm0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnmntm0.isSelected()) {
					casaDecimal = 0;
				}
				System.out.println(casaDecimal);
			}
		});
		buttonGroupCasaDecimal.add(rdbtnmntm0);
		rdbtnmntm0.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mnCasasDecimais.add(rdbtnmntm0);

		rdbtnmntm1 = new JRadioButtonMenuItem("1 - 0,0");
		rdbtnmntm1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnmntm1.isSelected()) {
					casaDecimal = 1;
				}
			}
		});
		buttonGroupCasaDecimal.add(rdbtnmntm1);
		rdbtnmntm1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mnCasasDecimais.add(rdbtnmntm1);

		rdbtnmntm2 = new JRadioButtonMenuItem("2 - 0,00");
		rdbtnmntm2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnmntm2.isSelected()) {
					casaDecimal = 2;
			}
			}
		});
		rdbtnmntm2.setSelected(true);
		buttonGroupCasaDecimal.add(rdbtnmntm2);
		rdbtnmntm2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mnCasasDecimais.add(rdbtnmntm2);

		rdbtnmntm3 = new JRadioButtonMenuItem("3 - 0,000");
		rdbtnmntm3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnmntm3.isSelected()) {
					casaDecimal = 3;
			}
			}
		});
		buttonGroupCasaDecimal.add(rdbtnmntm3);
		rdbtnmntm3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mnCasasDecimais.add(rdbtnmntm3);

		rdbtnmntm4 = new JRadioButtonMenuItem("4 - 0,0000");
		rdbtnmntm4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnmntm4.isSelected()) {
					casaDecimal = 4;
				}
			}
		});
		buttonGroupCasaDecimal.add(rdbtnmntm4);
		rdbtnmntm4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mnCasasDecimais.add(rdbtnmntm4);

		rdbtnmntm5 = new JRadioButtonMenuItem("5 - 0,00000");
		rdbtnmntm5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnmntm5.isSelected()) {
					casaDecimal = 5;
				}
			}
		});
		buttonGroupCasaDecimal.add(rdbtnmntm5);
		rdbtnmntm5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mnCasasDecimais.add(rdbtnmntm5);

		rdbtnmntm6 = new JRadioButtonMenuItem("6 - 0,000000");
		rdbtnmntm6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnmntm6.isSelected()) {
					casaDecimal = 6;
				}
			}
		});
		buttonGroupCasaDecimal.add(rdbtnmntm6);
		rdbtnmntm6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mnCasasDecimais.add(rdbtnmntm6);

		mnAjuda = new JMenu("Ajuda???");
		mnAjuda.setFont(new Font("Tahoma", Font.PLAIN, 14));
		menuBar.add(mnAjuda);
		
		mntmAjuda = new JMenuItem("O que é cada coisa?");
		mntmAjuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frames.Ajuda.main(null); // invoca a tela "Ajuda"
			}
		});
		mnAjuda.add(mntmAjuda);
		
		if (rdbtnmntmXi.isSelected()) {
			setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{panelInputDados.getTxXi(), panelInputDados.getTxFi(), panelInputDados.getBtInserir()}));

		} else if (rdbtnmntmClasses.isSelected()) {
			setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{panelInputClasses.getTextAreaFi(), panelInputClasses.getTxPMI(), panelInputClasses.getTxPosicao(), panelInputClasses.getTxClassesH(), panelInputClasses.getTxLimiteInferior(), panelInputClasses.getTxLimiteSuperior(), panelInputClasses.getBtnInserirClasses()}));

			
		} else {
			setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{getContentPane()}));			
		}

	}
}
