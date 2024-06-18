package frames;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class Ajuda {

	private JFrame frAjuda;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ajuda window = new Ajuda();
					window.frAjuda.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ajuda() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frAjuda = new JFrame();
		frAjuda.setResizable(false);
		frAjuda.getContentPane().setBackground(new Color(255, 255, 204));
		frAjuda.setType(Type.UTILITY);
		frAjuda.setBounds(100, 100, 450, 500);
		frAjuda.setLocationRelativeTo(null);
		frAjuda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frAjuda.getContentPane().setLayout(null);

		JLabel lblAjuda = new JLabel(
				"<html>\r\n<h3>Bem vindo ao Estatística JCS!</h3>\r\nEste programa de estatística permite calcular diversos dados estatísticos essenciais a partir de dados brutos ou agrupados em classes. Ao fornecer os valores de <b>[xi]</b> ou as classes (seu limite inferior e limite superior ou PMIs) e <b>[fi]</b>, o programa irá obter os valores para:\r\n<b>[N]</b>, <b>[x̄]</b>, <b>[s²]</b>, <b>[s]</b> e <b>[C.V.]</b>.\r\n<ul>\r\n<li>xi = Dados ou informações numéricas</li>\r\n<li>fi = Frequências individuais absolutas</li>\r\n<li>N (&sum;fi) = Número total de informações do conjunto ou amostra</li>\r\n<li>x̄ = Média do cálculo: &sum;xi &divide; N</li>\r\n<li>s² = Variância: (&sum;xi&times;fi) &divide; (N-1)</li>\r\n<li>s = Desvio padrão: &radic; (s²)</li>\r\n<li>C.V. = Coeficiente de variação: 100&times;(s &divide; x̄)</li>\r\n</ul><br>\r\nAlém disso, o programa gera a distribuição normal correspondente (tabela Z) dos dados informados, facilitando análises estatísticas e interpretações de resultados.\r\n</html>");
		lblAjuda.setVerticalAlignment(SwingConstants.TOP);
		lblAjuda.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAjuda.setBounds(10, 10, 416, 353);	
		frAjuda.getContentPane().add(lblAjuda);
		
		JButton btnDuvidas = new JButton("Ainda tenho dúvidas...");
		btnDuvidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Calma, usuário! \nAinda estamos desenvolvendo e aprofundando este programa! \nPara mais dúvidas, procure o auxílio do mestre JC! \nBora trabalhar!",
						"Atenção!", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnDuvidas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDuvidas.setBounds(10, 413, 416, 40);
		frAjuda.getContentPane().add(btnDuvidas);
	}

}
