package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.modelo.SerieTemporal;

public class MediaMovelSimples implements Indicador {

	private Indicador outroIndicador;

	public MediaMovelSimples(Indicador outroIndicador) {
		this.outroIndicador = outroIndicador;

	}

	public double calcula(int posicao, int intervalo, SerieTemporal serie) {
		double soma = 0.0;
		
		for (int i = posicao; i > posicao - intervalo; i--) {
			soma += outroIndicador.calcula(i, intervalo, serie);
		}
		return soma / intervalo;
	}
	
	public String toString() {
		return "MMS de " + outroIndicador.toString();
	}
}
