package classes;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
/**
 * Classe com métodos para alterações na interface do sistema
 * @author Patrick Hiroshi Katsuta
 * @version 1.0
 */
public class Visual {
/**
 * Método para trocar telas (JPanel) dentro de um JLayeredPane
 * @param lp é o JLayeredPane que terá a sua tela trocada
 * @param painel é o JPanel que ficará ativo no JLayeredPane
 * @author Patrick Hiroshi Katsuta
 * @since 1.0
 */
	public static void trocaTela(JLayeredPane lp, JPanel painel) {
		lp.removeAll();
		lp.add(painel);
		lp.repaint();
		lp.revalidate();
	}
	
}
