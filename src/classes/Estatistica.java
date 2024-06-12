package classes;

import javax.swing.table.DefaultTableModel;

public class Estatistica {

	public static boolean ehNumero(String linha) {
		try {
			Double.parseDouble(linha);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public double inserirXi(String valorXi) {
		String alterarXi = valorXi.replace(',', '.'); // Substituir a vírgula pelo ponto
		Double xi = Double.parseDouble(alterarXi);
		return (xi);
	}

	public double inserirFi(String valorFi) {
		String alterarFi = valorFi.replace(',', '.'); // Substituir a vírgula pelo ponto
		Double fi = Double.parseDouble(alterarFi);
		return (fi);
	}
	public double inserirMedia(String valorMedia) {
		String alterarMedia = valorMedia.replace(',', '.');
		Double media = Double.parseDouble(alterarMedia);
		return (media);
	}

	public double somaXi(DefaultTableModel model) {
		double somaXi = 0.0;

		for (int i = 0; i < model.getRowCount(); i++) {
			somaXi += Double.parseDouble(model.getValueAt(i, 0).toString());
		}

		return somaXi;
	}

	public double somaFi(DefaultTableModel model) {
		double somaFi = 0.0;

		for (int i = 0; i < model.getRowCount(); i++) {
			somaFi += ((Double) model.getValueAt(i, 1));
		}

		return somaFi;
	}

	public double somaXiFi(DefaultTableModel model) {
		double somaXiFi = 0.0;
		for (int i = 0; i < model.getRowCount(); i++) {
			somaXiFi += ((Double) model.getValueAt(i, 2));
		}

		return somaXiFi;
	}

	public double media(DefaultTableModel model) {
		double media = 0.0;
		media = somaXiFi(model)/somaFi(model);
		return media;
	}

//	public double media(double xi, double fi) {
//		double media = somaXiFi(xi)/somaFi(fi);
//		return media;
//	}
	
	public double somaXiMediaQuadradoFi(DefaultTableModel model) {
		double somaXiMediaQuadradoFi = 0.0;

		for (int i = 0; i < model.getRowCount(); i++) {
			somaXiMediaQuadradoFi += Double.parseDouble(model.getValueAt(i, 3).toString().replace(',', '.'));
		}

		return somaXiMediaQuadradoFi;
	}

	public double xiMediaQuadradoFi(double xi, double fi, String media) {
		double xiMediaQuadradoFi = 0.0;
		xiMediaQuadradoFi = Math.pow((xi - inserirMedia(media)), 2) * fi;

		return xiMediaQuadradoFi;
	}

//	public void atualizarCalculos(DefaultTableModel model) {
//		somaXi(model);
//		somaFi(model);
//		somaXiFi(model);
//		txSomaFi.setText(String.format("%.2f", somaFi()));
//		txSomaXi.setText(String.format("%.2f", somaXi()));
//		txSomaXifi.setText(String.format("%.2f", somaXiFi()));
//		
//	}
//	
//	public void ativarDistribuicaoNormal(DefaultTableModel model) {
//		
//		if (model.getRowCount() <= 1) {
//			btnDistribuicaoNormal.setEnabled(false);
//		} else if ((model.getRowCount() >= 2) && (btnDistribuicaoNormal.isEnabled() == false)) {
//				btnDistribuicaoNormal.setEnabled(true);
//			}
//	}
	
}
