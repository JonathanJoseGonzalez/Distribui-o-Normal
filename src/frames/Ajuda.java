package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Window.Type;
import javax.swing.JLabel;

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
		frAjuda.setType(Type.UTILITY);
		frAjuda.setBounds(100, 100, 450, 300);
		frAjuda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frAjuda.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel(
				"<html>\r\nPrograma para cálculo de dados estatísticos.<br />"
				+ "\r\nInforme os valores de <b>[xi]</b> e <b>[fi]</b> para obter os valores para:<br>"
				+ "\r\n<b>[N]</b>, <b>[x̄]</b>, <b>[s²]</b>, <b>[s]</b> e <b>[C.V.]</b>."
				+ "\r\n<ul>"
				+ "\r\n<li>xi = Dados ou informações numéricas</li>"
				+ "\r\n<li>fi = Frequências individuais absolutas</li>"
				+ "\r\n<li>N (&sum;fi) = Número total de informações do conjunto ou amostra</li>"
				+ "\r\n<li>x̄ = Média do cálculo: &sum;xi &divide; N</li>"
				+ "\r\n<li>s² = Variância: (&sum;xi&times;fi) &divide; (N-1)</li>"
				+ "\r\n<li>s = Desvio padrão: &radic; (s²)</li>"
				+ "\r\n<li>C.V. = Coeficiente de variação: 100&times;(s &divide; x̄)</li>"
				+ "\r\n</ul>\r\n</html>");
		lblNewLabel.setBounds(10, 10, 416, 243);
		frAjuda.getContentPane().add(lblNewLabel);
	}

}
